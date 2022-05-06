package com.example.suggestmeamovie.view_adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suggestmeamovie.R;
import com.example.suggestmeamovie.data.Review;

import java.util.ArrayList;

public class Reviews_Adapter extends RecyclerView.Adapter<Reviews_Adapter.ViewHolder> {
    final ArrayList<Review> list;

    public Reviews_Adapter(ArrayList<Review> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.authourTxt.setText(list.get(position).getAuthor());
        holder.contentTxt.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends  RecyclerView.ViewHolder{
        final TextView authourTxt;
        final TextView contentTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            authourTxt = itemView.findViewById(R.id.authour_textView);
            contentTxt = itemView.findViewById(R.id.content_textView);
        }
    }


}
