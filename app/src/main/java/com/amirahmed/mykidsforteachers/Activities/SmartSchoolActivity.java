package com.amirahmed.mykidsforteachers.Activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.amirahmed.mykidsforteachers.Adapters.SmartClassesAdapter;
import com.amirahmed.mykidsforteachers.Models.SmartClassItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import info.hoang8f.android.segmented.SegmentedGroup;

public class SmartSchoolActivity extends AppCompatActivity {

    private Toolbar mToolbar,mToolbar2;

    TinyDB tinyDB;

    int language;

    Spinner classesSpinner;

    ArrayAdapter<String> adapter;

    List<String> classes = new ArrayList<>();

    Calendar myCalendar;

    TextView classDate,classTime,className;

    String selectedClass,datee;

    RecyclerView rv;

    List<SmartClassItem> smartClassItems;

    LinearLayout newclasslayout;

    SegmentedGroup tabsgroup;
    RadioButton student,parent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_school);


        tinyDB = new TinyDB(this);

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

            mToolbar.setTitle("المدرسة الزكية");

            TextView textView = mToolbar.findViewById(R.id.toolbartext);
            textView.setText("المدرسة الزكية");

            getActionBarTextView().setText("المدرسة الزكية");

            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SmartSchoolActivity.super.onBackPressed();
                }
            });

            getActionBarTextView().setVisibility(View.GONE);
        }else
        {
            mToolbar2.setVisibility(View.VISIBLE);
            mToolbar.setVisibility(View.GONE);

            mToolbar2.setTitle("Smart School");

            TextView textView = mToolbar2.findViewById(R.id.toolbartext);
            textView.setText("Smart School");

            getActionBarTextView().setText("Smart School");

            arrowen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SmartSchoolActivity.super.onBackPressed();
                }
            });


            getActionBarTextView().setVisibility(View.GONE);
        }

        classDate = findViewById(R.id.classdate);
        className = findViewById(R.id.classname);
        classTime = findViewById(R.id.classtime);

        classes.add("اختر الفصل");
        classes.add("1/1");
        classes.add("1/2");
        classes.add("1/3");
        classes.add("2/1");
        classes.add("2/2");

        classesSpinner = findViewById(R.id.classes);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, classes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classesSpinner.setAdapter(adapter);

        adapter.notifyDataSetChanged();


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

                classDate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        classDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SmartSchoolActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                datee = classDate.getText().toString();
            }
        });


        classTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(SmartSchoolActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        classTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        rv = findViewById(R.id.rv);

        rv.setHasFixedSize(true);

        GridLayoutManager mGridlayoutManager = new GridLayoutManager(this,2);
        rv.setLayoutManager(mGridlayoutManager);

        initializeData();
        initializeAdapter();

        newclasslayout = findViewById(R.id.newclasslayout);

        newclasslayout.setVisibility(View.VISIBLE);
        rv.setVisibility(View.GONE);

        tabsgroup = findViewById(R.id.segmented2);

        parent = findViewById(R.id.button22);
        student = findViewById(R.id.button21);

        student.setChecked(false);
        parent.setChecked(true);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv.setVisibility(View.VISIBLE);
                newclasslayout.setVisibility(View.GONE);
            }
        });

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newclasslayout.setVisibility(View.VISIBLE);
                rv.setVisibility(View.GONE);

            }
        });

    }


    private void initializeData() {

        smartClassItems = new ArrayList<>();

        smartClassItems.add(new SmartClassItem("فصل ١","١/٢"));
        smartClassItems.add(new SmartClassItem("فصل ٢","١/٢"));
        smartClassItems.add(new SmartClassItem("فصل ٣","١/٢"));
        smartClassItems.add(new SmartClassItem("فصل ٤","١/٢"));


    }

    private void initializeAdapter() {

        SmartClassesAdapter adapter = new SmartClassesAdapter(smartClassItems);
        rv.setAdapter(adapter);
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
}
