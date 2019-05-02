package com.example.android.DITO;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.DITO.model.Modelberita;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterAdmin extends RecyclerView.Adapter<AdapterAdmin.Myviewholder> {
    List<Modelberita> modelberitaa;
    Context context;

    public AdapterAdmin(List<Modelberita> modelberitaa, Context context) {
        this.modelberitaa = modelberitaa;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Myviewholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_admin,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final Myviewholder myviewholder, final int i) {
        final Modelberita modelberitaaa = modelberitaa.get(i);

        myviewholder.tvTitle.setText(modelberitaaa.judul);
        myviewholder.tvAuthor.setText(modelberitaaa.author);

        if (modelberitaaa.tipe.equals("News")){
            myviewholder.btnAsep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Modelberita md = new Modelberita(modelberitaaa.uid, modelberitaaa.judul, modelberitaaa.gambar, "News", modelberitaaa.berita, modelberitaaa.author, "Sudah Lulus Sensor", System.currentTimeMillis());
                    DatabaseReference a = FirebaseDatabase.getInstance().getReference("News").child(modelberitaaa.uid);
                    a.setValue(md).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context, "MANTUL", Toast.LENGTH_SHORT).show();
                            modelberitaa.remove(i);
                            notifyItemRemoved(i);
                            notifyDataSetChanged();
                        }
                    });
                }
            });
            myviewholder.btnDelina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Modelberita md = new Modelberita(modelberitaaa.uid, modelberitaaa.judul, modelberitaaa.gambar, "News", modelberitaaa.berita, modelberitaaa.author, "Tidak Lulus Sensor", System.currentTimeMillis());
                    DatabaseReference a = FirebaseDatabase.getInstance().getReference("News").child(modelberitaaa.uid);
                    a.setValue(md).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context, "MANTUL", Toast.LENGTH_SHORT).show();
                            modelberitaa.remove(i);
                            notifyItemRemoved(i);
                            notifyDataSetChanged();
                        }
                    });
                }
            });
        }else if (modelberitaaa.tipe.equals("Student")){
            myviewholder.btnAsep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Modelberita md = new Modelberita(modelberitaaa.uid, modelberitaaa.judul, modelberitaaa.gambar, "Student", modelberitaaa.berita, modelberitaaa.author, "Sudah Lulus Sensor", System.currentTimeMillis());
                    DatabaseReference a = FirebaseDatabase.getInstance().getReference("Article").child(modelberitaaa.uid);
                    a.setValue(md).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context, "MANTUL", Toast.LENGTH_SHORT).show();
                            modelberitaa.remove(i);
                            notifyItemRemoved(i);
                            notifyDataSetChanged();
                        }
                    });
                }
            });
            myviewholder.btnDelina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Modelberita md = new Modelberita(modelberitaaa.uid, modelberitaaa.judul, modelberitaaa.gambar, "Student", modelberitaaa.berita, modelberitaaa.author, "Tidak Lulus Sensor", System.currentTimeMillis());
                    DatabaseReference a = FirebaseDatabase.getInstance().getReference("Article").child(modelberitaaa.uid);
                    a.setValue(md).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context, "MANTUL", Toast.LENGTH_SHORT).show();
                            modelberitaa.remove(i);
                            notifyItemRemoved(i);
                            notifyDataSetChanged();
                        }
                    });
                }
            });
        }

        myviewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailStudentCorner.class);
                i.putExtra("julid", modelberitaaa.judul);
                i.putExtra("aufar", modelberitaaa.author);
                i.putExtra("hoax", modelberitaaa.berita);
                i.putExtra("ambar", modelberitaaa.gambar);
                i.putExtra("anggal", modelberitaaa.mogumogu);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelberitaa.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvAuthor;
        Button btnAsep, btnDelina;
        CardView cardView;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            btnAsep = itemView.findViewById(R.id.btn_accept);
            btnDelina = itemView.findViewById(R.id.btn_decline);
            cardView = itemView.findViewById(R.id.cv_admin);
        }
    }
}

