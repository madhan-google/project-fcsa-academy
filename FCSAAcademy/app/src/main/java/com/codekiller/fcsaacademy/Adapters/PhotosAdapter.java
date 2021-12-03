package com.codekiller.fcsaacademy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codekiller.fcsaacademy.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    Context context;
    ArrayList<HashMap<String, String>> arrayList;

    public PhotosAdapter(Context context, ArrayList<HashMap<String, String>> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.photo_adapter_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(arrayList.get(position).get("download_url"))
                .centerCrop()
                .placeholder(R.drawable.download)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }

}
