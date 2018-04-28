package com.amirahmed.mykidsforteachers.Activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Adapters.ClassScheduleAdapter;
import com.amirahmed.mykidsforteachers.Adapters.ExamsMonthAdapter;
import com.amirahmed.mykidsforteachers.Adapters.StudentsListAdapter;
import com.amirahmed.mykidsforteachers.Models.ClassScheduleItem;
import com.amirahmed.mykidsforteachers.Models.ExamsMonthItem;
import com.amirahmed.mykidsforteachers.Models.StudentItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

public class ClassActivity extends AppCompatActivity implements View.OnClickListener{

    TinyDB tinyDB;

    RelativeLayout scheduletab,attendtab,evaluationtab,examstab,messagestab,notificationstab;
    TextView scheduletabtext,attendtabtext,evaluationtabtext,examstabtext,messagestabtext,notificationstabtext;

    private RecyclerView mRecyclerView;
    private List<StudentItem> studentItemList;

    RelativeLayout selection,create,result;

    LinearLayout examslayout,studentlist,examcreatelayout,messageslayout,schedulelayout;

    TextView createtext,resulttext,classestextmain,classestextexams;

    private RecyclerView mRecyclerView2;
    private List<ExamsMonthItem> examsMonthItems;


    String state,thatIS;


    EditText notifititle,notifibody;

    private RecyclerView mRecyclerView3;
    private List<ClassScheduleItem> classScheduleItems;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        tinyDB = new TinyDB(this);
        state = tinyDB.getString("state");
        thatIS = tinyDB.getString("that is");

        examslayout = findViewById(R.id.examslayout);
        examslayout.setVisibility(View.GONE);
        studentlist = findViewById(R.id.studentlist);
        studentlist.setVisibility(View.GONE);
        messageslayout = findViewById(R.id.messageslayout);
        messageslayout.setVisibility(View.GONE);
        schedulelayout = findViewById(R.id.schedulelayout);
        schedulelayout.setVisibility(View.VISIBLE);

        scheduletab = findViewById(R.id.scheduletab);
        attendtab = findViewById(R.id.attendtab);
        evaluationtab = findViewById(R.id.evaluationtab);
        examstab = findViewById(R.id.examstab);
        messagestab = findViewById(R.id.messagestab);
        notificationstab = findViewById(R.id.notifitab);

        scheduletabtext = findViewById(R.id.scheduletabtext);
        attendtabtext = findViewById(R.id.attendtabtext);
        evaluationtabtext = findViewById(R.id.evaluationtabtext);
        examstabtext = findViewById(R.id.examstabtext);
        messagestabtext = findViewById(R.id.messagestabtext);
        notificationstabtext = findViewById(R.id.notifitabtext);

        scheduletab.setBackgroundColor(getResources().getColor(R.color.whitegray));
        attendtab.setBackgroundColor(getResources().getColor(R.color.white));
        evaluationtab.setBackgroundColor(getResources().getColor(R.color.white));
        examstab.setBackgroundColor(getResources().getColor(R.color.white));
        messagestab.setBackgroundColor(getResources().getColor(R.color.white));
        notificationstab.setBackgroundColor(getResources().getColor(R.color.white));

        scheduletabtext.setTextColor(getResources().getColor(R.color.white));
        attendtabtext.setTextColor(getResources().getColor(R.color.whitegray));
        evaluationtabtext.setTextColor(getResources().getColor(R.color.whitegray));
        examstabtext.setTextColor(getResources().getColor(R.color.whitegray));
        messagestabtext.setTextColor(getResources().getColor(R.color.whitegray));
        notificationstabtext.setTextColor(getResources().getColor(R.color.whitegray));

        scheduletab.setOnClickListener(this);
        attendtab.setOnClickListener(this);
        evaluationtab.setOnClickListener(this);
        examstab.setOnClickListener(this);
        messagestab.setOnClickListener(this);
        notificationstab.setOnClickListener(this);

