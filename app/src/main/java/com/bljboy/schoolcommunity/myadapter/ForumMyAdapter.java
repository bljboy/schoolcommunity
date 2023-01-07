package com.bljboy.schoolcommunity.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.list;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class ForumMyAdapter extends RecyclerView.Adapter<ForumMyAdapter.MyHolder> {
    private Context context;
    private List<list> list;
    private ShapeableImageView shapeableImageView;

    public ForumMyAdapter(Context context) {
        this.context = context;
//        this.list = list;

    }

    @NonNull
    @Override
    public ForumMyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_forum_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumMyAdapter.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    //容纳View视图,提供前端的视图文件
    public class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            shapeableImageView =
                    itemView.findViewById(R.id.forum_page_midimag);
            Glide.with(itemView)
                    .load("https://images.pexels.com/photos/13945391/pexels-photo-13945391.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
                    .placeholder(R.drawable.one)
                    .transform(new CenterCrop(),new RoundedCorners(50))
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

