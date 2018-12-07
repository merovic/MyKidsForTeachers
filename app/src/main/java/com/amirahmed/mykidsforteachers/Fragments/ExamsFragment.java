package com.amirahmed.mykidsforteachers.Fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amirahmed.mykidsforteachers.Adapters.ExamsResultsAdapter;
import com.amirahmed.mykidsforteachers.Models.ExamsMonthItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ExamsFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private RecyclerView mRecyclerView;
    private List<ExamsMonthItem> examsItemList;

    TinyDB tinyDB;

    int language;

    Spinner subjectsSpinner;

    String selectedSubject,datee;

    List<String> subjects = new ArrayList<>();

    ArrayAdapter<String> adapter;
    HighLightArrayAdapter adapter2;

    Calendar myCalendar;

    LinearLayout newexam,examresults,newexambutton,examresultsbutton;

    LinearLayout examnamelayout,examdatelayout,minlayout,maxlayout,detailslayout,tabs,minmaxlayout;

    TextView newexamtext,examresultstext,contenthint;

    Button create;

    TextView subjectdate,min,max;

    TextInputLayout subjectdatetext,mintext,maxtext;

    EditText examcontent;

    ImageView newexampic,examresultspic;

    View newexamview,examresultsview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exams, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tinyDB = new TinyDB(getContext());

        language = tinyDB.getInt("language");

        examnamelayout = getActivity().findViewById(R.id.subjectnamelayout);
        examdatelayout = getActivity().findViewById(R.id.subjectdatelayout);
        maxlayout = getActivity().findViewById(R.id.fullmarklayout);
        minlayout = getActivity().findViewById(R.id.passscorelayout);
        detailslayout = getActivity().findViewById(R.id.examcontentlayout);
        tabs = getActivity().findViewById(R.id.tabss);
        minmaxlayout = getActivity().findViewById(R.id.minmaxlayout);


        subjectdate = getActivity().findViewById(R.id.subjectdate);
        min = getActivity().findViewById(R.id.finalscore);
        max = getActivity().findViewById(R.id.passscore);


        subjectdatetext = getActivity().findViewById(R.id.subjectdatetext);
        mintext = getActivity().findViewById(R.id.finalscoretext);
        maxtext = getActivity().findViewById(R.id.passscoretext);

        examcontent = getActivity().findViewById(R.id.examcontentedit);

        create = getActivity().findViewById(R.id.createbutton);

        newexam = getActivity().findViewById(R.id.newexamlayout);
        examresults = getActivity().findViewById(R.id.resultslayout);

        newexambutton = getActivity().findViewById(R.id.newexambutton);
        examresultsbutton = getActivity().findViewById(R.id.examresultsbutton);

        newexamtext = getActivity().findViewById(R.id.newexamtext);
        examresultstext = getActivity().findViewById(R.id.resultstext);
        contenthint = getActivity().findViewById(R.id.examcontenthint);

        newexampic = getActivity().findViewById(R.id.newexampic);
        examresultspic = getActivity().findViewById(R.id.resultspic);

        newexamview = getActivity().findViewById(R.id.newexamview);
        examresultsview = getActivity().findViewById(R.id.resultsview);

        subjectsSpinner = getActivity().findViewById(R.id.subjects);

        if(language==1)
        {
            tabs.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            examresultstext.setText("نتائج الاختبارات");
            newexamtext.setText("اختبار جديد");

            examnamelayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            examdatelayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            maxlayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            minlayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            minmaxlayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            detailslayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


            subjectdatetext.setHint("تاريخ الاختبار *");
            subjectdate.setGravity(Gravity.CENTER);

            mintext.setHint("الدرجة النهائية");
            min.setGravity(Gravity.RIGHT);

            maxtext.setHint("درجة النجاح");
            max.setGravity(Gravity.RIGHT);

            contenthint.setText("طلبات الاختبار *");

            create.setText("انشاء الاختبار");

            subjects.add("اختيار المادة *");
            subjects.add("لغة عربية");
            subjects.add("تربية دينية");
            subjects.add("خط عربى");

        }else
            {
                tabs.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                examresultstext.setText("Exam Results");
                newexamtext.setText("New Exam");

                examnamelayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                examdatelayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                maxlayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                minlayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                minmaxlayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                detailslayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);



                subjectdatetext.setHint("Subject Date *");
                subjectdate.setGravity(Gravity.CENTER);

                mintext.setHint("Final Score");
                min.setGravity(Gravity.LEFT);

                maxtext.setHint("Pass Score");
                max.setGravity(Gravity.LEFT);

                contenthint.setText("Exam Requirements *");

                create.setText("Create Exam");

                subjects.add("Select Subject *");
                subjects.add("Arabic");
                subjects.add("Relation");
                subjects.add("Arabic Font");

            }

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, subjects);
        adapter2 = new HighLightArrayAdapter(getContext(), R.layout.item_spinner, subjects);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectsSpinner.setAdapter(adapter2);
        subjectsSpinner.setOnItemSelectedListener(this);

        adapter.notifyDataSetChanged();


        newexambutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(newexam.getVisibility()==View.GONE)
                {
                    newexam.setVisibility(View.VISIBLE);
                    newexamtext.setTextColor(getResources().getColor(R.color.colorAccent));
                    newexampic.setImageDrawable(getResources().getDrawable(R.drawable.newexam));
                    newexamview.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 5));


                    examresults.setVisibility(View.GONE);
                    examresultstext.setTextColor(getResources().getColor(R.color.black_p50));
                    examresultspic.setImageDrawable(getResources().getDrawable(R.drawable.grade));
                    examresultsview.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3));
                }

            }
        });

        examresultsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(examresults.getVisibility()==View.GONE)
                {
                    examresults.setVisibility(View.VISIBLE);
                    examresultstext.setTextColor(getResources().getColor(R.color.colorAccent));
                    examresultspic.setImageDrawable(getResources().getDrawable(R.drawable.newresult));
                    examresultsview.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 5));

                    newexam.setVisibility(View.GONE);
                    newexamtext.setTextColor(getResources().getColor(R.color.black_p50));
                    newexampic.setImageDrawable(getResources().getDrawable(R.drawable.newexamblack));
                    newexamview.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3));


                }

            }
        });

        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                subjectdate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        subjectdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                datee = subjectdate.getText().toString();
            }
        });



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(language==1)
                {
                    showMessage("تم انشاء الاختبار بنجاح");

                }else
                    {
                        showMessage("Exam Created Successfully");
                    }

                    subjectsSpinner.setSelection(0);

                subjectdate.setText("");
                min.setText("");
                max.setText("");
                examcontent.getText().clear();

            }
        });

        mRecyclerView = getActivity().findViewById(R.id.exams_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager mGridlayoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(mGridlayoutManager);

        initializeData();
        initializeAdapter();

    }

    private void showMessage(String _s) {
        Toast.makeText(getContext(), _s, Toast.LENGTH_SHORT).show();
    }

    private void initializeData() {

        examsItemList = new ArrayList<>();

        examsItemList.add(new ExamsMonthItem("ديسمبر","لغة عربية"));
        examsItemList.add(new ExamsMonthItem("ديسمبر","لغة عربية"));
        examsItemList.add(new ExamsMonthItem("ديسمبر","لغة عربية"));
        examsItemList.add(new ExamsMonthItem("ديسمبر","لغة عربية"));
        examsItemList.add(new ExamsMonthItem("ديسمبر","لغة عربية"));
        examsItemList.add(new ExamsMonthItem("ديسمبر","لغة عربية"));

    }

    private void initializeAdapter() {

        ExamsResultsAdapter adapter = new ExamsResultsAdapter(examsItemList);
        mRecyclerView.setAdapter(adapter);
    }



    private class HighLightArrayAdapter extends ArrayAdapter<String> {

        private int mSelectedIndex = -1;


        void setSelection(int position) {
            mSelectedIndex =  position;
            notifyDataSetChanged();
        }

        HighLightArrayAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }


        @Override
        public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {

            View itemView =  super.getDropDownView(position, convertView, parent);
            TextView textView = (TextView) super.getDropDownView(position, convertView, parent);

            //LinearLayout linearLayout = (LinearLayout) super.getDropDownView(position,convertView,parent);

            if (position == mSelectedIndex) {
                textView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textView.setTextColor(Color.WHITE);
            } else {
                textView.setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.BLACK);
            }

            return textView;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        adapter2.setSelection(i);


        selectedSubject = subjectsSpinner.getSelectedItem().toString();

        if(selectedSubject.equals("اسم المادة *") || selectedSubject.equals("Subject Name *"))
        {
            selectedSubject = "";
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        showMessage("No Selection");
    }
}
