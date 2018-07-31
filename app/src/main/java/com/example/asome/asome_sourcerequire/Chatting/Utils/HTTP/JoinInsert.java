package com.example.asome.asome_sourcerequire.Chatting.Utils.HTTP;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.INSERT_JOIN;

/**[OUTLINE]
 * 쪽지를 보낸다
 */


public class JoinInsert extends AsyncTask<String, Void, String> {
    String myName, yourId, msg_content, myRoom,userType;

/*    public JoinInsert(String myName, String yourId, String msg_content, String myRoom, String userType) {
        this.myName = myName;
        this.yourId = yourId;
        this.msg_content = msg_content;
        this.myRoom = myRoom;
        this.userType=userType;
    }*/

    protected void onPreExecute() {
    }

    protected String doInBackground(String... arg0) {


        try {
/*
PHP
*  $user_name = $_POST['user_name'];
 $user_email = $_POST['user_email'];
 $user_pwd = $_POST['user_pwd'];
 $user_company = $_POST['user_company'];
 $user_department = $_POST['user_department'];
 $user_position = $_POST['$user_position'];
 $user_phone = $_POST['$user_phone'];
 $user_profile_image = $_POST['$user_profile_image'];   //skip
*
* */
            URL url = new URL(INSERT_JOIN); // here is your URL path
            JSONObject postDataParams = new JSONObject();
            postDataParams.put("user_name", "경연리");
            postDataParams.put("user_email", "kyl@");
            postDataParams.put("user_pwd", "111");
            postDataParams.put("user_company", " ");
            postDataParams.put("user_type",  " ");
            Log.e("[postMsgContent_params]", postDataParams.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));
            Log.e("params", getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new
                        InputStreamReader(
                        conn.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                in.close();
                Log.e("[InsertMsgContent]", sb.toString());
                return sb.toString();

            } else {
                return new String("false : " + responseCode);
            }
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }

    }

    @Override
    protected void onPostExecute(String result) {
    }


    public static String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {
            String key = itr.next();
            Object value = params.get(key);
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }
}

