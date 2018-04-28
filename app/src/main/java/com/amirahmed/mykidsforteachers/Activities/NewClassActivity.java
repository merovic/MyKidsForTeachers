package com.amirahmed.mykidsforteachers.Activities;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Fragments.AttendanceFragment;
import com.amirahmed.mykidsforteachers.Fragments.EvaluationFragment;
import com.amirahmed.mykidsforteachers.Fragments.ExamsFragment;
import com.amirahmed.mykidsforteachers.Fragments.NotificationsFragment;
import com.amirahmed.mykidsforteachers.Fragments.ScheduleFragment;
import com.amirahmed.mykidsforteachers.Fragments.StudentListFragment;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class NewClassActivity extends AppCompatActivity{


    private Toolbar mToolbar,mToolbar2;
    private TabLayout tabLayout;

    TinyDB tinyDB;

    int language;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


        tinyDB = new TinyDB(getApplicationContext());
        language = tinyDB.getInt("language");

        mToolbar = findViewById(R.id.toolbar_actionbar);
        mToolbar2 = findViewById(R.id.toolbar_actionbar_en);
        setSupportActionBar(mToolbar);
        setSupportActionBar(mToolbar2);

        ImageView arrow = mToolbar.findViewById(R.id.arrow);
        ImageView arrowen = mToolbar2.findViewById(R.id.arrowen);

        Bundle extras  = getIntent().getExtras();

        String title = extras.getString("className");

        if(language==1)
        {
            mToolbar.setVisibility(View.VISIBLE);
            mToolbar2.setVisibility(View.GONE);

            mToolbar.setTitle(title);

            TextView textView = mToolbar.findViewById(R.id.toolbartext);
            textView.setText(title);

            getActionBarTextView().setText(title);

            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NewClassActivity.super.onBackPressed();
                }
            });

            getActionBarTextView().setVisibility(View.GONE);
        }else
        {
            mToolbar2.setVisibility(View.VISIBLE);
            mToolbar.setVisibility(View.GONE);

            mToolbar2.setTitle(title);

            TextView textView = mToolbar2.findViewById(R.id.toolbartext);
            textView.setText(title);

            getActionBarTextView().setText(title);

            arrowen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NewClassActivity.super.onBackPressed();
                }
            });


            getActionBarTextView().setVisibility(View.GONE);
        }

        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        if(language==1)
        {
            viewPager.setCurrentItem(5);
        }else
            {
                viewPager.setCurrentItem(0);
            }



        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if(language==1)
        {
            adapter.addFragment(new NotificationsFragment(), "");
            adapter.addFragment(new StudentListFragment(), "");
            adapter.addFragment(new ExamsFragment(), "");
            adapter.addFragment(new EvaluationFragment(), "");
            adapter.addFragment(new AttendanceFragment(), "");
            adapter.addFragment(new ScheduleFragment(), "");
        }else
            {
                adapter.addFragment(new ScheduleFragment(), "");
                adapter.addFragment(new AttendanceFragment(), "");
                adapter.addFragment(new EvaluationFragment(), "");
                adapter.addFragment(new ExamsFragment(), "");
                adapter.addFragment(new StudentListFragment(), "");
                adapter.addFragment(new NotificationsFragment(), "");
            }


        //adapter.notifyDataSetChanged();

        viewPager.setAdapter(adapter);
        //viewPager.setOffscreenPageLimit(5);

    }


    private void setupTabIcons() {

        //tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        Typeface type = Typeface.createFromAsset(this.getAssets(),"fonts/lmaar.ttf");

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        if(language==1)
        {
            tabOne.setText("الجدول");
        }else
            {
                tabOne.setText("Schedule");
            }

        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tabscalender ,0 , 0);
        if(language==1)
        {
            tabLayout.getTabAt(5).setCustomView(tabOne);

        }else
            {
                tabLayout.getTabAt(0).setCustomView(tabOne);

            }


        tabOne.setTypeface(type);

        TextView  tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        if(language==1)
        {
            tabTwo.setText("الغياب");
        }else
        {
            tabTwo.setText("Attendance");
        }

        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.absence ,0 , 0);
        if(language==1)
        {
            tabLayout.getTabAt(4).setCustomView(tabTwo);
        }else
        {
            tabLayout.getTabAt(1).setCustomView(tabTwo);
        }

        tabLayout.setAnimation(new AlphaAnimation(Animation.ABSOLUTE, Animation.RELATIVE_TO_PARENT));

        tabTwo.setTypeface(type);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        if(language==1)
        {
            tabThree.setText("التقييم");
        }else
        {
            tabThree.setText("Evaluation");
        }


        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.feedback ,0 , 0);
        if(language==1)
        {
            tabLayout.getTabAt(3).setCustomView(tabThree);
        }else
        {
            tabLayout.getTabAt(2).setCustomView(tabThree);
        }


        tabThree.setTypeface(type);

        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        if(language==1)
        {
            tabFour.setText("اختبارات");
        }else
        {
            tabFour.setText("Exams");
        }

        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.exams2 ,0 , 0);
        if(language==1)
        {
            tabLayout.getTabAt(2).setCustomView(tabFour);
        }else
        {
            tabLayout.getTabAt(3).setCustomView(tabFour);
        }


        tabFour.setTypeface(type);

        TextView  tabFive = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        if(language==1)
        {
            tabFive.setText("الرسائل");
        }else
        {
            tabFive.setText("Messages");
        }

        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.messages ,0 , 0);
        if(language==1)
        {
            tabLayout.getTabAt(1).setCustomView(tabFive);
        }else
        {
            tabLayout.getTabAt(4).setCustomView(tabFive);
        }

        tabLayout.setAnimation(new AlphaAnimation(Animation.ABSOLUTE, Animation.RELATIVE_TO_PARENT));

        tabFive.setTypeface(type);

        TextView tabSix = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        if(language==1)
        {
            tabSix.setText("اشعارات");
        }else
        {
            tabSix.setText("Notifications");
        }

        tabSix.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.notifications ,0 , 0);
        if(language==1)
        {
            tabLayout.getTabAt(0).setCustomView(tabSix);
        }else
        {
            tabLayout.getTabAt(5).setCustomView(tabSix);
        }


        tabSix.setTypeface(type);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
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

}
