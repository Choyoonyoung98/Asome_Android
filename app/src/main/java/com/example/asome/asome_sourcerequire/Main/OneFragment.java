package com.example.asome.asome_sourcerequire.Main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.asome.asome_sourcerequire.R;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends ListFragment {
    GridView gridView;
    UserAdapter ua;

    public OneFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        gridView = (GridView)view.findViewById(R.id.gridView);

        ua = new UserAdapter();
        ua.addItem(new UserItem(R.drawable.user,"조윤영","기획팀","yoon98028@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"이경연","경영팀","adusan23@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"김연지","개발팀","gdsc53d@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"황은선","UI/UX팀","dgbge43g@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"김민규","기획팀","bbee348@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"전미경","경영팀","bbh58@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"조우석","개발팀","njdj53@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"조영서","UI/UX팀","njfdndjn3@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"최혜원","개발팀","bmeajhbadnb@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"안지후","UI/UX팀","htvdd4@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"한예지","기획팀"," bmf3fsvx@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"박세영","기획팀","wrfvsda@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"박주경","경영팀"," cryjfdca@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"김영진","개발팀","rwfvxda@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"김연진","개발팀","xdtaxc@asome.com"));
        ua.addItem(new UserItem(R.drawable.user,"고준혁","개발팀","fncxaFS@asome.com"));



        gridView.setAdapter(ua);
        ua.notifyDataSetChanged();


    }
    class UserAdapter extends BaseAdapter {
        ArrayList<UserItem> items = new ArrayList<UserItem>();
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        public void addItem(UserItem item){
            items.add(item);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            UserItemView view = new UserItemView(getContext());

            final UserItem item = items.get(position);
            view.setUserName(item.getName());
            view.setUserProfile(item.getResID());
            view.setUserDepartment(item.getDepartment());
            view.setUserEmail(item.getEmail());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),item.getName(),Toast.LENGTH_LONG).show();
/*                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setType("plain/text");
                    sendIntent.setData(Uri.parse("test@gmail.com"));
                    sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                    sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "test@gmail.com" });
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, "test");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "hello. this is a message sent from my demo app :-)");
                    startActivity(sendIntent);*/
                    show(item);

                }
            });
            return view;

        }

    }
    void show(final UserItem item)
    {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("메일보내기");
        ListItems.add("전화걸기");
        ListItems.add("문자보내기");
        final CharSequence[] items =  ListItems.toArray(new String[ ListItems.size()]);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(item.getName()+"님께 ");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int pos) {
                String selectedText = items[pos].toString();
                if(pos==0) {
                    Uri uri = Uri.parse("mailto:" + item.getEmail());
                    Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                    it.putExtra(Intent.EXTRA_EMAIL, "me@abc.com");
                    it.putExtra(Intent.EXTRA_TEXT, "\n\n\n\n[Abird에서 보내는 메일입니다]");
                    //  it.setType("text/plain");
                    startActivity(it);
                }else if(pos==1){
                    Uri uri = Uri.parse("tel:xxxxxx");
                    Intent it = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(it);
                }else {

                    Uri uri = Uri.parse("smsto:0800000123");
                    Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                    it.putExtra("sms_body", "The SMS text");
                    startActivity(it);
                }
               // Toast.makeText(MainActivity.this, selectedText, Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

}
