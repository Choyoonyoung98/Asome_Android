package com.example.asome.asome_sourcerequire.Project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asome.asome_sourcerequire.R;

import java.util.ArrayList;

public class RoleDetailDialogActivity extends Activity {
    //AutoCompleteTextView testTag;
    TextView showTag, showStartDate, showEndDate, textStart, textEnd;
    DatePicker dp_start_date, dp_end_date;
    LinearLayout datePage, datePage2;
    String start_day, start_month, start_year, end_day, end_month, end_year;
    LinearLayout rolePage;
    Button setStartBtn, setEndBtn, selectBtn, selectBtn2, showBtn, btn_role_add,btn_complete;


    EditText et_role_name;
    String final_end_date, final_start_date;
    ListView lv_role;
    Role role;
    ArrayList<Role> role_arr_list = new ArrayList<Role>();
    RoleAdapter roleAdapter;


    String role_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_role_detail_dialog);


        et_role_name = (EditText) findViewById(R.id.et_role_name);
        role_name = et_role_name.getText().toString();

        dp_start_date = findViewById(R.id.datePicker);
        dp_end_date = findViewById(R.id.datePicker2);


        textStart = findViewById(R.id.textStart);
        textEnd = findViewById(R.id.textEnd);


        setStartBtn = (Button) findViewById(R.id.setStartBtn);
        setEndBtn = (Button) findViewById(R.id.setEndBtn);
        selectBtn = (Button) findViewById(R.id.selectBtn);
        selectBtn2 = (Button) findViewById(R.id.selectBtn2);

        btn_role_add = (Button) findViewById(R.id.btn_role_add);

        lv_role = (ListView) findViewById(R.id.lv_role);
        datePage = (LinearLayout) findViewById(R.id.datePage);
        datePage2 = (LinearLayout) findViewById(R.id.datePage2);
        btn_complete = (Button)findViewById(R.id.btn_complete);

        btn_role_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                role = new Role(role_name, final_start_date, final_end_date);

                role_arr_list.add(role);
                Toast.makeText(getApplicationContext(), "complete", Toast.LENGTH_LONG).show();
                roleAdapter = new RoleAdapter(getApplicationContext(), role_arr_list);
                lv_role.setAdapter(roleAdapter);
                roleAdapter.notifyDataSetChanged();
            }
        });


        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoleDetailDialogActivity.this, NewProjectActivity.class);
               /* intent.putExtra("start_date", final_start_date);
                intent.putExtra("end_date", final_end_date);
                intent.putExtra("name", "이름값");//역할값, 프로젝트 UUID*/
                intent.putExtra("some",role_arr_list);
    //            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           //     intent.putParcelableArrayListExtra("key", (ArrayList<? extends Parcelable>) role_arr_list);

                startActivity(intent);
                finish();

            }
        });
    }


    public void onBackClicked(View view) {
        finish();
    }


    public void onShowDatePicker1(View v) {
        datePage.setVisibility(v.VISIBLE);
    }

    public void onShowDatePicker2(View v) {
        datePage2.setVisibility(v.VISIBLE);
    }

    public void onSelectClicked1(View v) {
        datePage.setVisibility(v.GONE);
        start_day = "" + dp_start_date.getDayOfMonth();
        int m1 = dp_start_date.getMonth() + 1;
        start_month = "" + m1;
        start_year = "" + dp_start_date.getYear();
        final_start_date = start_year + "-" + start_month + "-" + start_day;
        textStart.setText(final_start_date);
    }

    public void onSelectClicked2(View v) {
        datePage2.setVisibility(v.GONE);
        end_day = "" + dp_end_date.getDayOfMonth();
        int m2 = dp_end_date.getMonth() + 1;
        end_month = "" + m2;
        end_year = "" + dp_end_date.getYear();
        final_end_date = end_year + "-" + end_month + "-" + end_day;
        textEnd.setText(final_end_date);
    }

}



