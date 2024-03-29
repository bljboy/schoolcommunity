package com.bljboy.schoolcommunity.myadapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bljboy.schoolcommunity.tablayout_schoolnews.JxuthonorFragment;
import com.bljboy.schoolcommunity.tablayout_schoolnews.JxutdynamicFragment;
import com.bljboy.schoolcommunity.tablayout_schoolnews.JxutmediaFragment;
import com.bljboy.schoolcommunity.tablayout_schoolnews.JxutnewsFragment;
import com.bljboy.schoolcommunity.tablayout_schoolnews.JxutnoticeFragment;


public class SchoolnewsMyAdapter extends FragmentStateAdapter {

    private String[] title;

    public SchoolnewsMyAdapter(@NonNull FragmentActivity fragmentActivity, String[] title) {
        super(fragmentActivity);
        this.title = title;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // 每个页面对应的fragment
        switch (position) {
            case 0:
                return new JxutnewsFragment();
            case 1:
                return new JxutdynamicFragment();
            case 2:
                return new JxutnoticeFragment();
            case 3:
                return new JxutmediaFragment();
            case 4:
                return new JxuthonorFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        // 有几个页面就返回几
        return title.length;
    }
}
