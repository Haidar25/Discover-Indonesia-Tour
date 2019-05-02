package com.example.android.DITO;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.android.DITO.model.Attraction;

public class AttractionActivity extends SingleActivity {
    /* Class Constants */
    private static final String EXTRA_ATTRACTION = "com.example.android.DITO.attraction";

    public static Intent newIntent(Context packageContext, Attraction attraction) {
        Intent intent = new Intent(packageContext, AttractionActivity.class);
        intent.putExtra(EXTRA_ATTRACTION, attraction);

        return intent;
    }

    @Override
    protected Fragment createFragment() {
        Attraction attraction = getIntent().getParcelableExtra(EXTRA_ATTRACTION);
        return AttractionFragment.newInstance(attraction);
    }
}
