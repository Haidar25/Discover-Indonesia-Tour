package com.example.android.DITO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Masuk extends AppCompatActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener {

    EditText useremail, userpass;
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    CheckBox cbForgot;

    private static final String TAG = "LoginActivity";
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        useremail = findViewById(R.id.textemail);
        userpass = findViewById(R.id.textpass);
        mAuth = FirebaseAuth.getInstance();
        cbForgot = findViewById(R.id.cbForgot);

        if (sharedPreferences.getBoolean(KEY_REMEMBER, false))
            cbForgot.setChecked(true);
        else
            cbForgot.setChecked(false);

        useremail.setText(sharedPreferences.getString(KEY_USERNAME, ""));
        userpass.setText(sharedPreferences.getString(KEY_PASS, ""));

        useremail.addTextChangedListener(this);
        userpass.addTextChangedListener(this);
        cbForgot.setOnCheckedChangeListener(this);

    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        managePrefs();

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefs();
    }

    private void managePrefs() {
        if (cbForgot.isChecked()) {
            editor.putString(KEY_USERNAME, useremail.getText().toString().trim());
            editor.putString(KEY_PASS, userpass.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        } else {
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);//editor.putString(KEY_PASS,"");
            editor.remove(KEY_USERNAME);//editor.putString(KEY_USERNAME, "");
            editor.apply();
        }
    }

    public boolean check() {
        if (useremail.getText().toString().equals("")) {
            useremail.setError("Isi email");
            useremail.requestFocus();
            return false;
        }
        if (userpass.getText().toString().equals("")) {
            userpass.setError("Isi pass");
            userpass.requestFocus();
            return false;
        }
        return true;
    }

    public void ForgotPassword(View view) {
        if (useremail.getText().toString().equals("")) {
            useremail.setError("Isi Emailmu");
            useremail.requestFocus();
        } else {
            mAuth.sendPasswordResetEmail(useremail.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Masuk.this, "Email sent.", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "Email sent.");
                            }
                        }
                    });
        }
    }

    public void login(View view) {
        if (check()) {
            new AsyncTask<Void, Void, Boolean>() {
                @SuppressLint("WrongThread")
                @Override
                protected Boolean doInBackground(Void... voids) {
                    mAuth.signInWithEmailAndPassword(useremail.getText().toString(), userpass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(Masuk.this, Main2.class));
                                        Toast.makeText(Masuk.this, "Anda berhasil masuk", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(Masuk.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    return null;
                }

                @Override
                protected void onPreExecute() {
                    //Toast.makeText(Masuk.this, "Anda berhasil masuk", Toast.LENGTH_SHORT).show();
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                }
            }.execute();
        }
    }

    public void gotORegist(View view) {
        startActivity(new Intent(Masuk.this, Daftar.class));
        finish();
    }

}
