package com.example.suggestmeamovie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Movie_Details extends AppCompatActivity {

    TextView titleTxtV;
    TextView releaseDateTxtV;
    TextView rateTxtV;
    TextView overviewTxtV;
    TextView reviewsTxtView;
    ImageView posterImgV;
    String movieId;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__details);

        int movie_index = getIntent().getIntExtra(getString(R.string.index),0);
        int movie_type = getIntent().getIntExtra(getString(R.string.movie_type),0);


        switch (movie_type){
            case 0:
                movie = MainActivity.popularMoviesList.get(movie_index);
                break;
            case 1:
                movie = MainActivity.topratedMoviesList.get(movie_index);
                break;
            case 2:
                movie = MainActivity.upcomingMoviesList.get(movie_index);
                break;
        }

        titleTxtV = findViewById(R.id.titleTxtView);
        titleTxtV.setText(movie.getOriginalTitle());

        releaseDateTxtV = findViewById(R.id.releaseDateTextView);
        releaseDateTxtV.setText(movie.getReleaseDate());

        rateTxtV = findViewById(R.id.rateTextView);
        rateTxtV.setText(movie.getVoteAverage());

        overviewTxtV = findViewById(R.id.overViewTextView);
        overviewTxtV.setText(movie.getOverview());

        posterImgV = findViewById(R.id.posterImageView);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("image.tmdb.org")
                .appendPath("t")
                .appendPath("p")
                .appendPath("w185")
                .appendPath(movie.getMoviePosterUrl());
        Picasso.get().load(builder.toString()).fit().into(posterImgV);


        reviewsTxtView = findViewById(R.id.reviewsTxtView);
    }
}