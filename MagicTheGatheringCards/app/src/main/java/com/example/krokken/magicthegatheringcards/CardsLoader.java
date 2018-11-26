package com.example.krokken.magicthegatheringcards;

import android.content.Context;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

public class CardsLoader extends AsyncTaskLoader<List<Cards>> {

    // URL used to get the JSON information
    private String mUrl;

    CardsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Cards> loadInBackground() {
        if (mUrl == null) return null;

        List<Cards> cardsList = QueryUtils.fetchCardData(mUrl, getContext());
        Log.v("Cards Loader", "loaded in background");
        return cardsList;
    }
}

