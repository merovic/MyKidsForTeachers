package com.amirahmed.mykidsforteachers.Activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.lang.reflect.Field;

public class MessagesActivity extends AppCompatActivity{

    private Toolbar mToolbar,mToolbar2;

    LinearLayout layout,contentlayout;

    TextView textView,textView2;

    TextInputLayout textInputLayout;

    Button button;

    TinyDB tinyDB;

    int language;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

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

            mToolbar.setTitle("الرسائل");

            TextView textView = mToolbar.findViewById(R.id.toolbartext);
            textView.setText("الرسائل");

            getActionBarTextView().setText("الرسائل");

            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MessagesActivity.super.onBackPressed();
                }
            });

            getActionBarTextView().setVisibility(View.GONE);
        }else
        {
            mToolbar2.setVisibility(View.VISIBLE);
            mToolbar.setVisibility(View.GONE);

            mToolbar2.setTitle("Messages");

            TextView textView = mToolbar2.findViewById(R.id.toolbartext);
            textView.setText("Messages");

            getActionBarTextView().setText("Messages");

            arrowen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MessagesActivity.super.onBackPressed();
                }
            });


            getActionBarTextView().setVisibility(View.GONE);
        }



        layout = findViewById(R.id.notificationtitle);
        contentlayout = findViewById(R.id.contentlayout);

        textView = findViewById(R.id.subjectdate);
        textView2 = findViewById(R.id.contect);

        textInputLayout = findViewById(R.id.text);

        button = findViewById(R.id.button);

        if(language==1)
        {
            layout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            contentlayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            textView.setGravity(Gravity.RIGHT);
            textInputLayout.setHint("عنوان الرسالة *");
            textView2.setText("تفاصيل الرسالة *");

            button.setText("ارسال الرسالة");

        }else
        {
            layout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            contentlayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            textView.setGravity(Gravity.LEFT);
            textInputLayout.setHint("Message Title *");
            textView2.setText("Message Details *");


            button.setText("Send Message");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(language==1)
                {
                    showMessage("تم ارسال الرسالة بنجاح");
                }else
                {
                    showMessage("Message Sent Successfully");
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
