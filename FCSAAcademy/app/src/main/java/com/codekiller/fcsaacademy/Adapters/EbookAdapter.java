package com.codekiller.fcsaacademy.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.codekiller.fcsaacademy.Datas.EBookData;
import com.codekiller.fcsaacademy.Firebase.FButils;
import com.codekiller.fcsaacademy.R;
import com.codekiller.fcsaacademy.Utils.UtilClass;
import com.daimajia.androidanimations.library.Techniques;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.ViewHolder> {
    Context context;
    ArrayList<EBookData> arrayList;
    UtilClass utilClass;
    StorageReference storageReference;

    public EbookAdapter(Context context, ArrayList<EBookData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        utilClass = new UtilClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ebook_adapter_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EBookData eBookData = arrayList.get(position);
        holder.textView.setText(eBookData.getFilename());
        holder.downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilClass.animate(Techniques.RubberBand, holder.downloadBtn);
                if(ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) context, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },100);
                    utilClass.toast("click download button");
                }else {
                    utilClass.toast("Downloading");
                    utilClass.notifyMessage("Downloading", "");
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/FCSA Academy Notes");
                    if( !file.exists() ){
                        file.mkdirs();
                    }
                    File dir = new File(file, eBookData.getFilename());
                    if (!dir.exists()) {
                        try {
                            dir.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(eBookData.getDownloadUrl());
                    storageReference.getFile(dir)
                            .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                    utilClass.delNotification();
                                    utilClass.toast("Download complete\n" + dir);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    utilClass.toast("download failed");
                                    utilClass.delNotification();
                                }
                            });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView downloadBtn;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            downloadBtn = itemView.findViewById(R.id.download_btn);
            textView = itemView.findViewById(R.id.pdf_name);
        }
    }
}
