package com.amirahmed.mykidsforteachers.Activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amirahmed.mykidsforteachers.Adapters.TeachersMessagingAdapter;
import com.amirahmed.mykidsforteachers.Models.TeachersMessagingItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TeachersMessagingActivity extends AppCompatActivity {

    private List<TeachersMessagingItem> teachersMessagingItems;
    private RecyclerView rv2;

    private Toolbar mToolbar,mToolbar2;

    TextView to,text1,text2;

    LinearLayout layout1,layout2;

    Button button;

    TinyDB tinyDB;

    int language;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachersmessaging);

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

            mToolbar.setTitle("رسائل هيئة الدريس");

            TextView textView = mToolbar.findViewById(R.id.toolbartext);
            textView.setText("رسائل هيئة الدريس");

            getActionBarTextView().setText("رسائل هيئة الدريس");

            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TeachersMessagingActivity.super.onBackPressed();
                }
            });

            getActionBarTextView().setVisibility(View.GONE);
        }else
        {
            mToolbar2.setVisibility(View.VISIBLE);
            mToolbar.setVisibility(View.GONE);

            mToolbar2.setTitle("Teachers Messages");

            TextView textView = mToolbar2.findViewById(R.id.toolbartext);
            textView.setText("Teachers Messages");

            getActionBarTextView().setText("Teachers Messages");

            arrowen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TeachersMessagingActivity.super.onBackPressed();
                }
            });


            getActionBarTextView().setVisibility(View.GONE);
        }


        to = findViewById(R.id.totext);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);

        layout1 = findViewById(R.id.container1);
        layout2 = findViewById(R.id.container2);

        button = findViewById(R.id.button);

        rv2 = findViewById(R.id.teachers_recycler_view);

        rv2.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv2.setLayoutManager(llm);

        initializeData();
        initializeAdapter();

        if(language==1)
        {
            to.setText("الى:");
            to.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

            layout1.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            layout2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            text1.setText("عنوان الرسالة *");
            text2.setText("تفاصيل الرسالة *");

            button.setText("ارسال");

        }else
            {
                to.setText("To:");
                to.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

                layout1.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                layout2.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

                text1.setText("Message Title *");
                text2.setText("Message Body *");

                button.setText("Send");
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(language==1)
                    {
                        showMessage("تم ارسال الرسالة بنجاح");
                        to.setText("الى:");

                    }else
                        {
                            showMessage("Message Sent Successfully");
                            to.setText("To:");
                        }

                }
            });

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

    private void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }


    private void initializeAdapter() {

        TeachersMessagingAdapter adapter = new TeachersMessagingAdapter(teachersMessagingItems,to);
        rv2.setAdapter(adapter);
    }

    private void initializeData() {
        teachersMessagingItems = new ArrayList<>();

        if(language==1)
        {
            teachersMessagingItems.add(new TeachersMessagingItem("احمد محمد ابراهيم","مدرس لغة عربية للمرحلة الاعدادية"));
            teachersMessagingItems.add(new TeachersMessagingItem("احمد محمد ابراهيم","مدرس لغة عربية للمرحلة الاعدادية"));
            teachersMessagingItems.add(new TeachersMessagingItem("احمد محمد ابراهيم","مدرس لغة عربية للمرحلة الاعدادية"));
            teachersMessagingItems.add(new TeachersMessagingItem("احمد محمد ابراهيم","مدرس لغة عربية للمرحلة الاعدادية"));
            teachersMessagingItems.add(new TeachersMessagingItem("احمد محمد ابراهيم","مدرس لغة عربية للمرحلة الاعدادية"));
            teachersMessagingItems.add(new TeachersMessagingItem("احمد محمد ابراهيم","مدرس لغة عربية للمرحلة الاعدادية"));
            teachersMessagingItems.add(new TeachersMessagingItem("احمد محمد ابراهيم","مدرس لغة عربية للمرحلة الاعدادية"));
        }else
            {
                teachersMessagingItems.add(new TeachersMessagingItem("Ahmed Mohamed Ibrahim","Arabic Teacher for Preparatory Stage"));
                teachersMessagingItems.add(new TeachersMessagingItem("Ahmed Mohamed Ibrahim","Arabic Teacher for Preparatory Stage"));
                teachersMessagingItems.add(new TeachersMessagingItem("Ahmed Mohamed Ibrahim","Arabic Teacher for Preparatory Stage"));
                teachersMessagingItems.add(new TeachersMessagingItem("Ahmed Mohamed Ibrahim","Arabic Teacher for Preparatory Stage"));
                teachersMessagingItems.add(new TeachersMessagingItem("Ahmed Mohamed Ibrahim","Arabic Teacher for Preparatory Stage"));
                teachersMessagingItems.add(new TeachersMessagingItem("Ahmed Mohamed Ibrahim","Arabic Teacher for Preparatory Stage"));
                teachersMessagingItems.add(new TeachersMessagingItem("Ahmed Mohamed Ibrahim","Arabic Teacher for Preparatory Stage"));

            }


    }

}
