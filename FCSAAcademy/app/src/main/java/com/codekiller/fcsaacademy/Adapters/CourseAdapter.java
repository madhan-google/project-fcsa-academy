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
import com.codekiller.fcsaacademy.Datas.Courses;
import com.codekiller.fcsaacademy.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    Context context;
    Courses[] courses;

    public CourseAdapter(Context context, Courses[] courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.course_adapter_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(courses[position].getImage()).into(holder.imageView);
        holder.titleView.setText(courses[position].getTitle());
        holder.textView.setText(courses[position].getText());
    }

    @Override
    public int getItemCount() {
        return courses.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView titleView, textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.logo);
            titleView = itemView.findViewById(R.id.title_view);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
