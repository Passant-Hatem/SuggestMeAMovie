package com.example.suggestmeamovie;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

    final String url;

    public ReviewLoader(@NonNull Context context , String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Nullable
    @Override
    public List<Review> loadInBackground() {
        OkHttpClient client = new OkHttpClient();
        String Res;
        ArrayList<Review> ReviewArrayList = new ArrayList<>();
        Request request = new Request.Builder()
                .url(url)
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

