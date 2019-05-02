package com.example.android.DITO;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;


public class Main2 extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    sharedpref mysharedpref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

            mysharedpref = new sharedpref(this);
            if (mysharedpref.loadNightModeState() == true) {
                if (mysharedpref.loadBigFontState() == true) {

                    setTheme(R.style.bigfontdark);
                } else
                    setTheme(R.style.DarkTheme);
            } else {
                setTheme(R.style.AppTheme);
                if (mysharedpref.loadBigFontState() == true) {
                    setTheme(R.style.bigfontwhite);
                } else setTheme(R.style.AppTheme);
            }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        CardView menuutamaa = (CardView) findViewById(R.id.menuutama);
        CardView triviakuis = (CardView) findViewById(R.id.trivia);
        CardView pperjalanan = (CardView) findViewById(R.id.pengalaman);
        CardView setting = (CardView) findViewById(R.id.Pengaturan);
        CardView about = (CardView) findViewById(R.id.tentang);
        CardView exit = (CardView) findViewById(R.id.keluar);

        menuutamaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2.this, MainActivity.class));
            }
        });

        triviakuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2.this,SelamatdatangquizActivity.class));
            }
        });

        pperjalanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2.this,ReviewActivity.class));
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2.this,SettingActivity.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2.this,AboutusActivity.class));
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2.this,Masuk.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
