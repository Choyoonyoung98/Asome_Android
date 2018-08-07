package com.example.asome.asome_sourcerequire.Main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.asome.asome_sourcerequire.R;
import com.example.asome.asome_sourcerequire.Utils.HTTP.ProjSelect;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewPager();
        setTabLayout();
        setListener();

        ProjSelect projSelect = new ProjSelect();
        projSelect.execute();
    }
    public void setViewPager(){
        viewPager = findViewById(R.id.viewPager);
        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
    public void setTabLayout(){
        tabLayout = findViewById(R.id.tabLayout);
    }
    public void setListener(){
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
