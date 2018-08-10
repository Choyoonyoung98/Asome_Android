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

import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.INSERT_ROLE;

/**
 * [OUTLINE]
 * 쪽지를 보낸다
 */


public class RoleInsert extends AsyncTask<String, Void, String> {
    String proj_id, role_name, role_user_id, role_start_date, role_end_date, role_status;

    public RoleInsert(String proj_id, String role_name, String role_user_id, String role_start_date, String role_end_date, String role_status) {
        this.proj_id = proj_id;
        this.role_name = role_name;
        this.role_user_id = role_user_id;
        this.role_start_date = role_start_date;
        this.role_end_date = role_end_date;
        this.role_status = role_status;
    }

    protected void onPreExecute() {
        Log.e("[ROLE_INSERT]", "fortest" + proj_id);

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
* */
            Log.e("[ROLE_INSERT]", "fortest2" + role_user_id);

            URL url = new URL(INSERT_ROLE); // here is your URL path
            JSONObject postDataParams = new JSONObject();

            postDataParams.put("proj_id", proj_id);
            postDataParams.put("role_name", role_name);
            postDataParams.put("role_user_id", role_user_id);
            postDataParams.put("role_start_date", role_start_date);
            postDataParams.put("role_end_date", role_end_date);
            postDataParams.put("role_status", role_status);

            Log.e("[ROLE_INSERT]", postDataParams.toString());

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
                Log.e("[ROLE_INSERT]", sb.toString());
                return sb.toString();

            } else {
                Log.e("[ROLE_INSERT]", "false : " + responseCode);
                return new String("false : " + responseCode);
            }
        } catch (Exception e) {
            Log.e("[ROLE_INSERT]", "Exceptiondfdf: " + e.getMessage());
            return new String("Exceptiondfdf: " + e.getMessage());
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

