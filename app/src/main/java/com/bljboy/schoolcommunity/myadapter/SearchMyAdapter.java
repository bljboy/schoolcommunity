package com.bljboy.schoolcommunity.myadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bljboy.schoolcommunity.R;
import com.bljboy.schoolcommunity.model.ForumMyData;
import com.bljboy.schoolcommunity.model.ForumMyDataList;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchMyAdapter extends RecyclerView.Adapter<SearchMyAdapter.MyHolder> {
    private Context context;
    private List<ForumMyData> myDataList;

    public SearchMyAdapter(Context context, List<ForumMyData> myDataList) {
        this.context = context;
        this.myDataList = myDataList;
    }

    public SearchMyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SearchMyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_search_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMyAdapter.MyHolder holder, int position) {
        if (myDataList != null) {
            ForumMyData forumMyData = myDataList.get(position);
//            Log.e("SearchMyAdapter", "" + myDataList);
            holder.search_page_author.setText(forumMyData.getEmail());
            holder.search_page_pushtime.setText(forumMyData.getTime());
//            Log.e("onBindViewHolder", "onBindViewHolder: " + forumMyData.getEmail());
            holder.search_page_context.setText(forumMyData.getContent());
        }

    }


    @Override
    public int getItemCount() {
        return myDataList == null ? 0 : myDataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public MaterialTextView search_page_author, search_page_pushtime, search_page_context;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            search_page_author = itemView.findViewById(R.id.search_page_author);
            search_page_pushtime = itemView.findViewById(R.id.search_page_pushtime);
            search_page_context = itemView.findViewById(R.id.search_page_context);
        }
    }
}
