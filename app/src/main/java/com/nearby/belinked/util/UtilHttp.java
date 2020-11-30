package com.nearby.belinked.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UtilHttp {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    static OkHttpClient client = new OkHttpClient();

   public static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    //方法一：使用JSONObject
    public static void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String code = jsonObject.getString("code");
                String msg = jsonObject.getString("msg");
                System.out.println("code" + code + ";msg" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
