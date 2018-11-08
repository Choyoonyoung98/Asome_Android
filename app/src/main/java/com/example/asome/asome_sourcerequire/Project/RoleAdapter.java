package com.example.asome.asome_sourcerequire.Project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asome.asome_sourcerequire.R;

import java.util.ArrayList;

public class RoleAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Role> role_arr_list;
    private LayoutInflater inflater;
    private Role role;
    //밑에 새거용
    private ArrayList<Role> arraylist;

   public RoleAdapter(Context context, ArrayList<Role> role_arr_list) {
        this.context = context;
        this.role_arr_list = role_arr_list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//필터용
        this.arraylist = new ArrayList<Role>();
        this.arraylist.addAll(role_arr_list);
    }

    @Override
    public int getCount() {
        return role_arr_list.size();
    }

    @Override
    public Object getItem(int position) {
        return role_arr_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup viewGroup) {
        View view = v;
        if (null == view) {
            view = this.inflater.inflate(R.layout.item_role_detail, null);
        }
        role = role_arr_list.get(position);
       // ImageView profile_img = (ImageView) view.findViewById(R.id.imageView);
        TextView tv_role_name = (TextView) view.findViewById(R.id.tv_role_name);
        TextView tv_start_date = (TextView) view.findViewById(R.id.tv_start_date);
        TextView tv_end_date = (TextView) view.findViewById(R.id.tv_end_date);
       //  GlideUtil.setBitmapToViewRound(this.role_arr_list.get(position).getProfileUrl(), profile_img);
        tv_role_name.setText(role.getRole_name());
        tv_end_date.setText(role.getRole_end_date());
     //   chat_roomid_hidden.setText(this.role_arr_list.get(position).getRoom_name());
        tv_start_date.setText(role.getRole_start_date());

        return view;
    }


}
