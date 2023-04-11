package com.bljboy.schoolcommunity.nav_fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.ForumMyData;
import com.bljboy.schoolcommunity.model.ForumMyDataList;
import com.bljboy.schoolcommunity.myadapter.ChatMyAdapter;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChatFragment extends Fragment {

    private RecyclerView chat_recyclerview;
    private ChatMyAdapter chatMyAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        chat_recyclerview = view.findViewById(R.id.chat_recyclerview);
        getUser();
        return view;
    }

    public void getUser() {
        SharedPreferences sp = getContext().getSharedPreferences(GlobalVars.LOGIN_STATUS, Context.MODE_PRIVATE);
        String email = sp.getString("email", "");
        if (!email.isEmpty()) {
            OkhttpHelper.getRequest(GlobalVars.URL + "/user/query_all?email=" + email, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Gson gson = new Gson();
                        ForumMyDataList dataList = gson.fromJson(jsonData, ForumMyDataList.class);
                        List<ForumMyData> myDataList = dataList.getData();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showMyDataList(myDataList);
                            }
                        });
                    }
                }
            });
        }

    }

    public void showMyDataList(List<ForumMyData> myDataList) {
        chatMyAdapter = new ChatMyAdapter(getContext(), myDataList);
        chat_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        chat_recyclerview.setAdapter(chatMyAdapter);
    }
}