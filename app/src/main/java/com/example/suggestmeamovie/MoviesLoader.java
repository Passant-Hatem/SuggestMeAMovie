package com.example.suggestmeamovie;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MoviesLoader extends AsyncTaskLoader<MoviesData> {

    public MoviesLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public MoviesData loadInBackground() {
        MoviesData moviesData = new MoviesData();

        moviesData.setPopularmoviesList(loadPopularMovies());
        moviesData.setTopratedmoviesList(loadTopRatedMovies());
        moviesData.setUpcomingmoviesList(loadUpComingMovies());

        return moviesData;
    }

   private ArrayList<Movie> loadPopularMovies(){

       Uri.Builder builder = new Uri.Builder();
       builder.scheme("https")
               .authority("api.themoviedb.org")
               .appendPath("3")
               .appendPath("movie")
               .appendPath("popular")
               .appendQueryParameter("api_key", "2ac9981a561616a4a7bce0c72f84aa78")
               .appendQueryParameter("language", "en-US")
               .appendQueryParameter("page", "2");

       OkHttpClient client = new OkHttpClient();
       String Res;
       ArrayList<Movie> movieArrayList = new ArrayList<>();
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
                   movieArrayList.add(new Movie(jsonArray.getJSONObject(i).getInt("id")
                           ,jsonArray.getJSONObject(i).getString("original_title")
                           , jsonArray.getJSONObject(i).getString("poster_path")
                           , jsonArray.getJSONObject(i).getString("overview")
                           , jsonArray.getJSONObject(i).getString("vote_average")
                           , jsonArray.getJSONObject(i).getString("release_date")
                   ));
               }
           } catch (Exception e) {
               Log.e("myLoader", Objects.requireNonNull(e.getMessage()));
           }
       } catch (IOException e) {
           Log.e("myLoader", Objects.requireNonNull(e.getMessage()));
       }

       return movieArrayList;
   }

    private ArrayList<Movie> loadTopRatedMovies(){

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath("top_rated")
                .appendQueryParameter("api_key", "2ac9981a561616a4a7bce0c72f84aa78")
                .appendQueryParameter("language", "en-US")
                .appendQueryParameter("page", "2");

        OkHttpClient client = new OkHttpClient();
        String Res;
        ArrayList<com.example.suggestmeamovie.Movie> movieArrayList = new ArrayList<>();
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
                    movieArrayList.add(new com.example.suggestmeamovie.Movie(jsonArray.getJSONObject(i).getInt("id")
                            ,jsonArray.getJSONObject(i).getString("original_title")
                            , jsonArray.getJSONObject(i).getString("poster_path")
                            , jsonArray.getJSONObject(i).getString("overview")
                            , jsonArray.getJSONObject(i).getString("vote_average")
                            , jsonArray.getJSONObject(i).getString("release_date")
                    ));
                }
            } catch (Exception e) {
                Log.e("myLoader", Objects.requireNonNull(e.getMessage()));
            }
        } catch (IOException e) {
            Log.e("myLoader", Objects.requireNonNull(e.getMessage()));
        }

        return movieArrayList;
    }

    private ArrayList<Movie> loadUpComingMovies(){

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath("upcoming")
                .appendQueryParameter("api_key", "2ac9981a561616a4a7bce0c72f84aa78")
                .appendQueryParameter("language", "en-US")
                .appendQueryParameter("page", "2");

        OkHttpClient client = new OkHttpClient();
        String Res;
        ArrayList<com.example.suggestmeamovie.Movie> movieArrayList = new ArrayList<>();
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
                    movieArrayList.add(new com.example.suggestmeamovie.Movie(jsonArray.getJSONObject(i).getInt("id")
                            ,jsonArray.getJSONObject(i).getString("original_title")
                            , jsonArray.getJSONObject(i).getString("poster_path")
                            , jsonArray.getJSONObject(i).getString("overview")
                            , jsonArray.getJSONObject(i).getString("vote_average")
                            , jsonArray.getJSONObject(i).getString("release_date")
                    ));
                }
            } catch (Exception e) {
                Log.e("myLoader", Objects.requireNonNull(e.getMessage()));
            }
        } catch (IOException e) {
            Log.e("myLoader", Objects.requireNonNull(e.getMessage()));
        }

        return movieArrayList;
    }

}
