package com.amirahmed.mykidsforteachers.Activities;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amirahmed.mykidsforteachers.Adapters.ExamesSubmissionAdapter;
import com.amirahmed.mykidsforteachers.Models.ExamsSubmissionItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.amirahmed.mykidsforteachers.R.id.rv2;

public class ExamSubmissionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private RecyclerView mRecyclerView;
    private List<ExamsSubmissionItem> examsSubmissionItems;

    private Toolbar mToolbar,mToolbar2;

    Spinner studentSpinner;

    String selectedName;

    List<String> students = new ArrayList<>();

    ArrayAdapter<String> adapter;
    HighLightArrayAdapter adapter2;

    LinearLayout selection,score,comment;

    TextInputLayout scoreint,commentint;

    TextView scoretext,commenttext;

    Button button;

    TinyDB tinyDB;

    int language;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examsubmission);

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

            mToolbar.setTitle("اضافة نتيجة");

            TextView textView = mToolbar.findViewById(R.id.toolbartext);
            textView.setText("اضافة نتيجة");

            getActionBarTextView().setText("اضافة نتيجة");

            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ExamSubmissionActivity.super.onBackPressed();
                }
            });

            getActionBarTextView().setVisibility(View.GONE);
        }else
        {
            mToolbar2.setVisibility(View.VISIBLE);
            mToolbar.setVisibility(View.GONE);

            mToolbar2.setTitle("Add Result");

            TextView textView = mToolbar2.findViewById(R.id.toolbartext);
            textView.setText("Add Result");

            getActionBarTextView().setText("Add Result");

            arrowen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ExamSubmissionActivity.super.onBackPressed();
                }
            });


            getActionBarTextView().setVisibility(View.GONE);
        }


        selection = findViewById(R.id.selectionlayout);
        score = findViewById(R.id.scorelayout);
        comment = findViewById(R.id.commentlayout);

        scoreint = findViewById(R.id.scoreint);
        commentint = findViewById(R.id.commentint);

        scoretext = findViewById(R.id.studentscore);
        commenttext = findViewById(R.id.studentcomment);

        studentSpinner = findViewById(R.id.students);

        button = findViewById(R.id.button);

        mRecyclerView = findViewById(rv2);

        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);

        initializeData();
        initializeAdapter();

        if(language==1)
        {
            selection.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            score.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            comment.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            scoreint.setHint("درجة الاختبار");
            commentint.setHint("تعليق");

            scoretext.setGravity(Gravity.RIGHT);
            scoretext.setGravity(Gravity.RIGHT);

            button.setText("اضافة نتيجة");

            students.add("اسم الطالب *");
            students.add("احمد ابراهيم");
            students.add("احمد ابراهيم");
            students.add("احمد ابراهيم");
            students.add("احمد ابراهيم");
            students.add("احمد ابراهيم");
            students.add("احمد ابراهيم");
            students.add("احمد ابراهيم");

        }else
            {
                selection.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                score.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                comment.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

                scoreint.setHint("Student Score *");
                commentint.setHint("Comment");

                scoretext.setGravity(Gravity.LEFT);
                scoretext.setGravity(Gravity.LEFT);

                button.setText("Add Score");

                students.add("Student Name *");
                students.add("Ahmed Ibrahim");
                students.add("Ahmed Ibrahim");
                students.add("Ahmed Ibrahim");
                students.add("Ahmed Ibrahim");
                students.add("Ahmed Ibrahim");
                students.add("Ahmed Ibrahim");

            }



        adapter = new ArrayAdapter<>(ExamSubmissionActivity.this, android.R.layout.simple_spinner_item, students);
        adapter2 = new HighLightArrayAdapter(ExamSubmissionActivity.this, R.layout.item_spinner, students);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentSpinner.setAdapter(adapter2);
        studentSpinner.setOnItemSelectedListener(this);

        adapter.notifyDataSetChanged();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(language==1)
                {
                    showMessage("تم اضافة النتيجة للطالب بنجاح");
                    scoretext.setText("");
                    commenttext.setText("");
                }else
                    {
                        showMessage("Score Inserted Successfully");
                        scoretext.setText("");
                        commenttext.setText("");
                    }
            }
        });

    }



    private void initializeData() {

        examsSubmissionItems = new ArrayList<>();

        examsSubmissionItems.add(new ExamsSubmissionItem("عمر سعيد"));
        examsSubmissionItems.add(new ExamsSubmissionItem("عمر سعيد"));
        examsSubmissionItems.add(new ExamsSubmissionItem("عمر سعيد"));
        examsSubmissionItems.add(new ExamsSubmissionItem("عمر سعيد"));
        examsSubmissionItems.add(new ExamsSubmissionItem("عمر سعيد"));
        examsSubmissionItems.add(new ExamsSubmissionItem("عمر سعيد"));
        examsSubmissionItems.add(new ExamsSubmissionItem("عمر سعيد"));
        examsSubmissionItems.add(new ExamsSubmissionItem("عمر سعيد"));


    }

    private void initializeAdapter() {

        ExamesSubmissionAdapter adapter = new ExamesSubmissionAdapter(examsSubmissionItems);
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



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



        adapter2.setSelection(i);


        selectedName = studentSpinner.getSelectedItem().toString();

        if(selectedName.equals("اسم الطالب *") || selectedName.equals("Student Name *"))
        {
            selectedName = "";
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        showMessage("No Selection");
    }

    private void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }
}
