package com.example.artistsearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailRecyclerAdapter extends RecyclerView.Adapter<DetailRecyclerAdapter.DetailViewHolder> {

    Context mContext;
    private List<String> options;
    private List<String> data;

    public DetailRecyclerAdapter(List<String> options, List<String> data, Context mContext) {
        this.options = options;
        this.data = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_items, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        holder.option.setText(options.get(position));
        holder.data.setText(data.get(position)) ;
    }

    @Override
    public int getItemCount() {
       return options.size();
    }

    public static class DetailViewHolder extends RecyclerView.ViewHolder {
        public TextView option;
        public TextView data;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            option = (TextView) itemView.findViewById(R.id.field);
            data = (TextView) itemView.findViewById(R.id.data);

        }
    }

}
