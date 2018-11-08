package com.example.asome.asome_sourcerequire.Utils.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.asome.asome_sourcerequire.Chatting.Etc.Constant;
import com.example.asome.asome_sourcerequire.Main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DongHoon on 2017-09-23.
 */


public class DBHelperUser extends SQLiteOpenHelper {

    Context context;

    public DBHelperUser(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //scheduler db
        db.execSQL("CREATE TABLE USER (user_no INTEGER PRIMARY KEY, user_email TEXT, user_name TEXT, user_token TEXT, " +
                "user_profile_image TEXT, user_login_api_type TEXT, user_gender TEXT, user_age INTEGER," +
                " user_token_refresh INTEGER, user_guide_check TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(int user_no, String user_email, String user_name, String user_token, String user_profile_image, String user_login_api_type,
                       String user_gender, int user_age, int user_token_refresh, String user_guide_check) {//updatedate=null, sync_check=1

        SQLiteDatabase db = getWritableDatabase();

        Log.e("SQLITE_USER", "INSERT OR REPLACE INTO USER VALUES('"+ user_no + "','" + user_email + "','" + user_name + "','" + user_token + "','" + user_profile_image + "','" + user_login_api_type
                + "','"  + user_gender + "','" + user_age +"','" + user_token_refresh+"','" + user_guide_check +"');");

        db.execSQL("INSERT OR REPLACE INTO USER VALUES('"+ user_no + "','" + user_email + "','" + user_name + "','" + user_token + "','" + user_profile_image + "','" + user_login_api_type
                + "','"  + user_gender + "','" + user_age +"','" + user_token_refresh+"','" + user_guide_check +"');");
        db.close();

    }

    public void update(int user_no, String user_name, String user_profile_image, String user_gender, int user_age){ // 프로필 변경 시 사용
        SQLiteDatabase db = getWritableDatabase();

        Log.e("SQLITE_USER", "UPDATE USER SET user_name = '"+ user_name + "', " + "user_profile_image = '" + user_profile_image + "', " + "user_gender = '"
                + user_gender + "', "  +"user_age = '" + user_age  + "' WHERE user_no='"+ user_no + "'" );

        db.execSQL("UPDATE USER SET user_name = '"+ user_name + "', " + "user_profile_image = '" + user_profile_image + "', " + "user_gender = '"
                + user_gender + "', "  +"user_age = '" + user_age + "' WHERE user_no='"+ user_no + "'" );
        db.close();
    }

    public void delete (int user_no){ // 로그아웃 또는 서비스 탈퇴 시 사용

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM USER WHERE user_no = " + user_no +";");

        Log.i("SQLITE_USER", "delete id : "+ user_no);
    }

    public int select (String user_email) {//updatedate=null, sync_check=1

        int user_no = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER WHERE user_email = '"+user_email+"'",null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                user_no = cursor.getInt(cursor.getColumnIndex("user_no"));

            }
        }
        db.close();

        return user_no;
    }

    public String select (int user_no){ // 후기 작성 시 사용
        String data = "";

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM USER WHERE user_no = '"+user_no+"'", null);
        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                String user_name = c.getString(c.getColumnIndex("user_name"));
                String user_profile_image = c.getString(c.getColumnIndex("user_profile_image"));
                String user_gender = c.getString(c.getColumnIndex("user_gender"));

                JSONObject obj = new JSONObject();
                try {
                    obj.put("user_name", user_name);
                    obj.put("user_profile_image", user_profile_image);
                    obj.put("user_gender", user_gender);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                data = obj.toString();
            }
            Log.e("SQLITE_USER", data);
        }
        c.close();

        return data;
    }

    public void ProfileHistory() {
        //TODO:서버가서 가져와 비교
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER order by guide_id desc", null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int guide_id = cursor.getInt(cursor.getColumnIndex("guide_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String image_path = Constant.PROFILE_IMG_URL+cursor.getString(cursor.getColumnIndex("image_path"));
                String profile_thumbnail_image_url = Constant.GET_PROFILE_THUMBNAIL_IMG_URL +cursor.getString(cursor.getColumnIndex("image_path"));
                String age = cursor.getString(cursor.getColumnIndex("age"));
                String job = cursor.getString(cursor.getColumnIndex("job"));
                String self_introduction = cursor.getString(cursor.getColumnIndex("self_introduction"));
                String gender = cursor.getString(cursor.getColumnIndex("gender"));
                String created_at = cursor.getString(cursor.getColumnIndex("created_at"));
                String location = cursor.getString(cursor.getColumnIndex("location"));
                String guide_kind = cursor.getString(cursor.getColumnIndex("guide_kind"));
                String available_services = cursor.getString(cursor.getColumnIndex("available_services"));
                String consultation_time_1 = cursor.getString(cursor.getColumnIndex("consultation_time_1"));
                String consultation_time_2 = cursor.getString(cursor.getColumnIndex("consultation_time_2"));
                String consultation_time_3 = cursor.getString(cursor.getColumnIndex("consultation_time_3"));
                String tour_experience = cursor.getString(cursor.getColumnIndex("tour_experience"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String star_avg = cursor.getString(cursor.getColumnIndex("star_avg")); // 가이드 평점 평균

                MainActivity ha = new MainActivity();

                Log.d("SQLITE_USER",profile_thumbnail_image_url);


            }
        }
        cursor.close();
        db.close();
    }

}
