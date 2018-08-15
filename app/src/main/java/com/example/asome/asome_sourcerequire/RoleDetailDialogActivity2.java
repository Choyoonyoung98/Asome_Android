package com.example.asome.asome_sourcerequire;

import android.app.Activity;

public class  RoleDetailDialogActivity2 extends Activity {/*
    AutoCompleteTextView testTag;
    TextView showTag, showStartDate, showEndDate, textStart, textEnd;
    DatePicker datePicker, datePicker2;
    LinearLayout datePage, datePage2;
    String day, month, year, day2, month2, year2;
    LinearLayout rolePage;
    roleSub layout;
    Button setStartBtn, setEndBtn, selectBtn, selectBtn2, showBtn;
    Boolean startD = false;
    Boolean endD = false;

    String sd, ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_role_detail_dialog);


        rolePage = findViewById(R.id.rolePage);


    }

    private static final String[] USER_TAG = new String[]{
            "@조윤영", "@김연지", "@황은선", "@이경연"
    };

    public void onBackClicked(View view) {
        finish();
    }


    public void onAddClicked(View view) {
        layout = new roleSub(getApplicationContext());
        rolePage.addView(layout);

        //showTag = layout.findViewById(R.id.resultUser);

        testTag = layout.findViewById(R.id.test_tag);
        ArrayAdapter<String> Tadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, USER_TAG);
        testTag.setAdapter(Tadapter);

        datePicker = layout.findViewById(R.id.datePicker);
        datePage = layout.findViewById(R.id.datePage);
        datePicker2 = layout.findViewById(R.id.datePicker2);
        datePage2 = layout.findViewById(R.id.datePage2);


     *//*   showStartDate = layout.findViewById(R.id.resultStartDay);
        showEndDate = layout.findViewById(R.id.resultEndDay);*//*

        textStart = layout.findViewById(R.id.textStart);
        textEnd = layout.findViewById(R.id.textEnd);


        setStartBtn = (Button) layout.findViewById(R.id.setStartBtn);
        setEndBtn = (Button) layout.findViewById(R.id.setEndBtn);
        selectBtn = (Button) layout.findViewById(R.id.selectBtn);
        selectBtn2 = (Button) layout.findViewById(R.id.selectBtn2);


        setStartBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePage.setVisibility(v.VISIBLE);
            }
        });
        setEndBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePage2.setVisibility(v.VISIBLE);
            }
        });
        selectBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePage.setVisibility(v.GONE);

                day = "" + datePicker.getDayOfMonth();
                int m1 = datePicker.getMonth() + 1;
                month = "" + m1;
                year = "" + datePicker.getYear();
                sd = year + "-" + month + "-" + day;
                showStartDate.setText(sd);
                textStart.setText(sd);
            }
        });
        selectBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePage2.setVisibility(v.GONE);

                day2 = "" + datePicker2.getDayOfMonth();
                int m2 = datePicker2.getMonth() + 1;
                month2 = "" + m2;
                year2 = "" + datePicker2.getYear();
                ed = year2 + "-" + month2 + "-" + day2;
                showEndDate.setText(ed);
                textEnd.setText(ed);
            }
        });


        showBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
      *//*          showTag.setVisibility(v.VISIBLE);
                String textTag = testTag.getText().toString();
                String tag = textTag.substring(1, textTag.length());
                String show_tag = tag + ":";
                showTag.setText(show_tag);

                showStartDate.setVisibility(v.VISIBLE);
                showEndDate.setVisibility(v.VISIBLE);*//*
      //TODO: 널값체크

                Intent intent = new Intent(getApplicationContext(), ResourceChartActivity.class);
                intent.putExtra("start_date", sd);
                intent.putExtra("end_date", ed);
                intent.putExtra("name", "이름값");//역할값, 프로젝트 UUID
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }

*/
}



