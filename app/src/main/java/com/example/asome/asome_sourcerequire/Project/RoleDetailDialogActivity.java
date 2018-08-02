package com.example.asome.asome_sourcerequire.Project;

import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
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
<<<<<<< HEAD
    Button setStartBtn, setEndBtn, selectBtn, selectBtn2;


    LinearLayout showPage;



    ListView userList;
    String textTag,tag;
    String ed,sd;
    private ArrayList mUserArr;
    private MainAdapter mMainAdapter;
    private boolean mIsInitAdapter=false;
    private int mNumber;



=======
    Button setStartBtn, setEndBtn, selectBtn, selectBtn2, showBtn,btn_role_add;
    Boolean startD = false;
    Boolean endD = false;

    LinearLayout showPage;

    String final_end_date, final_start_date;
    ListView lv_role;
    Role role;
    ArrayList<Role> role_arr_list = new ArrayList<Role>();;
    RoleAdapter roleAdapter;
>>>>>>> 8a9ce73964c244231d3539e967a6e6ae7eb7c57a

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

<<<<<<< HEAD
        userList = findViewById(R.id.userList);
        mUserArr = new ArrayList<>();
        mMainAdapter = new MainAdapter(this);
=======

        btn_role_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                role = new Role("여기 이름값1", final_start_date, final_end_date);
>>>>>>> 8a9ce73964c244231d3539e967a6e6ae7eb7c57a

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

<<<<<<< HEAD
    public void onAddClicked(View v) {

        initData();
        initList();
        //layout = new roleSub(getApplicationContext());
=======


    public void onAddClicked(View v,Role role) {
        // layout = new roleSub(getApplicationContext());

// ChatList cl = new ChatList("김지원 멘토님", "11", "123", "안녕", "ㅇㄹㄴㄴㅇㄹ");

   /*     showTag = layout.findViewById(R.id.resultUser);
        showStartDate = layout.findViewById(R.id.resultStartDay);
        showEndDate = layout.findViewById(R.id.resultEndDay);
>>>>>>> 8a9ce73964c244231d3539e967a6e6ae7eb7c57a

        textTag = testTag.getText().toString();
        tag = textTag.substring(1, textTag.length());

        String show_tag = tag + ":";
        showTag.setText(show_tag);
<<<<<<< HEAD

        showStartDate.setText(sd);
        showEndDate.setText(ed);

        //showPage.addView(layout);
    }
    private void initList() {
        if(!mIsInitAdapter) {
            userList.setAdapter(mMainAdapter);
        }else {
            mMainAdapter.notifyDataSetChanged();
        }
    }
    public void initData() {
        for (int i = 0; i < 5; i++) {
            UserListData userListData = new UserListData();
            userListData.name = tag;
            userListData.start = sd;
            userListData.end = ed;
            mUserArr.add(userListData);
            mNumber++;
        }
    }
    private class MainAdapter extends BaseAdapter {

        private Context mContext;
        LayoutInflater mInflater;

        public MainAdapter(Context context) {
            this.mContext = context;
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mUserArr.size();
        }

        @Override
        public Object getItem(int position) {
            return mUserArr.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            UserViewHolder userViewHolder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.user_list_item, null);
                userViewHolder = new UserViewHolder();
                userViewHolder.userName = (TextView) convertView.findViewById(R.id.resultUser);
                userViewHolder.userStartday = (TextView) convertView.findViewById(R.id.resultStartDay);
                userViewHolder.userEndday = (TextView) convertView.findViewById(R.id.resultEndDay);
                convertView.setTag(userViewHolder);

            } else {
                userViewHolder = (UserViewHolder) convertView.getTag();
            }

/*            userViewHolder.userName.setText(mUserArr.get(position).name );
            userViewHolder.userStartday.setText(mUserArr.get(position).start);
            userViewHolder.userEndday.setText(mUserArr.get(position).end);*/

            return convertView;

        }
    }

    public class UserViewHolder {
        public TextView userName;
        public TextView userStartday;
        public TextView userEndday;
    }
    public class UserListData {
        String name;
        String start;
        String end;
=======
        showStartDate.setText(final_start_date);
        showEndDate.setText(final_end_date);*/

        // showPage.addView(layout);
>>>>>>> 8a9ce73964c244231d3539e967a6e6ae7eb7c57a
    }
}



