package com.example.asome.asome_sourcerequire.Utils.HTTP;

import android.os.AsyncTask;
import android.util.Log;

import com.example.asome.asome_sourcerequire.Project.ProjectItem;
import com.example.asome.asome_sourcerequire.Project.Role;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import static com.example.asome.asome_sourcerequire.Chatting.Activity.ChatActivity.roles;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.SELECT_ROLE;
import static com.example.asome.asome_sourcerequire.Main.TwoFragment.projectAdapter;

/**
 * [OUTLINE]
 * 채팅방 가져온다
 */

public class RoleSelect extends AsyncTask<String, Void, String> {
    // DBHelperChatting dbHelper_room = new DBHelperChatting(getApplicationContext(), "ROOM.db", null, 1);
    String proj_id;

    public RoleSelect(String proj_id) {
        this.proj_id = proj_id;
    }

    protected void onPreExecute() {

    }

    protected String doInBackground(String... arg0) {

        try {

            URL url = new URL(SELECT_ROLE); // here is your URL path
            JSONObject postDataParams = new JSONObject();
          postDataParams.put("proj_id", proj_id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString2(postDataParams));

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
                Log.e("[RoleSelect]", sb.toString());
                jsonArrToRole(sb.toString());
                return sb.toString();

            } else {
                Log.e("[RoleSelect]", "false : " + responseCode);


                return new String("false : " + responseCode);

            }
        } catch (Exception e) {
            Log.e("[RoleSelect]", "Exception: " + e.getMessage());

            return new String("Exception: " + e.getMessage());
        }

    }

    @Override
    protected void onPostExecute(String result) {

    }

    public String jsonArrToRole(String jsonArr) {
        Log.e("[RoleSelect]", jsonArr);
        try {
            JSONArray jsonArray = new JSONArray(jsonArr);
            for (int i = 0; i < jsonArray.length(); i++) {
                String role_name = jsonArray.getJSONObject(i).getString("role_name");
                String role_start_date = jsonArray.getJSONObject(i).getString("role_start_date");
                String role_end_date = jsonArray.getJSONObject(i).getString("role_end_date");
                String role_status = jsonArray.getJSONObject(i).getString("role_status");
                // String role_real_name = jsonArray.getJSONObject(i).getString("role_real_name");

                Log.e("[RoleSelect]" + i, jsonArray.getJSONObject(i).toString());


                roles.add(new Role(role_name, role_start_date, role_end_date, role_status, role_name));
                // projectAdapter.addItem(new ProjectItem(proj_name,proj_about,proj_id));
            }
            projectAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            Log.e("[RoleSelect]", e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public static String getPostDataString2(JSONObject params) throws Exception {

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