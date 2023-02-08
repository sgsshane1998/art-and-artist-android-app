package com.example.artistsearch;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistRecyclerAdapter extends RecyclerView.Adapter<ArtistRecyclerAdapter.ArtistViewHolder> {

    private List<ArtistCard> artistCardList;
    private OnArtistListener mOnArtistListener;
    public ArtistRecyclerAdapter(List<ArtistCard> artistCardList, OnArtistListener onArtistListener) {
        this.artistCardList = artistCardList;
        this.mOnArtistListener = onArtistListener;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_items, parent, false);
        return new ArtistViewHolder(view, mOnArtistListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        ArtistCard artistCard = artistCardList.get(position);
        holder.artistName.setText(artistCard.getName());
        if (artistCard.getLink().equals("/assets/shared/missing_image.png")) {
            holder.artistImage.setImageResource(R.drawable.artsy_missing);
        } else {
            Picasso.get().load(artistCard.getLink()).into(holder.artistImage);
        }
    }

    @Override
    public int getItemCount() {
        return artistCardList.size();
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView artistName;
        public ImageView artistImage;
        OnArtistListener onArtistListener;
        public ArtistViewHolder(@NonNull View itemView, OnArtistListener onArtistListener) {
            super(itemView);
            artistName = itemView.findViewById(R.id.artist_name);
            artistImage = itemView.findViewById(R.id.art_img);
            this.onArtistListener = onArtistListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onArtistListener.onArtistClick(getAdapterPosition());
        }
    }

    public interface OnArtistListener {
        void onArtistClick(int position);
    }
}
