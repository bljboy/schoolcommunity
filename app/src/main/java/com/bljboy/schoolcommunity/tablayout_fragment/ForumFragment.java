package com.bljboy.schoolcommunity.tablayout_fragment;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.ForumMyData;
import com.bljboy.schoolcommunity.model.ForumMyDataList;
import com.bljboy.schoolcommunity.myadapter.ForumMyAdapter;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.helper.Validate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ForumFragment extends Fragment {
    private RecyclerView recyclerView;
    private ForumMyAdapter myAdapterForum;
    private CircularProgressIndicator linearProgressIndicator;
    private Button forum_page_reply;

    public ForumFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_forum);
        linearProgressIndicator = view.findViewById(R.id.linearProgressIndicator);
        getMyData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
//            }
//        });
//        swipe_refresh_layout.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() {
//            @Override
//            public boolean canChildScrollUp(@NonNull SwipeRefreshLayout parent, @Nullable View child) {
//                return recyclerView.computeVerticalScrollOffset() > 0;
//            }
//        });

//// 判断 RecyclerView 是否滚动到了顶部
//        if (recyclerView.computeVerticalScrollOffset() == 0) {
//            // RecyclerView 已经滚动到顶部
//            swipe_refresh_layout.setEnabled(false);
//            Toast.makeText(getActivity(), "到顶", Toast.LENGTH_SHORT).show();
//
//        } else {
//            // RecyclerView 仍然能够向上滚动
//            swipe_refresh_layout.setEnabled(true);
//            Toast.makeText(getActivity(), "m", Toast.LENGTH_SHORT).show();
//
//        }

    }

    public void getMyData() {
        final List<ForumMyData> myDataList = new ArrayList<>();
        OkhttpHelper.getRequest(GlobalVars.URL + "forum", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String jsonData = response.body().string();
                    Log.e("jsonData", "onResponse: " + jsonData);
                    Gson gson = new Gson();
                    ForumMyDataList dataList = gson.fromJson(jsonData, ForumMyDataList.class);
                    Log.e("jsonData", "onResponse: " + dataList);
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

    private void showMyDataList(List<ForumMyData> myDataList) {
        myAdapterForum = new ForumMyAdapter(getActivity(), myDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        linearProgressIndicator.setVisibility(View.GONE);
        recyclerView.setAdapter(myAdapterForum);
    }

    @Override
    public void onResume() {
        super.onResume();
        getMyData();
    }

    public static int dpToPx(float dp) {
        return Math.round(dp * Resources.getSystem().getDisplayMetrics().density);
    }
}