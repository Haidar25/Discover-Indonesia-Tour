package com.example.android.DITO;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.example.android.DITO.Fragment.TabAdapter;

public class NewsWall extends AppCompatActivity {

    final String PREF_NIGHT_MODE = "NightMode";
    SharedPreferences spNight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        spNight = getSharedPreferences(PREF_NIGHT_MODE , Context.MODE_PRIVATE);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }else{
            setTheme(R.style.AppTheme);

            if(spNight.getBoolean(PREF_NIGHT_MODE,false)){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.batas_postingan);
        getSupportActionBar().setTitle("DITO (Discover Indonesia Tour)");

        //android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        // Set the text for each tab.
        tabLayout.addTab(tabLayout.newTab().setText("Berita Terbaru"));
        tabLayout.addTab(tabLayout.newTab().setText("Pengalaman Perjalanan"));
        tabLayout.addTab(tabLayout.newTab().setText("Acara Komunitas"));



        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final TabAdapter adapter = new TabAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        if (getIntent()!= null){
            String cv = getIntent().getStringExtra("typ");
            if(cv.equals("student")){
                viewPager.setCurrentItem(1);
            }
            if (cv.equals("event")){
                viewPager.setCurrentItem(2);
            }
        }

        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });

    }
}
