package com.example.asome.asome_sourcerequire.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.asome.asome_sourcerequire.Utils.HTTP.JoinInsert;
import com.example.asome.asome_sourcerequire.R;

public class JoinActivity extends AppCompatActivity {

    EditText userName, userPwd, userDepartment, userPhone, userPosition, userCompany, userEmail;
    String user_name,user_pwd, user_department,user_phone, user_position, user_company, user_email;

    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "asome.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        userName = findViewById(R.id.user_name);
        userPwd = findViewById(R.id.user_pwd);
        userDepartment = findViewById(R.id.user_department);
        userPhone = findViewById(R.id.user_phone);
        userPosition = findViewById(R.id.user_position);
        userCompany = findViewById(R.id.user_company);
        userEmail = findViewById(R.id.user_email);


    }


    public void onJoinClicked(View view) {


        user_name = userName.getText().toString();
        user_pwd = userPwd.getText().toString();
        user_department = userDepartment.getText().toString();
        user_phone = userPhone.getText().toString();
        user_position = userPosition.getText().toString();
        user_company = userCompany.getText().toString();
        user_email = userEmail.getText().toString();


        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        JoinInsert joinInsert = new JoinInsert(user_name, user_pwd, user_department, user_phone, user_position, user_company, user_email);
        joinInsert.execute();
        //TODO:SHARED_PP
        finish();
    }
}
