package com.example.android.DITO;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.android.DITO.quiz.java.Soal1Activity;
import com.google.firebase.auth.FirebaseAuth;

public class SelamatdatangquizActivity extends AppCompatActivity {

    private Button btAlert;
    private Button btBalik;
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
        startActivity(new Intent(SelamatdatangquizActivity.this, Masuk.class));
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_utama:
                Intent i = new Intent (SelamatdatangquizActivity.this, Main2.class);
                startActivity(i);
                return true;
            case R.id.trivia_quiz:
                Intent intent = new Intent (SelamatdatangquizActivity.this, SelamatdatangquizActivity.class);
                startActivity(intent);
                return true;
            case R.id.about:
                Intent ab = new Intent (SelamatdatangquizActivity.this, AboutusActivity.class);
                startActivity(ab);
                return true;
            case R.id.Settings:
                Intent is = new Intent (SelamatdatangquizActivity.this, SettingActivity.class);
                startActivity(is);
                return true;
            case R.id.sign_out:
                Intent in = new Intent (SelamatdatangquizActivity.this, Masuk.class);
                startActivity(in);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            mysharedpref = new sharedpref(this);
            if (mysharedpref.loadNightModeState() == true) {
                setTheme(R.style.DarkTheme);
            } else
            {
                setTheme(R.style.AppTheme);
            }


            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selamatdatangquiz);

        btAlert = (Button) findViewById(R.id.mulai);
        btBalik = (Button) findViewById(R.id.batal);
        btAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        btBalik.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SelamatdatangquizActivity.this, Main2.class);
                SelamatdatangquizActivity.this.startActivity(intent);
            }
        });
    }
        private void showDialog(){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Trivia Quiz?");
            alertDialogBuilder
                    .setMessage("Apakah anda ingin melanjutkannya?")
                    .setCancelable(false)
                    .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(SelamatdatangquizActivity.this, Soal1Activity.class);
                            SelamatdatangquizActivity.this.startActivity(intent);
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Intent intent = new Intent(SelamatdatangquizActivity.this, MainActivity.class);
//                            SelamatdatangquizActivity.this.startActivity(intent);
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
}
