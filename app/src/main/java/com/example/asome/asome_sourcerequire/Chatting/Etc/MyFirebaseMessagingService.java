package com.example.asome.asome_sourcerequire.Chatting.Etc;


import com.google.firebase.messaging.RemoteMessage;

/**
 * ++미완
 * 채팅서버를 이전하면서 서버쪽 fcm 부분 작업이 완료되지 않은 관계로
 * 클라이언트 fcm 부분 작업을 했으나 쓸 수 없음
 * 따라서 이부분 코드를 사용하려면 네티서버에서 fcm 보내는 부분 설계후 그에 맞춰서 이 곳을 설계해야한다.
 * 주석처리 해두겠음
 * <p>
 * [OUTLINE]
 * 들어오는 FCM 신호에 따라 안드로이드에서 어떻게 반응할지 정하는 클래스
 * 한마디로 fcm 핸들러 클래스
 * <p>
 * [METHOD]
 * push_notification_chat : 채팅 왔을때 알림
 * push_notification_request_msg : 쪽지 왔을때 알림
 */
public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";
    String name, content, type;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
/*
        //if (!sharedPreferencePackage.getPreferences().equals(ChatActivity.myRoom)) {
        if (false) {
            try {
                Log.e("remoteM", remoteMessage.getData().toString());
                JSONObject jsonObject = new JSONObject(remoteMessage.getData().get("message"));
                name = jsonObject.getString("name");
                content = jsonObject.getString("content");
                type = jsonObject.getString("type");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (type.equals("chat")) {
                push_notification_chat(name, content);
            } else if (type.equals("msg")) {
                push_notification_request_msg(name, content);
            }
            Log.e("chatON", type);
        } else {
            Log.e("mutechat", remoteMessage.toString());

        }

        android.content.SharedPreferences pref2 = getBaseContext().getSharedPreferences("badgesave", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = pref2.edit();
        int result = pref2.getInt("count", 77);
        int Newresult = result;

        editor = pref2.edit();
        editor.putInt("count", Newresult);
        editor.commit();

        int string3 = pref2.getInt("count", 77);
        Log.e("badgesave", String.valueOf(string3));
        //ShortcutBadger.applyCount(getBaseContext(), string3); //for 1.1.4+*/
    }
/*

    public void push_notification_chat(String name, String content) {
        System.out.println("received message chat : " + content);
        Intent intent = new Intent(this, HorizontalListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 */
/* Request code *//*
, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_ic_notification).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle(name)
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(defaultSoundUri).setLights(000000255, 500, 2000)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakelock.acquire(5000);
        notificationManager.notify(0 */
/* ID of sharedPreferencePackage *//*
, notificationBuilder.build());

        ShortcutBadger.applyCount(getBaseContext(), BadgeNum()); //for 1.1.4+


    }

*//*
    public void push_notification_request_msg(String name, String content) {
        System.out.println("received message msg : " + content);
        Intent intent = new Intent(this, HorizontalListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 *//* Request code *//*, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_ic_notification).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle(name + " 길잡이의 쪽지가 도착")
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(defaultSoundUri).setLights(000000255, 500, 2000)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakelock.acquire(5000);

        notificationManager.notify(0 *//* ID of sharedPreferencePackage *//*, notificationBuilder.build());


        //int badgeCount = BadgeNum();
        ShortcutBadger.applyCount(getBaseContext(), BadgeNum());


    }

    int outerBadge;

    //TODO: fcm params : roomId guidename
    public int BadgeNum() {
        DBHelperChatting dbHelperChatting = new DBHelperChatting(getBaseContext(), "ROOM.db", null, 1);
        DBHelperChatting dbHelperChatting2 = new DBHelperChatting(getBaseContext(), "CHAT.db", null, 1);

        ArrayList<String> roomArr = new ArrayList<String>();
        roomArr = dbHelperChatting.get_room_list_arr();


        for (int i = 0; i < roomArr.size(); i++) {
            outerBadge += Integer.parseInt(dbHelperChatting2.get_inner_badge_count(roomArr.get(i)));
        }
        return outerBadge;
    }

*/
}