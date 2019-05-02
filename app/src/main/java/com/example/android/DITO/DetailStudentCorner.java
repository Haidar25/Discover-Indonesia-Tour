package com.example.android.DITO;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailStudentCorner extends AppCompatActivity {
    final String PREF_NIGHT_MODE = "NightMode";
    SharedPreferences spNight;

    TextView tvTitle, tvTanggalstudent, tvContributorstudent,tvIsicornerstudent;
    ImageView Imgstudent;
    EditText commentstudent;
    Button Submitstudent;

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
        setContentView(R.layout.activity_pengalaman_perjalanan);

        tvTitle = findViewById(R.id.titlestudent);
        tvTanggalstudent = findViewById(R.id.tanggalstudent);
        tvContributorstudent = findViewById(R.id.contributorstudent);
        tvIsicornerstudent = findViewById(R.id.isicornerstudent);
        Imgstudent = findViewById(R.id.imgstudent);
        commentstudent = findViewById(R.id.komenstudent);
        Submitstudent = findViewById(R.id.submitstudent);

        if (getIntent() != null){
            String a = getIntent().getStringExtra("julid");
            String b = getIntent().getStringExtra("hoax");
            String c = getIntent().getStringExtra("aufar");
            String d = getIntent().getStringExtra("ambar");
            Long e = getIntent().getLongExtra("anggal", 0);

            if (a != null) {
                tvTitle.setText(a);
            }
            if (b != null) {
                tvIsicornerstudent.setText(b);
            }
            if (c != null) {
                tvContributorstudent.setText(c);
            }
            if (d != null) {
                Picasso.get().load(d).into(Imgstudent);
            }

            Date z = new Date(e);
            SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
            String x = df.format(z);
            tvTanggalstudent.setText(x);

        }
    }
}
