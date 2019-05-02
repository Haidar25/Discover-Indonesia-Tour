package com.example.android.DITO;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.DITO.model.ModelEvent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Create_Acara extends AppCompatActivity {
    final String PREF_NIGHT_MODE = "NightMode";
    SharedPreferences spNight;
    Button mbtnPostEvent, mAddEvent;
    ImageView getImgEvent;
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    byte[] bit;
    private String mantul;
    private static final int REQUEST_GET_SINGLE_FILE = 1;

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
        setContentView(R.layout.create_acara);
        getSupportActionBar().hide();

        mbtnPostEvent = findViewById(R.id.btnPostE);
        mAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance();
        mAddEvent= findViewById(R.id.btnAdd_Event);
        getImgEvent = findViewById(R.id.gambar_dummy);

        mAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                        REQUEST_GET_SINGLE_FILE);

            }
        });

        mbtnPostEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bit != null){
                    final StorageReference ref = FirebaseStorage.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()+""+ System.currentTimeMillis());
                    ref.putBytes(bit).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()){
                                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        mantul = uri.toString();

                                        DatabaseReference dbnews = mDatabase.getReference("Event").push();
                                        ModelEvent mb = new ModelEvent(dbnews.getKey(),mantul, "Event", "Sudah Lulus Sensor", System.currentTimeMillis());

                                        dbnews.setValue(mb).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toast.makeText(Create_Acara.this, "Posted", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(Create_Acara.this, ReviewUtama.class));
                                                    finish();
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });
                }else{
                    Toast.makeText(Create_Acara.this, "sukses", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Create_Acara.this, "Silahkan input poster", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_GET_SINGLE_FILE && resultCode == RESULT_OK){
            if (data != null){
                Uri uri = data.getData();
                try {
                    Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    getImgEvent.setImageBitmap(imageBitmap);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
                    bit = baos.toByteArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

}

