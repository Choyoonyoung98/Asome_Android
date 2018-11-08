package com.example.asome.asome_sourcerequire.Project;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.asome.asome_sourcerequire.R;

public class roleSub  extends LinearLayout {
    public roleSub(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }
    public roleSub(Context context){
        super(context);

        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.role_sub, this, true);
    }
}