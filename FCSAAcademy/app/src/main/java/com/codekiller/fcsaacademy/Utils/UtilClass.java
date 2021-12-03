package com.codekiller.fcsaacademy.Utils;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.codekiller.fcsaacademy.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static maes.tech.intentanim.CustomIntent.customType;

public class UtilClass {

    Context context;
    ProgressDialog progressDialog;
    NotificationManager notificationManager;
    NotificationCompat.Builder builder;

    public UtilClass(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void animate(Techniques tech, View v){
        YoYo.with(tech).duration(800).playOn(v);
    }
    public void intentAnimate(String dir){
        customType(context, dir);
    }

    public void showProgress(String title, String message){
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }
    public void dismissProgress(){
        progressDialog.dismiss();
    }
    public void toast(String s){
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
    public String getDate(){
        return new SimpleDateFormat("dd-mm-yyyy").format(new Date());
    }
    public String getTime(){
        return new SimpleDateFormat("hh:mm:ss").format(new Date());
    }
    public String showDatePicker(){
        Calendar cal = Calendar.getInstance();
        final String[] date = new String[1];
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date[0] = dayOfMonth+" - "+(month+1)+" - "+year;
            }
        },cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        return date[0];
    }
    public void notifyMessage(String title, String message){
        builder = new NotificationCompat.Builder(context)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.download)
                .setContentTitle(title)
                .setContentText(message);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("notify", "Notification", NotificationManager.IMPORTANCE_HIGH);
            builder.setChannelId("notify");
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0, builder.build());
    }
    public void delNotification(){
        notificationManager.cancel(0);
    }
}
