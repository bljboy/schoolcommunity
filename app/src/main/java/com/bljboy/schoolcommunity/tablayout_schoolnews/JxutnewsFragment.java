package com.bljboy.schoolcommunity.tablayout_schoolnews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.JkywModel;
import com.bljboy.schoolcommunity.myadapter.JxutnewsMyAdapter;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JxutnewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private JxutnewsMyAdapter myAdapterJxutnews;
    private List<JkywModel> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getRequest();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jxutnews, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_jxutnews);

        return view;
    }

    public void getRequest() {
        String URL = "http://192.168.10.166:5000/jkyw.json";
        OkhttpHelper.getRequest(URL, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data = response.body().string();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 0;
                        message.obj = data;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }


    public Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String data = (String) msg.obj;
                try {
                    JSONArray jsonArray = new JSONArray(data);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject js = jsonArray.getJSONObject(i);
                        String day = js.getString("day");
                        String year = js.getString("yea");
                        String title = js.getString("title");
                        String page_content = js.getString("page_content");
                        String page_url = js.getString("page_url");
                        JkywModel jkywModel = new JkywModel(day, year, title, page_content, page_url);
                        list.add(jkywModel);
                    }
                    myAdapterJxutnews = new JxutnewsMyAdapter(getActivity(), list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(myAdapterJxutnews);
//                    customProgressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    };

}