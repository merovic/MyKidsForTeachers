package com.amirahmed.mykidsforteachers.Activities;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Adapters.StudentsReportsAdapter;
import com.amirahmed.mykidsforteachers.Models.StudentsReportsItem;
import com.amirahmed.mykidsforteachers.R;

import java.util.ArrayList;
import java.util.List;


public class StudentsReportsActivity extends Activity {

    private RecyclerView rv2;
    private List<StudentsReportsItem> studentsReportsItems;

    EditText searchbar;

    StudentsReportsAdapter adapter;

    RelativeLayout attendancetab,evaluationtab,examstab;

    TextView attendancetext,evaluationtext,examstext,text1,text2,text3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        searchbar = findViewById(R.id.search);

        attendancetab = findViewById(R.id.attendancereports);
        evaluationtab = findViewById(R.id.evaluationreports);
        examstab = findViewById(R.id.examsreports);

        attendancetab.setBackgroundColor(getResources().getColor(R.color.whitegray));
        evaluationtab.setBackgroundColor(getResources().getColor(R.color.white));
        examstab.setBackgroundColor(getResources().getColor(R.color.white));

        attendancetext = findViewById(R.id.attendancereportstext);
        evaluationtext = findViewById(R.id.evaluationreportstext);
        examstext = findViewById(R.id.examsreportstext);

        attendancetext.setTextColor(getResources().getColor(R.color.white));
        evaluationtext.setTextColor(getResources().getColor(R.color.whitegray));
        examstext.setTextColor(getResources().getColor(R.color.whitegray));

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);

        attendancetab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attendancetab.setBackgroundColor(getResources().getColor(R.color.whitegray));
                evaluationtab.setBackgroundColor(getResources().getColor(R.color.white));
                examstab.setBackgroundColor(getResources().getColor(R.color.white));

                attendancetext.setTextColor(getResources().getColor(R.color.white));
                evaluationtext.setTextColor(getResources().getColor(R.color.whitegray));
                examstext.setTextColor(getResources().getColor(R.color.whitegray));

                text1.setText("غياب");
                text2.setText("غياب بعزر");
                text3.setText("حضور");

            }
        });

        evaluationtab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attendancetab.setBackgroundColor(getResources().getColor(R.color.white));
                evaluationtab.setBackgroundColor(getResources().getColor(R.color.whitegray));
                examstab.setBackgroundColor(getResources().getColor(R.color.white));

                attendancetext.setTextColor(getResources().getColor(R.color.whitegray));
                evaluationtext.setTextColor(getResources().getColor(R.color.white));
                examstext.setTextColor(getResources().getColor(R.color.whitegray));

                text1.setText("ايجابى");
                text2.setText("سلبى");
                text3.setText("النسبة");

            }
        });

        examstab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attendancetab.setBackgroundColor(getResources().getColor(R.color.white));
                evaluationtab.setBackgroundColor(getResources().getColor(R.color.white));
                examstab.setBackgroundColor(getResources().getColor(R.color.whitegray));

                attendancetext.setTextColor(getResources().getColor(R.color.whitegray));
                evaluationtext.setTextColor(getResources().getColor(R.color.whitegray));
                examstext.setTextColor(getResources().getColor(R.color.white));

                text1.setText("النتيجة");
                text2.setText("الدرجة");
                text3.setText("النسبة");

            }
        });

        rv2 = findViewById(R.id.rv2);

        rv2.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv2.setLayoutManager(llm);

        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable.toString());
                
            }
        });

        initializeData();
        initializeAdapter();
    }

    private void filter(String text) {

        ArrayList<StudentsReportsItem> filterdNames = new ArrayList<>();

        for (StudentsReportsItem s : studentsReportsItems) {

            if(s.getStudentName().contains(text.toLowerCase()))
            {
                filterdNames.add(s);
            }
        }
        adapter.filterList(filterdNames);

    }

    private void initializeAdapter() {

        adapter = new StudentsReportsAdapter(studentsReportsItems);
        rv2.setAdapter(adapter);
    }

    private void initializeData() {
        studentsReportsItems = new ArrayList<>();
        studentsReportsItems.add(new StudentsReportsItem("عمر هشام","0","0"));
        studentsReportsItems.add(new StudentsReportsItem("عمر هشام","0","0"));
        studentsReportsItems.add(new StudentsReportsItem("عمر هشام","0","0"));
        studentsReportsItems.add(new StudentsReportsItem("امير هشام","0","0"));
        studentsReportsItems.add(new StudentsReportsItem("عمر هشام","0","0"));
        studentsReportsItems.add(new StudentsReportsItem("عمر هشام","0","0"));
        studentsReportsItems.add(new StudentsReportsItem("عمر هشام","0","0"));
        studentsReportsItems.add(new StudentsReportsItem("عمر هشام","0","0"));
        studentsReportsItems.add(new StudentsReportsItem("عمر هشام","0","0"));
    }


}
