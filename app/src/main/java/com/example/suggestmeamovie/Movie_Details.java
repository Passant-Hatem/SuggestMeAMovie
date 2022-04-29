package com.example.suggestmeamovie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Movie_Details extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<List<Review>> {

    TextView titleTxtV;
    TextView releaseDateTxtV;
    TextView rateTxtV;
    TextView overviewTxtV;
    TextView reviewsTxtView;
    ImageView posterImgV;


    private Movie movie;
    private Loader<List<Review>> loader;

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


        reviewsTxtView = findViewById(R.id.reviewsTxtView);
    }

    @Override
    public Loader<List<Review>> onCreateLoader(int i, Bundle bundle) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(movie.getId())
                .appendPath("reviews")
                .appendQueryParameter("api_key", "2ac9981a561616a4a7bce0c72f84aa78")
                .appendQueryParameter("language", "en-US")
                .appendQueryParameter("page", "2");
        return new ReviewLoader(this ,builder.toString());
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