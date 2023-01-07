package com.bljboy.schoolcommunity;

import android.util.Log;
import android.view.View;

import junit.framework.TestCase;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class getSyncTest extends TestCase {

    public void testGetSync() {
        getSync();
    }

    //注意网络同步请求必须要有一个子线程
    //get同步请求
    public void getSync() {
        String url = "http://127.0.0.1:5000/jkyw.json";
        OkHttpClient client = new OkHttpClient();
        new Thread() {
            @Override
            public void run() {
                //get请求参数可以直接写在url后面
                //https://www.httpbin.org/get为开源测试网址
                Request request = new Request.Builder().url(url).build();
                //请求的call对象
                Call call = client.newCall(request);
                try {
                    Response response = call.execute();
                    Log.e("get同步请求:", "getSync:" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}