        mRecyclerView = findViewById(R.id.students_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager mGridlayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(mGridlayoutManager);

        initializeData();
        initializeAdapter();

        //-------------------------------------------------------------------------------------------------

        selection = findViewById(R.id.examsselection);

        examcreatelayout = findViewById(R.id.examcreatelayout);


        classestextexams = findViewById(R.id.classestextexams);



        mRecyclerView2 = findViewById(R.id.exams_recycler_view);

        mRecyclerView2.setHasFixedSize(true);

        GridLayoutManager mGridlayoutManager2 = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView2.setLayoutManager(mGridlayoutManager2);

        initializeData2();
        initializeAdapter2();

        if(!thatIS.equals("examstab")){

            selection.setVisibility(View.VISIBLE);
            mRecyclerView2.setVisibility(View.GONE);
            examcreatelayout.setVisibility(View.VISIBLE);
        }

        create.setBackgroundColor(getResources().getColor(R.color.whitegray));
        createtext.setTextColor(getResources().getColor(R.color.white));

        result.setBackgroundColor(getResources().getColor(R.color.white));
        resulttext.setTextColor(getResources().getColor(R.color.black_p50));

        create.setOnClickListener(this);
        result.setOnClickListener(this);

        //-------------------------------------------------------------------------------------------------

        notifititle = findViewById(R.id.edit5);
        notifibody = findViewById(R.id.edit6);

        notifititle.setHint("عنوان الاشعار");
        notifibody.setHint("تفاصيل الاشعار");

        //-------------------------------------------------------------------------------------------------

        mRecyclerView3 = findViewById(R.id.schedule_recycler_view);

        mRecyclerView3.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView3.setLayoutManager(llm);

        initializeData3();
        initializeAdapter3();

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
        adapter.notifyDataSetChanged();
    }


    private void initializeData2() {

        examsMonthItems = new ArrayList<>();

        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","الفصل ١/٣","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","الفصل ١/٣","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","الفصل ١/٣","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","الفصل ١/٣","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","الفصل ١/٣","١-٣-٢٠١٨","اللغة العربية"));
        examsMonthItems.add(new ExamsMonthItem("المرحلة الاعدادية","الفصل ١/٣","١-٣-٢٠١٨","اللغة العربية"));

    }

    private void initializeAdapter2() {

        ExamsMonthAdapter adapter = new ExamsMonthAdapter(examsMonthItems);
        mRecyclerView2.setAdapter(adapter);
    }

    private void initializeData3() {

        classScheduleItems = new ArrayList<>();

        classScheduleItems.add(new ClassScheduleItem("لغة عربية","الحصة الاولى","من: ٠٨:٠٠","الى: ٠٨:٤٥"));


    }

