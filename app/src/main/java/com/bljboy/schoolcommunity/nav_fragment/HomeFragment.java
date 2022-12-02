package com.bljboy.schoolcommunity.nav_fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.bljboy.schoolcommunity.R;

public class HomeFragment extends Fragment {

    private ImageButton appbar_navigation;
    private DrawerLayout drawer_layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawer_layout = getActivity().findViewById(R.id.drawer_layout);
        appbar_navigation = getActivity().findViewById(R.id.appbar_navigation);
        appbar_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("appbar", "appbar_navigation ");
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        });
    }
}