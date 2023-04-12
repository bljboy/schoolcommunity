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
import com.bljboy.schoolcommunity.activity.SystemForumActivity;
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

public class SystemForumMyAdapter extends RecyclerView.Adapter<SystemForumMyAdapter.MyHolder> {
    private Context context;
    private List<ForumMyData> myDataList;

    public SystemForumMyAdapter(Context context, List<ForumMyData> myDataList) {
        this.context = context;
        this.myDataList = myDataList;
    }

    public SystemForumMyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SystemForumMyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_system_forum_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemForumMyAdapter.MyHolder holder, int position) {
        ForumMyData forumMyData = myDataList.get(position);
        holder.forum_page_author.setText(forumMyData.getEmail());
        holder.forum_page_pushtime.setText(forumMyData.getTime());
        Log.e("onBindViewHolder", "onBindViewHolder: " + forumMyData.getEmail());
        holder.forum_page_context.setText(forumMyData.getContent());
        holder.myforum_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                builder.setTitle("警告");
                builder.setMessage("确定要删除此帖子吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(forumMyData.getId());
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
        public MaterialTextView forum_page_author, forum_page_pushtime, forum_page_context;
        public Button myforum_delete;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            forum_page_author = itemView.findViewById(R.id.forum_page_author);
            forum_page_pushtime = itemView.findViewById(R.id.forum_page_pushtime);
            forum_page_context = itemView.findViewById(R.id.forum_page_context);
            myforum_delete = itemView.findViewById(R.id.myforum_delete);
        }
    }

    public void delete(int id) {
        OkhttpHelper.getRequest(GlobalVars.URL + "/forum/myforum/delete?id=" + id, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonData = response.body().string();
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Code code = gson.fromJson(jsonData, Code.class);
                    if (code.getCode() == 200) {
                        ((SystemForumActivity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "" + code.getMessage(), Toast.LENGTH_SHORT).show();
                                ((SystemForumActivity) context).getForum();
                            }
                        });
                    }
                }
            }
        });
    }
}
