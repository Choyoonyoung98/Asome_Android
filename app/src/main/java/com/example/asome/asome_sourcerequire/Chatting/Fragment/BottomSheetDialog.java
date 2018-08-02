package com.example.asome.asome_sourcerequire.Chatting.Fragment;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;
import android.content.SharedPreferences;

import com.example.asome.asome_sourcerequire.Chatting.Activity.MyScheduleActivity;
import com.example.asome.asome_sourcerequire.Chatting.Activity.TeamScheduleActivity;
import com.example.asome.asome_sourcerequire.Main.OneFragment;
import com.example.asome.asome_sourcerequire.R;

import static android.content.Context.MODE_PRIVATE;

public class BottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener{

    public static BottomSheetDialog getInstance() { return new BottomSheetDialog(); }

    private LinearLayout msgLo;
    private LinearLayout emailLo;
    private LinearLayout cloudLo;
    private LinearLayout bluetoothLo;
    SharedPreferences sp;

    String err;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_dialog, container,false);
        msgLo = (LinearLayout) view.findViewById(R.id.myLo);
        emailLo = (LinearLayout) view.findViewById(R.id.teamLo);
        cloudLo = (LinearLayout) view.findViewById(R.id.errLo);
  //      bluetoothLo = (LinearLayout) view.findViewById(R.id.bluetoothLo);

        msgLo.setOnClickListener(this);
        emailLo.setOnClickListener(this);
        cloudLo.setOnClickListener(this);
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
            case R.id.myLo:
                Intent intent1 = new Intent(getContext(), MyScheduleActivity.class);
                startActivity(intent1);

                break;
            case R.id.teamLo:
                Intent intent2 = new Intent(getContext(), TeamScheduleActivity.class);
                intent2.putExtra("status","");
                startActivity(intent2);
                break;
            case R.id.errLo:

                boolean b=true;
                Intent intent3 = new Intent(getContext(), TeamScheduleActivity.class);
                if(b == true) {
                    intent3.putExtra("status","doing");
                    b=false;
                }
                if(b==false) {
                    intent3.putExtra("status","err");

                }
                startActivity(intent3);


                break;
    /*
    */    }
        dismiss();
    }

}

