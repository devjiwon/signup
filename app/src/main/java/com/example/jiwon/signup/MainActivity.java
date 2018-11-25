package com.example.jiwon.signup;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Member;

public class MainActivity extends  AppCompatActivity {

    TextView emailTv, pwdTv;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        String email = intent.getStringExtra("email");
        String pwd = intent.getStringExtra("pwd");
        emailTv = (TextView)findViewById(R.id.emailTv);
        pwdTv = (TextView)findViewById(R.id.pwdTv);
        emailTv.setText(email);
        pwdTv.setText(pwd);
    }
}