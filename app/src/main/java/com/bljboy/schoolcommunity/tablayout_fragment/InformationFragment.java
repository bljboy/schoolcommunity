package com.bljboy.schoolcommunity.tablayout_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.NewsMyData;
import com.bljboy.schoolcommunity.myadapter.InformationMyAdapter;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class InformationFragment extends Fragment {
    private RecyclerView recyclerView;
    private InformationMyAdapter myAdapterInformation;
    private StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    private int currentPage = 0;

    public InformationFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_information);
//        myAdapterInformation = new InformationMyAdapter(getContext());
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        recyclerView.setAdapter(myAdapterInformation);
        getNews();
        getScrollPage();
        return view;
    }

    public void getNews() {
        // 计算下一页的 start 和 num 参数
        int start = currentPage * 10;
        int num = 10;
//        String URL = "https://api.jisuapi.com/news/get?channel=%E5%A4%B4%E6%9D%A1&start=" + start + "&num=" + num + "&appkey=28484dd6dafeebd7";
        String URL = "https://api.jisuapi.com/news/get?channel=%E5%A4%B4%E6%9D%A1&start=" + start + "&num=" + num + "&appkey=9c4a04ec045a9a3c";
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkhttpHelper.getRequest(URL, new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            final String jsonData = response.body().string();
                            // 将 JSON 数据转换成 JsonElement 对象
                            JsonElement jsonElement = JsonParser.parseString(jsonData);
                            Log.d("jsonElement", "" + jsonElement);
                            JsonObject jsonObject = jsonElement.getAsJsonObject().get("result").getAsJsonObject();
                            JsonArray jsonArray = jsonObject.getAsJsonArray("list");
                            List<NewsMyData> newsList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.size(); i++) {
                                JsonObject newsObject = jsonArray.get(i).getAsJsonObject();
                                String title = newsObject.get("title").getAsString();
                                String time = newsObject.get("time").getAsString();
                                String url = newsObject.get("url").getAsString();
                                String pic = newsObject.get("pic").getAsString();
                                String content = newsObject.get("content").getAsString();
                                NewsMyData news = new NewsMyData(time, pic, url, content, title);
                                newsList.add(news);
                            }
                            currentPage++;
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                myAdapterInformation.setData(newsList);
                                    showRecyclerview(newsList);
                                    myAdapterInformation.notifyDataSetChanged();
                                }
                            });
                        }

                    }

                });
            }
        }).

                start();

    }

    public void showRecyclerview(List<NewsMyData> newsList) {
        myAdapterInformation = new InformationMyAdapter(getContext(), newsList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(layoutManager);
//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.setAdapter(myAdapterInformation);
    }

    public void getScrollPage() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && !recyclerView.canScrollVertically(1)) {
                    // 滑动到底部，执行分页加载操作
//                    Toast.makeText(getActivity(), "加载...", Toast.LENGTH_SHORT).show();
                    getNews();
                }
            }
        });
    }

}
