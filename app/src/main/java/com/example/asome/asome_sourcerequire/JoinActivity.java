package com.example.asome.asome_sourcerequire;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class JoinActivity extends AppCompatActivity {

    EditText userName, userPwd, userDepartment, userPhone, userPosition, userCompany, userEmail;

    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "asome.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        userName = findViewById(R.id.user_name);
        userPwd = findViewById(R.id.user_pwd);
        userDepartment = findViewById(R.id.user_phone);
        userPhone = findViewById(R.id.user_phone);
        userPosition = findViewById(R.id.user_position);
        userCompany = findViewById(R.id.user_company);
        userEmail = findViewById(R.id.user_email);


    }
    String user_name = userName.getText().toString();
    String user_pwd = userPwd.getText().toString();
    String user_department = userDepartment.getText().toString();
    String user_phone = userPhone.getText().toString();
    String user_position = userPosition.getText().toString();
    String user_email = userEmail.getText().toString();




    public void onJoinClicked(View view) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
