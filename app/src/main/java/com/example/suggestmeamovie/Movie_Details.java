package com.example.suggestmeamovie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suggestmeamovie.data.Movie;
import com.example.suggestmeamovie.data.Review;
import com.example.suggestmeamovie.data_loaders.ReviewLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Movie_Details extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<List<Review>> {

    TextView titleTxtV;
    TextView releaseDateTxtV;
    TextView rateTxtV;
    TextView overviewTxtV;
    TextView reviewsTxtView;
    ImageView posterImgV;

    ImageView playTrailerOne;
    ImageView getPlayTrailertwo;

    final int REVIEW_LOADER_ID = 6;

    LoaderManager loaderManager;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__details);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.movie_detail);

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

        loaderManager = getLoaderManager();
        loaderManager.initLoader(REVIEW_LOADER_ID, null, this);
        reviewsTxtView = findViewById(R.id.reviewsTxtView);


        Intent intent = new Intent(Movie_Details.this, Trailer.class);
        intent.putExtra("movie_id",movie.getId());

       playTrailerOne = findViewById(R.id.playTrailer1Img);
       playTrailerOne.setOnClickListener(view -> {
           intent.putExtra("trailer",1);
           startActivity(intent);
       });

        getPlayTrailertwo = findViewById(R.id.playTrailer2Img);
       getPlayTrailertwo.setOnClickListener(view -> {
           intent.putExtra("trailer",2);
           startActivity(intent);
       });
    }

    @Override
    public Loader<List<Review>> onCreateLoader(int i, Bundle bundle) {
        return new ReviewLoader(this ,movie.getId());
    }

    @Override
    public void onLoadFinished(Loader<List<Review>> loader, List<Review> reviews) {
        if(reviews.isEmpty())
            reviewsTxtView.setText(R.string.no_reviews);
        else {
            for (Review review :
                    reviews) {
                String r = '\n' + review.getAuthor() + '\n' + review.getContent();
                reviewsTxtView.setText(r);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Review>> loader) {

    }
}