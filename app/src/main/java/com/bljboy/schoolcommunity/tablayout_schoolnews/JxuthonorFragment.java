package com.bljboy.schoolcommunity.tablayout_schoolnews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.myadapter.JxuthonorMyAdapter;
import com.bljboy.schoolcommunity.myadapter.JxutmediaMyAdapter;

public class JxuthonorFragment extends Fragment {
    private RecyclerView recyclerView;
    private JxuthonorMyAdapter jxuthonorMyAdapter;
    public JxuthonorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jxuthonorfragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_jxuthonor);
        jxuthonorMyAdapter = new JxuthonorMyAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(jxuthonorMyAdapter);
        return view;
    }
}