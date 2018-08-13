package com.example.asome.asome_sourcerequire.Project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.asome.asome_sourcerequire.R;

public class NewProjectActivity extends AppCompatActivity {
//    EditText projName, projAbout, roleName, userName, roleStartDate, roleEndDate;
//    String proj_name, proj_about, role_name, user_name, role_start_date, role_end_date;

    LinearLayout aboutForm;
    //Animation translateDownAnim;
    Button  nextBtn2, addBtn, btn_proj_create, detailBtn;
    LinearLayout roleLayout;
    Sub layout;
    EditText et_proj_name, et_proj_about;
    String proj_name, proj_about;
    String proj_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

       // final ArrayList<Role> arr_role = (ArrayList<Role>) getIntent().getSerializableExtra("some");

        et_proj_name = (EditText) findViewById(R.id.et_proj_name);
        et_proj_about = (EditText) findViewById(R.id.et_proj_about);
        aboutForm = (LinearLayout) findViewById(R.id.AboutForm);

        addBtn = findViewById(R.id.addbtn);
        btn_proj_create = (Button) findViewById(R.id.createbtn);
        roleLayout = (LinearLayout) findViewById(R.id.roleLayout);

        btn_proj_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proj_name = et_proj_name.getText().toString();
                proj_about = et_proj_about.getText().toString();
                Intent intent = new Intent(getApplicationContext(),RoleDetailDialogActivity.class);
                intent.putExtra("name",proj_name);
                intent.putExtra("about",proj_about);
                startActivity(intent);
                finish();

            }
        });

    }

}
