package com.bljboy.schoolcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bljboy.schoolcommunity.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class SystemAdminActivity extends AppCompatActivity {
    private MaterialCardView cv_system_user, cv_system_forum;
    private MaterialToolbar back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_admin);
        cv_system_user = findViewById(R.id.cv_system_user);
        back = findViewById(R.id.back);
        cv_system_forum = findViewById(R.id.cv_system_forum);
        cv_system_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SystemAdminActivity.this, SystemForumActivity.class);
                startActivity(intent);
            }
        });
        back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cv_system_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SystemAdminActivity.this, SystemUserActivity.class);
                startActivity(intent);
            }
        });
    }
}