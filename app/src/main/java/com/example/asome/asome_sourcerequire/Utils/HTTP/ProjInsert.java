package com.example.asome.asome_sourcerequire.Utils.HTTP;

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

import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.INSERT_PROJ;

/**[OUTLINE]
 * 쪽지를 보낸다
 */


public class  ProjInsert extends AsyncTask<String, Void, String> {
    String proj_name, proj_about, role_name, role_user_id, role_start_date, role_end_date,role_status;

    public ProjInsert( String proj_name, String proj_about, String role_name, String role_user_id, String role_start_date, String role_end_date,String role_status) {
        this.proj_name = proj_name;
        this.proj_about = proj_about;
        this.role_name = role_name;
        this.role_user_id = role_user_id;
        this.role_start_date = role_start_date;
        this.role_end_date = role_end_date;
        this.role_status = role_status;
    }

    protected void onPreExecute() {
    }

    protected String doInBackground(String... arg0) {


        try {
/*
PHP
 $proj_UUID = $_POST['proj_UUID'];
 $proj_name = $_POST['proj_name'];
 $proj_about = $_POST['proj_about'];
 $role_name = $_POST['role_name'];
 $role_user_id = $_POST['role_user_id'];
 $role_start_date = $_POST['role_start_date'];
 $role_end_date = $_POST['role_end_date'];
 $role_hour = $_POST['role_hour'];
 $role_status = $_POST['role_status'];

*
*
* 1kvmfhwprxmaks dl
* */
            URL url = new URL(INSERT_PROJ); // here is your URL path
            JSONObject postDataParams = new JSONObject();
            postDataParams.put("proj_name", proj_name);
            postDataParams.put("proj_about", proj_about);
            Log.e("[insert_proj]", postDataParams.toString());

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
                Log.e("[InsertPrject]", sb.toString());
                //요거 리턴값
                return String.valueOf(sb);

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

