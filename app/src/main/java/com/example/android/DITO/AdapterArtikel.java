package com.example.android.DITO;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.DITO.model.Modelberita;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterArtikel extends RecyclerView.Adapter<AdapterArtikel.Myviewholder> {
    List<Modelberita> modelberitaa;
    Context context;

    public AdapterArtikel(List<Modelberita> modelberitaa, Context context) {
        this.modelberitaa = modelberitaa;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Myviewholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_pengalaman_perjalanan,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {
        final Modelberita modelberitaaa = modelberitaa.get(i);
        myviewholder.tvJudul.setText(modelberitaaa.judul);
        myviewholder.tvAuthor.setText(modelberitaaa.author);
        Date z = new Date(modelberitaaa.mogumogu);
        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        String x = df.format(z);
        myviewholder.tvDate.setText(x);

        if (!modelberitaaa.gambar.isEmpty()) {
            Picasso.get().load(modelberitaaa.gambar).into(myviewholder.gambar);
        }

        myviewholder.mCard.setOnClickListener(new View.OnClickListener() {
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
        TextView tvAuthor, tvDate, tvJudul;
        ImageView gambar;
        CardView mCard;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.txtPenulis);
            gambar = itemView.findViewById(R.id.imgStud);
            tvDate = itemView.findViewById(R.id.txtDate);
            mCard = itemView.findViewById(R.id.card);
            tvJudul = itemView.findViewById(R.id.txtJudul);
        }
    }
}
