package com.bljboy.schoolcommunity.myadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.ForumMyData;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class ReplyMyAdapter extends RecyclerView.Adapter<ReplyMyAdapter.MyHolder> {
    private Context context;
    private List<ForumMyData> myDataList;

    public ReplyMyAdapter(Context context, List<ForumMyData> myDataList) {
        this.context = context;
        this.myDataList = myDataList;
    }

    public ReplyMyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ReplyMyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_reply_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyMyAdapter.MyHolder holder, int position) {
        ForumMyData forumMyData = myDataList.get(position);
        holder.reply_page_author.setText(forumMyData.getEmail());
        holder.reply_page_pushtime.setText(forumMyData.getTime());
        Log.e("onBindViewHolder", "onBindViewHolder: " + forumMyData.getEmail());
        holder.reply_page_context.setText(forumMyData.getContent());
    }


    @Override
    public int getItemCount() {
        return myDataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public MaterialTextView reply_page_author, reply_page_pushtime, reply_page_context;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            reply_page_author = itemView.findViewById(R.id.reply_page_author);
            reply_page_pushtime = itemView.findViewById(R.id.reply_page_pushtime);
            reply_page_context = itemView.findViewById(R.id.reply_page_context);
        }
    }
}
