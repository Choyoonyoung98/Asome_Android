package com.example.asome.asome_sourcerequire.Utils.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by anfrh on 2017-08-08.
 */

public class SharedPreferencesUtils {
    Context context;

    public SharedPreferencesUtils(Context context) {
        this.context = context;
    }

    /**
     * 사용자 프로필 정보 모음
     *
     */
    public void setUserInfo(String user_email, String user_login_api_type, String user_gender,
                            String user_name, String user_profile_image, int user_age, int user_no) {

        android.content.SharedPreferences pref = context.getSharedPreferences("user", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = pref.edit();

        editor.putString("user_email", user_email); // email
        editor.putString("user_login_api_type", user_login_api_type);
        editor.putString("user_gender", user_gender);
        editor.putString("user_name", user_name);
        editor.putString("user_profile_image", user_profile_image);
        editor.putInt("user_age", user_age);
        editor.putInt("user_no", user_no); // 사용자 유저 아이디

        editor.commit();
    }
    public void deleteUserInfo(String user_email, int user_no, String user_token, int user_token_refresh,
                               String user_login_api_type, String user_gender, String user_name, String user_profile_image, int user_age)
    {

        android.content.SharedPreferences pref = context.getSharedPreferences("user", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = pref.edit();

        editor.putString("user_email", user_email); // email
        editor.putInt("user_no", user_no); // email
        editor.putString("user_token", user_token);
        editor.putInt("user_token_refresh", user_token_refresh);
        editor.putString("user_login_api_type", user_login_api_type);
        editor.putString("user_gender", user_gender);
        editor.putString("user_name", user_name);
        editor.putString("user_profile_image", user_profile_image);
        editor.putInt("user_age", user_age);

        editor.commit();
    }

    public void updateUserInfo(String user_name, int user_age, String user_profile_image, String user_gender)
    {

        android.content.SharedPreferences pref = context.getSharedPreferences("user", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = pref.edit();

        editor.putString("user_gender", user_gender);
        editor.putString("user_name", user_name);
        editor.putString("user_profile_image", user_profile_image);
        editor.putInt("user_age", user_age);

        editor.commit();
    }
    public void setUserToken(String user_token, int user_token_refresh) {
        android.content.SharedPreferences pref = context.getSharedPreferences("user", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = pref.edit();
        editor.putString("user_token", user_token); // 로그인 상태가 되니까 token 집어넣습니다.
        editor.putInt("user_token_refresh", user_token_refresh); //로그인 상태가 되니까 1이 들어갑니다
        editor.commit();
    }
    public int getUserNo() {
        android.content.SharedPreferences pref = context.getSharedPreferences("user", MODE_PRIVATE);
        int user_no = pref.getInt("user_no", -1);
        return user_no;
    }
    public String getUserEmail() {
        android.content.SharedPreferences pref = context.getSharedPreferences("user", MODE_PRIVATE);
        String string = pref.getString("user_email", "null");
        return string;
    }
    public String getUserGender() {
        android.content.SharedPreferences pref = context.getSharedPreferences("user", MODE_PRIVATE);
        String string = pref.getString("user_gender", "null");
        return string;
    }
    public String getUserName() {
        android.content.SharedPreferences pref = context.getSharedPreferences("user", MODE_PRIVATE);
        String string = pref.getString("user_name", "null");
        return string;
    }
    public String getUserLoginType() {
        android.content.SharedPreferences pref = context.getSharedPreferences("user", MODE_PRIVATE);
        String string = pref.getString("user_login_api_type", "null");
        return string;
    }
    public String getUserProfileImage() {
        android.content.SharedPreferences pref = context.getSharedPreferences("user", MODE_PRIVATE);
        String string = pref.getString("user_profile_image", "null");
        return string;
    }
    public int getUserTokenRefresh() {
        android.content.SharedPreferences pref = context.getSharedPreferences("user", MODE_PRIVATE);
         int user_token_refresh= pref.getInt("user_token_refresh",-1);
        return user_token_refresh;
    }
    public int getUserAge() {
        android.content.SharedPreferences pref = context.getSharedPreferences("user", MODE_PRIVATE);
        int user_age= pref.getInt("user_age",-1);
        return user_age;
    }

    // 상품에서 셋팅한 가이드 값 셋팅하기
    public void setGuideNo(int guide_no) {
        android.content.SharedPreferences pref = context.getSharedPreferences("product", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = pref.edit();
        editor.putInt("guide_no", guide_no);
        editor.commit();
    }

    // 상품에서 셋팅한 가이드 값 셋팅하기
    public int getGuideNo() {
        android.content.SharedPreferences pref = context.getSharedPreferences("product", MODE_PRIVATE);
        int guide_no = pref.getInt("guide_no", 0);
        return guide_no;
    }

    // 값 불러오기
    public String getPreferences() {
        android.content.SharedPreferences pref = context.getSharedPreferences("notification", MODE_PRIVATE);
        String string = pref.getString("roomId", "null");
        return string;
    }
    public void setUserTypeGuide() {
        android.content.SharedPreferences pref = context.getSharedPreferences("userType", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = pref.edit();
        editor.putString("userType", "guide");
        editor.commit();
    }
    public void setUserTypeCustomer() {
        android.content.SharedPreferences pref = context.getSharedPreferences("userType", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = pref.edit();
        editor.putString("userType", "customer");
        editor.commit();
    }
    public String getUserType() {
        android.content.SharedPreferences pref = context.getSharedPreferences("userType", MODE_PRIVATE);
        String string = pref.getString("userType", "null");
        return string;
    }
    /*
    //TODO: 노티 세부화 해당 방만 오프해주고 나머지는 켜 두어야 해
    public void notiOff(String roomId) {
        android.content.SharedPreferences pref = context.getSharedPreferences("notification", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = pref.edit();
        editor.putString("status", "off");
        editor.putString("roomId", roomId);
        editor.commit();
    }

    public void notiOn() {
        android.content.SharedPreferences pref = context.getSharedPreferences("notification", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = pref.edit();
        editor.putString("status", "on");
        editor.commit();
    }

    public void destroyOff() {
        android.content.SharedPreferences prefte = context.getSharedPreferences("Destroy", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = prefte.edit();
        editor.putString("status", "off");
        editor.commit();
    }*/
    // 값(ALL Data) 삭제하기
    public void removeUserType(){
        SharedPreferences pref = context.getSharedPreferences("userType", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }


}
