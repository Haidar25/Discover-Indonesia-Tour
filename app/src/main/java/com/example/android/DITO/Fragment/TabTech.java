package com.example.android.DITO.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.DITO.Adapterberita;
import com.example.android.DITO.model.Modelberita;
import com.example.android.DITO.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class TabTech extends Fragment {
    List<Modelberita> mList;
    RecyclerView rvArtikel;
    Adapterberita adapterartikel;
    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

    public TabTech() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_tech, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = new ArrayList<>();
        rvArtikel = view.findViewById(R.id.rv_tech);
        rvArtikel.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvArtikel.setHasFixedSize(true);
        adapterartikel = new Adapterberita(mList, getActivity());
        rvArtikel.setAdapter(adapterartikel);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("/Article" +
                "");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.hasChildren()) {
                    Modelberita modelberita = dataSnapshot.getValue(Modelberita.class);
                    if (modelberita != null) {
                        if (modelberita.mogimogi != null) {
                            if (modelberita.mogimogi.equals("Sudah Lulus Sensor")) {
                                mList.add(modelberita);
                                adapterartikel.notifyDataSetChanged();
                            }
                        }
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

}
