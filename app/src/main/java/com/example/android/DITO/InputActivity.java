package com.example.android.DITO;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InputActivity extends AppCompatActivity {
    int REQUEST = 91, REQUEST_GET_SINGLE_FILE = 202, REQUEST_CAPTURE_IMAGE = 234;
    ImageView image;
    Bitmap bitmap;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    String lokasi_image;
    EditText nama, harga, deskripsi;
    Uri uri;

    FirebaseStorage storagefirebase;
    StorageReference storageReference;
    Map<String, Object> menu;
    ProgressBar progressBar;
    boolean success = false;
    sharedpref mysharedpref;

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void logout() {
        mAuth.signOut();
        startActivity(new Intent(InputActivity.this, Masuk.class));
        finish();
    }
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_utama:
                Intent i = new Intent (InputActivity.this, Main2.class);
                startActivity(i);
                return true;
            case R.id.trivia_quiz:
                Intent intent = new Intent (InputActivity.this, SelamatdatangquizActivity.class);
                startActivity(intent);
                return true;
            case R.id.about:
                Intent ab = new Intent (InputActivity.this, AboutusActivity.class);
                startActivity(ab);
                return true;
            case R.id.Settings:
                Intent is = new Intent (InputActivity.this, SettingActivity.class);
                startActivity(is);
                return true;
            case R.id.sign_out:
                Intent in = new Intent (InputActivity.this, Masuk.class);
                startActivity(in);
                break;

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
        setContentView(R.layout.activity_input);
        image = findViewById(R.id.gambar_dummy);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        //Init
        storagefirebase = FirebaseStorage.getInstance();
        storageReference = storagefirebase.getReference();
        nama = findViewById(R.id.textview_inputnama);
        harga = findViewById(R.id.textview_masukkanharga);
        deskripsi = findViewById(R.id.textview_deskripsi);

        progressBar = findViewById(R.id.progressBar);

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
    }
    @SuppressLint("StaticFieldLeak")
    public void tambahMenu(View view) {
        new AsyncTask<Void, Boolean, Boolean>() {

            @Override
            protected void onPreExecute() {
                progressBar.setVisibility(View.VISIBLE);
                menu = new HashMap<>();

                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(InputActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                }
                super.onPostExecute(aBoolean);
            }
            @Override
            protected Boolean doInBackground(Void... voids) {
                menu.put("NamaMenu", nama.getText().toString());
                menu.put("Harga", harga.getText().toString());
                menu.put("Deskripsi", deskripsi.getText().toString());
                menu.put("Date",new SimpleDateFormat().format(new Date()));

                db.collection(mAuth.getUid())
                        .add(menu)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
//                                docRef = documentReference.getId();
                                success = true;
                                if (uri != null) {
                                    StorageReference ref = storageReference.child("images/" + documentReference.getId());
                                    ref.putFile(uri)
                                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                    Toast.makeText(InputActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();

                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(InputActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }

                                            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                            Toast.makeText(InputActivity.this, "Uploading..", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                finish();
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                return success;
            }
        }.execute();
    }public void upFireStore() {
    }
    public void selectPict(View view) {
        if (ContextCompat.checkSelfPermission(InputActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(InputActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(InputActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                    REQUEST_GET_SINGLE_FILE);
        } }
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAPTURE_IMAGE) {
                if (data != null && data.getExtras() != null) {
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            bitmap = (Bitmap) data.getExtras().get("data");
                            return null;
                        }
                        @Override
                        protected void onPostExecute(Void aVoid) {
                            image.setImageBitmap(bitmap);
                            super.onPostExecute(aVoid);
                        }
                    }.execute();
                }
            } else if (requestCode == REQUEST_GET_SINGLE_FILE) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected void onPreExecute() {
                        uri = data.getData();
                        super.onPreExecute();
                    }
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        image.setImageBitmap(bitmap);
                        super.onPostExecute(aVoid);
                    }
                    @Override
                    protected Void doInBackground(Void... voids) {
                        lokasi_image = getPathFromURI(getApplicationContext(), uri);
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),
                                    uri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute();
            }
        }
    }
    private static String getPathFromURI(Context context, Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        Log.i("URI", uri + "");
        String result = uri + "";

        if (isKitKat && (result.contains("media.documents"))) {
            String[] ary = result.split("/");
            int length = ary.length;
            String imgary = ary[length - 1];
            final String[] dat = imgary.split("%3A");
            final String docId = dat[1];
            final String type = dat[0];
            Uri contentUri = null;
            if ("image".equals(type)) {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(type)) {
            } else if ("audio".equals(type)) {
            }
            final String selection = "_id=?";
            final String[] selectionArgs = new String[]{
                    dat[1]
            };
            return getDataColumn(context, contentUri, selection, selectionArgs);
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
}