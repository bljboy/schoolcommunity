package com.bljboy.schoolcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.bljboy.schoolcommunity.R;

public class ReplyForumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_forum);
        Log.d("onCreate", "onCreate()");

    }
}