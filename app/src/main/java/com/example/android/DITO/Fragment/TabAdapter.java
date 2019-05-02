package com.example.android.DITO.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TabAdapter(FragmentManager fm, int  NumOfTabs){
        super(fm);
        this.mNumOfTabs = NumOfTabs;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new TabLatest();
            case 1: return new TabTech();
            case 2: return new TabLife();
            default: return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
