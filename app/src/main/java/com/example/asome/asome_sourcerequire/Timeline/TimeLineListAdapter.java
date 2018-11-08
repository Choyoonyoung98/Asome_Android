package com.example.asome.asome_sourcerequire.Timeline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.asome.asome_sourcerequire.R;
import com.example.asome.asome_sourcerequire.Timeline.model.TimeLineList;

import java.util.ArrayList;

public class TimeLineListAdapter extends BaseAdapter {

    private ArrayList<TimeLineList> mFeedList;
    private Context mContext;
    private LayoutInflater inflater;

    private TimeLineList chat_list;

    public TimeLineListAdapter(ArrayList<TimeLineList> mFeedList, Context mContext) {
        this.mFeedList = mFeedList;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return mFeedList.size();
    }

    @Override
    public Object getItem(int i) {
        return mFeedList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (null == view) {
            view = this.inflater.inflate(R.layout.item_timeline_list, null);
        }
        return view;
    }
}
