package com.example.asome.asome_sourcerequire.Timeline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.asome.asome_sourcerequire.R;
import com.example.asome.asome_sourcerequire.Timeline.model.OrderStatus;
import com.example.asome.asome_sourcerequire.Timeline.model.Orientation;
import com.example.asome.asome_sourcerequire.Timeline.model.TimeLine;
import com.example.asome.asome_sourcerequire.Timeline.model.TimeLineList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private List<TimeLine> mDataList,mDataList2 ;
    private Orientation mOrientation;
    private boolean mWithLinePadding;


    //LIST
   private ListView lv_timeline_list;
    private TimeLineListAdapter timeLineListAdapter;
    private ArrayList<TimeLineList> timeLineLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_list);

/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        mOrientation = (Orientation) Orientation.HORIZONTAL;
        mWithLinePadding = true;
        //getIntent().getBooleanExtra(MainActivity.EXTRA_WITH_LINE_PADDING, false);

        //setTitle(mOrientation == Orientation.HORIZONTAL ? getResources().getString(R.string.horizontal_timeline) : getResources().getString(R.string.vertical_timeline));
        lv_timeline_list=(ListView) findViewById(R.id.rv_timeline_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
       // lv_timeline_list = (RecyclerView) findViewById(R.id.lv_lists);

/*
        lv_timeline_list.setLayoutManager(getLinearLayoutManager());
        lv_timeline_list.setHasFixedSize(true);*/

  /*      mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);*/
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);

        initView();
    }

    private LinearLayoutManager getLinearLayoutManager() {
        if (mOrientation == Orientation.HORIZONTAL) {
            return new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        } else {
            return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        }
    }

    private void initView() {

        mDataList = new ArrayList<>();
        timeLineLists = new ArrayList<>();

        setDataListItems();
        setDataListItems();
        mTimeLineAdapter = new TimeLineAdapter(mDataList, mOrientation, mWithLinePadding);
        mRecyclerView.setAdapter(mTimeLineAdapter);


        timeLineLists.add(new TimeLineList(mDataList));
        timeLineListAdapter = new TimeLineListAdapter(timeLineLists,getApplicationContext());
        lv_timeline_list.setAdapter(timeLineListAdapter);


 //       timeLineListAdapter = new TimeLineListAdapter(getApplicationContext(),timeLineLists);
     //   lv_timeline_list.setAdapter(timeLineListAdapter);


    }

    private void setDataListItems() {
        mDataList.add(new TimeLine("new", "", OrderStatus.INACTIVE));
        mDataList.add(new TimeLine("개발 진행중입니다.", "2017-02-12 08:00", OrderStatus.ACTIVE));
        mDataList.add(new TimeLine("Item has reached courier facility at New Delhi", "2017-02-11 21:00", OrderStatus.COMPLETED));
        mDataList.add(new TimeLine("Item has been given to the courier", "2017-02-11 18:00", OrderStatus.COMPLETED));


        //    timeLineLists.add(new TimeLineList(mDataList));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Menu
        switch (item.getItemId()) {
            //When home is clicked
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        if (mOrientation != null)
            //savedInstanceState.putSerializable(Orientation.HORIZONTAL, true);
            super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
          /*  if (savedInstanceState.containsKey(Orientation.HORIZONTAL)) {
                mOrientation = (Orientation) savedInstanceState.getSerializable(MainActivity.EXTRA_ORIENTATION);
            }*/
        }
        super.onRestoreInstanceState(savedInstanceState);
    }
}
