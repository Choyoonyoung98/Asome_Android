package com.example.asome.asome_sourcerequire.Chatting.Etc;

import android.content.Context;
import android.util.Log;

import com.example.asome.asome_sourcerequire.Chatting.Activity.ChatActivity;
import com.example.asome.asome_sourcerequire.Chatting.Model.Chat;
import com.example.asome.asome_sourcerequire.Utils.SQLite.DBHelperChatting;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.asome.asome_sourcerequire.Chatting.Activity.ChatActivity.current_name;
import static com.example.asome.asome_sourcerequire.Chatting.Activity.ChatActivity.current_room_no;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_CONNECTED;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_DONE;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_IMG;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_RECEIVED;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_RECONNECTED;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_SCHEDULE_MY;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_SCHEDULE_OTHER;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_START;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_TEMP_DISCONNECTED;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_TEXT;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.TAG_ACTION;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.TAG_CUSTOMER;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.TAG_MESSAGE;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.TAG_MESSAGE_NO;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.TAG_READ;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.TAG_ROOM_NO;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.TAG_TIMESTAMP;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.TAG_UNREAD;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.TAG_USER_NO;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.TAG_USER_TYPE;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.hori_id;


/**
 * [OUTLINE]
 * 목적에 따라 채팅 메시지를 어떤 형식으로 바꿔주는 유틸 메소드가 모여있는 클래스이다
 * <p>
 * Part1 [ json_to_string ]
 * json 형식을 string 로 바꾸는 함수
 * 바꾼 데이터는 데이터 베이스에 인서트
 * <p>
 * Part2 [ json_to_chat_front ]
 * json 형식을 message 객체로 바꾸는 함수
 * 바꾼 데이터는 ChatActivity에 뿌려준다
 * <p>
 * Part3 [ message_to_json ]
 * message 객체를 json 형식으로 바꿔준다
 * 채팅서버로 패킷을 보낼 때 쓴다
 */


public class ChatUtils {
    Context context;


    public ChatUtils(Context context) {
        this.context = context;
    }

    public boolean counter; //상대방의 접속 여부
    private String TAG = "ChatUtils";

