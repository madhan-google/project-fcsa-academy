package com.codekiller.fcsaacademy.Fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.codekiller.fcsaacademy.Adapters.EventAdapter;
import com.codekiller.fcsaacademy.Firebase.FButils;
import com.codekiller.fcsaacademy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class HomeFragment extends Fragment {
    ImageView gifView1, gifView2, gifView3, gifView4;
    Context context;
    ImageView videoView;
    RecyclerView recyclerView;
    ArrayList<HashMap<String, String>> arrayList;

    DatabaseReference eventDB;
    FButils fButils;
    EventAdapter eventAdapter;


    public HomeFragment(Context context) {
        // Required empty public constructor
        this.context = context;
        fButils = new FButils();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventDB = fButils.getEventDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Home");
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        gifView1 = v.findViewById(R.id.gif_view1);
        gifView2 = v.findViewById(R.id.gif_view2);
        gifView3 = v.findViewById(R.id.gif_view3);
        gifView4 = v.findViewById(R.id.gif_view4);
        videoView = v.findViewById(R.id.video_view);
        recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        Glide.with(context)
                .load(R.drawable.biology_gif)
                .into(gifView1);
        Glide.with(context)
                .load(R.drawable.tablet)
                .into(gifView2);
        Glide.with(context)
                .load(R.drawable.nuclear_gif2)
                .into(gifView3);
        Glide.with(context)
                .load(R.drawable.chemistry_gif)
                .into(gifView4);
        Glide.with(context)
                .load(R.raw.digi_video)
                .centerCrop()
                .into(videoView);
        arrayList = new ArrayList<>();
        eventDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    arrayList.add((HashMap<String, String>) ds.getValue());
                }
                eventAdapter = new EventAdapter(context, arrayList);
                recyclerView.setAdapter(eventAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }
}