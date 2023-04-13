package com.bljboy.schoolcommunity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.Code;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class PushForumActivity extends AppCompatActivity {
    private TextInputEditText et_pushforum_title, et_pushforum_content;
    private MaterialToolbar push_back;
    private SharedPreferences sp;
    private FloatingActionButton fab_pushforum;
    final String FORUM_URL = GlobalVars.URL + "forum/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushforum);
        push_back = findViewById(R.id.push_back);
        et_pushforum_title = findViewById(R.id.et_pushforum_title);
        et_pushforum_content = findViewById(R.id.et_pushforum_content);
        fab_pushforum = findViewById(R.id.fab_pushforum);
        sp = getSharedPreferences(GlobalVars.LOGIN_STATUS, Context.MODE_PRIVATE);
        push_back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fab_pushforum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_pushforum_title.setText("test");
                String title = et_pushforum_title.getText().toString();
                String content = et_pushforum_content.getText().toString();
//                Log.e("title", "onCreate: " + title);
                String email = sp.getString("email", "");
                if (!title.equals("") && !content.equals("")) {
                    RequestBody requestBody = new FormBody.Builder()
                            .add("title", title)
                            .add("content", content)
                            .add("email", email)
                            .build();

                    OkhttpHelper.getRequest(FORUM_URL + "push", requestBody, new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            if (response.isSuccessful()) {
                                final String jsonData = response.body().string();
                                Gson gson = new Gson();
                                Code code = gson.fromJson(jsonData, Code.class);
                                if (code.getCode() == 200) {
                                    Log.e("fab_pushforum", "onResponse: " + jsonData);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(PushForumActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    });
                                } else {
                                    Toast.makeText(PushForumActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(PushForumActivity.this, "服务器错误", Toast.LENGTH_SHORT).show();
                            }
                            response.close();
                        }
                    });
                } else {
                    Toast.makeText(PushForumActivity.this, "请填写内容", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}