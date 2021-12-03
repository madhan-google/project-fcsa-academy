package com.codekiller.fcsaacademy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codekiller.fcsaacademy.R;

import java.util.ArrayList;
import java.util.HashMap;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    Context context;
    ArrayList<HashMap<String, String>> arrayList;

    public EventAdapter(Context context, ArrayList<HashMap<String, String>> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.event_adapter_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String, String> map = arrayList.get(position);
        holder.textView.setText(map.get("event_message"));
        Glide.with(context)
                .load(map.get("download_url"))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
