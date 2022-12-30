package com.bljboy.schoolcommunity.nav_fragment;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bljboy.schoolcommunity.myadapter.MyAdapter;
import com.bljboy.schoolcommunity.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    private ImageButton appbar_navigation;
    private DrawerLayout drawer_layout;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private String[] title;
    private MyAdapter myAdapter;

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
        //获取资源字符串数组
        Resources resources = getResources();
        title = resources.getStringArray(R.array.title_home);
        //抽屉视图控件绑定
        drawer_layout = getActivity().findViewById(R.id.drawer_layout);
        appbar_navigation = getActivity().findViewById(R.id.appbar_navigation);
        appbar_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("appbar", "appbar_navigation ");
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        });
        initView();
    }

    //tablayout与viewpager2配合滑动切换
    public void initView() {
        viewPager2 = getActivity().findViewById(R.id.viewpager_home);
        tabLayout = getActivity().findViewById(R.id.tablayout_home);
        //绑定适配器
        myAdapter = new MyAdapter(getActivity(),title);
//        viewPager2.setUserInputEnabled(false);
        viewPager2.setAdapter(myAdapter);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(title[position]);
            }
        });

        tabLayoutMediator.attach();
    }
}