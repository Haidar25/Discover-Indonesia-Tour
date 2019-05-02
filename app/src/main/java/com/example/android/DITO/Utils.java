package com.example.android.DITO;

import android.content.Context;

public final class Utils {

    public static int resolveStringToResId(Context context, CharSequence text) {
        if (context.getString(R.string.kotafavorit).contentEquals(text)) return R.string.kotafavorit;
        else if (context.getString(R.string.pariwisata).contentEquals(text)) return R.string.pariwisata;
        else if (context.getString(R.string.situskebudayaan).contentEquals(text)) return R.string.situskebudayaan;
        else if (context.getString(R.string.situssejarah).contentEquals(text)) return R.string.situssejarah;
        else return 0;
    }
}
