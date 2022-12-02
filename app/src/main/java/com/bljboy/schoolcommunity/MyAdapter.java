package com.bljboy.schoolcommunity;

import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bljboy.schoolcommunity.tablayout_fragment.ForumFragment;
import com.bljboy.schoolcommunity.tablayout_fragment.InformationFragment;
import com.bljboy.schoolcommunity.tablayout_fragment.SchoolnewsFragment;


public class MyAdapter extends FragmentStateAdapter {

    private String[] title;

    public MyAdapter(@NonNull FragmentActivity fragmentActivity, String[] title) {
        super(fragmentActivity);
        this.title = title;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // 每个页面对应的fragment
        switch (position) {
            case 0:
                return new ForumFragment();
            case 1:
                return new InformationFragment();
            case 2:
                return new SchoolnewsFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        // 有几个页面就返回几
        return title.length;
    }
}
