package com.bljboy.schoolcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.ChatMessageData;
import com.bljboy.schoolcommunity.myadapter.ChatMessageAdapter;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ChatMessageActivity extends AppCompatActivity {

    private MaterialToolbar back;
    private Socket mSocket;
    private Button bt_chat_sendmessage;
    private EditText et_chat_inputmessage;
    private TextView tv_chat_message;
    private List<ChatMessageData> mChatMessages = new ArrayList<>();
    private RecyclerView chat_message_recyclerview;
    private ChatMessageAdapter chatMessageAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);
        getConnect();
        back = findViewById(R.id.back);
        bt_chat_sendmessage = findViewById(R.id.bt_chat_sendmessage);
//        tv_chat_message = findViewById(R.id.tv_chat_message);
        et_chat_inputmessage = findViewById(R.id.et_chat_inputmessage);
        chat_message_recyclerview = findViewById(R.id.chat_message_recyclerview);
        chatMessageAdapter = new ChatMessageAdapter(ChatMessageActivity.this, mChatMessages);
        chat_message_recyclerview.setLayoutManager(new LinearLayoutManager(ChatMessageActivity.this, LinearLayoutManager.VERTICAL, false));
        chat_message_recyclerview.setAdapter(chatMessageAdapter);

        String receiver_id = getIntent().getStringExtra("email");

        back.setTitle(receiver_id);

        SharedPreferences sp = getSharedPreferences(GlobalVars.LOGIN_STATUS, Context.MODE_PRIVATE);
        String sender_id = sp.getString("email", "");
        bt_chat_sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = et_chat_inputmessage.getText().toString();
                sendMessage(sender_id, receiver_id, content);
            }
        });
        back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getConnect() {
        try {
            IO.Options opts = new IO.Options();
            mSocket = IO.socket(GlobalVars.URL, opts);
            mSocket.connect();
            mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d("getConnect", "Connected to server");
                }
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        SharedPreferences sp = getSharedPreferences(GlobalVars.LOGIN_STATUS, Context.MODE_PRIVATE);
        String sender_id = sp.getString("email", "");
        String receiver_id = getIntent().getStringExtra("email");
        sendMessage(sender_id, receiver_id, "");
        getMessage();

    }

    public void sendMessage(String senderId, String receiverId, String content) {
        try {
            JSONObject data = new JSONObject();
            data.put("sender_id", senderId);
            data.put("receiver_id", receiverId);
            data.put("content", content);
            mSocket.emit("message", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
    }

    public void getMessage() {
        mSocket.on("message", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String senderId = data.getString("sender_id");
                    String receiverId = data.getString("receiver_id");
                    String content = data.getString("content");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!content.equals("")) {
                                ChatMessageData message = new ChatMessageData(content, senderId, ChatMessageData.TYPE_OTHER);
                                mChatMessages.add(message);
                                chatMessageAdapter.notifyItemInserted(mChatMessages.size() - 1);
                                et_chat_inputmessage.setText("");
                            }

                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}