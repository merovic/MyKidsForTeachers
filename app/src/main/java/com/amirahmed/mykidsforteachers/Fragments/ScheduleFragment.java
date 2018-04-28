package com.amirahmed.mykidsforteachers.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amirahmed.mykidsforteachers.Adapters.ClassScheduleAdapter;
import com.amirahmed.mykidsforteachers.Models.ClassScheduleItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends Fragment {

    private RecyclerView mRecyclerView3;
    private List<ClassScheduleItem> classScheduleItems;

    TinyDB tinyDB;

    int language;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        tinyDB = new TinyDB(getContext());

        language = tinyDB.getInt("language");

        mRecyclerView3 = view.findViewById(R.id.schedule_recycler_view);

        mRecyclerView3.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRecyclerView3.setLayoutManager(llm);

        initializeAdapter3();
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
        initializeAdapter3();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeData3();
        initializeAdapter3();

    }

    private void initializeData3() {

        classScheduleItems = new ArrayList<>();

        if(language==1)
        {
            classScheduleItems.add(new ClassScheduleItem("لغة عربية","الحصة الاولى","الاربعاء","١-١-٢٠١٨"));
            classScheduleItems.add(new ClassScheduleItem("لغة عربية","الحصة الاولى","الاربعاء","١-١-٢٠١٨"));
            classScheduleItems.add(new ClassScheduleItem("لغة عربية","الحصة الاولى","الاربعاء","١-١-٢٠١٨"));
            classScheduleItems.add(new ClassScheduleItem("لغة عربية","الحصة الاولى","الاربعاء","١-١-٢٠١٨"));
            classScheduleItems.add(new ClassScheduleItem("لغة عربية","الحصة الاولى","الاربعاء","١-١-٢٠١٨"));
            classScheduleItems.add(new ClassScheduleItem("لغة عربية","الحصة الاولى","الاربعاء","١-١-٢٠١٨"));
            classScheduleItems.add(new ClassScheduleItem("لغة عربية","الحصة الاولى","الاربعاء","١-١-٢٠١٨"));
            classScheduleItems.add(new ClassScheduleItem("لغة عربية","الحصة الاولى","الاربعاء","١-١-٢٠١٨"));
            classScheduleItems.add(new ClassScheduleItem("لغة عربية","الحصة الاولى","الاربعاء","١-١-٢٠١٨"));
            classScheduleItems.add(new ClassScheduleItem("لغة عربية","الحصة الاولى","الاربعاء","١-١-٢٠١٨"));
        }else
            {
                classScheduleItems.add(new ClassScheduleItem("Arabic","First Class","Wednesday","1-1-2018"));
                classScheduleItems.add(new ClassScheduleItem("Arabic","First Class","Wednesday","1-1-2018"));
                classScheduleItems.add(new ClassScheduleItem("Arabic","First Class","Wednesday","1-1-2018"));
                classScheduleItems.add(new ClassScheduleItem("Arabic","First Class","Wednesday","1-1-2018"));
                classScheduleItems.add(new ClassScheduleItem("Arabic","First Class","Wednesday","1-1-2018"));
                classScheduleItems.add(new ClassScheduleItem("Arabic","First Class","Wednesday","1-1-2018"));
                classScheduleItems.add(new ClassScheduleItem("Arabic","First Class","Wednesday","1-1-2018"));
                classScheduleItems.add(new ClassScheduleItem("Arabic","First Class","Wednesday","1-1-2018"));
                classScheduleItems.add(new ClassScheduleItem("Arabic","First Class","Wednesday","1-1-2018"));
                classScheduleItems.add(new ClassScheduleItem("Arabic","First Class","Wednesday","1-1-2018"));
            }





    }

    private void initializeAdapter3() {

        ClassScheduleAdapter adapter = new ClassScheduleAdapter(classScheduleItems);
        mRecyclerView3.setAdapter(adapter);
    }


}
