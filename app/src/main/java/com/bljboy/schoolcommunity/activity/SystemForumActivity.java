package com.bljboy.schoolcommunity.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.ForumMyData;
import com.bljboy.schoolcommunity.model.ForumMyDataList;
import com.bljboy.schoolcommunity.myadapter.SystemForumMyAdapter;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SystemForumActivity extends AppCompatActivity {
    private MaterialToolbar back;
    private RecyclerView system_forum_recyclerview;

    private SystemForumMyAdapter systemForumMyAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_forum);
        getForum();
        back = findViewById(R.id.back);
        system_forum_recyclerview = findViewById(R.id.system_forum_recyclerview);
        back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getForum() {
        OkhttpHelper.getRequest(GlobalVars.URL + "forum/forum_all", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonData = response.body().string();
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    ForumMyDataList fromJson = gson.fromJson(jsonData, ForumMyDataList.class);
                    List<ForumMyData> forumMyDataList = fromJson.getData();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showMyAdapter(forumMyDataList);
                        }
                    });
                }
            }
        });
    }

    public void showMyAdapter(List<ForumMyData> forumMyDataList) {
        systemForumMyAdapter = new SystemForumMyAdapter(this, forumMyDataList);
        system_forum_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        system_forum_recyclerview.setAdapter(systemForumMyAdapter);
    }
}