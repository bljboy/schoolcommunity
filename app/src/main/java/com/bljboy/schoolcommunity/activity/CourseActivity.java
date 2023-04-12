package com.bljboy.schoolcommunity.activity;

import static android.app.Service.START_STICKY;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.utils.NotificationReceiver;
import com.google.android.material.appbar.MaterialToolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CourseActivity extends AppCompatActivity {
    private Button btn_send_simple;
    private EditText et_title, et_message;
    private EditText et_time;
    private NotificationManager notificationManager;
    private Notification notification;
    private MaterialToolbar back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        btn_send_simple = findViewById(R.id.btn_send_simple);
        et_title = findViewById(R.id.et_title);
        et_message = findViewById(R.id.et_message);
        et_time = findViewById(R.id.et_time);
        back = findViewById(R.id.back);
        back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_send_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_title.getText())) {
                    Toast.makeText(CourseActivity.this, "请填写消息标题", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(et_message.getText())) {
                    Toast.makeText(CourseActivity.this, "请填写消息内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                String title = et_title.getText().toString();
                String message = et_message.getText().toString();
                String time = et_time.getText().toString();
                int num = Integer.parseInt(time); // 将字符串类型转换为整数类型
                int num2 = num * 1000;
                Toast.makeText(CourseActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        // 定时执行的操作
                        sendSimpleNotify(title, message);
                    }
                };
                handler.postDelayed(runnable, num2); // 延迟1秒后执行
            }
        });
    }

    // 发送简单的通知消息（包括消息标题和消息内容）
    private void sendSimpleNotify(String title, String message) {
        notificationManager = (NotificationManager) this.getSystemService(Activity.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("v", "通知", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            notification = new Notification.Builder(this, "v")
                    .setContentText(message)
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.tools)
                    .build();
            notificationManager.notify(0, notification);
        }
    }


}
