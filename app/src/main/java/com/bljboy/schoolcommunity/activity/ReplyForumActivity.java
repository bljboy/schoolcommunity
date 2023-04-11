package com.bljboy.schoolcommunity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.ForumMyData;
import com.bljboy.schoolcommunity.model.ForumMyDataList;
import com.bljboy.schoolcommunity.myadapter.ForumMyAdapter;
import com.bljboy.schoolcommunity.myadapter.ReplyMyAdapter;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ReplyForumActivity extends AppCompatActivity {

    private MaterialToolbar push_back;
    private RecyclerView recyclerview_reply;
    private ReplyMyAdapter replyMyAdapter;
    private TextInputEditText reply_text;
    private Button reply_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_forum);
        push_back = findViewById(R.id.push_back);
        reply_text = findViewById(R.id.reply_text);
        reply_bt = findViewById(R.id.reply_bt);
        recyclerview_reply = findViewById(R.id.recyclerview_reply);
        SharedPreferences sp = getSharedPreferences(GlobalVars.LOGIN_STATUS, Context.MODE_PRIVATE);
        int id = sp.getInt("id", 0);
        String email = sp.getString("email", "");
        Log.d("onCreate", "onCreate: " + id);
        reply_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!reply_text.getText().toString().equals("")) {
                    RequestBody requestBody = new FormBody.Builder()
                            .add("forum_id", String.valueOf(id))
                            .add("email", email)
                            .add("content", reply_text.getText().toString())
                            .build();
                    OkhttpHelper.getRequest(GlobalVars.URL + "forum/reply", requestBody, new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            final String jsonData = response.body().string();
                            if (response.isSuccessful()) {
                                getReply(id);
                            }
                        }
                    });
                } else {
                    Toast.makeText(ReplyForumActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getReply(id);
        push_back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getReply(int id) {
        OkhttpHelper.getRequest(GlobalVars.URL + "/forum/reply/content?forum_id=" + id, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String jsonData = response.body().string();
                Log.d("onCreate", "onCreate: " + jsonData);
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    ForumMyDataList dataList = gson.fromJson(jsonData, ForumMyDataList.class);
                    Log.e("jsonData", "onResponse: " + dataList);
                    List<ForumMyData> myDataList = dataList.getData();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showMyDataList(myDataList);
                        }
                    });
                }
            }
        });
    }

    private void showMyDataList(List<ForumMyData> myDataList) {
        replyMyAdapter = new ReplyMyAdapter(this, myDataList);
        recyclerview_reply.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerview_reply.setAdapter(replyMyAdapter);
    }

}