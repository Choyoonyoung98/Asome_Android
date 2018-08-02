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

import com.example.asome.asome_sourcerequire.R;

import java.util.ArrayList;

public class RoleDetailDialogActivity extends Activity {
    AutoCompleteTextView testTag;
    TextView showTag, showStartDate, showEndDate, textStart, textEnd;
    DatePicker datePicker, datePicker2;
    LinearLayout datePage, datePage2;
    String day, month, year, day2, month2, year2;
    LinearLayout rolePage;
    roleSub layout;
    Button setStartBtn, setEndBtn, selectBtn, selectBtn2;


    LinearLayout showPage;



    ListView userList;
    String textTag,tag;
    String ed,sd;
    private ArrayList mUserArr;
    private MainAdapter mMainAdapter;
    private boolean mIsInitAdapter=false;
    private int mNumber;




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
        datePicker = findViewById(R.id.datePicker);
        datePage = findViewById(R.id.datePage);
        datePicker2 = findViewById(R.id.datePicker2);
        datePage2 = findViewById(R.id.datePage2);



        textStart =findViewById(R.id.textStart);
        textEnd =findViewById(R.id.textEnd);


        setStartBtn = (Button)findViewById(R.id.setStartBtn);
        setEndBtn = (Button)findViewById(R.id.setEndBtn);
        selectBtn = (Button) findViewById(R.id.selectBtn);
        selectBtn2 = (Button)findViewById(R.id.selectBtn2);

        userList = findViewById(R.id.userList);
        mUserArr = new ArrayList<>();
        mMainAdapter = new MainAdapter(this);

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

        day = "" + datePicker.getDayOfMonth();
        int m1 = datePicker.getMonth() + 1;
        month = "" + m1;
        year = "" + datePicker.getYear();
        sd = year + "-" + month + "-" + day;

        textStart.setText(sd);
    }

    public void onSelectClicked2(View v) {
        datePage2.setVisibility(v.GONE);

        day2 = "" + datePicker2.getDayOfMonth();
        int m2 = datePicker2.getMonth() + 1;
        month2 = "" + m2;
        year2 = "" + datePicker2.getYear();
        ed = year2 + "-" + month2 + "-" + day2;

        textEnd.setText(ed);
    }

    public void onAddClicked(View v) {

        initData();
        initList();
        //layout = new roleSub(getApplicationContext());

        textTag = testTag.getText().toString();
        tag = textTag.substring(1, textTag.length());

        String show_tag = tag + ":";
        showTag.setText(show_tag);

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
    }
}



