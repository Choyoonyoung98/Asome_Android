package com.example.asome.asome_sourcerequire.Timeline;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.asome.asome_sourcerequire.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeLineListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ll_timeline_list)
    LinearLayout ll_timeline_list;


    public TimeLineListViewHolder(View itemView, int viewType) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        }
}
