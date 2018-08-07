package com.example.asome.asome_sourcerequire.Utils.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.asome.asome_sourcerequire.Chatting.Activity.ChatActivity;
import com.example.asome.asome_sourcerequire.Chatting.Model.Chat;
import com.example.asome.asome_sourcerequire.Main.MainActivity;

import java.util.ArrayList;


/**
 * [OUTLINE]
 * 채팅 내용을 저장할 때 쓰는 내부 데이터베이스
 * 테이블 2개 {CHAT,ROOM}
 * <p>
 * [CHAT.db FIELD]
 * 1. _id
 * 2. user_no: 내아이디
 * 3. room_no: 방아이디
 * 4. msg: 메시지 내용
 * 5. read: 읽음/안읽음 문자열로
 * 6. read_for_badge: 읽음 = 0 /안읽음 = 1 처리, 뱃지 카운트 할 때 쓴다
 * 7. timestamp: 메시지 시간
 * 8. is_me: 내 메시지 인지 아닌지(왼쪽, 오른쪽 구분)
 * 9. action: 메시지 타임(text/img/map)
 * 10. lat: action=map의 latitude
 * 11. lng: action=map의 longitude
 * <p>
 * [ROOM.db FIELD]
 * 1. _id
 * 2. room_no: 방아이디
 * 3. profile_url: 가이드 프로필 사진 주소
 * 4. last_msg: 그 방의 마지막 메시지
 * 5. last_timestamp: 그 방의 마지막 메시지 보낸 시간
 * <p>
 * <p>
 * [SECTION]
 * 1. ROOM.db 쓰는 메소드
 * 2. CHAT.db 쓰는 메소드
 * 2. ROOM.db & CHAT.db 혼합해서 쓰는 메소드
 */

public class DBHelperChatting extends SQLiteOpenHelper {
    Context context;

    public DBHelperChatting(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }


    // 룸 인서트는 디테일엑티비티, 겟은 horizontal 에서 에드할때
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE CHAT (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_no TEXT, " +
                "room_no TEXT, " +
                "msg TEXT, " +
                "read TEXT, " +
                "read_for_badge INTEGER, " +
                "timestamp TEXT, " +
                "is_me TEXT, " +
                "type TEXT," +
                "lat REAL DEFAULT 0," +
                "lng REAL DEFAULT 0" +
                ");");

        db.execSQL("CREATE TABLE ROOM (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "room_no TEXT, " +
                "guide_name TEXT, " +
                "profile_url TEXT, " +
                "last_msg TEXT, " +
                "last_timestamp TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /**
     * [SECTION1]ROOM RELATED FUNCTION
     * 1. void delete_room
     * 2. void delete_room_all
     * 3. void delete_chat(String)
     * 4.
     */


    //룸/가이드/유저아이디/라스트 메시지/라스트 데이트
    //유저아이디 지워
    public void insert_room(String room_no, String guide_name, String profile_url, String last_msg, String last_timestamp) {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteDatabase db_read = getReadableDatabase();
        Cursor cursor = db_read.rawQuery("SELECT * FROM ROOM  WHERE room_no='" + room_no + "';", null);
        if (cursor.getCount() > 0) {
            Log.e("겹치는 방 처리.", "");
        } else {
            if (last_msg.contains("'")) {
                last_msg = last_msg.replaceAll("'", "''");
            }
            db.execSQL("INSERT INTO ROOM VALUES(null, '" + room_no + "', '" + guide_name + "', '" + profile_url + "', '" + last_msg + "', '" + last_timestamp + "');");
            db.close();
        }
    }

    public void delete_room(String targetRoom) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM ROOM WHERE room_no='" + targetRoom + "';");
        db.close();
    }

