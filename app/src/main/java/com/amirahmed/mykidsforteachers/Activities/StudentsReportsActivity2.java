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

import com.amirahmed.mykidsforteachers.Fragments.ReportsAttendanceFragment;
import com.amirahmed.mykidsforteachers.Fragments.ReportsEvaluationFragment;
import com.amirahmed.mykidsforteachers.Fragments.ReportsExamsFragment;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class StudentsReportsActivity2 extends AppCompatActivity {

    private Toolbar mToolbar,mToolbar2;
    private TabLayout tabLayout;

    TinyDB tinyDB;

    int language;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentreports);

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

            mToolbar.setTitle("تقارير الطلاب");

            TextView textView = mToolbar.findViewById(R.id.toolbartext);
            textView.setText("تقارير الطلاب");

            getActionBarTextView().setText("تقارير الطلاب");

            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StudentsReportsActivity2.super.onBackPressed();
                }
            });

            getActionBarTextView().setVisibility(View.GONE);
        }else
            {
                mToolbar2.setVisibility(View.VISIBLE);
                mToolbar.setVisibility(View.GONE);

                mToolbar2.setTitle("Students Reports");

                TextView textView = mToolbar2.findViewById(R.id.toolbartext);
                textView.setText("Students Reports");

                getActionBarTextView().setText("Students Reports");

                arrowen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StudentsReportsActivity2.super.onBackPressed();
                    }
                });


                getActionBarTextView().setVisibility(View.GONE);
            }



        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        if(language==1)
        {
            viewPager.setCurrentItem(2);
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
            adapter.addFragment(new ReportsExamsFragment(), "");
            adapter.addFragment(new ReportsEvaluationFragment(), "");
            adapter.addFragment(new ReportsAttendanceFragment(), "");
        }else
            {
                adapter.addFragment(new ReportsAttendanceFragment(), "");
                adapter.addFragment(new ReportsEvaluationFragment(), "");
                adapter.addFragment(new ReportsExamsFragment(), "");
            }


        adapter.notifyDataSetChanged();

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);

    }


    private void setupTabIcons() {

        //tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        Typeface type = Typeface.createFromAsset(this.getAssets(),"fonts/lmaar.ttf");

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        if(language==1)
        {
            tabOne.setText("الغياب");
        }else
            {
                tabOne.setText("Attendance");
            }

        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tabscalender ,0 , 0);
        if(language==1)
        {
            tabLayout.getTabAt(2).setCustomView(tabOne);
        }else
        {
            tabLayout.getTabAt(0).setCustomView(tabOne);
        }


        tabOne.setTypeface(type);

        TextView  tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        if(language==1)
        {
            tabTwo.setText("التقييم");
        }else
        {
            tabTwo.setText("Evaluation");
        }

        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.absence ,0 , 0);
        if(language==1)
        {
            tabLayout.getTabAt(1).setCustomView(tabTwo);
        }else
        {
            tabLayout.getTabAt(1).setCustomView(tabTwo);
        }

        tabLayout.setAnimation(new AlphaAnimation(Animation.ABSOLUTE, Animation.RELATIVE_TO_PARENT));

        tabTwo.setTypeface(type);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        if(language==1)
        {
            tabThree.setText("الاختبارات");
        }else
        {
            tabThree.setText("Exams");
        }


        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.feedback ,0 , 0);
        if(language==1)
        {
            tabLayout.getTabAt(0).setCustomView(tabThree);
        }else
        {
            tabLayout.getTabAt(2).setCustomView(tabThree);
        }


        tabThree.setTypeface(type);


    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

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
