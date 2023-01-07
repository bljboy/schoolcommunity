package com.bljboy.schoolcommunity.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bljboy.schoolcommunity.R;

public class InformationMyAdapter extends RecyclerView.Adapter<InformationMyAdapter.MyHolder> {
    Context context;

    public InformationMyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public InformationMyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_information_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InformationMyAdapter.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
