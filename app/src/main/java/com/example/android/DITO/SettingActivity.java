package com.example.android.DITO;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


public class SettingActivity extends AppCompatActivity {

    private Switch switchnight, switchbigfont;
    sharedpref mysharedpref;
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
//    private void logout() {
//        mAuth.signOut();
//        startActivity(new Intent(ReviewActivity.this, Masuk.class));
//        finish();
//    }
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_utama:
                Intent i = new Intent (SettingActivity.this, Main2.class);
                startActivity(i);
                return true;
            case R.id.trivia_quiz:
                Intent intent = new Intent (SettingActivity.this, SelamatdatangquizActivity.class);
                startActivity(intent);
                return true;
            case R.id.about:
                Intent ab = new Intent (SettingActivity.this, AboutusActivity.class);
                startActivity(ab);
                return true;
            case R.id.review:
                Intent is = new Intent (SettingActivity.this, ReviewActivity.class);
                startActivity(is);
                return true;
            case R.id.sign_out:
                Intent in = new Intent (SettingActivity.this, Masuk.class);
                startActivity(in);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mysharedpref = new sharedpref(this);
        if (mysharedpref.loadNightModeState() == true) {
            if (mysharedpref.loadBigFontState() == true){
                setTheme(R.style.BigTheme);
            }else {
                setTheme(R.style.NormalTheme);
            }
            setTheme(R.style    .DarkTheme);
        } else {
            if (mysharedpref.loadBigFontState() == true){
                setTheme(R.style.BigTheme);
            }else {
                setTheme(R.style.NormalTheme);
            }
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        switchbigfont = findViewById(R.id.sw_font_size);
        if (mysharedpref.loadBigFontState() == true) {
            switchbigfont.setChecked(true);
        }else
        {
            switchbigfont.setChecked(false);
        }

        switchnight = findViewById(R.id.sw_night_mode);
        if (mysharedpref.loadNightModeState() == true){
            switchnight.setChecked(true);
        } else
        {
            switchnight.setChecked(false);
        }

        switchbigfont = findViewById(R.id.sw_font_size);

        CompoundButton.OnCheckedChangeListener multiListener = new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                switch (v.getId()){
                    case R.id.sw_night_mode:
                        if (isChecked)
                        {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            mysharedpref.setNightModeState(true);
                            startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                            finish();
                        } else
                        {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            mysharedpref.setNightModeState(false);
                            startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                            finish();
                        }
                        break;
                    case R.id.sw_font_size:
                        if (isChecked)
                        {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            mysharedpref.setBigFontState(true);
                            startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                            finish();
                        } else
                        {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            mysharedpref.setBigFontState(false);
                            startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                            finish();
                        }
                        break;
                }
            }
        };

        switchnight.setOnCheckedChangeListener(multiListener);
        switchbigfont.setOnCheckedChangeListener(multiListener);
    }

    public void setting_save(View view) {
        startActivity(new Intent(SettingActivity.this, Main2.class));
    }
}
