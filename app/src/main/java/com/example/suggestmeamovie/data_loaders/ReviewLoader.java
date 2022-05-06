package com.example.suggestmeamovie.data_loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.suggestmeamovie.data.Review;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReviewLoader extends AsyncTaskLoader<List<Review>> {

    private final String movieId;

    public ReviewLoader(@NonNull Context context , String movieId) {
        super(context);
        this.movieId = movieId;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Nullable
    @Override
    public List<Review> loadInBackground() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(movieId)
                .appendPath("reviews")
                .appendQueryParameter("api_key", "2ac9981a561616a4a7bce0c72f84aa78")
                .appendQueryParameter("language", "en-US");
        OkHttpClient client = new OkHttpClient();
        String Res;
        ArrayList<Review> ReviewArrayList = new ArrayList<>();
        Request request = new Request.Builder()
                .url(builder.toString())
                .build();
        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            Res = response.body().string();
            try {
                JSONObject myObject = new JSONObject(Res);
                JSONArray jsonArray = myObject.getJSONArray("results");
                if(jsonArray.length() == 0) Log.e("myLoader" ,"size is equal to zero!!");
                for (int i = 0; i < jsonArray.length(); i++) {
                    ReviewArrayList.add(new Review(jsonArray.getJSONObject(i).getString("author")
                            ,jsonArray.getJSONObject(i).getString("content")
                    ));
                }
            } catch (Exception e) {
                Log.e("myLoader", Objects.requireNonNull(e.getMessage()));
            }
        } catch (IOException e) {
            Log.e("myLoader", Objects.requireNonNull(e.getMessage()));
        }
        return ReviewArrayList;
    }
}

