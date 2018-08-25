package com.example.krokken.newsreport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NewsReportLoader extends AsyncTaskLoader<List<NewsReport>> {

    // URL used to get the JSON information
    private String mUrl;

    public NewsReportLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsReport> loadInBackground() {
        if (mUrl == null) return null;

        List<NewsReport> newsReports = QueryUtils.fetchNewsData(mUrl);
        return newsReports;
    }
}
