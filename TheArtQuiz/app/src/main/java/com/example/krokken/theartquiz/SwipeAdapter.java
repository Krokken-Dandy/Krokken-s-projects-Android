package com.example.krokken.theartquiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.krokken.theartquiz.fragmentPages;

/**
 * Created by Krokken on 3/12/2018.
 */

public class SwipeAdapter extends FragmentStatePagerAdapter {


    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new fragmentPages();
        Bundle bundle = new Bundle();
        bundle.putInt("count", i+1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 12;
    }

}
