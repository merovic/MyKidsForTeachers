package com.amirahmed.mykidsforteachers.Activities;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.amirahmed.mykidsforteachers.Adapters.StudentsListAdapter;
import com.amirahmed.mykidsforteachers.Models.StudentItem;
import com.amirahmed.mykidsforteachers.R;

import java.util.ArrayList;
import java.util.List;

public class StudentListActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<StudentItem> studentItemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlist);

        mRecyclerView = findViewById(R.id.students_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager mGridlayoutManager = new GridLayoutManager(getApplicationContext(),3);
        mRecyclerView.setLayoutManager(mGridlayoutManager);

        initializeData();
        initializeAdapter();

    }


    private void initializeData() {

        studentItemList = new ArrayList<>();

        studentItemList.add(new StudentItem(R.drawable.picperson,"احمد ابراهيم"));
        studentItemList.add(new StudentItem(R.drawable.picperson,"احمد ابراهيم"));
        studentItemList.add(new StudentItem(R.drawable.picperson,"احمد ابراهيم"));
        studentItemList.add(new StudentItem(R.drawable.picperson,"احمد ابراهيم"));
        studentItemList.add(new StudentItem(R.drawable.picperson,"احمد ابراهيم"));
        studentItemList.add(new StudentItem(R.drawable.picperson,"احمد ابراهيم"));
        studentItemList.add(new StudentItem(R.drawable.picperson,"احمد ابراهيم"));
        studentItemList.add(new StudentItem(R.drawable.picperson,"احمد ابراهيم"));
        studentItemList.add(new StudentItem(R.drawable.picperson,"احمد ابراهيم"));
        studentItemList.add(new StudentItem(R.drawable.picperson,"احمد ابراهيم"));
        studentItemList.add(new StudentItem(R.drawable.picperson,"احمد ابراهيم"));
        studentItemList.add(new StudentItem(R.drawable.picperson,"احمد ابراهيم"));


    }

    private void initializeAdapter() {

        StudentsListAdapter adapter = new StudentsListAdapter(studentItemList);
        mRecyclerView.setAdapter(adapter);
    }

}
