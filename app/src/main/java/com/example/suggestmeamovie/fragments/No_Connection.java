package com.example.suggestmeamovie.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.suggestmeamovie.R;

public class No_Connection extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no__connection, container, false);
        Button refreshButton = view.findViewById(R.id.refresh_button);
        refreshButton.setOnClickListener(view1 -> {

        });
        return view;
    }



}