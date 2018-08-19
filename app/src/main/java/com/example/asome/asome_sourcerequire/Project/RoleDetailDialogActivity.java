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
import com.example.asome.asome_sourcerequire.RangePicker;
import com.example.asome.asome_sourcerequire.Utils.HTTP.ProjInsert;
import com.example.asome.asome_sourcerequire.Utils.HTTP.RoleInsert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.hori_id;
import static com.example.asome.asome_sourcerequire.Main.TwoFragment.projectAdapter;

public class RoleDetailDialogActivity extends Activity {
    //AutoCompleteTextView testTag;
    TextView showTag, showStartDate, showEndDate, textStart, textEnd;
    DatePicker dp_start_date, dp_end_date;
    LinearLayout datePage, datePage2;
    String start_day, start_month, start_year, end_day, end_month, end_year, proj_id;
    LinearLayout rolePage;
    Button setStartBtn, setEndBtn, selectBtn, selectBtn2, showBtn, btn_role_add, btn_complete;


    EditText et_role_name;
    String name, about, final_end_date, final_start_date;
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

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        about = intent.getStringExtra("about");

        et_role_name = (EditText) findViewById(R.id.et_role_name);

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
        btn_complete = (Button) findViewById(R.id.btn_complete);

        btn_role_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                role_name = et_role_name.getText().toString();

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
                ProjInsert projInsert = new ProjInsert(name, about, "role_name", "11", "st", "ed", "ong");
                try {
                    proj_id = projInsert.execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < role_arr_list.size(); i++) {
                    RoleInsert roleInsert = new RoleInsert(proj_id, role_arr_list.get(i).role_name, hori_id, role_arr_list.get(i).getRole_start_date(), role_arr_list.get(i).getRole_end_date(), role_arr_list.get(i).getRole_end_date());//상태만 변경
                    roleInsert.execute();
                    Toast.makeText(getApplicationContext(), proj_id, Toast.LENGTH_LONG).show();

                }
                overridePendingTransition(R.anim.anim_slide_out_right, R.anim.anim_slide_in_left);
                projectAdapter.addItem(new ProjectItem(name, about, proj_id));
                finish();

            }
        });

       /* setStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(getApplicationContext(), RangePicker.class));
                RangePicker rangePicker = new RangePicker(RoleDetailDialogActivity.this );
                rangePicker.show();
            }
        });*/
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
        int year = dp_start_date.getYear();
        int month = dp_start_date.getMonth();
        int day = dp_start_date.getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");String strDate = format.format(calendar.getTime());

        final_start_date =strDate;
        textStart.setText(final_start_date);


    }

    public void onSelectClicked2(View v) {
        datePage2.setVisibility(v.GONE);
  /*      end_day = "" + dp_end_date.getDayOfMonth();
        int m2 = dp_end_date.getMonth() + 1;
        end_month = "" + m2;
        end_year = "" + dp_end_date.getYear();
        final_end_date = end_year + "-" + end_month + "-" + end_day;
*/

        int year = dp_end_date.getYear();
        int month = dp_end_date.getMonth();
        int day = dp_end_date.getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(calendar.getTime());

        final_end_date =strDate;
        textEnd.setText(final_end_date);
    }

}