    private void initializeAdapter3() {

        ClassScheduleAdapter adapter = new ClassScheduleAdapter(classScheduleItems);
        mRecyclerView3.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.scheduletab:
                scheduletab.setBackgroundColor(getResources().getColor(R.color.whitegray));
                attendtab.setBackgroundColor(getResources().getColor(R.color.white));
                evaluationtab.setBackgroundColor(getResources().getColor(R.color.white));
                examstab.setBackgroundColor(getResources().getColor(R.color.white));
                messagestab.setBackgroundColor(getResources().getColor(R.color.white));
                notificationstab.setBackgroundColor(getResources().getColor(R.color.white));

                scheduletabtext.setTextColor(getResources().getColor(R.color.white));
                attendtabtext.setTextColor(getResources().getColor(R.color.whitegray));
                evaluationtabtext.setTextColor(getResources().getColor(R.color.whitegray));
                examstabtext.setTextColor(getResources().getColor(R.color.whitegray));
                messagestabtext.setTextColor(getResources().getColor(R.color.whitegray));
                notificationstabtext.setTextColor(getResources().getColor(R.color.whitegray));

                examslayout.setVisibility(View.GONE);
                studentlist.setVisibility(View.GONE);
                messageslayout.setVisibility(View.GONE);
                schedulelayout.setVisibility(View.VISIBLE);

                initializeAdapter3();

                break;

            case R.id.attendtab:
                scheduletab.setBackgroundColor(getResources().getColor(R.color.white));
                attendtab.setBackgroundColor(getResources().getColor(R.color.whitegray));
                evaluationtab.setBackgroundColor(getResources().getColor(R.color.white));
                examstab.setBackgroundColor(getResources().getColor(R.color.white));
                messagestab.setBackgroundColor(getResources().getColor(R.color.white));
                notificationstab.setBackgroundColor(getResources().getColor(R.color.white));

                scheduletabtext.setTextColor(getResources().getColor(R.color.whitegray));
                attendtabtext.setTextColor(getResources().getColor(R.color.white));
                evaluationtabtext.setTextColor(getResources().getColor(R.color.whitegray));
                examstabtext.setTextColor(getResources().getColor(R.color.whitegray));
                messagestabtext.setTextColor(getResources().getColor(R.color.whitegray));
                notificationstabtext.setTextColor(getResources().getColor(R.color.whitegray));

                tinyDB.putString("that is","attendance");

                examslayout.setVisibility(View.GONE);
                studentlist.setVisibility(View.VISIBLE);
                messageslayout.setVisibility(View.GONE);
                schedulelayout.setVisibility(View.GONE);

                initializeAdapter();

                break;

            case R.id.evaluationtab:
                scheduletab.setBackgroundColor(getResources().getColor(R.color.white));
                attendtab.setBackgroundColor(getResources().getColor(R.color.white));
                evaluationtab.setBackgroundColor(getResources().getColor(R.color.whitegray));
                examstab.setBackgroundColor(getResources().getColor(R.color.white));
                messagestab.setBackgroundColor(getResources().getColor(R.color.white));
                notificationstab.setBackgroundColor(getResources().getColor(R.color.white));

                scheduletabtext.setTextColor(getResources().getColor(R.color.whitegray));
                attendtabtext.setTextColor(getResources().getColor(R.color.whitegray));
                evaluationtabtext.setTextColor(getResources().getColor(R.color.white));
                examstabtext.setTextColor(getResources().getColor(R.color.whitegray));
                messagestabtext.setTextColor(getResources().getColor(R.color.whitegray));
                notificationstabtext.setTextColor(getResources().getColor(R.color.whitegray));

                tinyDB.putString("that is","evaluation");

                examslayout.setVisibility(View.GONE);
                messageslayout.setVisibility(View.GONE);
                studentlist.setVisibility(View.VISIBLE);
                schedulelayout.setVisibility(View.GONE);

                initializeAdapter();

                break;

            case R.id.examstab:
                scheduletab.setBackgroundColor(getResources().getColor(R.color.white));
                attendtab.setBackgroundColor(getResources().getColor(R.color.white));
                evaluationtab.setBackgroundColor(getResources().getColor(R.color.white));
                examstab.setBackgroundColor(getResources().getColor(R.color.whitegray));
                messagestab.setBackgroundColor(getResources().getColor(R.color.white));
                notificationstab.setBackgroundColor(getResources().getColor(R.color.white));

                scheduletabtext.setTextColor(getResources().getColor(R.color.whitegray));
                attendtabtext.setTextColor(getResources().getColor(R.color.whitegray));
                evaluationtabtext.setTextColor(getResources().getColor(R.color.whitegray));
                examstabtext.setTextColor(getResources().getColor(R.color.white));
                messagestabtext.setTextColor(getResources().getColor(R.color.whitegray));
                notificationstabtext.setTextColor(getResources().getColor(R.color.whitegray));

                tinyDB.putString("that is","examstab");

                initializeAdapter2();

                examslayout.setVisibility(View.VISIBLE);
                studentlist.setVisibility(View.GONE);
                messageslayout.setVisibility(View.GONE);
                classestextexams.setVisibility(View.GONE);
                schedulelayout.setVisibility(View.GONE);

                break;

            case R.id.messagestab:
                scheduletab.setBackgroundColor(getResources().getColor(R.color.white));
                attendtab.setBackgroundColor(getResources().getColor(R.color.white));
                evaluationtab.setBackgroundColor(getResources().getColor(R.color.white));
                examstab.setBackgroundColor(getResources().getColor(R.color.white));
                messagestab.setBackgroundColor(getResources().getColor(R.color.whitegray));
                notificationstab.setBackgroundColor(getResources().getColor(R.color.white));

                scheduletabtext.setTextColor(getResources().getColor(R.color.whitegray));
                attendtabtext.setTextColor(getResources().getColor(R.color.whitegray));
                evaluationtabtext.setTextColor(getResources().getColor(R.color.whitegray));
                examstabtext.setTextColor(getResources().getColor(R.color.whitegray));
                messagestabtext.setTextColor(getResources().getColor(R.color.white));
                notificationstabtext.setTextColor(getResources().getColor(R.color.whitegray));

                tinyDB.putString("that is","messagestab");
                examslayout.setVisibility(View.GONE);
                messageslayout.setVisibility(View.GONE);
                studentlist.setVisibility(View.VISIBLE);
                schedulelayout.setVisibility(View.GONE);
                initializeAdapter();

                break;

            case R.id.notifitab:
                scheduletab.setBackgroundColor(getResources().getColor(R.color.white));
                attendtab.setBackgroundColor(getResources().getColor(R.color.white));
                evaluationtab.setBackgroundColor(getResources().getColor(R.color.white));
                examstab.setBackgroundColor(getResources().getColor(R.color.white));
                messagestab.setBackgroundColor(getResources().getColor(R.color.white));
                notificationstab.setBackgroundColor(getResources().getColor(R.color.whitegray));

                scheduletabtext.setTextColor(getResources().getColor(R.color.whitegray));
                attendtabtext.setTextColor(getResources().getColor(R.color.whitegray));
                evaluationtabtext.setTextColor(getResources().getColor(R.color.whitegray));
                examstabtext.setTextColor(getResources().getColor(R.color.whitegray));
                messagestabtext.setTextColor(getResources().getColor(R.color.whitegray));
                notificationstabtext.setTextColor(getResources().getColor(R.color.white));


                tinyDB.putString("that is","notificationtab");

                examslayout.setVisibility(View.GONE);
                studentlist.setVisibility(View.GONE);
                messageslayout.setVisibility(View.VISIBLE);
                schedulelayout.setVisibility(View.GONE);

                break;
        }
    }
}