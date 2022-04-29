package com.example.suggestmeamovie;

public class Movie {

    private final int id ;
    private final String originalTitle;
    private final String moviePosterUrl;
    private final String overview;
    private final String voteAverage;
    private final String releaseDate;


    public Movie(int id, String originalTitle, String moviePosterUrl, String overview, String voteAverage, String releaseDate) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.moviePosterUrl = moviePosterUrl;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
    }


    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getMoviePosterUrl() {
        return moviePosterUrl;
    }

    public String getOverview() {
        return overview;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getId() {
        return Integer.toString(id);
    }

}
