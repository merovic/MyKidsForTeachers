package com.amirahmed.mykidsforteachers.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Adapters.StudentsReportsAdapter;
import com.amirahmed.mykidsforteachers.Models.StudentsReportsItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.util.ArrayList;
import java.util.List;


public class ReportsEvaluationFragment extends Fragment {

    private RecyclerView rv2;
    private List<StudentsReportsItem> studentsReportsItems;

    EditText searchbar;

    LinearLayout searchlayout;

    TextView text1,text2;

    TinyDB tinyDB;

    int language;

    StudentsReportsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reportevaluation, container, false);
        rv2 = view.findViewById(R.id.reports_recycler_view);

        rv2.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv2.setLayoutManager(llm);
        initializeAdapter();

        return view;
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


        searchbar = getActivity().findViewById(R.id.search3);

        tinyDB = new TinyDB(getContext());
        language = tinyDB.getInt("language");

        text1 = getActivity().findViewById(R.id.text5);
        text2 = getActivity().findViewById(R.id.text6);

        searchlayout = getActivity().findViewById(R.id.searchlayout3);


        if(language==1)
        {
            text1.setText("سلبى");
            text2.setText("ايجابى");

            searchlayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            searchbar.setHint("بحث");

        }else
        {
            text1.setText("Positive");
            text2.setText("Negative");

            searchlayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            searchbar.setHint("Search");

        }


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
