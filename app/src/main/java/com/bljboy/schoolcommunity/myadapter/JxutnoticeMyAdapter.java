package com.bljboy.schoolcommunity.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.list;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class JxutnoticeMyAdapter extends RecyclerView.Adapter<JxutnoticeMyAdapter.MyHolder> {
    private Context context;
    private List<list> list;
    private ShapeableImageView shapeableImageView;

    public JxutnoticeMyAdapter(Context context) {
        this.context = context;
//        this.list = list;

    }

    @NonNull
    @Override
    public JxutnoticeMyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_jxut_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JxutnoticeMyAdapter.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    //容纳View视图,提供前端的视图文件
    public class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            MaterialTextView jxutnews_title = itemView.findViewById(R.id.jxutnews_title);
            jxutnews_title.setText("通告");
//            jxutnews_title.setVisibility(View.GONE);
//            shapeableImageView =
//                    itemView.findViewById(R.id.forum_page_midimag);
//            Glide.with(itemView)
//                    .load("https://images.pexels.com/photos/13945391/pexels-photo-13945391.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
//                    .placeholder(R.drawable.one)
//                    .transform(new CenterCrop(),new RoundedCorners(50))
//                    .into(shapeableImageView);
//            shapeableImageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.i("shapeableImageView", "123");
//                }
//            });
        }
    }
}

