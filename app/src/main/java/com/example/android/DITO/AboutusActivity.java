package com.example.android.DITO;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class AboutusActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    sharedpref mysharedpref;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void logout() {
        mAuth.signOut();
        startActivity(new Intent(AboutusActivity.this, Masuk.class));
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_utama:
                Intent i = new Intent (AboutusActivity.this, Main2.class);
                startActivity(i);
                return true;
            case R.id.trivia_quiz:
                Intent intent = new Intent (AboutusActivity.this, SelamatdatangquizActivity.class);
                startActivity(intent);
                return true;
            case R.id.review:
                Intent in = new Intent (AboutusActivity.this, ReviewActivity.class);
                startActivity(in);
                return true;
            case R.id.Settings:
                Intent inte = new Intent (AboutusActivity.this, SettingActivity.class);
                startActivity(inte);
                return true;
            case R.id.sign_out:
                Intent inten = new Intent (AboutusActivity.this, Masuk.class);
                startActivity(inten);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
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
        setContentView(R.layout.about);
    }
}
