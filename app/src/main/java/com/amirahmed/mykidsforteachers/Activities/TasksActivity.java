package com.amirahmed.mykidsforteachers.Activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Adapters.TaskAdapter;
import com.amirahmed.mykidsforteachers.Fragments.AddTaskFragment;
import com.amirahmed.mykidsforteachers.Models.AddButtonClick;
import com.amirahmed.mykidsforteachers.Models.TaskItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TasksActivity extends AppCompatActivity {

    private Toolbar mToolbar,mToolbar2;

    TinyDB tinyDB;

    int language;

    List<TaskItem> taskItemList;

    RecyclerView rv;

    TaskAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        mToolbar = findViewById(R.id.toolbar_actionbar);
        mToolbar2 = findViewById(R.id.toolbar_actionbar_en);
        setSupportActionBar(mToolbar);
        setSupportActionBar(mToolbar2);

        ImageView arrow = mToolbar.findViewById(R.id.arrow);
        ImageView arrowen = mToolbar2.findViewById(R.id.arrowen);


            mToolbar.setVisibility(View.VISIBLE);
            mToolbar2.setVisibility(View.GONE);

            mToolbar.setTitle("");

            TextView textView = mToolbar.findViewById(R.id.toolbartext);
            textView.setText("المهام");

           getActionBarTextView().setText("");

            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TasksActivity.super.onBackPressed();
                }
            });

            getActionBarTextView().setVisibility(View.GONE);


        rv = findViewById(R.id.rv);

        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        initializeData();
        initializeAdapter();


        FloatingActionButton fab;
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FragmentManager fm = getFragmentManager();
                AddTaskFragment addTaskFragment = new AddTaskFragment();

                addTaskFragment.show(fm,"TV_tag");
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onButtonClick(AddButtonClick addButtonClick)
    {

        taskItemList.add(new TaskItem(addButtonClick.getEvent(),"١٢-١١-٢٠١٩","٠٢:٢٨",addButtonClick.getEvent2()));
        adapter.notifyItemInserted(taskItemList.size() - 1);


    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    private void initializeData() {

        taskItemList = new ArrayList<>();

        taskItemList.add(new TaskItem("تاسك جديد","١٢-١١-٢٠١٩","٠٢:٢٨","Work"));
        taskItemList.add(new TaskItem("تاسك جديد","١٢-١١-٢٠١٩","٠٢:٢٨","Private"));
        taskItemList.add(new TaskItem("تاسك جديد","١٢-١١-٢٠١٩","٠٢:٢٨","Work"));
        taskItemList.add(new TaskItem("تاسك جديد","١٢-١١-٢٠١٩","٠٢:٢٨","Private"));

    }

    private void initializeAdapter() {

        adapter = new TaskAdapter(taskItemList);
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
