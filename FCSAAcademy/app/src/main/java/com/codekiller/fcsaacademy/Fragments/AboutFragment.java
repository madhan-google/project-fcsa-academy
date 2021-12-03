package com.codekiller.fcsaacademy.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codekiller.fcsaacademy.Adapters.AboutAdapter;
import com.codekiller.fcsaacademy.Datas.AboutData;
import com.codekiller.fcsaacademy.Firebase.FButils;
import com.codekiller.fcsaacademy.R;
import com.codekiller.fcsaacademy.Utils.UtilClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AboutFragment extends Fragment {
    public static String TAG = "ABOUT FRAGMENT";
    public static final int CODE_REQUEST = 1001;
    public static Uri imageUri = null;

    String str;
    FButils fButils;
    DatabaseReference adminDB;
    UtilClass utilClass;
    Context context;
    RecyclerView recyclerView;
    AboutAdapter aboutAdapter;
    ArrayList<AboutData> arrayList;
    TextView textView;

    HashMap<String, String> map;

    public AboutFragment(Context context) {
        // Required empty public constructor
        this.context = context;
        fButils = new FButils();
        adminDB = fButils.getAdminDatabase();
        utilClass = new UtilClass(context);
        arrayList = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("About Us");
        View v =  inflater.inflate(R.layout.fragment_about, container, false);
        recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        textView = v.findViewById(R.id.text_view);
        map = new HashMap<>();
        utilClass.showProgress("","Loading");
        adminDB.child("About_Us").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for( DataSnapshot ds : snapshot.getChildren() ){
                    arrayList.add(ds.getValue(AboutData.class));
                }
                aboutAdapter = new AboutAdapter(context, arrayList);
                recyclerView.setAdapter(aboutAdapter);
                utilClass.dismissProgress();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                utilClass.dismissProgress();
                utilClass.toast("something went wrong");
            }
        });

        adminDB.child("Header")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for( DataSnapshot ds : snapshot.getChildren() ){
                            map = (HashMap<String, String>) ds.getValue();
                        }
                        textView.setText(map.get("text"));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return v;
    }

}








