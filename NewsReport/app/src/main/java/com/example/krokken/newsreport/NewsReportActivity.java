package com.example.krokken.newsreport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewsReportActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsReport>> {

    //Constant for log messages
    public static final String LOG_TAG = "LOG TAG";

    //URL to be used for listview
    private String NEWS_REPORT_URL;

    //Empty text when nothing can be displayed
    private TextView mEmptyStateTextView;

    //Simply loading indicator when first loading the app or refreshing the information
    private View loadingIndicator;

    //Adapter for storing all the news stories
    private NewsReportAdapter mAdapter;

    // Find a reference to the {@link ListView} in the layout
    ListView newsReportListView;

    //ID for the news loader
    private int NEWS_REPORT_ID = 1;

    SwipeRefreshLayout pullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Method that initializes all the global variables to be used
        initializeVariables();

        // Sets the empty text view for when there is no information or no connection
        newsReportListView.setEmptyView(mEmptyStateTextView);

        // Checks the connection of the device
        checkNetworkConnection();

        // Sets a listener for opening an intent with the relating article
        newsReportListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsReport nr = mAdapter.getItem(position);
                if (nr.getNewsReportWebsite() != null || TextUtils.isEmpty(nr.getNewsReportWebsite())) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(nr.getNewsReportWebsite()));
                    startActivity(browserIntent);
                }
            }
        });

        // Scroll listener so it can properly refresh
        newsReportListView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState)
            {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                int topRowVerticalPosition = (newsReportListView == null || newsReportListView.getChildCount() == 0) ? 0 : newsReportListView.getChildAt(0).getTop();
                pullToRefresh.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
            }
        });

        // Sets the refresh listener to refresh the listview when requested
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
                pullToRefresh.setRefreshing(false);
            }
        });

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsReportListView.setAdapter(mAdapter);
    }

    @Override
    public Loader<List<NewsReport>> onCreateLoader(int id, Bundle bundle) {
        return new NewsReportLoader(this, NEWS_REPORT_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsReport>> loader, List<NewsReport> newsReports) {
        // Hide loading indicator because the data has been loaded
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText(getString(R.string.no_news));
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (newsReports != null && !newsReports.isEmpty()) {
            mAdapter.addAll(newsReports);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsReport>> loader) {
        mAdapter.clear();
    }

    private void initializeVariables() {
        // Adapter for the News
        mAdapter = new NewsReportAdapter(this, new ArrayList<NewsReport>());

        // Constructs the URL that will be used for the request
        NEWS_REPORT_URL = getString(R.string.url_guardian_search) + "&" +
                getString(R.string.url_show_contributor) + "&" +
                getString(R.string.url_api_key) +
                getString(R.string.api_key);

        // Display the URL used
        Log.v(LOG_TAG, NEWS_REPORT_URL);

        // Finds the view for the empty text
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

        // Finds the view for the loading indicator
        loadingIndicator = findViewById(R.id.loading_indicator);

        // Finds the list view
        newsReportListView = (ListView) findViewById(R.id.list);

        // Finds the swipe to refresh view
        pullToRefresh = findViewById(R.id.pullToRefresh);
    }

    // Checks the connection of the device when loading or refreshing
    // Will let the user know if they've lost connection and try to refresh without one
    private void checkNetworkConnection() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // To clear the adapter if user tries to refresh after having not had a connection
            mAdapter.clear();
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWS_REPORT_ID, null, this);
        } else {
            // Checks if adapter was already populated before a losing connection
            if (mAdapter.isEmpty()) {
                loadingIndicator.setVisibility(View.GONE);
                mEmptyStateTextView.setText(R.string.no_internet);
            } else {
                Toast.makeText(this, "Unable to refresh, no connection found", Toast.LENGTH_LONG).show();
                loadingIndicator.setVisibility(View.GONE);
                mEmptyStateTextView.setText("");
            }
        }
    }

    // Used to refresh the data on swipe
    private void refreshData() {
        loadingIndicator.setVisibility(View.VISIBLE);
        checkNetworkConnection();
    }
}