    //여기서 리턴하는거 uuid
    public String json_to_string(String json) throws JSONException {
        DBHelperChatting dbHelperChattingChat = new DBHelperChatting(context, "CHAT.db", null, 1);
        DBHelperChatting dbHelperChattingRoom = new DBHelperChatting(context, "ROOM.db", null, 1);
        JSONObject jsonObject = new JSONObject(json);
        String read_or_unread = null;
        String chatUtilmyName = hori_id;
        String message_no = null;

        try {
            switch (jsonObject.getString(TAG_ACTION)) {

                case ACTION_SCHEDULE_MY:
                    message_no = jsonObject.getString(TAG_MESSAGE_NO);
                    if (jsonObject.get(TAG_USER_NO).equals(chatUtilmyName)) {
                        read_or_unread = counter_status();
                        dbHelperChattingChat.insert_chat(
                                chatUtilmyName,
                                jsonObject.getString(TAG_ROOM_NO),
                                jsonObject.getString(TAG_MESSAGE),
                                read_or_unread,
                                DateFormat.date_month_day_time(),
                                "true",
                                ACTION_TEXT, 0, 0
                        );
                        dbHelperChattingRoom.update_room_sequence(jsonObject.getString(TAG_ROOM_NO));

                    } else if (!jsonObject.get(TAG_USER_NO).equals(chatUtilmyName)) {
                        dbHelperChattingChat.insert_chat(
                                dbHelperChattingRoom.get_guide_real_name(jsonObject.getString(TAG_ROOM_NO)),
                                jsonObject.getString(TAG_ROOM_NO),
                                jsonObject.getString("오늘의 당신 일정입니다."),
                                read_or_unread,
                                DateFormat.date_month_day_time(),
                                "false",
                                ACTION_TEXT, 0, 0);
                        dbHelperChattingRoom.update_room_sequence(jsonObject.getString(TAG_ROOM_NO));
                    }
                    return message_no;


                case ACTION_TEXT:
                    message_no = jsonObject.getString(TAG_MESSAGE_NO);
                    if (jsonObject.get(TAG_USER_NO).equals(chatUtilmyName)) {
                        read_or_unread = counter_status();
                        dbHelperChattingChat.insert_chat(
                                chatUtilmyName,
                                jsonObject.getString(TAG_ROOM_NO),
                                jsonObject.getString(TAG_MESSAGE),
                                read_or_unread,
                                DateFormat.date_month_day_time(),
                                "true",
                                ACTION_TEXT, 0, 0
                        );
                        dbHelperChattingRoom.update_room_sequence(jsonObject.getString(TAG_ROOM_NO));

                    } else if (!jsonObject.get(TAG_USER_NO).equals(chatUtilmyName)) {
                        dbHelperChattingChat.insert_chat(
                                dbHelperChattingRoom.get_guide_real_name(jsonObject.getString(TAG_ROOM_NO)),
                                jsonObject.getString(TAG_ROOM_NO),
                                jsonObject.getString(TAG_MESSAGE),
                                read_or_unread,
                                DateFormat.date_month_day_time(),
                                "false",
                                ACTION_TEXT, 0, 0);
                        dbHelperChattingRoom.update_room_sequence(jsonObject.getString(TAG_ROOM_NO));
                    }
                    return message_no;



                case "connected":
                    if (!jsonObject.getString(TAG_USER_NO).equals(chatUtilmyName)) {
                        read_check_sync_db(jsonObject.getString(TAG_ROOM_NO));
                    } else {

                    }
                    return null;


                case "user-disconnected":
                    return null;

                default:
                    return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message_no;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Chat json_to_chat_front(String json) throws JSONException {
        Log.e("[json_to_chat_front]", json);
        Chat chat = null;
        JSONObject jsonObject = new JSONObject(json);
        String returnRead = "unread";
        String chatUtilmyName = hori_id;

        try {
            switch (jsonObject.getString(TAG_ACTION)) {
                case ACTION_TEXT:
                    if (jsonObject.getString(TAG_ROOM_NO).equals(current_room_no)) {
                        if (jsonObject.get(TAG_USER_NO).equals(current_name)) {
                            chat = new Chat(
                                    ChatActivity.current_counter_name,
                                    jsonObject.getString(TAG_ROOM_NO),
                                    DateFormat.date_apm(),
                                    jsonObject.getString(TAG_MESSAGE),
                                    true, ACTION_TEXT);
                        } else if (!jsonObject.get(TAG_USER_NO).equals(current_name)) {
                            chat = new Chat(
                                    ChatActivity.current_counter_name,
                                    jsonObject.getString(TAG_ROOM_NO),
                                    DateFormat.date_apm(),
                                    jsonObject.getString(TAG_MESSAGE),
                                    false, ACTION_TEXT);
                        }
                    }
                    return chat;


                case ACTION_SCHEDULE_MY:
                    if (jsonObject.getString(TAG_ROOM_NO).equals(current_room_no)) {
/*
                        if (jsonObject.get(TAG_USER_NO).equals(current_name)) {
                            chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "당신 오늘 스케줄은 ~~~이다.", false, ACTION_SCHEDULE_MY);

                        } else if (!jsonObject.get(TAG_USER_NO).equals(current_name)) {
                            chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "당신 오늘 스케줄은 ~~~이다.", false, ACTION_SCHEDULE_MY);

                        }
*/

                        chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "당신의 오늘 스케줄은 안드로이드 클라이언트 최적화 작업이다.", false, ACTION_SCHEDULE_MY);

                    }


                    return chat;



                case ACTION_SCHEDULE_OTHER:
                    if (jsonObject.getString(TAG_ROOM_NO).equals(current_room_no)) {
                        chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "누구 스케쥴 보시겠습니까?<Listview>", false, ACTION_SCHEDULE_OTHER);

                    }
                    return chat;
                case ACTION_DONE:
                    if (jsonObject.getString(TAG_ROOM_NO).equals(current_room_no)) {
                        chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "오늘 일정 완료하시겠습니까?", false, ACTION_DONE);

                    }


                    return chat;

                case ACTION_START:
                    if (jsonObject.getString(TAG_ROOM_NO).equals(current_room_no)) {
                        chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "무엇을 도와드릴까요?", false, ACTION_START);

                    }


                    return chat;

                case ACTION_CONNECTED:
                    if (!jsonObject.getString(TAG_USER_NO).equals(chatUtilmyName)) {
                        read_check_sync_on_activity(current_room_no);
                        if (!jsonObject.getString(TAG_USER_NO).equals(current_name)) {

                        } else {
                        }
                    } else {

                    }
                    return null;


                case "user-disconnected":
                    if (jsonObject.getString(TAG_ROOM_NO).equals(current_room_no)) {
                        //Ref
                        if (!jsonObject.getString(TAG_USER_NO).equals(current_name)) {
                            counter = false;
                        } else {
                        }
                    }
                    return null;


                default:
                    return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 스트링을 json 타입으로 만드는 블록
     * 1. chat_to_json_img
     * 2. chat_to_json_text
     * 3. chat_to_json_reconnect
     * 4. chat_to_json_temp_disconnected
     * 5.chat_to_json_received
     */


    public static String chat_to_json_text(Chat chat) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(TAG_ACTION, chat.getAction());
            jsonObject.put(TAG_USER_NO, chat.getUser_no());
            jsonObject.put(TAG_ROOM_NO, chat.getRoom_no());
            jsonObject.put(TAG_MESSAGE, chat.getMessage());
            jsonObject.put(TAG_TIMESTAMP, DateFormat.date_for_chat());
            jsonObject.put(TAG_USER_TYPE, TAG_CUSTOMER);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("[chat_to_json_text]",jsonObject.toString());
        return jsonObject.toString();
    }


    public static String chat_to_json_img(Chat chat) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(TAG_ACTION, ACTION_IMG);
            jsonObject.put(TAG_USER_NO, chat.getUser_no());
            jsonObject.put(TAG_ROOM_NO, chat.getRoom_no());
            jsonObject.put(TAG_MESSAGE, chat.getMessage());
            jsonObject.put(TAG_TIMESTAMP, DateFormat.date_for_chat());
            jsonObject.put(TAG_USER_TYPE, TAG_CUSTOMER);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static String chat_to_json_temp_disconnected(String roomId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(TAG_ACTION, ACTION_TEMP_DISCONNECTED);
            jsonObject.put(TAG_USER_NO, hori_id);
            jsonObject.put(TAG_ROOM_NO, roomId);
            jsonObject.put(TAG_USER_TYPE, TAG_CUSTOMER);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    public static String chat_to_json_reconnect(Chat chat) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(TAG_ACTION, ACTION_RECONNECTED);
            jsonObject.put(TAG_USER_NO, chat.getUser_no());
            jsonObject.put(TAG_ROOM_NO, chat.getRoom_no());
            jsonObject.put(TAG_USER_TYPE, TAG_CUSTOMER);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static String chat_to_json_connected(Chat chat) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(TAG_ACTION, ACTION_CONNECTED);
            jsonObject.put(TAG_USER_NO, chat.getUser_no());
            jsonObject.put(TAG_ROOM_NO, chat.getRoom_no());
            jsonObject.put(TAG_USER_TYPE, "c");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static String chat_to_json_received(String message_no) {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(TAG_ACTION, ACTION_RECEIVED);
            jsonObject.put(TAG_MESSAGE_NO, message_no);
            jsonObject.put(TAG_USER_TYPE, "c");
            jsonObject.put("sender_no", hori_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();


    }

    //상대방에 여부에 따라 읽음처리 문자열을 반환
    public String counter_status() {
        String readCheck = TAG_UNREAD;
        if (counter) {
            readCheck = TAG_READ;
        } else {
            readCheck = TAG_UNREAD;
        }
        return readCheck;
    }

    //사용자가 채팅화면에 있는데 상대방이 들어왔을 경우 보이는 메시지들을 보두 "읽음"으로 업데이트
    public void read_check_sync_on_activity(String room_no) {
        DBHelperChatting dbHelperChatting = new DBHelperChatting(context, "CHAT.db", null, 1);
        counter = true;
        dbHelperChatting.update_read_check(room_no);

        ChatActivity.chats.clear();
        dbHelperChatting.get_chat_history(room_no);
        ChatActivity.messages_adapter.notifyDataSetChanged();
    }

    //사용자가 채팅화면에 없을때 상대방이 들어왔을 경우 디비에 저장된 메시지들을 모두 읽음 처리하고 차후에 채팅 액티비티에 보여질때 "읽음"으로 처리한다
    public void read_check_sync_db(String room_no) {
        DBHelperChatting dbHelperChatting = new DBHelperChatting(context, "CHAT.db", null, 1);
        counter = true;
        dbHelperChatting.update_read_check(room_no);
    }

}

