package com.example.asome.asome_sourcerequire;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class TestDialog_error {

    private Context context;

    public TestDialog_error(Context context) {
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction() {

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

/*
        // PendingIntent를 등록 하고, noti를 클릭시에 어떤 클래스를 호출 할 것인지 등록.
        PendingIntent intent = PendingIntent.getActivity(
                TestDialog_add_sch.this, 0,
                new Intent(TestDialog_add_sch.this, Ac.class), 0);



*/


        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.logo_icon)
                .setContentTitle("A-bird")
                .setContentText("[김연지]님이 [기획]  업무에서 에러가 생겼습니다.")
                .setSound(uri)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        try {
            // 스레드에게 수행시킬 동작들 구현
            Thread.sleep(5000); // 1초간 Thread를 잠재운다
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(003, mBuilder.build());
        } catch (InterruptedException e) {
            e.printStackTrace();
            ;
        }

/*        new Thread(new Runnable() {
            @Override
            public void run() {


            }
        }).start();*/


        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.custom_dialog_2);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        //  final EditText message = (EditText) dlg.findViewById(R.id.mesgase);
        final Button okButton = (Button) dlg.findViewById(R.id.okButton);
        final Button cancelButton = (Button) dlg.findViewById(R.id.cancelButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // '확인' 버튼 클릭시 메인 액티비티에서 설정한 main_label에
                // 커스텀 다이얼로그에서 입력한 메시지를 대입한다.
                // main_label.setText(message.getText().toString());
                //    Toast.makeText(context, "\"" +  message.getText().toString() + "\" 을 입력하였습니다.", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "추가했습니다.", Toast.LENGTH_SHORT).show();

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
    }
}

