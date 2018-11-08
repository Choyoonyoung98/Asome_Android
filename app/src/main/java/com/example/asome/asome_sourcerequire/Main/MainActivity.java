package com.example.asome.asome_sourcerequire.Main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.asome.asome_sourcerequire.R;
import com.example.asome.asome_sourcerequire.TestDialog;
import com.example.asome.asome_sourcerequire.TestDialog_add_sch;
import com.example.asome.asome_sourcerequire.TestDialog_error;
import com.example.asome.asome_sourcerequire.Utils.HTTP.ProjSelect;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ProjSelect projSelect = new ProjSelect();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewPager();
        setTabLayout();
        setListener();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog Title");
        builder.setMessage("AlertDialog Content");
        builder.setNegativeButton("테스트1: 추가",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TestDialog_add_sch customDialog = new TestDialog_add_sch(MainActivity.this);

                        // 커스텀 다이얼로그를 호출한다.
                        // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                        customDialog.callFunction();
                    }
                });
        builder.setPositiveButton("테스트2: 리마인더",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TestDialog customDialog = new TestDialog(MainActivity.this);

                        // 커스텀 다이얼로그를 호출한다.
                        // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                        customDialog.callFunction();
                    }
                });
        builder.setNeutralButton("테스트3: 에러",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TestDialog_error customDialog = new TestDialog_error(MainActivity.this);

                        // 커스텀 다이얼로그를 호출한다.
                        // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                        customDialog.callFunction();
                    }
                });
        builder.show();
     //여기 테스트 코드 넣자
        //1
        //‘ASOME’ 프로젝트에서
        //‘기획’ 업무를 전달받았어요!
        //구글 캘린더에 추가할까요?


        //2
        //기획 업무 1일차입니다.
        //마감일까지 D-3 남았습니다.
    /*    try {
            projSelect.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    */
    }

    @Override
    protected void onResume() {
        super.onResume();

   /*     try {
            projSelect.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }

    public void setViewPager() {
        viewPager = findViewById(R.id.viewPager);
        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    public void setTabLayout() {
        tabLayout = findViewById(R.id.tabLayout);
    }

    public void setListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
