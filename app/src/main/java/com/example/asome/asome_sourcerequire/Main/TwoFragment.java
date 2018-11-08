package com.example.asome.asome_sourcerequire.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.asome.asome_sourcerequire.Chatting.Activity.ChatActivity;
import com.example.asome.asome_sourcerequire.Project.NewProjectActivity;
import com.example.asome.asome_sourcerequire.Project.ProjectItem;
import com.example.asome.asome_sourcerequire.Project.ProjectItemView;
import com.example.asome.asome_sourcerequire.R;
import com.example.asome.asome_sourcerequire.Utils.HTTP.ProjSelect;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TwoFragment extends ListFragment implements View.OnClickListener {
    GridView gridView;
    Button addBtn;
    public static ProjectAdapter projectAdapter;
    ProjSelect projSelect = new ProjSelect();

    private static final String DEBUG_TAG = "TwoFragment";

    public TwoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        gridView = (GridView) view.findViewById(R.id.gridView);
        addBtn = (Button) view.findViewById(R.id.addbtn);

        projectAdapter = new ProjectAdapter();
        try {
            projSelect.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
     //   projectAdapter.addItem(new ProjectItem("projectA", "이 프로젝트는 프로젝트 A입니다.이 프로젝트는 프로젝트 A입니다.이 프로젝트는 프로젝트 A입니다.",1));

        gridView.setAdapter(projectAdapter);
        projectAdapter.notifyDataSetChanged();


        view.findViewById(R.id.addbtn).setOnClickListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent;

                intent = new Intent(getContext(), ChatActivity.class);

                ProjectItem projectItem = (ProjectItem) gridView.getItemAtPosition(position);
                intent.putExtra("room_no",projectItem.getId());
                Log.e("projectItem", String.valueOf(projectItem.getId()));
                startActivity(intent);


            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

      projectAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(gridView.getContext(), NewProjectActivity.class);
        startActivityForResult(intent, 100);
    }

    public class ProjectAdapter extends BaseAdapter {
        ArrayList<ProjectItem> items = new ArrayList<ProjectItem>();

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

        public void addItem(ProjectItem item) {
            items.add(item);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ProjectItemView view = new ProjectItemView(getContext());


            ProjectItem item = items.get(position);
            view.setTextTitle(item.getTitle());
            view.setTextAbout(item.getAbout());
            return view;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
