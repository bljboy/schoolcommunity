package com.bljboy.schoolcommunity.myadapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.activity.MyForumActivity;
import com.bljboy.schoolcommunity.activity.SystemUserActivity;
import com.bljboy.schoolcommunity.model.Code;
import com.bljboy.schoolcommunity.model.ForumMyData;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SystemUserMyAdapter extends RecyclerView.Adapter<SystemUserMyAdapter.MyHolder> {
    private Context context;
    private List<ForumMyData> myDataList;

    public SystemUserMyAdapter(Context context, List<ForumMyData> myDataList) {
        this.context = context;
        this.myDataList = myDataList;
    }

    public SystemUserMyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SystemUserMyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_system_user_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemUserMyAdapter.MyHolder holder, int position) {
        ForumMyData forumMyData = myDataList.get(position);
        holder.user_author.setText(forumMyData.getEmail());
        holder.user_time.setText(forumMyData.getTime());
        Log.e("onBindViewHolder", "onBindViewHolder: " + forumMyData.getEmail());
        holder.user_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                builder.setTitle("警告");
                builder.setMessage("确定要删除此帖子吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(forumMyData.getEmail());
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击取消按钮后执行的操作
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return myDataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public MaterialTextView user_author, user_time;
        public Button user_delete;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            user_author = itemView.findViewById(R.id.user_author);
            user_time = itemView.findViewById(R.id.user_time);
            user_delete = itemView.findViewById(R.id.user_delete);
        }
    }

    public void delete(String email) {
        OkhttpHelper.getRequest(GlobalVars.URL + "/user/delete_all?email=" + email, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonData = response.body().string();
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Code code = gson.fromJson(jsonData, Code.class);
//                    if (code.getCode() == 200) {
//                        ((SystemUserActivity) context).runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
                                Toast.makeText(context, "" + code.getMessage(), Toast.LENGTH_SHORT).show();
//                                ((SystemUserActivity) context).getUserAll();
//                            }
//                        });
//                    }
                }
            }
        });
    }
}