    public void delete_room_all() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM ROOM;");
        db.close();
    }


    //방목록 리턴
    public ArrayList<String> get_room_list_arr() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> result = new ArrayList<String>();
        Cursor cursor = db.rawQuery("SELECT * FROM ROOM", null);
        while (cursor.moveToNext()) {
            result.add(cursor.getString(1));
        }
        return (result);
    }

    //최근 대화를 나눈 방을 최상단으로 바꾸는 메소드(미완)
    public void update_room_sequence(String targetRoom) {
        //TODO:여기 제대로
    }

    //방 중복 체크
    public Boolean room_overlap_check(String targetRoom) {
        SQLiteDatabase db = getReadableDatabase();
        Boolean check = false;
        Cursor cursor = db.rawQuery("SELECT * FROM ROOM  WHERE room_no='" + targetRoom + "';", null);
        if (cursor.getCount() != 0) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }


    //마지막으로 채팅 나눈 시간 업데이트
    public void update_last_timestamp(String room_no, String msg, String timestamp) {
        if (msg.contains("'")) {
            msg = msg.replaceAll("'", "''");
        }
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("UPDATE ROOM SET last_msg='" + msg + "' ,last_timestamp='" + timestamp + "'  WHERE room_no='" + room_no + "';");
        Log.e("update_last_timestamp", msg + room_no);
        db.close();
    }

    //방목록을 화면에 표시(HorizontalList_Activity)
    public void get_room_list() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ROOM order by _id desc", null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String room_no = cursor.getString(cursor.getColumnIndex("room_no"));
                String guide_name = cursor.getString(cursor.getColumnIndex("guide_name"));
                String last_msg = cursor.getString(cursor.getColumnIndex("last_msg"));
                String timestamp = cursor.getString(cursor.getColumnIndex("last_timestamp"));
                String profile_url = cursor.getString(cursor.getColumnIndex("profile_url"));

                MainActivity ha = new MainActivity();
      /*          ChatList cl = new ChatList(guide_name, room_no, timestamp, last_msg, profile_url);
                ha.chatList.add(cl);*/

            }
        }
        cursor.close();
        db.close();
    }

    //가이드 진짜이름 가져오는 것(예 87번 고유번호를 가진 가이드의 진짜이름-->홍길동)
    public String get_guide_real_name(String targetRoom) {
        String name = "";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT guide_name FROM ROOM WHERE room_no= " + "'" + targetRoom + "'", null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndex("guide_name"));
            }

        }
        cursor.close();
        db.close();
        return name;
    }

    /**
     * [SECTION2]CHAT RELATED FUNCTION
     * 1. void delete_chat(String)
     * 2. void delete_chat_all()
     * 3.
     */


    //채팅 저장
    public void insert_chat(String user_no, String room_no, String msg, String read, String timestamp, String is_me, String action, double lat, double lng) {
        SQLiteDatabase db = getWritableDatabase();
        if (msg.contains("'")) {
            msg = msg.replaceAll("'", "''");
        }
        if (is_me.equals("true")) {
            db.execSQL("INSERT INTO CHAT VALUES(null,'" + user_no + "','" + room_no + "','" + msg + "','" + read + "',0,'" + timestamp + "','" + is_me + "','" + action + "','" + lat + "','" + lng + "');");
        } else {
            db.execSQL("INSERT INTO CHAT VALUES(null,'" + user_no + "','" + room_no + "','" + msg + "','" + read + "',1,'" + timestamp + "','" + is_me + "','" + action + "','" + lat + "','" + lng + "');");
        }
        db.close();
    }


    //채팅 메시지 읽음 처리
    public void update_read_check(String targetRoom) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE CHAT SET read='read' WHERE room_no='" + targetRoom + "';");
        db.close();
    }

    //해당 방의 안읽은 메시지를 0으로 처리 = 그 방 뱃지 0으로 처리
    public void update_badge_to_zero(String targetRoom) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE CHAT SET read_for_badge=0 WHERE room_no='" + targetRoom + "';");
        db.close();
    }

    public int get_outer_badge_num(String targetRoom) {
        SQLiteDatabase db = getWritableDatabase();
        DBHelperChatting dbHelperChatting = new DBHelperChatting(context, "CHAT.db", null, 1);
        String myBadge = dbHelperChatting.get_inner_badge_count(targetRoom);
        String allBadge = dbHelperChatting.get_all_unread();
        int my = Integer.parseInt(myBadge);
        int all = Integer.parseInt(allBadge);
        int result = 0;
        if (all >= my) {
            result = all - my;
        }
        db.close();
        return result;
    }

    //해당 방의 메시지 삭제
    public void delete_chat(String targetRoom) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM CHAT WHERE room_no='" + targetRoom + "';");
        db.close();
    }

    //모든 메시지 삭제
    public void delete_chat_all() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM CHAT;");
        db.close();
    }

    //각 방마다 안읽은 메시지 개수 세서 방 목록 리스트에 표시
    public String get_inner_badge_count(String targetRoom) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(read_for_badge) FROM CHAT WHERE read_for_badge=1" +
                " AND room_no='" + targetRoom + "'" +
                "; ", null);
        String result = null;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result = cursor.getString(cursor.getColumnIndex("COUNT(read_for_badge)"));
                Log.d(targetRoom + " 뱃지", result);
            }
        }
        db.close();
        return result;
    }

    public String get_all_unread() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(read_for_badge) FROM CHAT WHERE read_for_badge=1" +
                // " AND room_no='" + targetRoom + "'" +
                "; ", null);
        String result = null;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result = cursor.getString(cursor.getColumnIndex("COUNT(read_for_badge)"));
                //Log.d(targetRoom + " 뱃지", result);
            }
        }
        db.close();
        return result;
    }

