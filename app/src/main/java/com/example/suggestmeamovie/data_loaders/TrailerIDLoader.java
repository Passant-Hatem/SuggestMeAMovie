package com.example.suggestmeamovie.data_loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TrailerIDLoader  extends AsyncTaskLoader<List<String>> {

     private final String movieId;
     private final String apiKey;

    public TrailerIDLoader(Context context ,String movieId ,String apiKey) {
        super(context);
        this.movieId = movieId;
        this.apiKey = apiKey;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<String> loadInBackground() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(movieId)
                .appendPath("videos")
                .appendQueryParameter("api_key", apiKey)
                .appendQueryParameter("language", "en-US");

        OkHttpClient client = new OkHttpClient();
        String Res;
        ArrayList<String> movieTrailerID = new ArrayList<>();
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
                for (int i = 0; i < jsonArray.length(); i++) {
                    movieTrailerID.add(jsonArray.getJSONObject(i).getString("key"));
                }
            } catch (Exception e) {
                Log.e("myLoader", Objects.requireNonNull(e.getMessage()));
            }
        } catch (IOException e) {
            Log.e("myLoader", Objects.requireNonNull(e.getMessage()));
        }

        return movieTrailerID;
    }
}