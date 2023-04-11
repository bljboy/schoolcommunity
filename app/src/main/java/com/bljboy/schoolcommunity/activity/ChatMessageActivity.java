package com.bljboy.schoolcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ChatMessageActivity extends AppCompatActivity {

    private MaterialToolbar back;
    private Socket mSocket;
    private Button bt_chat_sendmessage;
    private EditText et_chat_inputmessage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);
        getMessage();
        back = findViewById(R.id.back);
        bt_chat_sendmessage = findViewById(R.id.bt_chat_sendmessage);
        et_chat_inputmessage = findViewById(R.id.et_chat_inputmessage);
        String receiver_id = getIntent().getStringExtra("email");
        back.setTitle(receiver_id);
        SharedPreferences sp = getSharedPreferences(GlobalVars.LOGIN_STATUS, Context.MODE_PRIVATE);
        String sender_id = sp.getString("email", "");
        bt_chat_sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = et_chat_inputmessage.getText().toString();
                Log.e("sendMessage", "sendMessage: " + content);
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

    public void getMessage() {
        try {
            mSocket = IO.socket(GlobalVars.URL );
            mSocket.connect();
            mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d("getMessage", "Connected to server");
                }
            });
            mSocket.on("message", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    JSONObject data = (JSONObject) args[0];
                    try {
                        String senderId = data.getString("sender_id");
                        String receiverId = data.getString("receiver_id");
                        String content = data.getString("content");
                        Log.d("getMessage", "Received message from " + senderId + ": " + content);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendMessage(String senderId, String receiverId, String content) {
        try {
            JSONObject data = new JSONObject();
            data.put("sender_id", senderId);
            data.put("receiver_id", receiverId);
            data.put("content", content);
            mSocket.emit("message", data);
            Log.e("sendMessage", "sendMessage: " + data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
    }
}