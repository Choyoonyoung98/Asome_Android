package com.example.asome.asome_sourcerequire.Chatting.Etc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.content.Intent.FLAG_INCLUDE_STOPPED_PACKAGES;
import static android.content.Intent.FLAG_RECEIVER_REPLACE_PENDING;

/**
 * [OUTLINE]
 * (위치 : SocektClient.java의 onMessage())는 소켓에서 받은 메시지를 모두 방송한다.
 * 그 방송의 내용을 이 클래스의 리시버가 받아서 처리한다
 * 처리하는 내용은 다음과 같다
 *
 * 1. response라는 키를 가진 방송을 받는다
 * 2. 받은 패킷을 ChatUtils로 넘겨 준다. ChatUtils 에서는 받은 패킷을 해석 및 처리한다.
 * 3. 내 클라이언트가 메시지를 받았음을 서버에 알려야 한다. 따라서 내가 받은 메시지의 고유값을 서버에 보내준다
 * 4. 뱃지를 refresh 한다
 */
public class MsgReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String json = intent.getStringExtra("response");
        intent.addFlags(FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.addFlags(FLAG_RECEIVER_REPLACE_PENDING);
        Log.i("MsgReceiver", json);
      //  ShortcutBadger.applyCount(getApplicationContext(), BadgeNum()); //for 1.1.4+
 /*       try {
            ChatUtils chatUtils = new ChatUtils(context);
            String uuid = chatUtils.json_to_string(json);

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }
/*    public int BadgeNum() {
        DBHelperChatting dbHelperChatting = new DBHelperChatting(getApplicationContext(), "ROOM.db", null, 1);
        DBHelperChatting dbHelperChatting2 = new DBHelperChatting(getApplicationContext(), "CHAT.db", null, 1);
        int outerBadge=0;
        ArrayList<String> roomArr = new ArrayList<String>();
        roomArr = dbHelperChatting.get_room_list_arr();
        for (int i = 0; i < roomArr.size(); i++) {
            outerBadge += Integer.parseInt(dbHelperChatting2.get_inner_badge_count(roomArr.get(i)));
        }
        return outerBadge;
    }*/
}