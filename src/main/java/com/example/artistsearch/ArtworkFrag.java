package com.example.artistsearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArtworkFrag extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private TextView no_artwork;
    private List<Artwork> artworks;

    public ArtworkFrag(List<Artwork> artworks) {
        this.artworks = artworks;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_artwork, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.artwork_recycler);
        no_artwork = (TextView) view.findViewById(R.id.no_artwork);
        if (artworks.size() == 0) {
            no_artwork.setVisibility(View.VISIBLE);
        } else {
            no_artwork.setVisibility(View.INVISIBLE);
        }
        ArtworkRecyclerAdapter artworkRecyclerAdapter = new ArtworkRecyclerAdapter(artworks, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(artworkRecyclerAdapter);
        return view;
    }
}
