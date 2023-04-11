package com.bljboy.schoolcommunity.myadapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.activity.ReplyForumActivity;
import com.bljboy.schoolcommunity.model.ForumMyData;
import com.bljboy.schoolcommunity.model.ForumMyDataList;
import com.bljboy.schoolcommunity.model.list;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class ForumMyAdapter extends RecyclerView.Adapter<ForumMyAdapter.MyHolder> {
    private Context context;
    private ShapeableImageView shapeableImageView;
    private List<ForumMyData> myDataList;
    private int currentPage = 0;

    //    public void loadNextPage(){
//        List<ForumMyData> newData = loadPageData(currentPage + 1, pageSize);
//
//    }
    public ForumMyAdapter(Context context, List<ForumMyData> myDataList) {
        this.context = context;
        this.myDataList = myDataList;
    }

    public ForumMyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ForumMyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_forum_item, parent, false);
        Log.e("myDataList", "onCreateViewHolder: " + myDataList);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumMyAdapter.MyHolder holder, int position) {
        ForumMyData myData = myDataList.get(position);
        holder.forum_page_author.setText(myData.getEmail());
        holder.forum_page_pushtime.setText(myData.getTime());
        holder.forum_page_context.setText(myData.getContent());
        holder.forum_page_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = context.getSharedPreferences(GlobalVars.LOGIN_STATUS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("id", myData.getId());
                editor.apply();
                Toast.makeText(context, "点击", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ReplyForumActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
//        return 1;
        return myDataList.size();
    }

    //容纳View视图,提供前端的视图文件
    public class MyHolder extends RecyclerView.ViewHolder {
        public MaterialTextView forum_page_context, forum_page_author, forum_page_pushtime;
        public Button forum_page_reply;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            forum_page_context = itemView.findViewById(R.id.forum_page_context);
            forum_page_author = itemView.findViewById(R.id.forum_page_author);
            forum_page_pushtime = itemView.findViewById(R.id.forum_page_pushtime);
            forum_page_reply = itemView.findViewById(R.id.forum_page_reply);
            shapeableImageView =
                    itemView.findViewById(R.id.forum_page_midimag);
            Glide.with(itemView)
                    .load("https://images.pexels.com/photos/13945391/pexels-photo-13945391.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
                    .placeholder(R.drawable.one)
                    .transform(new CenterCrop(), new RoundedCorners(50))
                    .into(shapeableImageView);
//            shapeableImageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.i("shapeableImageView", "123");
//                }
//            });
        }
    }
}

