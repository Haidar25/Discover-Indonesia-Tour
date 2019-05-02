package com.example.android.DITO;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.DITO.model.Modelberita;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapterberita extends RecyclerView.Adapter<Adapterberita.Myviewholder> {
    List<Modelberita> modelberitaa;
    Context context;

    public Adapterberita(List<Modelberita> modelberitaa, Context context) {
        this.modelberitaa = modelberitaa;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Myviewholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tampilanberita,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {
        final Modelberita modelberitaaa = modelberitaa.get(i);
        myviewholder.tampilanevent.setText(modelberitaaa.judul);
        myviewholder.berita.setText(modelberitaaa.berita);

        if (!modelberitaaa.gambar.isEmpty()){
            Picasso.get().load(modelberitaaa.gambar).into(myviewholder.gambar);
        }


        myviewholder.mcons1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sc = new Intent(context, DetailStudentCorner.class);
                sc.putExtra("julid", modelberitaaa.judul);
                sc.putExtra("aufar", modelberitaaa.author);
                sc.putExtra("hoax", modelberitaaa.berita);
                sc.putExtra("ambar", modelberitaaa.gambar);
                sc.putExtra("anggal", modelberitaaa.mogumogu);
                context.startActivity(sc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelberitaa.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView tampilanevent;
        ImageView gambar;
        TextView berita;
        ConstraintLayout mcons1;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            tampilanevent = itemView.findViewById(R.id.textView6);
            gambar = itemView.findViewById(R.id.imageView2);
            berita = itemView.findViewById(R.id.textView5);
            mcons1 = itemView.findViewById(R.id.cons1);
        }
    }
}
