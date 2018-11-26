package com.example.krokken.magicthegatheringcards;

import android.content.Context;
import android.content.AsyncTaskLoader;

public class VersionLoader extends AsyncTaskLoader<Boolean> {

    // URL used to get the JSON information
    private String mUrl;

    public VersionLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Boolean loadInBackground() {
        if (mUrl == null) return false;

        boolean versionCheck = QueryUtils.checkJSONVersion(mUrl, getContext());
        return versionCheck;
    }
}