package com.codekiller.fcsaacademy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codekiller.fcsaacademy.Datas.AboutData;
import com.codekiller.fcsaacademy.Firebase.FButils;
import com.codekiller.fcsaacademy.Fragments.AboutFragment;
import com.codekiller.fcsaacademy.R;
import com.codekiller.fcsaacademy.Utils.UtilClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.codekiller.fcsaacademy.Fragments.AboutFragment.imageUri;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ViewHolder> {

    Context context;
    ArrayList<AboutData> arrayList;

    public AboutAdapter(Context context, ArrayList<AboutData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.about_adapter_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AboutData aboutData = arrayList.get(position);
        holder.aboutView.setText(aboutData.getAbout_them());
        holder.subjectView.setText(aboutData.getSubject());
        holder.nameView.setText(aboutData.getName());
        Glide.with(context)
                .load(aboutData.getDownload_url().equals("default")?R.drawable.group_icon:aboutData.getDownload_url())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imageView;
        TextView nameView, subjectView, aboutView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            nameView = itemView.findViewById(R.id.name_view);
            subjectView = itemView.findViewById(R.id.subject_view);
            aboutView = itemView.findViewById(R.id.about_view);
        }
    }
}
