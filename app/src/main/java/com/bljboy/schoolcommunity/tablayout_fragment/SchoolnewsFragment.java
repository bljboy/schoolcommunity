package com.bljboy.schoolcommunity.tablayout_fragment;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.myadapter.SchoolnewsMyAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SchoolnewsFragment extends Fragment {
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private String[] title;
    private SchoolnewsMyAdapter myAdapterSchoolnews;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schoolnews, container, false);
        //获取资源字符串数组
        Resources resources = getResources();
        title = resources.getStringArray(R.array.title_schoolnews);
        viewPager2 = view.findViewById(R.id.viewpager_schoolnews);
        tabLayout = view.findViewById(R.id.tablayout_schoolnews);
//        绑定适配器
        myAdapterSchoolnews = new SchoolnewsMyAdapter(getActivity(), title);
//        设置viewpager是否可以滑动
        viewPager2.setUserInputEnabled(false);
        viewPager2.setAdapter(myAdapterSchoolnews);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(title[position]);
            }
        });

        tabLayoutMediator.attach();
        return view;
    }

}