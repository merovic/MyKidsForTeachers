package com.amirahmed.mykidsforteachers.Activities;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Adapters.ExamsMonthAdapter;
import com.amirahmed.mykidsforteachers.Models.ExamsMonthItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

public class ExamsActivity extends Activity {

    RelativeLayout selection,create,result;

    LinearLayout examcreatelayout;

    TextView createtext,resulttext;

    private RecyclerView mRecyclerView;
    private List<ExamsMonthItem> examsMonthItems;

    TinyDB tinyDB;

    String state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        tinyDB = new TinyDB(getApplicationContext());
        state = tinyDB.getString("state");

        selection = findViewById(R.id.monthselection);

        examcreatelayout = findViewById(R.id.examcreatelayout);

        create = findViewById(R.id.createexam);
        result = findViewById(R.id.examresults);

        createtext = findViewById(R.id.createtext);
        resulttext = findViewById(R.id.resulttext);

        mRecyclerView = findViewById(R.id.exams_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager mGridlayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(mGridlayoutManager);

        initializeData();
        initializeAdapter();

        if(!state.equals("exams"))
        {
            selection.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            examcreatelayout.setVisibility(View.GONE);
        }

        create.setBackgroundColor(getResources().getColor(R.color.gray));
        createtext.setTextColor(getResources().getColor(R.color.white));

        result.setBackgroundColor(getResources().getColor(R.color.white));
        resulttext.setTextColor(getResources().getColor(R.color.black_p50));


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                create.setBackgroundColor(getResources().getColor(R.color.gray));
                createtext.setTextColor(getResources().getColor(R.color.white));

                result.setBackgroundColor(getResources().getColor(R.color.white));
                resulttext.setTextColor(getResources().getColor(R.color.black_p50));

                examcreatelayout.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);

            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                create.setBackgroundColor(getResources().getColor(R.color.white));
                createtext.setTextColor(getResources().getColor(R.color.black_p50));

                result.setBackgroundColor(getResources().getColor(R.color.gray));
                resulttext.setTextColor(getResources().getColor(R.color.white));

                examcreatelayout.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

            }
        });


    }


    private void initializeData() {

        examsMonthItems = new ArrayList<>();

        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","الفصل ١/٣","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","الفصل ١/٣","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","الفصل ١/٣","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","الفصل ١/٣","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","الفصل ١/٣","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","الفصل ١/٣","١-٣-٢٠١٨","اللغة العربية"));

    }

    private void initializeAdapter() {

        ExamsMonthAdapter adapter = new ExamsMonthAdapter(examsMonthItems);
        mRecyclerView.setAdapter(adapter);
    }


}
