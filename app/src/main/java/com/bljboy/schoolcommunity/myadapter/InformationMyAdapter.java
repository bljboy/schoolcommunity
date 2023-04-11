package com.bljboy.schoolcommunity.myadapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.activity.InformationWebActivity;
import com.bljboy.schoolcommunity.model.NewsMyData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class InformationMyAdapter extends RecyclerView.Adapter<InformationMyAdapter.MyHolder> {
    private Context context;
    private List<NewsMyData> newsList;

    public InformationMyAdapter(Context context, List<NewsMyData> newsList) {
        this.context = context;
        this.newsList = newsList;

    }

//    public void setData(List<NewsMyData> newsList) {
//        this.newsList = newsList;
//    }

    @NonNull
    @Override
    public InformationMyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_information_item, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InformationMyAdapter.MyHolder holder, int position) {
//        Log.e("NewsMyData", "NewsMyData_________" + newsList);
        NewsMyData newsMyData = newsList.get(position);
        Log.e("NewsMyData", "NewsMyData" + newsMyData.getTime());
        holder.infor_time.setText(newsMyData.getTime());
        holder.infor_title.setText(newsMyData.getTitle());
        Glide.with(holder.itemView)
                .load(newsMyData.getPic())
                .placeholder(R.drawable.one)
                .into(holder.infor_image);
        holder.news_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InformationWebActivity.class);
                intent.putExtra("url", newsMyData.getUrl());
                intent.putExtra("content", newsMyData.getContent());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public ImageView infor_image;
        public MaterialCardView news_card;
        public TextView infor_time, infor_title;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            infor_image = itemView.findViewById(R.id.infor_image);
            infor_time = itemView.findViewById(R.id.infor_time);
            infor_title = itemView.findViewById(R.id.infor_title);
            news_card = itemView.findViewById(R.id.news_card);

        }
    }
}
