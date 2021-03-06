package com.amirahmed.mykidsforteachers.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amirahmed.mykidsforteachers.Adapters.StudentsListAdapter2;
import com.amirahmed.mykidsforteachers.Models.StudentItem;
import com.amirahmed.mykidsforteachers.R;

import java.util.ArrayList;
import java.util.List;

public class EvaluationFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<StudentItem> studentItemList;

    StudentsListAdapter2 adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_studentlist, container, false);
        mRecyclerView = view.findViewById(R.id.students_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager mGridlayoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(mGridlayoutManager);


        initializeAdapter();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onResume()
    {
        super.onResume();
        initializeAdapter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeAdapter();
        initializeData();
    }

    private void initializeData() {

        studentItemList = new ArrayList<>();

        studentItemList.add(new StudentItem(R.drawable.kidround,"احمد ابراهيم مصطفى"));
        studentItemList.add(new StudentItem(R.drawable.kidround,"احمد ابراهيم مصطفى"));
        studentItemList.add(new StudentItem(R.drawable.kidround,"احمد ابراهيم مصطفى"));
        studentItemList.add(new StudentItem(R.drawable.kidround,"احمد ابراهيم مصطفى"));
        studentItemList.add(new StudentItem(R.drawable.kidround,"احمد ابراهيم مصطفى"));
        studentItemList.add(new StudentItem(R.drawable.kidround,"احمد ابراهيم مصطفى"));
        studentItemList.add(new StudentItem(R.drawable.kidround,"احمد ابراهيم مصطفى"));
        studentItemList.add(new StudentItem(R.drawable.kidround,"احمد ابراهيم مصطفى"));
        studentItemList.add(new StudentItem(R.drawable.kidround,"احمد ابراهيم مصطفى"));
        studentItemList.add(new StudentItem(R.drawable.kidround,"احمد ابراهيم مصطفى"));
        studentItemList.add(new StudentItem(R.drawable.kidround,"احمد ابراهيم مصطفى"));

    }

    private void initializeAdapter() {

        adapter = new StudentsListAdapter2(studentItemList);
        mRecyclerView.setAdapter(adapter);
    }
}
