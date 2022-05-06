package com.example.suggestmeamovie.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suggestmeamovie.R;
import com.example.suggestmeamovie.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesViewAdapter extends RecyclerView.Adapter<MoviesViewAdapter.ViewHolder> {

    final ArrayList<Movie> list;
    final ItemClicked activity;

    public MoviesViewAdapter(Context context, ArrayList<Movie> list) {
        this.activity = (ItemClicked) context;
        this.list = list;
    }

    @NonNull
    @Override
    public MoviesViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_poster, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(list.get(position));

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("image.tmdb.org")
                .appendPath("t")
                .appendPath("p")
                .appendPath("w185")
                .appendPath(list.get(position).getMoviePosterUrl());
        Picasso.get().load(builder.toString()).fit().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    interface ItemClicked {
        void onItemClicked(int i);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView mImageView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.poster);
            itemView.setOnClickListener(view -> activity.onItemClicked(list.indexOf(itemView.getTag())));
        }
    }
}
