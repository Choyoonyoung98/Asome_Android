package com.example.asome.asome_sourcerequire.Project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.asome.asome_sourcerequire.R;

public class NewProjectActivity extends AppCompatActivity {
//    EditText projName, projAbout, roleName, userName, roleStartDate, roleEndDate;
//    String proj_name, proj_about, role_name, user_name, role_start_date, role_end_date;

   LinearLayout aboutForm;
   Animation translateDownAnim;
   Button nextBtn,nextBtn2,addBtn,createBtn,detailBtn;
    LinearLayout roleLayout;
    Sub layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        /////////////////////////////////////////////////////////////////
//        projName = findViewById(R.id.editText9);
//        projAbout = findViewById(R.id.editText10);
//        roleName = findViewById(R.id.editText9);
//        userName = findViewById(R.id.editText10);
//        roleStartDate = findViewById(R.id.editText9);
//        roleEndDate = findViewById(R.id.editText10);
//
//        proj_name = projName.getText().toString();
//        proj_about = projAbout.getText().toString();
//        role_name = roleName.getText().toString();
//        user_name = userName.getText().toString();
//        role_start_date = roleStartDate.getText().toString();
//        role_end_date = roleEndDate.getText().toString();
        /////////////////////////////////////////////////////////////////

        aboutForm = (LinearLayout)findViewById(R.id.AboutForm);
        nextBtn = findViewById(R.id.nextbtn);
        nextBtn2 = findViewById(R.id.nextbtn2);
        addBtn = findViewById(R.id.addbtn);
        createBtn = findViewById(R.id.createbtn);
        roleLayout = (LinearLayout)findViewById(R.id.roleLayout);

        translateDownAnim =  AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_down);
    }



    public void onNextClicked(View view) {
        nextBtn.setVisibility(View.GONE);
        aboutForm.setVisibility(View.VISIBLE);
        aboutForm.startAnimation(translateDownAnim);
        nextBtn2.setVisibility(View.VISIBLE);
    }

    public void onNextClicked2(View view) {
        nextBtn2.setVisibility(View.GONE);
        roleLayout.setVisibility(View.VISIBLE);
        roleLayout.startAnimation(translateDownAnim);
        addBtn.setVisibility(View.VISIBLE);
        createBtn.setVisibility(View.VISIBLE);

    }
    public void addRoleClicked(View view) {
        layout = new Sub(getApplicationContext());
        roleLayout.addView(layout);
        /*여기다가 다이얼로그 이동 부분 추가*/
        Button btn = (Button)layout.findViewById(R.id.detailBtn);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(NewProjectActivity.this, RoleDetailDialogActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onCreateClicked(View view) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
