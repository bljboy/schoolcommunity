package com.bljboy.schoolcommunity.myadapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.JkywModel;
import com.bljboy.schoolcommunity.tablayout_schoolnews.JxutnewsUrl;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class JxutnewsMyAdapter extends RecyclerView.Adapter<JxutnewsMyAdapter.MyHolder> {
    private Context context;
    private List<JkywModel> list;

    public JxutnewsMyAdapter(Context context, List<JkywModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public JxutnewsMyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_jxut_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JxutnewsMyAdapter.MyHolder holder, int position) {
        JkywModel jkywModel = list.get(position);
        holder.jxutnews_timemonth.setText(jkywModel.getJxutnews_timemonth());
        holder.jxutnews_timeyear.setText(jkywModel.getJxutnews_timeyear());
        holder.jxutnews_title.setText(jkywModel.getJxutnews_title());
        holder.jxutnews_content.setText(jkywModel.getJxutnews_content());
        holder.jxutnews_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("position", "onClick: " + jkywModel.getJxutnews_url());
                Intent intent = new Intent(view.getContext(), JxutnewsUrl.class);
                intent.putExtra("jkyw_url", jkywModel.getJxutnews_url());
                intent.putExtra("jxutnews_title", jkywModel.getJxutnews_title());
                intent.putExtra("jxutnews_html", jkywModel.getJxutnews_html());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //容纳View视图,提供前端的视图文件
    public class MyHolder extends RecyclerView.ViewHolder {
        MaterialTextView jxutnews_timemonth;
        MaterialTextView jxutnews_timeyear;
        MaterialTextView jxutnews_title;
        MaterialTextView jxutnews_content;
        MaterialCardView jxutnews_card;
        View view;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            jxutnews_timemonth = itemView.findViewById(R.id.jxutnews_timemonth);
            jxutnews_timeyear = itemView.findViewById(R.id.jxutnews_timeyear);
            jxutnews_title = itemView.findViewById(R.id.jxutnews_title);
            jxutnews_content = itemView.findViewById(R.id.jxutnews_content);
            jxutnews_card = itemView.findViewById(R.id.jxutnews_card);
            this.view = itemView;
        }
    }

}

