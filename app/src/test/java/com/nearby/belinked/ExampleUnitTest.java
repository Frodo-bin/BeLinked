package com.nearby.belinked;

import android.util.ArrayMap;

import com.alibaba.fastjson.JSON;
import com.nearby.belinked.util.UtilHttp;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void pingTest() throws IOException {
        String url = "http://118.178.185.111:8090/ping";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }
    }
    @Test
    public void msgTest() throws IOException {
        final String urlSendVerificationCode = "http://118.178.185.111:8090/user/sendVerifyCode";
        Map<String, String> phoneNumber = new HashMap<>(1);
        phoneNumber.put("userTel","18678907302");
        System.out.println(JSON.toJSONString(phoneNumber));
        String jsonResult = UtilHttp.post(urlSendVerificationCode, JSON.toJSONString(phoneNumber));
        System.out.println(jsonResult);
    }

    @Test
    public void loginTest() throws IOException {
        final String urlSendVerificationCode = "http://118.178.185.111:8090/user/login";
        Map<String, String> userInfo = new HashMap<>(1);
        userInfo.put("userTel","18678907302");
        userInfo.put("password","18678907302");
        System.out.println(JSON.toJSONString(userInfo));
        String jsonResult = UtilHttp.post(urlSendVerificationCode, JSON.toJSONString(userInfo));
        System.out.println(jsonResult);
    }
}