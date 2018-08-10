package com.example.asome.asome_sourcerequire.Utils.HTTP;

import android.os.AsyncTask;
import android.util.Log;

import com.example.asome.asome_sourcerequire.Project.ProjectItem;

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
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.SELECT_PROJ;
import static com.example.asome.asome_sourcerequire.Main.TwoFragment.projectAdapter;

/**[OUTLINE]
 * 채팅방 가져온다
 */

public class ProjSelect extends AsyncTask<String, Void, String> {
   // DBHelperChatting dbHelper_room = new DBHelperChatting(getApplicationContext(), "ROOM.db", null, 1);

    protected void onPreExecute() {

    }

    protected String doInBackground(String... arg0) {

        try {

            URL url = new URL(SELECT_PROJ); // here is your URL path
            JSONObject postDataParams = new JSONObject();
          //  postDataParams.put("management_customer_no", hori_id);
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
                Log.e("[ProjSelect]", sb.toString());
                jsonArrToRoom(sb.toString());
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


    public String jsonArrToRoom(String jsonArr) {
        Log.e("[ProjSelect]", jsonArr);
        try {
            JSONArray jsonArray = new JSONArray(jsonArr);
            for (int i = 0; i < jsonArray.length(); i++) {
                //{"proj_id":"18","proj_UUID":null,"proj_name":"?????","proj_about":null,"role_name":null,"role_user_id":null,
                // "role_start_date":null,"role_end_date":null,"role_hour":null,"role_status":null}
                String proj_id = jsonArray.getJSONObject(i).getString("proj_id");
                String proj_UUID = jsonArray.getJSONObject(i).getString("proj_UUID");
                String proj_name = jsonArray.getJSONObject(i).getString("proj_name");
                String proj_about = jsonArray.getJSONObject(i).getString("proj_about");

                String role_name = jsonArray.getJSONObject(i).getString("role_name");
                String role_user_id = jsonArray.getJSONObject(i).getString("role_user_id");
                String role_start_date = jsonArray.getJSONObject(i).getString("role_start_date");
                String role_end_date = jsonArray.getJSONObject(i).getString("role_end_date");
                String role_status = jsonArray.getJSONObject(i).getString("role_status");
                Log.e("[ProjSelect]"+i, jsonArray.getJSONObject(i).toString());

            //    dbHelper_room.insert_room(roomName, guideName, profileUrl, " ", DateFormat.date_full());
                projectAdapter.addItem(new ProjectItem(proj_name,proj_about,Integer.parseInt(proj_id)));
            }
        //    chatListItem_adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            Log.e("[ProjSelect]", e.toString());
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