//해당 방에서 마지막 메시지 보낸 시간 리턴
    public String get_last_date(String targetRoom) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CHAT WHERE room_no = " + "'" + targetRoom + "'", null);

        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
            String[] array = timestamp.split(" ");
            timestamp = array[0];
            return timestamp;
        }
        cursor.close();
        db.close();
        return null;
    }

    public void lastDataChatToRoom() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CHAT WHERE room_no = " + "'" + ChatActivity.current_room_no + "'", null);
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            String room_no = cursor.getString(cursor.getColumnIndex("room_no"));
            String msg = cursor.getString(cursor.getColumnIndex("msg"));
            String read = cursor.getString(cursor.getColumnIndex("read"));
            String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));

            DBHelperChatting dbHelperChatting_room = new DBHelperChatting(context, "ROOM.db", null, 1);
            dbHelperChatting_room.update_last_timestamp(room_no, msg, timestamp);
        }
        cursor.close();
        db.close();
    }

    //해당 방에서 나눈 마지막 메시지 리턴
    public String get_last_msg(String targetRoom) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CHAT WHERE room_no = " + "'" + targetRoom + "'", null);
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            String msg = cursor.getString(cursor.getColumnIndex("msg"));
            return msg;
        }
        cursor.close();
        db.close();
        return null;
    }

    //채팅 내역을 화면에 표시(chatactivity)
    public void get_chat_history(String targetRoom) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CHAT WHERE room_no = " + "'" + targetRoom + "'", null);
        if (cursor.getCount() > 0) {
            Boolean isme = false;
            while (cursor.moveToNext()) {

                String from_id = cursor.getString(cursor.getColumnIndex("user_no"));
                String msg = cursor.getString(cursor.getColumnIndex("msg"));
                String read = cursor.getString(cursor.getColumnIndex("read"));
                String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
                String is_me = cursor.getString(cursor.getColumnIndex("is_me"));
                String action = cursor.getString(cursor.getColumnIndex("type"));
                double lat = cursor.getDouble(cursor.getColumnIndex("lat"));
                double lng = cursor.getDouble(cursor.getColumnIndex("lng"));

                if (is_me.equals("true")) {
                    isme = true;
                } else {
                    isme = false;
                }
                String[] timestamptime = timestamp.split(" ");
                timestamp = new StringBuilder(timestamptime[0]).append(" ").append(timestamptime[1]).toString();
                Chat chat = new Chat(from_id, timestamp, msg, isme, action, lat, lng, read);
                ChatActivity.chats.add(chat);

            }
            cursor.close();
        }
    }


}