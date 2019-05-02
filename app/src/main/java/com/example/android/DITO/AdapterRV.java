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

import com.example.android.DITO.model.ModelEvent;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRV extends RecyclerView.Adapter <AdapterRV.MyViewOlder> {

    List<ModelEvent> mList;
    Context context;

    public AdapterRV(List<ModelEvent> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewOlder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewOlder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tampilanevent, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewOlder myViewOlder, int i) {
        final ModelEvent item = mList.get(i);
        Picasso.get().load(item.gambar).into(myViewOlder.gambar);
        myViewOlder.mcons1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sc = new Intent(context, DetailStudentCorner.class);
                sc.putExtra("ambar", item.gambar);
                sc.putExtra("anggal", item.mogumogu);
                context.startActivity(sc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewOlder extends RecyclerView.ViewHolder {

        ImageView gambar;
        ConstraintLayout mcons1;
        public MyViewOlder(@NonNull View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.imgevent);
            mcons1 = itemView.findViewById(R.id.event);
        }
    }
}
