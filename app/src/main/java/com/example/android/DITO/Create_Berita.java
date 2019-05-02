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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.DITO.model.Modelberita;
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

public class Create_Berita extends AppCompatActivity {
    final String PREF_NIGHT_MODE = "NightMode";
    SharedPreferences spNight;
    Button mbutonPost, mAdd;
    EditText mtitle, mcontent, mauthor;
    ImageView getImg;
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
        setContentView(R.layout.create_berita);
        getSupportActionBar().hide();

        mbutonPost = findViewById(R.id.btnPostE);
        mtitle = findViewById(R.id.titleArticle);
        mcontent = findViewById(R.id.contentArticle);
        mauthor = findViewById(R.id.authorArticle);
        mAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance();
        mAdd= findViewById(R.id.btnAdd_Event);
        getImg = findViewById(R.id.gambar_dummy);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                        REQUEST_GET_SINGLE_FILE);

            }
        });



        mbutonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String title,author, content;

                mantul = "";
                title = mtitle.getText().toString().trim();
                content = mcontent.getText().toString().trim();
                author = mauthor.getText().toString().trim();

                if (title.isEmpty()){
                    pesan("Title harus diisi");
                    return;
                }
                if (author.isEmpty()){
                    pesan("Author harus diisi");
                    return;
                }
                if (content.isEmpty()){
                    pesan("Deskripsi harus diisi");
                    return;
                }

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

                                        DatabaseReference dbnews = mDatabase.getReference("News").push();
                                        Modelberita mb = new Modelberita(dbnews.getKey(),title,mantul, "News",content,author, "Belum Lulus Sensor", System.currentTimeMillis());

                                        dbnews.setValue(mb).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toast.makeText(Create_Berita.this, "Posted", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(Create_Berita.this, ReviewUtama.class));
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
                    DatabaseReference dbnews = mDatabase.getReference("News").push();
                    Modelberita mb = new Modelberita(dbnews.getKey(),title,"", "News",content,author, "Belum Lulus Sensor", System.currentTimeMillis());

                    dbnews.setValue(mb).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Create_Berita.this, "Posted", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Create_Berita.this, ReviewUtama.class));
                                finish();
                            }
                        }
                    });
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
                    getImg.setImageBitmap(imageBitmap);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
                    bit = baos.toByteArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private void pesan(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
