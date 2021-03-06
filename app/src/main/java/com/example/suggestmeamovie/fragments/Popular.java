package com.example.suggestmeamovie.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suggestmeamovie.MainActivity;
import com.example.suggestmeamovie.R;
import com.example.suggestmeamovie.view_adapters.MoviesViewAdapter;
import com.example.suggestmeamovie.data.Movie;

import java.util.ArrayList;

public class Popular extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_popular, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = view.findViewById(R.id.popularMovies_view);
        adapter = new MoviesViewAdapter(this.getActivity(), (ArrayList<Movie>) MainActivity.popularMoviesList);
        recyclerView.setAdapter(adapter);
        layoutManager = new GridLayoutManager(this.getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

    }
}