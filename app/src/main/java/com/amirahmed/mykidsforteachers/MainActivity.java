package com.amirahmed.mykidsforteachers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amirahmed.mykidsforteachers.Activities.ExamsActivity;
import com.amirahmed.mykidsforteachers.Activities.NotificationsandMessagesActivity;
import com.amirahmed.mykidsforteachers.Activities.TestActivity;
import com.amirahmed.mykidsforteachers.Utils.AnimatorUtils;
import com.amirahmed.mykidsforteachers.Utils.ClipRevealFrame;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;
import com.ogaclejapan.arclayout.ArcLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    Toast toast = null;
    View rootLayout;
    ClipRevealFrame menuLayout;
    ArcLayout arcLayout;
    LinearLayout linearLayout;
    //View centerItem;
    TinyDB tinydb;

    Button b1, b2, b3, b4, b5, b6, b7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tinydb = new TinyDB(getApplicationContext());

        linearLayout = findViewById(R.id.linearlaycate);

        if(hasSoftKeys(getWindowManager()))
        {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)linearLayout.getLayoutParams();
            params.setMargins(0, 0, 0, 135);
            linearLayout.setLayoutParams(params);
        }

        b1 = findViewById(R.id.attendance);
        b2 = findViewById(R.id.homework);
        b3 = findViewById(R.id.schedule);
        b4 = findViewById(R.id.exams);
        b5 = findViewById(R.id.scores);
        b6 = findViewById(R.id.messages);
        b7 = findViewById(R.id.center_item);

        b1.setBackgroundResource(R.drawable.mainchange1);
        b2.setBackgroundResource(R.drawable.mainchange2);
        b3.setBackgroundResource(R.drawable.mainchange3);
        b4.setBackgroundResource(R.drawable.mainchange4);
        b5.setBackgroundResource(R.drawable.mainchange5);
        b6.setBackgroundResource(R.drawable.mainchange6);
        b7.setBackgroundResource(R.drawable.mainchange7);

        ImageView img3 = findViewById(R.id.notificationsbutton);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this , NotificationsandMessagesActivity.class );
                tinydb.putString("state","notifications");
                startActivity(intent);
            }
        });

        /*int language = tinydb.getInt("language");

        if(language==1)
        {
            b1.setBackgroundResource(R.drawable.mainchange1);
            b2.setBackgroundResource(R.drawable.mainchange2);
            b3.setBackgroundResource(R.drawable.mainchange3);
            b4.setBackgroundResource(R.drawable.mainchange4);
            b5.setBackgroundResource(R.drawable.mainchange5);
            b6.setBackgroundResource(R.drawable.mainchange6);
            b7.setBackgroundResource(R.drawable.mainchange7);
        } else
        {
            b1.setBackgroundResource(R.drawable.mainchangeen1);
            b2.setBackgroundResource(R.drawable.mainchangeen2);
            b3.setBackgroundResource(R.drawable.mainchangeen3);
            b4.setBackgroundResource(R.drawable.mainchangeen4);
            b5.setBackgroundResource(R.drawable.mainchangeen5);
            b6.setBackgroundResource(R.drawable.mainchangeen6);
            b7.setBackgroundResource(R.drawable.mainchangeen7);
        }*/

        b1.setSoundEffectsEnabled(false);
        b2.setSoundEffectsEnabled(false);
        b3.setSoundEffectsEnabled(false);
        b4.setSoundEffectsEnabled(false);
        b5.setSoundEffectsEnabled(false);
        b6.setSoundEffectsEnabled(false);
        b7.setSoundEffectsEnabled(false);



        rootLayout = findViewById(R.id.root_layout);
        menuLayout = findViewById(R.id.menu_layout);
        arcLayout = findViewById(R.id.arc_layout);

        b7.setOnClickListener(this);
        for (int i = 0, size = arcLayout.getChildCount(); i < size; i++) {
            arcLayout.getChildAt(i).setOnClickListener(this);
        }

        findViewById(R.id.fab).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            onFabClick(v);
            findViewById(R.id.fab).setVisibility(View.GONE);
            return;
        }

        Intent intent;
        final MediaPlayer mp = MediaPlayer.create(this,R.raw.snap);

        switch (v.getId())
        {
            case R.id.schedule :
                mp.start();
                intent = new Intent(MainActivity.this , TestActivity.class );
                tinydb.putInt("language",1);
                startActivity(intent);
                break;
            case R.id.homework :
                mp.start();
                /*intent = new Intent(MainActivity.this , HomeWorkActivity.class );
                startActivity(intent);*/
                break;
            case R.id.attendance :
                mp.start();
                intent = new Intent(MainActivity.this , ExamsActivity.class);
                tinydb.putString("state","attendance");
                startActivity(intent);
                break;
            case R.id.exams :
                mp.start();
                intent = new Intent(MainActivity.this , ExamsActivity.class );
                tinydb.putString("state","exams");
                startActivity(intent);
                break;
            case R.id.scores :
                mp.start();
                intent = new Intent(MainActivity.this , ExamsActivity.class );
                tinydb.putString("state","evaluation");
                startActivity(intent);
                break;
            case R.id.messages :
                mp.start();
                intent = new Intent(MainActivity.this , NotificationsandMessagesActivity.class );
                tinydb.putString("state","messages");
                startActivity(intent);
                break;
            case R.id.center_item :
                mp.start();
                intent = new Intent(MainActivity.this , Main2Activity.class);
                startActivity(intent);
                break;

        }


    }

    private void onFabClick(View v) {
        int x = (v.getLeft() + v.getRight()) / 2;
        int y = (v.getTop() + v.getBottom()) / 2;
        float radiusOfFab = 1f * v.getWidth() / 2f;
        float radiusFromFabToRoot = (float) Math.hypot(
                Math.max(x, rootLayout.getWidth() - x),
                Math.max(y, rootLayout.getHeight() - y));

        if (v.isSelected()) {
            hideMenu(x, y, radiusFromFabToRoot, radiusOfFab);
        } else {
            showMenu(x, y, radiusOfFab, radiusFromFabToRoot);
        }
        v.setSelected(!v.isSelected());
    }


    private void showMenu(int cx, int cy, float startRadius, float endRadius) {
        menuLayout.setVisibility(View.VISIBLE);

        List<Animator> animList = new ArrayList<>();

        Animator revealAnim = createCircularReveal(menuLayout, cx, cy, startRadius, endRadius);
        revealAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        revealAnim.setDuration(200);

        animList.add(revealAnim);
        animList.add(createShowItemAnimator(b7));

        for (int i = 0, len = arcLayout.getChildCount(); i < len; i++) {
            animList.add(createShowItemAnimator(arcLayout.getChildAt(i)));
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.playSequentially(animList);
        animSet.start();
    }

    private void hideMenu(int cx, int cy, float startRadius, float endRadius) {
        List<Animator> animList = new ArrayList<>();

        for (int i = arcLayout.getChildCount() - 1; i >= 0; i--) {
            animList.add(createHideItemAnimator(arcLayout.getChildAt(i)));
        }

        animList.add(createHideItemAnimator(b7));

        Animator revealAnim = createCircularReveal(menuLayout, cx, cy, startRadius, endRadius);
        revealAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        revealAnim.setDuration(200);
        revealAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                menuLayout.setVisibility(View.INVISIBLE);
            }
        });

        animList.add(revealAnim);

        AnimatorSet animSet = new AnimatorSet();
        animSet.playSequentially(animList);
        animSet.start();

    }

    private Animator createShowItemAnimator(View item) {
        float dx = b7.getX() - item.getX();
        float dy = b7.getY() - item.getY();

        item.setScaleX(0f);
        item.setScaleY(0f);
        item.setTranslationX(dx);
        item.setTranslationY(dy);

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.scaleX(0f, 1f),
                AnimatorUtils.scaleY(0f, 1f),
                AnimatorUtils.translationX(dx, 0f),
                AnimatorUtils.translationY(dy, 0f)
        );

        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(50);
        return anim;
    }

    private Animator createHideItemAnimator(final View item) {
        final float dx = b7.getX() - item.getX();
        final float dy = b7.getY() - item.getY();

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.scaleX(1f, 0f),
                AnimatorUtils.scaleY(1f, 0f),
                AnimatorUtils.translationX(0f, dx),
                AnimatorUtils.translationY(0f, dy)
        );

        anim.setInterpolator(new DecelerateInterpolator());
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                item.setTranslationX(0f);
                item.setTranslationY(0f);
            }
        });
        anim.setDuration(50);
        return anim;
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    private Animator createCircularReveal(final ClipRevealFrame view, int x, int y, float startRadius, float endRadius) {
        final Animator reveal;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            reveal = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, endRadius);
        } else {
            view.setClipOutLines(true);
            view.setClipCenter(x, y);
            reveal = ObjectAnimator.ofFloat(view, "ClipRadius", startRadius, endRadius);
            reveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setClipOutLines(false);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
        return reveal;
    }


    public boolean hasSoftKeys(WindowManager windowManager){
        boolean hasSoftwareKeys;

        Display d = getWindowManager().getDefaultDisplay();

        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        d.getRealMetrics(realDisplayMetrics);

        int realHeight = realDisplayMetrics.heightPixels;
        int realWidth = realDisplayMetrics.widthPixels;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getMetrics(displayMetrics);

        int displayHeight = displayMetrics.heightPixels;
        int displayWidth = displayMetrics.widthPixels;

        hasSoftwareKeys =  (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
        return hasSoftwareKeys;
    }



}
