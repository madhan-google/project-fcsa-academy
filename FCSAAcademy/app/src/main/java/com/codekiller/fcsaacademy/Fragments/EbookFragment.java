package com.codekiller.fcsaacademy.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codekiller.fcsaacademy.Adapters.EbookAdapter;
import com.codekiller.fcsaacademy.Datas.EBookData;
import com.codekiller.fcsaacademy.Firebase.FButils;
import com.codekiller.fcsaacademy.R;
import com.codekiller.fcsaacademy.Utils.UtilClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EbookFragment extends Fragment {
    public static final String TAG = "EBOOK FRAGMENT";

    Context context;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<EBookData> arrayList;
    ImageView imageView;
    FButils fButils;
    EbookAdapter ebookAdapter;
    UtilClass utilClass;
    public EbookFragment(Context context) {
        // Required empty public constructor
        this.context = context;
        arrayList = new ArrayList<>();
        fButils = new FButils();
        utilClass = new UtilClass(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = fButils.getEBookDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("eBooks");
        View v = inflater.inflate(R.layout.fragment_ebook, container, false);
        recyclerView = v.findViewById(R.id.recycler_view);
        imageView = v.findViewById(R.id.image_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        Glide.with(context)
                .load(R.drawable.books_gif)
                .into(imageView);
        utilClass.showProgress("","Loading");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for( DataSnapshot snapshot : dataSnapshot.getChildren() ){
                    arrayList.add(snapshot.getValue(EBookData.class));
                }
                Log.d(TAG, "onDataChange: db values - "+arrayList.get(0).getDownloadUrl());
                ebookAdapter = new EbookAdapter(context, arrayList);
                recyclerView.setAdapter(ebookAdapter);
                utilClass.dismissProgress();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                utilClass.dismissProgress();
                utilClass.toast("something went wrong");
            }
        });
        return v;
    }
}