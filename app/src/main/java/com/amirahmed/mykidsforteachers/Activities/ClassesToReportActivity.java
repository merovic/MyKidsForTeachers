package com.amirahmed.mykidsforteachers.Activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Adapters.ClassesToReportAdapter;
import com.amirahmed.mykidsforteachers.Models.ExamsMonthItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClassesToReportActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<ExamsMonthItem> examsMonthItems;

    private Toolbar mToolbar,mToolbar2;

    TinyDB tinyDB;

    int language;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classestoreport);

        tinyDB = new TinyDB(getApplicationContext());

        language = tinyDB.getInt("language");

        mToolbar = findViewById(R.id.toolbar_actionbar);
        mToolbar2 = findViewById(R.id.toolbar_actionbar_en);
        setSupportActionBar(mToolbar);
        setSupportActionBar(mToolbar2);

        ImageView arrow = mToolbar.findViewById(R.id.arrow);
        ImageView arrowen = mToolbar2.findViewById(R.id.arrowen);

        if(language==1)
        {
            mToolbar.setVisibility(View.VISIBLE);
            mToolbar2.setVisibility(View.GONE);

            mToolbar.setTitle("الفصول الدراسية");

            TextView textView = mToolbar.findViewById(R.id.toolbartext);
            textView.setText("الفصول الدراسية");

            getActionBarTextView().setText("الفصول الدراسية");

            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClassesToReportActivity.super.onBackPressed();
                }
            });

            getActionBarTextView().setVisibility(View.GONE);
        }else
        {
            mToolbar2.setVisibility(View.VISIBLE);
            mToolbar.setVisibility(View.GONE);

            mToolbar2.setTitle("Classes");

            TextView textView = mToolbar2.findViewById(R.id.toolbartext);
            textView.setText("Classes");

            getActionBarTextView().setText("Classes");

            arrowen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClassesToReportActivity.super.onBackPressed();
                }
            });


            getActionBarTextView().setVisibility(View.GONE);
        }


        mRecyclerView = findViewById(R.id.exams_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager mGridlayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(mGridlayoutManager);

        initializeData();
        initializeAdapter();



    }

    private TextView getActionBarTextView() {
        TextView titleTextView = null;

        try {
            if(language==1)
            {
                Field f = mToolbar.getClass().getDeclaredField("mTitleTextView");
                f.setAccessible(true);
                titleTextView = (TextView) f.get(mToolbar);
            }else
            {
                Field f = mToolbar2.getClass().getDeclaredField("mTitleTextView");
                f.setAccessible(true);
                titleTextView = (TextView) f.get(mToolbar2);
            }

        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
        return titleTextView;
    }


    private void initializeData() {

        examsMonthItems = new ArrayList<>();

        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","١ / ٢","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","١ / ٢","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","١ / ٢","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","١ / ٢","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","١ / ٢","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","١ / ٢","١-٣-٢٠١٨","اللغة العربية"));

    }

    private void initializeAdapter() {

        ClassesToReportAdapter adapter = new ClassesToReportAdapter(examsMonthItems);
        mRecyclerView.setAdapter(adapter);
    }


}
