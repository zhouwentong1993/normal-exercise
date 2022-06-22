package com.wentong.demo.network.okhttp;

import com.wentong.demo.network.okhttp.event.HttpEventListener;
import okhttp3.*;

public class OkHttpTest {

    public static void main(String[] args) throws Exception {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().eventListenerFactory(call -> new HttpEventListener(call.request().url())).build();
        try (Response response = okHttpClient.newCall(new Request.Builder().url("https://baidu.com").build()).execute()) {
            System.out.println(response.body().string());
        }

    }

}
