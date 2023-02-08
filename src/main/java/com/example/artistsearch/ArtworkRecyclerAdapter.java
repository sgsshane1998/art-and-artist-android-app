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

public class ArtworkRecyclerAdapter extends RecyclerView.Adapter<ArtworkRecyclerAdapter.ArtworkViewHolder> {

    Context mContext;
    private List<Artwork> artworks;

    public ArtworkRecyclerAdapter(List<Artwork> artworks, Context mContext) {
        this.artworks = artworks;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ArtworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artwork_items, parent, false);
        return new ArtworkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtworkViewHolder holder, int position) {
        Artwork artwork = artworks.get(position);
        holder.title.setText(artwork.getTitle());
        Picasso.get().load(artwork.getLink()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return artworks.size();
    }

    public static class ArtworkViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img;

        public ArtworkViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.artwork_title);
            this.img = (ImageView) itemView.findViewById(R.id.artwork_img);

        }
    }

}