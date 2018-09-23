package com.example.asome.asome_sourcerequire.Chatting.Etc;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.asome.asome_sourcerequire.Utils.SQLite.DBHelperChatting;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.SOCKET_URL;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.hori_id;

/**
 * [OUTLINE]
 * 안드로이드 웹소켓 클라이언트
 * <p>++미완
 * 한방에 똑같은 클라이언트가 2번 붙는 경우 예외 처리
 * <p>
 * Created by anfrh on 2017-07-17.
 */

public class SocketClient {
    Context context;

    public SocketClient(Context context) {
        this.context = context;
    }

    public static WebSocketClient mWebSocketClient;
    DBHelperChatting dbHelper_chatting_room = new DBHelperChatting(context, "ROOM.db", null, 1);
    static String clientCheck = "ok";
    URI uri;

    //TODO: 중복 체크 부분 누락
    //만약 한 방에 같은 클라이언트가 두번 접속하려거든 막아야 함..
    //주어진 방번호로 소켓에 연결
    public void connectWebSocket(String roomId) {
        Log.i("Websocket", "connectWebSocket");

        try {
            if (hori_id == null)
                return;
            String url_full = SOCKET_URL + "connect/" + hori_id + "/" + roomId + "/c";
            uri = new URI(url_full);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        //clientCheck = null;
        Log.i("Websocket", "connectWebSocket");
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Open");
                Log.i("Websocket", "reOpen");
            }

            @Override
            public void onMessage(String s) {
                Log.i("Websocket", s);
                //소켓에서 받은 모든 메시지를 broadcast
                Intent intent = new Intent("Response");
                intent.putExtra("response", s);
                context.sendBroadcast(intent);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e);
            }
        };
        mWebSocketClient.connect();
    }

    //내가 가진 전체방 목록을 가져와 소켓에 접속시킨다
    public void get_room_list_and_connect_to_socket() throws ExecutionException, InterruptedException {

        ArrayList<String> result = new ArrayList<String>();
        result = dbHelper_chatting_room.get_room_list_arr();
        for (int i = 0; i < result.size(); i++) {
            connectWebSocket(result.get(i));
          //  new GetLostChat(result.get(i)).execute().get();
        }

    }
}


