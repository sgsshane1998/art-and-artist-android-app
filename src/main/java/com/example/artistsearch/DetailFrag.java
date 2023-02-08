package com.example.artistsearch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class DetailFrag extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private List<String> options;
    private List<String> data;

    public DetailFrag(List<String> options, List<String> data) {
        this.options = options;
        this.data = data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.detail_recycler);
        DetailRecyclerAdapter detailRecyclerAdapter = new DetailRecyclerAdapter(options, data, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(detailRecyclerAdapter);
        return view;
    }
}