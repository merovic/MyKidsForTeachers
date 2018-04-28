package com.amirahmed.mykidsforteachers.Activities;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.CircleDisplay;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.lang.reflect.Field;


public class ClassesReportsActivity extends AppCompatActivity {

    CircleDisplay cd1,cd2,cd3,cd4,cd5,cd6;

    LinearLayout main,layout1,layout2,layout3;

    private Toolbar mToolbar,mToolbar2;

    TextView title1,title2,title3;

    TextView t1,t2,t3,t4,t5,t6;

    TextView g1,g2,g3,g4,g5,g6,g7,g8;

    TinyDB tinyDB;

    int language;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classreport);

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

            mToolbar.setTitle("تقارير الفصل");

            TextView textView = mToolbar.findViewById(R.id.toolbartext);
            textView.setText("تقارير الفصل");

            getActionBarTextView().setText("تقارير الفصل");

            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClassesReportsActivity.super.onBackPressed();
                }
            });

            getActionBarTextView().setVisibility(View.GONE);
        }else
        {
            mToolbar2.setVisibility(View.VISIBLE);
            mToolbar.setVisibility(View.GONE);

            mToolbar2.setTitle("Classes Reports");

            TextView textView = mToolbar2.findViewById(R.id.toolbartext);
            textView.setText("Classes Reports");

            getActionBarTextView().setText("Classes Reports");

            arrowen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClassesReportsActivity.super.onBackPressed();
                }
            });


            getActionBarTextView().setVisibility(View.GONE);
        }



        main = findViewById(R.id.layoutmain);
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);

        title1 = findViewById(R.id.title1);
        title2 = findViewById(R.id.title2);
        title3 = findViewById(R.id.title3);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);

        g1 = findViewById(R.id.g1);
        g2 = findViewById(R.id.g2);
        g3 = findViewById(R.id.g3);
        g4 = findViewById(R.id.g4);
        g5 = findViewById(R.id.g5);
        g6 = findViewById(R.id.g6);
        g7 = findViewById(R.id.g7);
        g8 = findViewById(R.id.g8);

        cd1 = findViewById(R.id.cd1);
        cd2 = findViewById(R.id.cd2);
        cd3 = findViewById(R.id.cd3);
        cd4 = findViewById(R.id.cd4);
        cd5 = findViewById(R.id.cd5);
        cd6 = findViewById(R.id.cd6);

        if(language==1)
        {
            main.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            layout1.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            layout2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            layout3.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            title1.setText("تقارير الغياب");
            title2.setText("التقييمات");
            title3.setText("الاختبارات");

            title1.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            title2.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            title3.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

            t1.setText("حضور");
            t2.setText("غياب");
            t3.setText("أيجابى");
            t4.setText("سلبى");
            t5.setText("نجاح");
            t6.setText("رسوب");

            g1.setText("عدد الحضور: ٨٢");
            g2.setText("نسبة الحضور: ٧٨٪");
            g3.setText("عدد الغياب: ٨٢");
            g4.setText("نسبة لغياب: ٧٨٪");
            g5.setText("الايجابى: ٨٢٪");
            g6.setText("السلبى: ٨٢٪");
            g7.setText("نجاح: ٧٨٪");
            g8.setText("رسوب: ٨٢");

        }else
            {
                main.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                layout1.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                layout2.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                layout3.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

                title1.setText("Attendance Reports");
                title2.setText("Evaluations");
                title3.setText("Exams");

                title1.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                title2.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                title3.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

                t1.setText("Attendance");
                t2.setText("Absence");
                t3.setText("Positive");
                t4.setText("Negative");
                t5.setText("Success");
                t6.setText("Failed");

                g1.setText("Attendance No.: 82");
                g2.setText("Attendance Rate: 78%");
                g3.setText("Absence No.: 82");
                g4.setText("Absence Rate: 78%");
                g5.setText("Positive: 82%");
                g6.setText("Negative: 82%");
                g7.setText("Success: 78%");
                g8.setText("Failed: 82");

            }

        cd2.setAnimDuration(3000);
        cd2.setValueWidthPercent(30f);
        cd2.setTextSize(20f);
        cd2.setColor(Color.parseColor("#227585"));
        cd2.setDrawText(true);
        cd2.setDrawInnerCircle(true);
        cd2.setInnerCircleColor(Color.parseColor("#2C5768"));
        cd2.setTextColor(Color.WHITE);
        cd2.setFormatDigits(0);
        cd2.setTouchEnabled(false);
        cd2.setUnit("%");
        cd2.showValue(Float.parseFloat(String.valueOf("30")), 100f, true);
        cd2.setStepSize(0.5f);

        cd3.setAnimDuration(3000);
        cd3.setValueWidthPercent(30f);
        cd3.setTextSize(20f);
        cd3.setColor(Color.parseColor("#009284"));
        cd3.setDrawText(true);
        cd3.setDrawInnerCircle(true);
        cd3.setInnerCircleColor(Color.parseColor("#2C5768"));
        cd3.setTextColor(Color.WHITE);
        cd3.setFormatDigits(0);
        cd3.setTouchEnabled(false);
        cd3.setUnit("%");
        cd3.showValue(Float.parseFloat(String.valueOf("80")), 100f, true);
        cd3.setStepSize(0.5f);

        cd4.setAnimDuration(3000);
        cd4.setValueWidthPercent(30f);
        cd4.setTextSize(20f);
        cd4.setColor(Color.parseColor("#227585"));
        cd4.setDrawText(true);
        cd4.setDrawInnerCircle(true);
        cd4.setInnerCircleColor(Color.parseColor("#2C5768"));
        cd4.setTextColor(Color.WHITE);
        cd4.setFormatDigits(0);
        cd4.setTouchEnabled(false);
        cd4.setUnit("%");
        cd4.showValue(Float.parseFloat(String.valueOf("20")), 100f, true);
        cd4.setStepSize(0.5f);

        cd5.setAnimDuration(3000);
        cd5.setValueWidthPercent(30f);
        cd5.setTextSize(20f);
        cd5.setColor(Color.parseColor("#009284"));
        cd5.setDrawText(true);
        cd5.setDrawInnerCircle(true);
        cd5.setInnerCircleColor(Color.parseColor("#2C5768"));
        cd5.setTextColor(Color.WHITE);
        cd5.setFormatDigits(0);
        cd5.setTouchEnabled(false);
        cd5.setUnit("%");
        cd5.showValue(Float.parseFloat(String.valueOf("90")), 100f, true);
        cd5.setStepSize(0.5f);

        cd6.setAnimDuration(3000);
        cd6.setValueWidthPercent(30f);
        cd6.setTextSize(20f);
        cd6.setColor(Color.parseColor("#227585"));
        cd6.setInnerCircleColor(Color.parseColor("#2C5768"));
        cd6.setTextColor(Color.WHITE);
        cd6.setDrawText(true);
        cd6.setDrawInnerCircle(true);
        cd6.setFormatDigits(0);
        cd6.setTouchEnabled(false);
        cd6.setUnit("%");
        cd6.showValue(Float.parseFloat(String.valueOf("86")), 100f, true);
        cd6.setStepSize(0.5f);

        cd1.setAnimDuration(3000);
        cd1.setValueWidthPercent(30f);
        cd1.setTextSize(20f);
        cd1.setColor(Color.parseColor("#009284"));
        cd1.setDrawText(true);
        cd1.setDrawInnerCircle(true);
        cd1.setInnerCircleColor(Color.parseColor("#2C5768"));
        cd1.setTextColor(Color.WHITE);
        cd1.setFormatDigits(0);
        cd1.setTouchEnabled(false);
        cd1.setUnit("%");
        cd1.showValue(Float.parseFloat(String.valueOf("14")), 100f, true);
        cd1.setStepSize(0.5f);

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
