package com.codekiller.fcsaacademy.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codekiller.fcsaacademy.Adapters.CourseAdapter;
import com.codekiller.fcsaacademy.Datas.Courses;
import com.codekiller.fcsaacademy.R;


public class CourseFragment extends Fragment {

    Courses[] courses;
    RecyclerView recyclerView;
    CourseAdapter courseAdapter;
    Context context;

    public CourseFragment(Context context) {
        // Required empty public constructor
        courses = new Courses[8];
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courses[0] = new Courses(R.drawable.physics_gif, "Foundation Course For VI to X",
                "Timing : 5.00pm to 6.30pm Mon, Tue, Thu, Fri\n\n" +
                        "1. IIT/NEET Foundation class\n" +
                        "2. Preparing children for Olympiads and all competitive exams\n" +
                        "3. Weekly 8  hours of teaching\n" +
                        "4. Stress free education");
        courses[1] = new Courses(R.drawable.chemistry_gif, "One Year Medical ( OYM )",
                "Timing : 6.00pm to 9.00pm Mon, Tue, Wed, Thu\n\n" +
                        "1. XII regular(CBSE/Matric)+NEET Coaching\n" +
                        "2. For every two weeks parent teacher interaction\n" +
                        "3. Weekly 12  hours of teaching\n" +
                        "4. Every Wednesday NEET/IIT pattern exam");
        courses[2] = new Courses(R.drawable.biology_gif, "Two Year Medical ( TYM )",
                "Timing : 6.00pm to 9.00pm Mon, Tue, Wed, Thu\n\n" +
                        "1. Completing syllabus in time\n" +
                        "2. Daily Assessment test\n" +
                        "3. For every two weeks parent and teacher interaction\n" +
                        "4. Every Wednesday NEET/IIT pattern exam\n" +
                        "5. Weekly 12 hours of Teaching");
        courses[3] = new Courses(R.drawable.nuclear_gif2, "Weekend Batches( Saturday & Sunday )",
                "Saturday : 5.00pm to 9.00pm\n" +
                        "Sunday : 9.00am to 4.00 pm\n\n" +
                        "1. One year medical + Two year Medical\n" +
                        "2. 12 Hours of teaching\n" +
                        "3. Weekly Assessment and every Wednesday NEET/IIT pattern exam\n");
        courses[4] = new Courses(R.drawable.tablet, "Long Term NEET",
                "Timing : 10.00am to 5.00pm Mon, Tue, Thu, Fri\n\n" +
                        "1. Syllabus completion in time\n" +
                        "2. Conceptual Teaching\n" +
                        "3. Personal student care in academics and health\n" +
                        "4. For every two weeks parent teacher interaction\n" +
                        "5. only 20 students per class\n" +
                        "6.NEET/IIT exam on Wednesday and Sunday for 720 marks");
        courses[5] = new Courses(R.drawable.loading_gif, "JEE Main",
                "One year program, Two year program\n\n" +
                        "1. Syllabus completion in time\n" +
                        "2. Conceptual Teaching\n" +
                        "3. Personal student care in academics and health\n" +
                        "4. For every two weeks parent teacher interaction\n");
        courses[6] = new Courses(R.drawable.chemistry_gif, "JEE Advanced",
                "One year program, Two year program\n\n" +
                        "1. Syllabus completion in time\n" +
                        "2. Conceptual Teaching\n" +
                        "3. Personal student care in academics and health\n" +
                        "4. For every two weeks parent teacher interaction\n");
        courses[7] = new Courses(R.drawable.nuclear_gif2, "GATE Exam",
                "One year program, Two year program\n\n" +
                        "1. Syllabus completion in time\n" +
                        "2. Daily Assessment test\n" +
                        "3. Personal student care in academics and health\n" +
                        "4. For every two weeks parent teacher interaction\n");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Courses");
        View v = inflater.inflate(R.layout.fragment_course, container, false);
        recyclerView = v.findViewById(R.id.recycler_view);
        courseAdapter = new CourseAdapter(context, courses);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(courseAdapter);
        return v;
    }
}