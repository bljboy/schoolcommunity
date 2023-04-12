package com.bljboy.schoolcommunity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.ForumMyData;
import com.bljboy.schoolcommunity.model.ForumMyDataList;
import com.bljboy.schoolcommunity.myadapter.SystemUserMyAdapter;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SystemUserActivity extends AppCompatActivity {

    private MaterialToolbar back;
    private RecyclerView recyclerView;
    private SystemUserMyAdapter systemUserMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_user);
        back = findViewById(R.id.back);
        getUserAll();
        recyclerView = findViewById(R.id.recyclerview_system_user);
        back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getUserAll() {
        OkhttpHelper.getRequest(GlobalVars.URL + "user/user_all", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonData = response.body().string();
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    ForumMyDataList forumMyData = gson.fromJson(jsonData, ForumMyDataList.class);
                    List<ForumMyData> myDataList = forumMyData.getData();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showMyAdapter(myDataList);
                        }
                    });
                }
            }
        });
    }

    public void showMyAdapter(List<ForumMyData> myDataList) {
        systemUserMyAdapter = new SystemUserMyAdapter(this, myDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(systemUserMyAdapter);
    }
}