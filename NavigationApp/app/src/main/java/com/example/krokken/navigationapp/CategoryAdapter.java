package com.example.krokken.navigationapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private Context mContext;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    //Starts related fragments from each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BreweriesFragment();
        } else if (position == 1) {
            return new HikesFragment();
        } else if (position == 2) {
            return new ParksFragment();
        } else {
            return new EventsFragment();
        }
    }

    //Sets the titles for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.breweries_title);
        } else if (position == 1) {
            return mContext.getString(R.string.hikes_title);
        } else if (position == 2) {
            return mContext.getString(R.string.parks_title);
        } else {
            return mContext.getString(R.string.events_title);
        }
    }
}
