package com.bljboy.schoolcommunity.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.ForumMyData;
import com.bljboy.schoolcommunity.model.ForumMyDataList;
import com.bljboy.schoolcommunity.myadapter.MyForumMyAdapter;
import com.bljboy.schoolcommunity.myadapter.SearchMyAdapter;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyForumActivity extends AppCompatActivity {
    private RecyclerView myforum_recyclerview;
    private MaterialToolbar myform_back;
    private MyForumMyAdapter myForumMyAdapter;
    private Button myforum_delete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_forum);
        myforum_recyclerview = findViewById(R.id.myforum_recyclerview);
        myform_back = findViewById(R.id.myform_back);
        myforum_delete = findViewById(R.id.myforum_delete);
        getMyForum();
        myform_back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getMyForum() {
        SharedPreferences sp = getSharedPreferences(GlobalVars.LOGIN_STATUS, Context.MODE_PRIVATE);
        String email = sp.getString("email", "");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkhttpHelper.getRequest(GlobalVars.URL + "forum/myforum?email=" + email, new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            Gson gson = new Gson();
                            ForumMyDataList dataList = gson.fromJson(jsonData, ForumMyDataList.class);
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
        }).start();

    }

    private void showMyDataList(List<ForumMyData> myDataList) {
        myForumMyAdapter = new MyForumMyAdapter(this, myDataList);
        myforum_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myforum_recyclerview.setAdapter(myForumMyAdapter);
    }


}