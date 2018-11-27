package com.example.krokken.magicthegatheringcards;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CardsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {

    //Constant for log messages
    private static final int CARDS_LOADER_ID = 1;
    private static final int VERSION_LOADER_ID = 2;

    private String url;
    private String versionUrl;
    SearchView searchView;
    Boolean versionCheckBoolean;
    ListView cardsListView;
    SwipeRefreshLayout pullToRefresh;
    private String CARDS_JSON_URL;
    private TextView mEmptyStateTextView;
    private View loadingIndicator;
    private CardsAdapter mAdapter;

    public static NetworkInfo checkNetworkConnection(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        setTitle("MTG Card List");
        searchView = findViewById(R.id.search_view);

        // Method that initializes all the global variables to be used
        initializeVariables();

        // Sets the empty text view for when there is no information or no connection
        cardsListView.setEmptyView(mEmptyStateTextView);

        // Sets a listener for opening an intent with the relating article
        cardsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cardDetailPopup(view, position);
            }
        });

        // Scroll listener so it can properly refresh
        cardsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topRowVerticalPosition = (cardsListView == null || cardsListView.getChildCount() == 0) ? 0 : cardsListView.getChildAt(0).getTop();
                pullToRefresh.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
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

        getLoaderManager().initLoader(VERSION_LOADER_ID, null, this);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        cardsListView.setAdapter(mAdapter);
    }



    @Override
    public Loader onCreateLoader(int id, Bundle bundle) {
        // Base URL that's parsed to uri to begin adding to
        Uri baseUri = Uri.parse(CARDS_JSON_URL);

        // Builds URI for getting Cards JSON
        Uri.Builder cardUriBuilder = baseUri.buildUpon();
        cardUriBuilder.path(getString(R.string.url_all_cards));

        // Builds URI to check version
        Uri.Builder versionUriBuilder = baseUri.buildUpon();
        versionUriBuilder.path(getString(R.string.url_cards_version));

        url = cardUriBuilder.toString();
        versionUrl = versionUriBuilder.toString();

        if (id == VERSION_LOADER_ID) {
            return new VersionLoader(this, versionUrl);
        }
        if (id == CARDS_LOADER_ID) {
            return new CardsLoader(this, url);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onLoadFinished(Loader loader, Object o) {

        if (loader.getId() == VERSION_LOADER_ID) {
            versionCheckBoolean = (Boolean) o;
            if (!(new File(CardsActivity.this.getFilesDir(), "AllCards.json").exists()) ||
                    !versionCheckBoolean) {
                final DownloadTask downloadTask = new DownloadTask(CardsActivity.this);
                downloadTask.execute(url, versionUrl);
                Log.v("download", "executed");
            }
            checkAndRefresh();

        } else if (loader.getId() == CARDS_LOADER_ID) {
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(getString(R.string.no_cards));
            mAdapter.clear();
            List<Cards> ab = ((List<Cards>) o);

            if (ab != null && !ab.isEmpty()) {
                mAdapter.addAll(ab);
            }
        }
        mAdapter.getFilter().filter("any");
    }

    @Override
    public void onLoaderReset(Loader loader) {
        if (loader.getId() == CARDS_LOADER_ID) {
            mAdapter.clear();
        }
    }

    private void initializeVariables() {
        // Adapter for the News
        mAdapter = new CardsAdapter(this, new ArrayList<Cards>());

        // Constructs the URL that will be used for the request
        CARDS_JSON_URL = getString(R.string.url_mtg_json);

        // Finds the view for the empty text
        mEmptyStateTextView = findViewById(R.id.empty_view);

        // Finds the view for the loading indicator
        loadingIndicator = findViewById(R.id.loading_indicator);

        // Finds the list view
        cardsListView = findViewById(R.id.list);

        // Finds the swipe to refresh view
        pullToRefresh = findViewById(R.id.pullToRefresh);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Checks the connection of the device when loading or refreshing
    // Will let the user know if they've lost connection and try to refresh without one
    @SuppressWarnings({"unchecked"})
    private void checkAndRefresh() {
        NetworkInfo networkInfo = checkNetworkConnection(CardsActivity.this);
        if (networkInfo != null && networkInfo.isConnected()) {
            // To clear the adapter if user tries to refresh after having not had a connection
            mAdapter.clear();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            getLoaderManager().initLoader(CARDS_LOADER_ID, null, this);
        } else {
            // Checks if adapter was already populated before losing connection
            if (mAdapter.isEmpty()) {
                loadingIndicator.setVisibility(View.GONE);
                mEmptyStateTextView.setText(R.string.no_internet);
            } else {
                Toast.makeText(this, R.string.no_refresh_no_connection, Toast.LENGTH_LONG).show();
                loadingIndicator.setVisibility(View.GONE);
                mEmptyStateTextView.setText("");
            }
        }
    }

    // Used to refresh the data on swipe
    private void refreshData() {
        loadingIndicator.setVisibility(View.VISIBLE);
        checkAndRefresh();
    }

    private void cardDetailPopup(View anchorView, int position) {

        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        PopupWindow popupWindow = new PopupWindow(popupView,
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        TextView cardName = popupView.findViewById(R.id.popup_card_name);
        //TODO Remove location icon and phone
        ImageView locationIcon = popupView.findViewById(R.id.popup_location_icon);
        TextView locationPhone = popupView.findViewById(R.id.popup_location_phone_number);
        //TODO Have website instead search WOTC for the card
        //TODO Create another hyperlink for TCG player
        TextView locationWebsite = popupView.findViewById(R.id.popup_location_website);

        TextView cardType = popupView.findViewById(R.id.popup_card_type);
        TextView locationHours = popupView.findViewById(R.id.popup_card_power_and_toughness);
        TextView locationDescription = popupView.findViewById(R.id.popup_card_text);
        LinearLayout manaCostSymbolsLayout = popupView.findViewById(R.id.popup_mana_cost_symbols_layout);
        FrameLayout popupFrame = popupView.findViewById(R.id.popup_frame);

//        popupFrame.setBackgroundColor(getResources().getColor(breweryPopupColorID));

        //Popup window is focusable
        popupWindow.setFocusable(true);

        //Dismiss popup window when clicked outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        //Sets the animation for the popup window
        popupWindow.setAnimationStyle(R.style.AnimationPopup);

        //Set location of the popup to the center
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);

        Cards cardPosition = mAdapter.getItem(position);
        String[] cardManaCosts = cardPosition.getCardManaCost();
        Drawable[] cardManaCostSymbols = new Drawable[cardManaCosts.length];
        ArrayList<ImageView> imageViews = new ArrayList<>();
        for (int i = 0; i < cardManaCosts.length; i++) {
            cardManaCostSymbols[i] = cardPosition.getManaCostSymbol(this, cardManaCosts[i]);
            ImageView subImage = new ImageView(this);
            subImage.setImageDrawable(cardManaCostSymbols[i]);
            subImage.setAdjustViewBounds(true);
            subImage.setVisibility(View.VISIBLE);
            imageViews.add(subImage);
            manaCostSymbolsLayout.addView(subImage);
        }

        cardName.setText(cardPosition.getCardName());
        cardType.setText(cardPosition.getCardType());
    }
}

