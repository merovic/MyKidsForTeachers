package com.amirahmed.mykidsforteachers.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.lang.reflect.Field;


public class PlanActivity extends AppCompatActivity {

    LinearLayout layout1,layout2,layout3;

    private Toolbar mToolbar,mToolbar2;

    TextView text1,text2,text3;

    Button button;

    TinyDB tinyDB;

    int language;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

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

            mToolbar.setTitle("الخطة");

            TextView textView = mToolbar.findViewById(R.id.toolbartext);
            textView.setText("الخطة");

            getActionBarTextView().setText("الخطة");

            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlanActivity.super.onBackPressed();
                }
            });

            getActionBarTextView().setVisibility(View.GONE);
        }else
        {
            mToolbar2.setVisibility(View.VISIBLE);
            mToolbar.setVisibility(View.GONE);

            mToolbar2.setTitle("Plan");

            TextView textView = mToolbar2.findViewById(R.id.toolbartext);
            textView.setText("Plan");

            getActionBarTextView().setText("Plan");

            arrowen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlanActivity.super.onBackPressed();
                }
            });


            getActionBarTextView().setVisibility(View.GONE);
        }



        layout1 = findViewById(R.id.container1);
        layout2 = findViewById(R.id.container2);
        layout3 = findViewById(R.id.container3);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);

        button = findViewById(R.id.button);

        if(language==1)
        {
            layout1.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            layout2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            layout3.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            text1.setText("عنوان الدرس *");
            text2.setText("الاهداف *");
            text3.setText("الواجب *");

            button.setText("حفظ");

        }else
            {
                layout1.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                layout2.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                layout3.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

                text1.setText("Lesson Title *");
                text2.setText("Objectives *");
                text3.setText("Homework *");

                button.setText("Save");
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(language==1)
                    {
                        showMessage("تم وضع الخطة بنجاح");
                    }else
                        {
                            showMessage("Plan Adapted Successfully");
                        }

                        onBackPressed();
                }
            });

    }

    private void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
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
