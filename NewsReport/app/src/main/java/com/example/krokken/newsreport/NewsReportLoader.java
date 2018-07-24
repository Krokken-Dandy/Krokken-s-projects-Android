package com.example.krokken.newsreport;

import android.content.Context;
import android.support.annotation.Nullable;
import android.content.AsyncTaskLoader;

import java.util.List;

public class NewsReportLoader extends AsyncTaskLoader<List<NewsReport>> {

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
        if(mUrl == null) return null;

        List<NewsReport> newsReports = QueryUtils.fetchEarthquakeData(mUrl);
        return newsReports;
    }
}
