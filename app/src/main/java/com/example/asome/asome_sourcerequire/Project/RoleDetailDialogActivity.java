package com.example.asome.asome_sourcerequire.Project;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asome.asome_sourcerequire.R;

import java.util.ArrayList;

public class RoleDetailDialogActivity extends Activity {
    AutoCompleteTextView testTag;
    TextView showTag, showStartDate, showEndDate, textStart, textEnd;
    DatePicker dp_start_date, dp_end_date;
    LinearLayout datePage, datePage2;
    String start_day, start_month, start_year, end_day, end_month, end_year;
    LinearLayout rolePage;
    roleSub layout;
    Button setStartBtn, setEndBtn, selectBtn, selectBtn2, showBtn,btn_role_add;
    Boolean startD = false;
    Boolean endD = false;

    LinearLayout showPage;

    String final_end_date, final_start_date;
    ListView lv_role;
    Role role;
    ArrayList<Role> role_arr_list = new ArrayList<Role>();;
    RoleAdapter roleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_role_detail_dialog);

        rolePage = findViewById(R.id.rolePage);
        testTag = findViewById(R.id.test_tag);
        ArrayAdapter<String> Tadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, USER_TAG);
        testTag.setAdapter(Tadapter);

        dp_start_date = findViewById(R.id.datePicker);
        dp_end_date = findViewById(R.id.datePicker2);
        datePage = findViewById(R.id.datePage);
        datePage2 = findViewById(R.id.datePage2);


        textStart = findViewById(R.id.textStart);
        textEnd = findViewById(R.id.textEnd);


        setStartBtn = (Button) findViewById(R.id.setStartBtn);
        setEndBtn = (Button) findViewById(R.id.setEndBtn);
        selectBtn = (Button) findViewById(R.id.selectBtn);
        selectBtn2 = (Button) findViewById(R.id.selectBtn2);

        btn_role_add =(Button)findViewById(R.id.btn_role_add);

        lv_role = (ListView) findViewById(R.id.lv_role);


        btn_role_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                role = new Role("여기 이름값1", final_start_date, final_end_date);

                role_arr_list.add(role);
               /* Role role2 = new Role("여기 이름값2", final_start_date, final_end_date);
                role_arr_list.add(role2);*/
               Toast.makeText(getApplicationContext(),"complete",Toast.LENGTH_LONG).show();
                roleAdapter = new RoleAdapter(getApplicationContext(), role_arr_list);
                lv_role.setAdapter(roleAdapter);
                roleAdapter.notifyDataSetChanged();
            }
        });
    }

    private static final String[] USER_TAG = new String[]{
            "@조윤영", "@김연지", "@황은선", "@이경연"
    };

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



    public void onAddClicked(View v,Role role) {
        // layout = new roleSub(getApplicationContext());

// ChatList cl = new ChatList("김지원 멘토님", "11", "123", "안녕", "ㅇㄹㄴㄴㅇㄹ");

   /*     showTag = layout.findViewById(R.id.resultUser);
        showStartDate = layout.findViewById(R.id.resultStartDay);
        showEndDate = layout.findViewById(R.id.resultEndDay);


        String textTag = testTag.getText().toString();
        String tag = textTag.substring(1, textTag.length());
        String show_tag = tag + ":";
        showTag.setText(show_tag);
        showStartDate.setText(final_start_date);
        showEndDate.setText(final_end_date);*/

        // showPage.addView(layout);
    }
}



