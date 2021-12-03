package com.codekiller.fcsaacademy.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codekiller.fcsaacademy.Datas.QueryData;
import com.codekiller.fcsaacademy.Firebase.FButils;
import com.codekiller.fcsaacademy.R;
import com.codekiller.fcsaacademy.Utils.UtilClass;
import com.daimajia.androidanimations.library.Techniques;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;

/*
insta link : https://instagram.com/firstchoicestudentsacademy?utm_medium=copy_link
facebook link : https://m.facebook.com/Pernambutcoachingacademy/?__tn__=C-R
youtube link : https://youtube.com/channel/UCUDBCR2R7M3TW4jisHX8VnQ
 */

public class ContactFragment extends Fragment {

    TextInputLayout nameText, queryText, mailText;
    MaterialButton sendBtn;
    ImageView fbBtn, instaBtn, youtubeBtn, locationBtn, imageView;

    UtilClass utilClass;
    DatabaseReference databaseReference;
    QueryData queryData;
    FButils fButils;
    Context context;

    public ContactFragment(Context context) {
        // Required empty public constructor
        this.context = context;
        utilClass = new UtilClass(context);
        fButils = new FButils();
        queryData = new QueryData();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Contact");
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        nameText = v.findViewById(R.id.name_text);
        queryText = v.findViewById(R.id.query_text);
        sendBtn = v.findViewById(R.id.send_btn);
        mailText = v.findViewById(R.id.mail_text);
        fbBtn = v.findViewById(R.id.fb_btn);
        instaBtn = v.findViewById(R.id.insta_btn);
        youtubeBtn = v.findViewById(R.id.youtube_btn);
        locationBtn = v.findViewById(R.id.location_btn);
        imageView = v.findViewById(R.id.image_view);
        Glide.with(context)
                .load(R.drawable.mail_gif)
                .into(imageView);
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?cid=11865238759431288686")));
            }
        });
        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/Pernambutcoachingacademy/?tn=C-R")));
            }
        });
        instaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/firstchoicestudentsacademy?utm_medium=copy_link")));
            }
        });
        youtubeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/channel/UCUDBCR2R7M3TW4jisHX8VnQ")));
            }
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getEditText().getText().toString().trim();
                String query = queryText.getEditText().getText().toString().trim();
                if (name.length() != 0 && mailText.getEditText().getText().toString().trim().length() != 0 && query.length() != 0) {
                    utilClass.animate(Techniques.RubberBand, sendBtn);
                    utilClass.showProgress("", "Sending");
                    databaseReference = fButils.getQueryDB();
                    String pushKey = databaseReference.push().getKey();
                    queryData.setName(name);
                    queryData.setQuery(query);
                    queryData.setPushkey(pushKey);
                    queryData.setSeen(false);
                    queryData.setMailId(mailText.getEditText().getText().toString().trim());
                    databaseReference.child(pushKey).setValue(queryData)
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    utilClass.toast("query failed");
                                    utilClass.dismissProgress();
                                }
                            })
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    utilClass.toast("query sent successfully");
                                    utilClass.dismissProgress();
                                }
                            });
                } else {
                    utilClass.animate(Techniques.Wobble, sendBtn);
                    utilClass.toast("fill all the fields");
                }
            }
        });
        return v;
    }
}