package com.bljboy.schoolcommunity.tablayout_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.myadapter.ForumMyAdapter;

public class ForumFragment extends Fragment {
    private RecyclerView recyclerView;
    private ForumMyAdapter myAdapterForum;

    public ForumFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum,container,false);
        recyclerView = view.findViewById(R.id.recyclerview_forum);
        myAdapterForum = new ForumMyAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(myAdapterForum);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化控件
//        initView();
    }


    //绑定控件
    public void initView() {

    }
}