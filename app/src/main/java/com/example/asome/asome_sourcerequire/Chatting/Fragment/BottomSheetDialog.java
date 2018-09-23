package com.example.asome.asome_sourcerequire.Chatting.Fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.asome.asome_sourcerequire.CalendarActivity;
import com.example.asome.asome_sourcerequire.Chatting.Activity.MyScheduleActivity;
import com.example.asome.asome_sourcerequire.R;

import static com.example.asome.asome_sourcerequire.Chatting.Activity.ChatActivity.roles;

public class BottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener{

    public static BottomSheetDialog getInstance() { return new BottomSheetDialog(); }

    private LinearLayout ll_team_schedule;
    private LinearLayout ll_my_schedule;
    //private LinearLayout ll_error;
    private LinearLayout bluetoothLo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_dialog, container,false);

        ll_team_schedule = (LinearLayout) view.findViewById(R.id.ll_team_schedule);
        ll_my_schedule = (LinearLayout) view.findViewById(R.id.ll_my_schedule);
        //ll_error = (LinearLayout) view.findViewById(R.id.ll_error);


        ll_team_schedule.setOnClickListener(this);
        ll_my_schedule.setOnClickListener(this);
        //ll_error.setOnClickListener(this);
//        bluetoothLo.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        DialogFragment df = new DialogFragment();
        FragmentManager Frag_Manger = getFragmentManager();
        FragmentTransaction Frag_Trans = Frag_Manger.beginTransaction();
        // Frag_Trans.add(R.id.AddListActivity, of );
        Frag_Trans.commit();

        switch (view.getId()){
            case R.id.ll_my_schedule:
                Intent i = new Intent();

//Froyo or greater (mind you I just tested this on CM7 and the less than froyo one worked so it depends on the phone...)
                //cn = new ComponentName("com.google.android.calendar", "com.android.calendar.LaunchActivity");

//less than Froyo
                ComponentName  cn = new ComponentName(  "com.android.calendar", "com.android.calendar.LaunchActivity");
                i.setComponent(cn);
                startActivity(i);
                break;
            case R.id.ll_team_schedule:
                Intent intent2 = new Intent(getContext(), CalendarActivity.class);
                intent2.putExtra("arr_role",roles);
                startActivity(intent2);
                break;
            /*case R.id.ll_error:

                boolean b=true;
                Intent intent3 = new Intent(getContext(), CalendarActivity.class);
                if(b == true) {
                    intent3.putExtra("status","doing");
                    b=false;
                }
                if(b==false) {
                    intent3.putExtra("status","err");

                }
                startActivity(intent3);


                break;*/
    /*
    */    }
        dismiss();
    }


}

