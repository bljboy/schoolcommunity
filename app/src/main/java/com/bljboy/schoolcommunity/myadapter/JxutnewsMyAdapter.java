package com.bljboy.schoolcommunity.myadapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.Jkyw;
import com.bljboy.schoolcommunity.model.JkywModel;
import com.bljboy.schoolcommunity.model.list;
import com.bljboy.schoolcommunity.tablayout_schoolnews.JxutnewsFragment;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JxutnewsMyAdapter extends RecyclerView.Adapter<JxutnewsMyAdapter.MyHolder> {
    private Context context;
    private List<JkywModel> list;

    public JxutnewsMyAdapter(Context context, List<JkywModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public JxutnewsMyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_jxut_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JxutnewsMyAdapter.MyHolder holder, int position) {
        JkywModel jkywModel = list.get(position);
        holder.jxutnews_timemonth.setText(jkywModel.getJxutnews_timemonth());
        holder.jxutnews_timeyear.setText(jkywModel.getJxutnews_timeyear());
        holder.jxutnews_title.setText(jkywModel.getJxutnews_title());
        holder.jxutnews_content.setText(jkywModel.getJxutnews_content());
//        holder.jxutnews_timemonth.setText(jkywModel.getJxutnews_url());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //容纳View视图,提供前端的视图文件
    public class MyHolder extends RecyclerView.ViewHolder {
        MaterialTextView jxutnews_timemonth;
        MaterialTextView jxutnews_timeyear;
        MaterialTextView jxutnews_title;
        MaterialTextView jxutnews_content;
        View view;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            jxutnews_timemonth = itemView.findViewById(R.id.jxutnews_timemonth);
            jxutnews_timeyear = itemView.findViewById(R.id.jxutnews_timeyear);
            jxutnews_title = itemView.findViewById(R.id.jxutnews_title);
            jxutnews_content = itemView.findViewById(R.id.jxutnews_content);
            this.view = itemView;
        }
    }

}

