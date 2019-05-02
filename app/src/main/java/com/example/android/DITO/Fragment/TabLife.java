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

import com.example.android.DITO.AdapterRV;
import com.example.android.DITO.model.ModelEvent;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class TabLife extends Fragment {
    List<ModelEvent> mList;
    RecyclerView rvEvent;
    AdapterRV adapterevent;
    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

    public TabLife() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_life, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = new ArrayList<>();
        rvEvent = view.findViewById(R.id.rv_life);
        rvEvent.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvEvent.setHasFixedSize(true);
        adapterevent = new AdapterRV(mList, getActivity());
        rvEvent.setAdapter(adapterevent);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("/Event" +
                "");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.hasChildren()) {
                    ModelEvent modelEvent = dataSnapshot.getValue(ModelEvent.class);
                    if (modelEvent != null) {
                        if (modelEvent.mogimogi != null) {
                            if (modelEvent.mogimogi.equals("Sudah Lulus Sensor")) {
                                mList.add(modelEvent);
                                adapterevent.notifyDataSetChanged();
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
