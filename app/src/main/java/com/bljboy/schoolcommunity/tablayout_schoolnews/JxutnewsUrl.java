package com.bljboy.schoolcommunity.tablayout_schoolnews;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.utils.HtmlFormat;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textview.MaterialTextView;

public class JxutnewsUrl extends AppCompatActivity {
    WebView jxut_newsurl;
    MaterialToolbar jxut_url_toolbar;
    MaterialTextView jxut_url_title;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jxut_url);
        Intent intent = getIntent();
        String jkyw_url = intent.getStringExtra("jkyw_url");
        String jkyw_html = intent.getStringExtra("jxutnews_html");
        String jkyw_title = intent.getStringExtra("jxutnews_title");

        jxut_newsurl = findViewById(R.id.jxut_newsurl);
        jxut_url_toolbar = findViewById(R.id.jxut_url_toolbar);
        jxut_url_title = findViewById(R.id.jxut_url_title);
        jxut_url_title.setText(jkyw_title);
        jxut_url_toolbar.setTitle("详情");
        jxut_url_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        jxut_newsurl.loadUrl(jkyw_url);
        jxut_newsurl.loadDataWithBaseURL(null, HtmlFormat.getNewContent(jkyw_html), "text/html", "UTF-8", null);
    }
}