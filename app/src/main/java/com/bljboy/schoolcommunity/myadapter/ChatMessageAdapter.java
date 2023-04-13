package com.bljboy.schoolcommunity.myadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.ChatMessageData;

import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ChatMessageData> mChatMessages;
    private Context context;

    public ChatMessageAdapter(Context context, List<ChatMessageData> mChatMessages) {
        this.context = context;
        this.mChatMessages = mChatMessages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == ChatMessageData.TYPE_USER) {
            // 用户发送的消息
            view = inflater.inflate(R.layout.item_chat_message_right, parent, false);
            return new UserMessageViewHolder(view);
        } else {
            // 接收到的消息
            view = inflater.inflate(R.layout.item_chat_message_left, parent, false);
            return new OtherMessageViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessageData message = mChatMessages.get(position);
        if (holder instanceof UserMessageViewHolder) {
            // 设置用户发送的消息
            ((UserMessageViewHolder) holder).textViewMessage.setText(message.getMessage());
//            ((UserMessageViewHolder) holder).textViewTime.setText((int) message.getTime());
        } else if (holder instanceof OtherMessageViewHolder) {
            // 设置接收到的消息
            ((OtherMessageViewHolder) holder).textViewName.setText(message.getName());
            ((OtherMessageViewHolder) holder).textViewMessage.setText(message.getMessage());
//            ((OtherMessageViewHolder) holder).textViewTime.setText((int) message.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return mChatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mChatMessages.get(position).getType();
    }

    // 用户发送的消息的 ViewHolder
    public static class UserMessageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewMessage;
        public TextView textViewTime;

        public UserMessageViewHolder(View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.text_view_message);
            textViewTime = itemView.findViewById(R.id.text_view_time);
        }
    }

    // 接收到的消息的 ViewHolder
    public static class OtherMessageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewMessage;

        public OtherMessageViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewMessage = itemView.findViewById(R.id.text_view_message);
//            textViewTime = itemView.findViewById(R.id.text_view_time);
        }
    }
}
