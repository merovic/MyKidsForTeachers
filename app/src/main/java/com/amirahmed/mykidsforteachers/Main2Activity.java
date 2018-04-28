package com.amirahmed.mykidsforteachers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Activities.AboutApplicationActivity;
import com.amirahmed.mykidsforteachers.Activities.AboutSchoolActivity;
import com.amirahmed.mykidsforteachers.Activities.ClassesToReportActivity;
import com.amirahmed.mykidsforteachers.Activities.ExamsActivity;
import com.amirahmed.mykidsforteachers.Activities.InboxActivity;
import com.amirahmed.mykidsforteachers.Activities.NavigationDrawerFragment;
import com.amirahmed.mykidsforteachers.Activities.NotificationsActivity;
import com.amirahmed.mykidsforteachers.Activities.ScheduleActivity;
import com.amirahmed.mykidsforteachers.Activities.SettingActivity;
import com.amirahmed.mykidsforteachers.Activities.StudentsReportsActivity2;
import com.amirahmed.mykidsforteachers.Activities.TeachersMessagingActivity;
import com.amirahmed.mykidsforteachers.Utils.NavigationDrawerCallbacks;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;


public class Main2Activity extends Activity implements NavigationDrawerCallbacks,BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar,mToolbar2;

    DrawerLayout drawerLayout;

    ImageView drawerbutton,messagesbutton,aboutbutton,notificationsbutton;

    TinyDB tinyDB;

    int language;

    TextView textView1,textView2,textView3,textView4;

    TextView notification,message,schedule,nothing;

    //TextView notifimenu,messagesmenu,aboutmenu;

    private SliderLayout mDemoSlider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        drawerLayout = findViewById(R.id.drawer);

        tinyDB = new TinyDB(getApplicationContext());

        language = tinyDB.getInt("language");

        textView1 = findViewById(R.id.notificationstext);
        textView2 = findViewById(R.id.messagestext);
        textView3 = findViewById(R.id.schduletext);
        textView4 = findViewById(R.id.nothingtext);

        notification = findViewById(R.id.notifi);
        message = findViewById(R.id.message);
        schedule = findViewById(R.id.schedule);
        nothing = findViewById(R.id.nothing);

       // notifimenu = findViewById(R.id.notifimenu);
       // messagesmenu = findViewById(R.id.messagesmenu);
       // aboutmenu = findViewById(R.id.aboutmenu);


        mToolbar = findViewById(R.id.toolbar_actionbar);
        mToolbar2 = findViewById(R.id.toolbar_actionbar_en);
        if(language==1)
        {
            mToolbar.setVisibility(View.VISIBLE);
            mToolbar2.setVisibility(View.GONE);

            drawerLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            textView1.setText("الاشعارات");
            textView2.setText("الرسائل");
            textView3.setText("الجدول");
            textView4.setText("التقارير");

            notification.setText("اجتماع اةليائ الامور اليوم الموافق السبت");
            message.setText("اجتماع اةليائ الامور اليوم الموافق السبت");
            schedule.setText("الحصة الثالثة - رياضيات - فصل موز");
            nothing.setText("اجتماع اةليائ الامور اليوم الموافق السبت");

           // notifimenu.setText("الاشعارات");
           // messagesmenu.setText("الرسائل");
           // aboutmenu.setText("عن المدرسة");

        }else
        {
            mToolbar.setVisibility(View.GONE);
            mToolbar2.setVisibility(View.VISIBLE);

            drawerLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            textView1.setText("Notifications");
            textView2.setText("Messages");
            textView3.setText("Schedule");
            textView4.setText("Reports");

            notification.setText("Parents Day will be next saturday in our school");
            message.setText("Parents Day will be next saturday in our school");
            schedule.setText("Class 3 - Math - Banana");
            nothing.setText("Parents Day will be next saturday in our school");

           // notifimenu.setText("Notifications");
           // messagesmenu.setText("Messages");
           // aboutmenu.setText("About School");

        }
        //mToolbar.setVisibility(View.GONE);
        //setSupportActionBar(mToolbar);

        messagesbutton = findViewById(R.id.messagesbutton);
        aboutbutton = findViewById(R.id.aboutbutton);
        notificationsbutton = findViewById(R.id.notificationsbutton);



        drawerbutton = findViewById(R.id.drawerbutton);
        drawerbutton.setVisibility(View.GONE);
        drawerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mNavigationDrawerFragment.isDrawerOpen())
                    mNavigationDrawerFragment.closeDrawer();
                else

                    {
                        mNavigationDrawerFragment.openDrawer();
                    }

            }
        });





        // Set up the drawer.

        if(language==1)
        {
            mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
            mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        }else
        {

            mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
            mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar2);

        }

        messagesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this , InboxActivity.class);
                startActivity(intent);
            }
        });

        aboutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this , AboutSchoolActivity.class);
                startActivity(intent);
            }
        });

        notificationsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this , NotificationsActivity.class);
                startActivity(intent);
            }
        });



        mDemoSlider = findViewById(R.id.slider);


        HashMap<String,Integer> file_maps = new HashMap<>();
        file_maps.put("School-1",R.drawable.school);
        file_maps.put("School-2",R.drawable.school2);
        file_maps.put("School-3",R.drawable.school3);
        file_maps.put("School-4",R.drawable.school4);
        file_maps.put("School-5",R.drawable.school5);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);




    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        if(position==0)
        {
            Intent intent = new Intent(Main2Activity.this , ScheduleActivity.class);
            startActivity(intent);
        }else if(position==1)
        {
            Intent intent = new Intent(Main2Activity.this , ExamsActivity.class );
            startActivity(intent);
        }else if(position==2)
        {
            Intent intent = new Intent(Main2Activity.this , TeachersMessagingActivity.class );
            startActivity(intent);
        }
        else if(position==3)
        {
            Intent intent = new Intent(Main2Activity.this , ClassesToReportActivity.class );
            startActivity(intent);
        }else if(position==4)
        {
            Intent intent = new Intent(Main2Activity.this , StudentsReportsActivity2.class );
            startActivity(intent);
        }else if(position==5)
        {
            Intent intent = new Intent(Main2Activity.this , SettingActivity.class );
            startActivity(intent);
        }else if(position==6)
        {
            Intent intent = new Intent(Main2Activity.this , AboutApplicationActivity.class );
            startActivity(intent);
        }else if(position==7)
        {
            Intent intent = new Intent(Main2Activity.this , AboutApplicationActivity.class );
            startActivity(intent);
        }

    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main2, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mDemoSlider.stopAutoCycle();
        super.onDestroy();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
