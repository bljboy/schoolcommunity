package com.bljboy.schoolcommunity.myadapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.activity.ChatMessageActivity;
import com.bljboy.schoolcommunity.model.ForumMyData;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class ChatMyAdapter extends RecyclerView.Adapter<ChatMyAdapter.MyHolder> {
    private Context context;
    private List<ForumMyData> myDataList;

    public ChatMyAdapter(Context context, List<ForumMyData> myDataList) {
        this.context = context;
        this.myDataList = myDataList;
    }

    public ChatMyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ChatMyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_chat_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMyAdapter.MyHolder holder, int position) {
        ForumMyData forumMyData = myDataList.get(position);
        holder.chat_page_author.setText(forumMyData.getEmail());
        holder.cv_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatMessageActivity.class);
                intent.putExtra("email", forumMyData.getEmail());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return myDataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public MaterialTextView chat_page_author;
        private MaterialCardView cv_chat;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            chat_page_author = itemView.findViewById(R.id.chat_page_author);
            cv_chat = itemView.findViewById(R.id.cv_chat);
        }
    }
}
