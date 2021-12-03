package com.codekiller.fcsaacademy.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codekiller.fcsaacademy.Datas.UserField;
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

import java.util.Calendar;

public class RegistrationFragment extends Fragment {

    Context context;
    Spinner classText, boardText, stateText, optingCourseText, testText;
    MaterialButton registerBtn;
    TextInputLayout nameText, fatherNameText, motherNameText, mailText, collegeText, townText, numberText, districtText;
    TextView dobText;
    ImageView imageView;

    String name, fatherName, motherName, state, clas, board, optingcourse, dob, mail, college, town, number, district, test;

    DatabaseReference databaseReference;
    FButils fButils;
    UtilClass utilClass;
    UserField userField;

    String[] states, classes, boards, optingCourses, tests;
    ArrayAdapter<String> stateAdapter, classAdapter, boardAdapter, optingCourseAdapter, testAdapter;

    public RegistrationFragment(Context context) {
        // Required empty public constructor
        this.context = context;
        fButils = new FButils();
        utilClass = new UtilClass(context);
        userField = new UserField();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        classes = new String[]{
                "Class*",
                "8th Standard",
                "9th Standard",
                "10th Standard",
                "11th Studying",
                "12th Studying",
                "12th Passed"
        };
        boards = new String[]{
                "Board*",
                "State Board",
                "CBSE",
                "ICSE/ISC",
                "Cambridge",
                "IB",
                "Others"
        };
        optingCourses = new String[]{
                "Opting Courses*",
                "8th Foundation",
                "9th Foundation",
                "10th Foundation",
                "One year medical",
                "One year IIT",
                "Two year Medical",
                "Two year IIT",
                "Long term or repeater NEET",
                "JEE Main",
                "JEE Advanced",
                "GATE Exam"
        };
        states = new String[]{
                "State*",
                "Andhra Pradesh",
                "Arunachal Pradesh",
                "Assam",
                "Bihar",
                "Chattigarh",
                "Delhi",
                "Goa",
                "Gujarat",
                "Haryana",
                "Himachal Pradesh",
                "Jharkhand",
                "Karnataka",
                "Kerala",
                "Madhya Pradesh",
                "Maharastra",
                "Manipur",
                "Meghalaya",
                "Mizoram",
                "Nagaland",
                "Odisha",
                "Punjab",
                "Sikkim",
                "Tamil Nadu",
                "Telangana",
                "Tripura",
                "Uttar Pradesh",
                "Uttarakhand",
                "West Bengal",
                "Andaman and Nicobar",
                "Chandigarh",
                "Dadra & Nagar Haveli and Daman & Diu",
                "Jammu and Kashmir",
                "Lakshadeep",
                "Puducherry",
                "Ladakh"
        };
        tests = new String[]{
                "Test Timing*",
                "Weekly Test",
                "Daily Test"
        };

        classAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, classes);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boardAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, boards);
        boardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optingCourseAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, optingCourses);
        optingCourseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, states);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        testAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, tests);
        testAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Registration");
        View v = inflater.inflate(R.layout.fragment_registration, container, false);
        nameText = v.findViewById(R.id.name_text);
        fatherNameText = v.findViewById(R.id.father_name_text);
        motherNameText = v.findViewById(R.id.mother_name_text);
        dobText = v.findViewById(R.id.dob_text);
        mailText = v.findViewById(R.id.mail_text);
        numberText = v.findViewById(R.id.number_text);
        classText = v.findViewById(R.id.class_text);
        collegeText = v.findViewById(R.id.college_text);
        boardText = v.findViewById(R.id.board_text);
        townText = v.findViewById(R.id.town_text);
        districtText = v.findViewById(R.id.district_text);
        stateText = v.findViewById(R.id.state_text);
        optingCourseText = v.findViewById(R.id.opting_course_text);
        registerBtn = v.findViewById(R.id.register_btn);
        testText = v.findViewById(R.id.test_text);
        imageView = v.findViewById(R.id.image_view);

        classText.setAdapter(classAdapter);
        boardText.setAdapter(boardAdapter);
        stateText.setAdapter(stateAdapter);
        optingCourseText.setAdapter(optingCourseAdapter);
        testText.setAdapter(testAdapter);

        Glide.with(context)
                .load(R.drawable.writing)
                .into(imageView);

        classText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    clas = classes[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        boardText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    board = boards[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        stateText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    state = states[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        optingCourseText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    optingcourse = optingCourses[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        testText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    test = tests[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dobText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        dob = dayOfMonth + "/" + month + "/" + year;
                        dobText.setText(dob);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameText.getEditText().getText().toString().trim();
                fatherName = fatherNameText.getEditText().getText().toString().trim();
                motherName = motherNameText.getEditText().getText().toString().trim();
                mail = mailText.getEditText().getText().toString().trim();
                number = numberText.getEditText().getText().toString().trim();
                town = townText.getEditText().getText().toString().trim();
                college = collegeText.getEditText().getText().toString().trim();
                district = districtText.getEditText().getText().toString().trim();
                if (len(clas) != 0 && len(board) != 0 && len(state) != 0 && len(optingcourse) != 0 &&
                        len(dob) != 0 && len(name) != 0 && len(fatherName) != 0 && len(motherName) != 0 &&
                        len(mail) != 0 && len(number) != 0 && len(town) != 0 && len(college) != 0 && len(district) != 0) {
                    utilClass.animate(Techniques.RubberBand, registerBtn);
                    utilClass.showProgress("", "Registering");
                    databaseReference = fButils.getUserDB();
                    String push_key = databaseReference.push().getKey();
                    userField.setName(name);
                    userField.setYour_class(clas);
                    userField.setBoard(board);
                    userField.setState(state);
                    userField.setOpting_course(optingcourse);
                    userField.setDob(dob);
                    userField.setPh_no(number);
                    userField.setFather_name(fatherName);
                    userField.setMother_name(motherName);
                    userField.setTown(town);
                    userField.setCollege_name(college);
                    userField.setDistrict(district);
                    userField.setMail_id(mail);
                    userField.setTest("");
                    userField.setSeen(false);
                    userField.setFavorite(false);
                    userField.setPushKey(push_key);
                    userField.setFavoritePushkey("");
                    userField.setDate_time(utilClass.getDate() + "|" + utilClass.getTime());
                    databaseReference.child(push_key).setValue(userField)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    utilClass.dismissProgress();
                                    utilClass.toast("registration successfully completed");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    utilClass.dismissProgress();
                                    utilClass.toast("registration failed");
                                }
                            });

                } else {
                    utilClass.animate(Techniques.Wobble, registerBtn);
                    utilClass.toast("fill all the fields");
                }

            }
        });

        return v;
    }

    public int len(String s) {
        return s != null ? s.length() : 0;
    }
}