/*
str = "We are the Pioneers in the Professional Entrance Exams.<br>We help to turn our Students dreams into reality.<br>" +
        "Our Educators are the foundations for our success stories of students.<br>" +
        "An overall experience of 20+ years experience in producing Doctors & IITians by our Expertise faculties..<br><br>" +
        "<b><u>CORE TEAM:</u></b><br>" +
        "This is our Core team of Academicians who will do everything to achieve what a Student have<br>" +
        "dreamt for. We take this opportunity to present to all of you Our Dream Team of Faculties…..<br><hr><br><br>" +
        "<b><u>1. ACADEMIC MENTOR</u></b><br>" +
        "<b><u>Mrs.Ayesha Jaweed</u></b><br>" +
        "Correspondent<br>" +
        "Green Valley Schools, Matric & CBSE<br>" +
        "Pernambut.<br>" +
        "She is an inspiration for all of us. FCSA is a Brain child of her. Always worked for the upliment of<br>" +
        "all sectors of Students in Education. Her Vision is to share her knowledge and wisdom with<br>" +
        "students for their success.<br><hr><br><br>" +
        "<b><u>2. Dr. Rajalakshmi</u></b><br>" +
        "<b><u>Professor in ZOOLOGY</u></b><br>" +
        "She herself is an inspiration for all our students, with 25+ years of Experience in given amazing<br>" +
        "results for Class 12. She has worked in various institutions and have many achievements to her<br>" +
        "credit. She has also made her students to achieve incredible heights in their careers .<br><hr><br><br>" +
        "<b><u>3. ACADEMIC INCHARGE-NEET</u></b><br>" +
        "<b><u>BIOLOGY</u></b><br>" +
        "<b><u>Mr.R.Khadir Khan</u></b><br>" +
        "He is an experience Academician for over 18+ years by transforming many NEET aspirants into<br>" +
        "Doctors. He has worked with many reputed institutions in his career. Presently he is an DSE with<br>" +
        "UNACADEMY as well. He is also authoring an NEET Biology book. His vision is to make each and<br>" +
        "every student of 12" +
        "th to crack Competitive exams with impeccable scores.<br><hr><br><br>" +
        "<b><u>4. Asst Professor of BOTANY</u></b><br>" +
        "<b><u>Mr.Kota Vijayakumar</u></b><br>" +
        "He has a vast experience of 12+ years in Botany. He has worked in Top Institutions across India. He is from Bangalore. His vision is to make more students to crack NEET with exceptional result.<br><hr><br><br>" +
        "<b><u>5. Asst professor of ZOOLOGY</u></b><br>" +
        "<b><u>Mr.B.V.N.V.Prasad</u></b><br>" +
        "He has 15+ years of experience as Educator in Zoology. Have worked with various Institutions. One of the Best subject experts in Zoology. He has produced many Doctors in his career.<br><hr><br><br>" +
        "<b><u>6. ZOOLOGY Academician</u></b><br>" +
        "<b><u>Ms.Ramya Venkatesan</u></b><br>" +
        "She has 6+years as an Educator with a Zeal to learn. Result oriented educator, wants to transform<br>" +
        "the lives of students through Education.<br><hr><br><br>" +
        "<b><u>7. Asst Professor of PHYSICS</u></b><br>" +
        "<b><u>Mr. Shaikh Mukhter</u></b><br>" +
        "He is an Amazing personality with 13+ years of experience in producing Doctors & IITians in his<br>" +
        "career. Excellent academic track record and experience in bringing out the hidden topper in every<br>" +
        "student. He is from Kadapa.<br><hr><br><br>" +
        "<b><u>8. IIT-JEE Academician-PHYSICS</u></b><br>" +
        "<b><u>Mr. Bhavik Barot</u></b><br>" +
        "At very young age he has more produced many IITians & NITians. He is also very good motivator. He has worked in reputed institutes.<br><hr><br><br>" +
        "<b><u>9. Asst Professor of CHEMISTRY</u></b><br>" +
        "<b><u>Mrs.Lakshmi</u></b><br>" +
        "Having 15+ years of experience in producing exceptional results. She has worked in reputed<br>" +
        "Institutions as well. Her method of implementing Concepts is amazing and treat to watch.<br><hr><br><br>" +
        "<b><u>10. Asst Professor of CHEMISTRY</u></b><br>" +
        "<b><u>Mr.Savanapally Bharat</u></b><br>" +
        "He is excellent Academician and role model for students from his amazing teaching skills. He has 5+ years of experience working with Sri Chaitanya groups & Narayana groups. Result oriented Academician concentrating even on chore skill development of students.<br><hr><br><br>" +
        "<b><u>11. NEET Academic Co-ordinator</u></b><br>" +
        "<b><u>CHEMISTRY</u></b><br>" +
        "<b><u>Ms. Lavanya</u></b><br>" +
        "Talented Academician with lot of passion towards teaching and making students to achieve<br>" +
        "impossible with her Innovave Approach in teaching using advance Technology.<br><hr><br><br>" +
        "<b><u>12. Asst Professor of CHEMISTRY</u></b><br>" +
        "<b><u>Mr.S.Suresh Babu</u></b><br>" +
        "His passion towards teaching is unparalled with 20+ years of experience. Transformed many<br>" +
        "students into IITians/Doctors.<br><hr><br><br>" +
        "<b><u>13. Asst professor of MATHS</u></b><br>" +
        "<b><u>Mrs.Elavarasi.D</u></b><br>" +
        "Stupendous achievements as Educator with a career spanning over 15+ years. Transformed the<br>" +
        "life of ‘n’ number of students to perform excellently in their Competitive exams" +
        ".";*/
