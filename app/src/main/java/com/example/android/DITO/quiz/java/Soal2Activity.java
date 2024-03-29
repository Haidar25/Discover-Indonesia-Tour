package com.example.android.DITO.quiz.java;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.DITO.AboutusActivity;
import com.example.android.DITO.Main2;
import com.example.android.DITO.MainActivity;
import com.example.android.DITO.Masuk;
import com.example.android.DITO.R;
import com.example.android.DITO.ReviewActivity;
import com.example.android.DITO.ReviewUtama;
import com.example.android.DITO.SelamatdatangquizActivity;
import com.example.android.DITO.SettingActivity;
import com.example.android.DITO.sharedpref;
import com.google.firebase.auth.FirebaseAuth;

public class Soal2Activity extends AppCompatActivity {

    AlertDialog.Builder builder;
    RadioGroup radiogroup;
    FirebaseAuth mAuth;
    sharedpref mysharedpref;
    //deklarasi var or obj
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_utama:
                Intent i = new Intent (Soal2Activity.this, Main2.class);
                startActivity(i);
                return true;
            case R.id.trivia_quiz:
                Intent intent = new Intent (Soal2Activity.this, SelamatdatangquizActivity.class);
                startActivity(intent);
                return true;
            case R.id.about:
                Intent ab = new Intent (Soal2Activity.this, AboutusActivity.class);
                startActivity(ab);
                return true;
            case R.id.Settings:
                Intent is = new Intent (Soal2Activity.this, SettingActivity.class);
                startActivity(is);
                return true;
            case R.id.sign_out:
                Intent in = new Intent (Soal2Activity.this, Masuk.class);
                startActivity(in);
                break;
            case R.id.review:
                Intent rv = new Intent (Soal2Activity.this, ReviewUtama.class);
                startActivity(rv);
                break;
            case R.id.catatan:
                Intent ctt = new Intent (Soal2Activity.this, ReviewActivity.class);
                startActivity(ctt);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        mAuth.signOut();
        startActivity(new Intent(Soal2Activity.this, Masuk.class));
        finish();
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
        setContentView(R.layout.activity_soal2);

        radiogroup = (RadioGroup) findViewById(R.id.radioGroup);
    }

    //Memilih RadioButton

    public void onRadioButton(View view) {

        Boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButton:
                if (checked)
                    jawabanSalah();
                break;

            case R.id.radioButton2:
                if (checked)
                    jawabanSalah();
                break;
            case R.id.radioButton3:
                if (checked)
                    tampilDialog();
                break;
            case R.id.radioButton4:
                if (checked)
                    jawabanSalah();
                break;
        }
    }
    //menampilkan dialog
    public void tampilDialog() {
        builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Selamat !!!");
        builder.setMessage("Jawaban kamu benar : Jaipong");
        builder.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Soal2Activity.this, "Selamat", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Soal2Activity.this, Soal3Activity.class);
                Soal2Activity.this.startActivity(intent);
            }
        });

        builder.setNegativeButton("ULANGI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                radiogroup.clearCheck();
            }
        });

        builder.create().show();

    }
    //menampilkan toast text jawaban salah
    public void jawabanSalah(){
        builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Sayang sekali");
        builder.setMessage("Jawaban kamu salah");
        builder.setPositiveButton("LEWATI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Soal2Activity.this, "Coba Lagi", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Soal2Activity.this, Soal3Activity.class);
                Soal2Activity.this.startActivity(intent);
            }
        });

        builder.setNegativeButton("ULANGI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                radiogroup.clearCheck();
            }
        });

        builder.create().show();
    }

}