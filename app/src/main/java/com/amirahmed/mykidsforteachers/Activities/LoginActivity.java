package com.amirahmed.mykidsforteachers.Activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Main2Activity;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;


public class LoginActivity extends Activity{

    Button login;

    TinyDB tinyDB;

    LinearLayout username,password;

    TextInputLayout usernametext,passwordtext;

    AutoCompleteTextView usernametext1,passwordtext1;

    TextView resetpass;

    int language;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.loginbutton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this , Main2Activity.class );
                startActivity(intent);
            }
        });


        tinyDB = new TinyDB(getApplicationContext());

        language = tinyDB.getInt("language");

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        usernametext = findViewById(R.id.usenametext);
        passwordtext = findViewById(R.id.passwordtext);

        usernametext1 = findViewById(R.id.usenametext1);
        passwordtext1 = findViewById(R.id.passwordtext1);

        resetpass = findViewById(R.id.resetpass);

        login = findViewById(R.id.loginbutton);

        if(language == 1)
        {

        }else
        {
            username.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            password.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


            usernametext.setTextDirection(View.TEXT_DIRECTION_LTR);
            passwordtext.setTextDirection(View.TEXT_DIRECTION_LTR);


            usernametext.setHint("Username");
            passwordtext.setHint("Password");

            resetpass.setText("Forget Password ?");

            login.setText("Sign in");
        }
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
