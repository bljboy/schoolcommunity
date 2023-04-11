package com.bljboy.schoolcommunity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;


import com.bljboy.schoolcommunity.activity.MyForumActivity;
import com.bljboy.schoolcommunity.myadapter.ForumMyAdapter;
import com.bljboy.schoolcommunity.nav_fragment.ChatFragment;
import com.bljboy.schoolcommunity.nav_fragment.HomeFragment;
import com.bljboy.schoolcommunity.nav_fragment.MeFragment;
import com.bljboy.schoolcommunity.nav_fragment.ToolsFragment;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView my_nav;
    private HomeFragment homeFragment;
    private ChatFragment chatFragment;
    private ToolsFragment toolsFragment;
    private MeFragment meFragment;
    private ImageButton drawerheader_image1;
    private NavigationView drawerLayout;
    private View drawer_header;
    private ImageView drawer_header_image, drawer_header_image1, drawer_header_image3;
    private RecyclerView recyclerView;
    private ForumMyAdapter myAdapterForum;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //底部导航栏切换
        my_nav = findViewById(R.id.nav_view);
        my_nav.setOnItemSelectedListener((my_navItem));
        homeFragment = new HomeFragment();
        chatFragment = new ChatFragment();
        toolsFragment = new ToolsFragment();
        meFragment = new MeFragment();
        switchFragment(homeFragment);
        //抽屉头部绑定
        drawerLayout = findViewById(R.id.nav_left);
        drawer_header = drawerLayout.inflateHeaderView(R.layout.drawer_header);
        drawer_header_image = drawer_header.findViewById(R.id.drawer_header_image);
        drawer_header_image1 = drawer_header.findViewById(R.id.drawer_header_image1);
        drawer_header_image3 = drawer_header.findViewById(R.id.drawer_header_image3);
        drawer_header_image3.setImageResource(R.drawable.one);
        drawer_header_image1.getBackground().setAlpha(65);
        drawer_header_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Log:", "头部被点击了！");
            }
        });
    }

    private NavigationBarView.OnItemSelectedListener my_navItem = new NavigationBarView.OnItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    Log.d("Nav", "首页");
                    switchFragment(homeFragment);
                    break;
                case R.id.nav_chat:
                    switchFragment(chatFragment);
                    Log.d("Nav", "聊天");
                    break;
                case R.id.nav_tools:
                    switchFragment(toolsFragment);
                    Log.d("Nav", "工具");
                    break;
                case R.id.nav_me:
                    switchFragment(meFragment);
                    Log.d("Nav", "我的");
                    break;
            }
            return true;
        }
    };

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_container, fragment).commit();
    }

    public void onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            SharedPreferences sp = getSharedPreferences(GlobalVars.LOGIN_STATUS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.apply();
            if (TextUtils.isEmpty(sp.getString("isLogin", ""))) {
                finish();
                Toast.makeText(this, "退出成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
            }
        }
        if (item.getItemId() == R.id.myforum_item) {

            Intent intent = new Intent(this, MyForumActivity.class);
//            intent.putExtra("email", email);
            startActivity(intent);
        }
    }
}
