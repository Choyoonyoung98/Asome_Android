package com.example.asome.asome_sourcerequire.Project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asome.asome_sourcerequire.R;
import com.example.asome.asome_sourcerequire.Utils.HTTP.ProjInsert;

import java.util.ArrayList;

public class NewProjectActivity extends AppCompatActivity {
//    EditText projName, projAbout, roleName, userName, roleStartDate, roleEndDate;
//    String proj_name, proj_about, role_name, user_name, role_start_date, role_end_date;

    LinearLayout aboutForm;
    //Animation translateDownAnim;
    Button btn_set_role, nextBtn2, addBtn, btn_proj_create, detailBtn;
    LinearLayout roleLayout;
    Sub layout;
    EditText et_proj_name, et_proj_about;
    String proj_name, proj_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        Intent intent = getIntent();
        ArrayList<Role> a = (ArrayList<Role>) intent.getSerializableExtra("some");

        if (a != null)
            Toast.makeText(getApplicationContext(), a.size(), Toast.LENGTH_LONG).show();
        /////////////////////////////////////////////////////////////////
        et_proj_name = (EditText) findViewById(R.id.et_proj_name);
        et_proj_about = (EditText) findViewById(R.id.et_proj_about);
        btn_set_role = (Button) findViewById(R.id.btn_set_role);
//        roleName = findViewById(R.id.editText9);
//        userName = findViewById(R.id.editText10);
//        roleStartDate = findViewById(R.id.editText9);
//        roleEndDate = findViewById(R.id.editText10);
//

//        role_name = roleName.getText().toString();
//        user_name = userName.getText().toString();
//        role_start_date = roleStartDate.getText().toString();
//        role_end_date = roleEndDate.getText().toString();
        /////////////////////////////////////////////////////////////////

        aboutForm = (LinearLayout) findViewById(R.id.AboutForm);

        addBtn = findViewById(R.id.addbtn);
        btn_proj_create = (Button) findViewById(R.id.createbtn);
        roleLayout = (LinearLayout) findViewById(R.id.roleLayout);

        btn_proj_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proj_name = et_proj_name.getText().toString();
                proj_about = et_proj_about.getText().toString();
                //    public ProjInsert(String proj_UUID, String proj_name, String proj_about, String role_name, String role_user_id, String role_start_date, String role_end_date,String role_status) {
                ProjInsert projInsert = new ProjInsert("uuid", proj_name, proj_about, "role_name", "11", "st", "ed", "ong");
                projInsert.execute();
                Toast.makeText(getApplicationContext(), "cp", Toast.LENGTH_LONG).show();
            }
        });
        btn_set_role.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RoleDetailDialogActivity.class));

            }
        });

        //  translateDownAnim =  AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_down);
    }


    public void onCreateClicked(View view) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
