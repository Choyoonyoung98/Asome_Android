package com.example.asome.asome_sourcerequire;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RoleDetailDialogActivity extends Activity {
    AutoCompleteTextView testTag;
    TextView showTag,showStartDate,showEndDate;
    DatePicker datePicker;
    LinearLayout datePage;
    String day, month, year;
    Boolean startD=false;
    Boolean endD=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_role_detail_dialog);
        showTag = findViewById(R.id.resultUser);

        testTag = findViewById(R.id.test_tag);
        ArrayAdapter<String> Tadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, USER_TAG);
        testTag.setAdapter(Tadapter);
        datePicker = findViewById(R.id.datePicker);
        datePage = findViewById(R.id.datePage);

        showStartDate = findViewById(R.id.resultStartDay);
        showEndDate = findViewById(R.id.resultEndDay);

        day =""+datePicker.getDayOfMonth();
        month = ""+datePicker.getMonth();
        year = ""+datePicker.getYear();

    }
    private static final String[] USER_TAG = new String[] {
            "@조윤영","@김연지","@황은선","@이경연"
    };
    public void onBackClicked(View view) {
        finish();
    }
    public void userUploadClicked(View view) {
        showTag.setVisibility(view.VISIBLE);
        String textTag = testTag.getText().toString();
        String tag =textTag.substring(1,textTag.length())+":";
        showTag.setText(tag);


    }

    public void setStartDateClicked(View view) {
        datePage.setVisibility(view.VISIBLE);
        startD=true;

    }

    public void setEndDateClicked(View view) {
        datePage.setVisibility(view.VISIBLE);
        endD=true;

    }

    public void onDateSelectClickd(View view) {
        datePage.setVisibility(view.GONE);
        if(startD==true){
            showStartDate.setVisibility(view.VISIBLE);
            showStartDate.setText(year+"-"+month+"-"+day);
        }
        if(endD==true) {
            showEndDate.setVisibility(view.INVISIBLE);
            showEndDate.setText(year+"-"+month+"-"+day);
        }


    }
}
