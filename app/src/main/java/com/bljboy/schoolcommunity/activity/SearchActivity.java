package com.bljboy.schoolcommunity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.bljboy.schoolcommunity.myadapter.SearchMyAdapter;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {
    private MaterialToolbar search_back;
    private SearchMyAdapter searchMyAdapter;
    private RecyclerView recyclerView;
    private TextInputEditText search_text;
    private Button search_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search_back = findViewById(R.id.search_back);
        recyclerView = findViewById(R.id.search_recyclerview);
        search_text = findViewById(R.id.search_text);
        search_bt = findViewById(R.id.search_bt);
        searchMyAdapter = new SearchMyAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(searchMyAdapter);
        search_back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!search_text.getText().toString().equals("")) {
                    getSearch(search_text.getText().toString());
                } else {
                    Toast.makeText(SearchActivity.this, "请输入关键字", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getSearch(String search_text) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkhttpHelper.getRequest(GlobalVars.URL + "/search?content=" + search_text, new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        final String jsonData = response.body().string();
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
        }).start();

    }

    private void showMyDataList(List<ForumMyData> myDataList) {
        searchMyAdapter = new SearchMyAdapter(this, myDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(searchMyAdapter);
    }
}