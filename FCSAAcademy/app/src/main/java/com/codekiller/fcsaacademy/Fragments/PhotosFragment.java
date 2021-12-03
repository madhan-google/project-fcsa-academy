package com.codekiller.fcsaacademy.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codekiller.fcsaacademy.Adapters.PhotosAdapter;
import com.codekiller.fcsaacademy.Firebase.FButils;
import com.codekiller.fcsaacademy.R;
import com.codekiller.fcsaacademy.Utils.UtilClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class PhotosFragment extends Fragment {

    Context context;
    DatabaseReference photoDB;
    FButils fButils;
    UtilClass utilClass;
    RecyclerView recyclerView;
    PhotosAdapter photosAdapter;
    ArrayList<HashMap<String, String>> arrayList;
    public PhotosFragment(Context context) {
        // Required empty public constructor
        this.context = context;
        fButils = new FButils();
        utilClass = new UtilClass(context);
        arrayList = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoDB = fButils.getPhotosDatabase();
        getActivity().setTitle("Photo Session");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_photos, container, false);
        recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        utilClass.showProgress("","Loading");
        photoDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for( DataSnapshot ds : snapshot.getChildren() ){
                    arrayList.add((HashMap<String, String>) ds.getValue());
                }
                photosAdapter = new PhotosAdapter(context, arrayList);
                recyclerView.setAdapter(photosAdapter);
                utilClass.dismissProgress();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                utilClass.dismissProgress();
            }
        });
        return v;
    }
}