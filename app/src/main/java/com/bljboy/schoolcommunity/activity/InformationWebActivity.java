package com.bljboy.schoolcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.utils.HtmlFormat;
import com.google.android.material.appbar.MaterialToolbar;

public class InformationWebActivity extends AppCompatActivity {

    private WebView infor_web;
    private MaterialToolbar infor_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_web);
        infor_web = findViewById(R.id.infor_web);
        infor_back = findViewById(R.id.infor_back);
//        Intent intent = new Intent();
        String url = getIntent().getStringExtra("url");
        String content = getIntent().getStringExtra("content");
//        infor_web.loadUrl(url);
        infor_web.loadDataWithBaseURL(null, HtmlFormat.getNewContent(content), "text/html", "UTF-8", null);
        infor_back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}