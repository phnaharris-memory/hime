package com.dttv.hime_droid.helpers;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostHttpRequest extends AsyncTask<String, Integer, String> {
    public String doInBackground(String... keyword) {
        String API_SEARCH = new Constant().API_DOMAIN + "/v1/search";
        HttpURLConnection urlConnection = null;
        String res = "";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("keyword", keyword[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(jsonObject.toString(), mediaType); // new
            Request request = new Request.Builder()
                    .url(API_SEARCH)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }
}
