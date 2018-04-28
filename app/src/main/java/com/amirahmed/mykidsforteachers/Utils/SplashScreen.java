package com.amirahmed.mykidsforteachers.Utils;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.amirahmed.mykidsforteachers.Activities.LoginActivity;
import com.amirahmed.mykidsforteachers.R;
import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashScreen extends Activity {

    TinyDB tinydb;
    ImageView english,arabic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tinydb = new TinyDB(getApplicationContext());
        //tinydb.clear();

        english = findViewById(R.id.englishlang);
        arabic = findViewById(R.id.arabiclang);

        Glide.with(this).load(R.drawable.langeng).into(english);
        Glide.with(this).load(R.drawable.langarab).into(arabic);



        YoYo.with(Techniques.Pulse)
                .duration(1350)
                .repeat(999999999)
                .playOn(english);

        YoYo.with(Techniques.Pulse)
                .duration(1350)
                .repeat(999999999)
                .playOn(arabic);



        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinydb.putInt("language",1);
                Intent intent = new Intent(SplashScreen.this , LoginActivity.class);
                startActivity(intent);
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinydb.putInt("language",2);
                Intent intent = new Intent(SplashScreen.this , LoginActivity.class);
                startActivity(intent);
            }
        });

        ImageView splash = findViewById(R.id.splash);
        Glide.with(this).load(R.drawable.splashscreen).into(splash);




    }
}
