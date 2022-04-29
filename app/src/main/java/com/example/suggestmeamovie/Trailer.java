package com.example.suggestmeamovie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;

import java.util.List;

public class Trailer extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<String>> {

    LoaderManager loaderManager;
    private final int TRAILER_LOADER = 85;

    String movieID;
    int trailerNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieID = getIntent().getStringExtra("movie_id");
        trailerNumber = getIntent().getIntExtra("trailer" ,1);

        loaderManager = getLoaderManager();
        loaderManager.initLoader(TRAILER_LOADER ,null ,this);
    }

    @Override
    public Loader<List<String>> onCreateLoader(int i, Bundle bundle) {
        return new TrailerIDLoader(this ,movieID);
    }

    @Override
    public void onLoadFinished(Loader<List<String>> loader, List<String> strings) {
        final int TRAILER_ONE_KEY = 0 ,TRAILER_TWO_KEY = 1;

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("www.youtube.com")
                .appendPath("watch");

        Intent openTrailerIntent = new Intent(Intent.ACTION_VIEW);
        if(trailerNumber == 2) {
            builder.appendQueryParameter("v" ,strings.get(TRAILER_TWO_KEY));
        }
        else {
            builder.appendQueryParameter("v" ,strings.get(TRAILER_ONE_KEY));
        }
        openTrailerIntent.setData(Uri.parse(builder.toString()));
        startActivity(openTrailerIntent);
    }

    @Override
    public void onLoaderReset(Loader<List<String>> loader) {

    }
}