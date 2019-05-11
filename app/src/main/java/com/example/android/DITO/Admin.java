package com.example.android.DITO;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.DITO.model.Modelberita;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Admin extends AppCompatActivity {

    final String PREF_NIGHT_MODE = "NightMode";
    SharedPreferences spNight;

    AdapterAdmin adapterAdmin;
    RecyclerView recyclerView;
    List<Modelberita> modelberitas;

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
        setContentView(R.layout.activity_admin);

        modelberitas = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_admin);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterAdmin = new AdapterAdmin(modelberitas, this);
        recyclerView.setAdapter(adapterAdmin);

        setData();
    }

    private void setData() {
        modelberitas.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("News");
        DatabaseReference ref = database.getReference("Article");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.hasChildren()){
                    Modelberita modelberita = dataSnapshot.getValue(Modelberita.class);
                    if (modelberita != null && modelberita.mogimogi.equals("Belum Lulus Sensor")){
                        modelberitas.add(modelberita);
                        adapterAdmin.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.hasChildren()){
                    Modelberita modelberita = dataSnapshot.getValue(Modelberita.class);
                    if (modelberita != null && modelberita.mogimogi.equals("Belum Lulus Sensor")){
                        modelberitas.add(modelberita);
                        adapterAdmin.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menuadmin,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent newtask = new Intent(Admin.this, Masuk.class);
        newtask.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        newtask.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        switch (item.getItemId()){
            case R.id.menu_logout:
                startActivity(newtask);
                break;
//            case R.id.menu_setting:
//                Intent intent = new Intent(Admin.this, SettingActivity.class);
//                startActivity(intent);
//                finish();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
