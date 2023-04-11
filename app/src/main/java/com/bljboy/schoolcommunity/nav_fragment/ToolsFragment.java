package com.bljboy.schoolcommunity.nav_fragment;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bljboy.schoolcommunity.MainActivity;
import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.activity.CourseActivity;
import com.bljboy.schoolcommunity.utils.MyDragEventListener;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;
import com.google.android.material.card.MaterialCardView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ToolsFragment extends Fragment {
    private MaterialCardView tools_course, tools_ip;
    private String URL = "https://api.jisuapi.com/ip/location?appkey=9c4a04ec045a9a3c&ip=";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tools, container, false);
        tools_course = view.findViewById(R.id.tools_course);
        tools_ip = view.findViewById(R.id.tools_ip);
        tools_ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });
        tools_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CourseActivity.class);
                startActivity(intent);
            }
        });
//        MyDragEventListener myDragEventListener = new MyDragEventListener();
//        tools_course.setOnDragListener(myDragEventListener);
//        tools_course.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Log.d("tools_course", "" + tools_course);
//                ClipData data = ClipData.newPlainText("", "");
//                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
//                v.startDragAndDrop(data, shadowBuilder, v, 0);
//                return true;
//            }
//        });
        return view;
    }

    private void showInputDialog() {
        // 获取Dialog布局
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_input, null);
        EditText editText = (EditText) dialogView.findViewById(R.id.et_dialog);
        // 创建AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(dialogView);
        builder.setTitle("输入内容");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String content = editText.getText().toString();
                // TODO: 将输入的内容提交到服务器或其他逻辑处理
                if (!content.isEmpty()) {
                    OkhttpHelper.getRequest(URL + content, new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String jsonData = response.body().string();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(), "" + jsonData, Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });
                }else {
                    Toast.makeText(getActivity(), "不能为空", Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 取消按钮的点击事件
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
