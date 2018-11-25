package com.example.jiwon.signup;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    MembershipOpenHelper openHelper;
    EditText emailEt, pwdEt;
    SQLiteDatabase db;
    Button loginBtn, joinBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        openHelper = new MembershipOpenHelper(this);
        db = openHelper.getWritableDatabase();
        emailEt = (EditText)findViewById(R.id.emailEt);
        pwdEt = (EditText) findViewById(R.id.pwdEt);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        joinBtn = (Button)findViewById(R.id.joinBtn);
        loginBtn.setOnClickListener(listener);
        joinBtn.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.joinBtn:
                    startActivity(new Intent(LoginActivity.this, JoinActivity.class));
                    finish();
                    break;
                case R.id.loginBtn:
                    String email = emailEt.getText().toString();
                    String pwd = pwdEt.getText().toString();

                    String sql = "Select * from membership where email = '"+email+"'and pwd = '"+pwd+"'";
                    Cursor cursor = db.rawQuery(sql, null);
                    while (cursor.moveToNext()){
                        String no = cursor.getString(0);
                        String rest_id = cursor.getString(1);
                        Log.d("select","no : "+ "\nrest_id : " + rest_id);
                    }
                    if(cursor.getCount() == 1){
                        Toast.makeText(LoginActivity.this, email+"님 환영합니다.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("email",email);
                        intent.putExtra("pwd",pwd);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "이메일 또는 비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                    break;
            }
        }
    };
}
