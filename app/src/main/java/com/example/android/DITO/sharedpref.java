package com.example.android.DITO;

import android.content.Context;
import android.content.SharedPreferences;

public class sharedpref
{
    SharedPreferences mysharedpreferences;

    public sharedpref(Context context)
    {
        mysharedpreferences = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    public void setNightModeState(Boolean state)
    {
        SharedPreferences.Editor editor = mysharedpreferences.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }

    public Boolean loadNightModeState()
    {
        Boolean state = mysharedpreferences.getBoolean("NightMode", false);
        return state;
    }

    public void setBigFontState(Boolean state)
    {
        SharedPreferences.Editor editor = mysharedpreferences.edit();
        editor.putBoolean("BigFont", state);
        editor.commit();
    }

    public Boolean loadBigFontState()
    {
        Boolean state = mysharedpreferences.getBoolean("BigFont", false);
        return state;
    }
}