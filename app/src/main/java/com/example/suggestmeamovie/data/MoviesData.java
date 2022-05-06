package com.example.suggestmeamovie.data;

import com.example.suggestmeamovie.data.Movie;

import java.util.ArrayList;

public class MoviesData {
    private ArrayList<Movie> popularmoviesList;
    private ArrayList<Movie> topratedmoviesList;
    private ArrayList<Movie> upcomingmoviesList;

    public ArrayList<Movie> getPopularmoviesList() {
        return popularmoviesList;
    }

    public void setPopularmoviesList(ArrayList<Movie> popularmoviesList) {
        this.popularmoviesList = popularmoviesList;
    }

    public ArrayList<Movie> getTopratedmoviesList() {
        return topratedmoviesList;
    }

    public void setTopratedmoviesList(ArrayList<Movie> topratedmoviesList) {
        this.topratedmoviesList = topratedmoviesList;
    }

    public ArrayList<Movie> getUpcomingmoviesList() {
        return upcomingmoviesList;
    }

    public void setUpcomingmoviesList(ArrayList<Movie> upcomingmoviesList) {
        this.upcomingmoviesList = upcomingmoviesList;
    }
}
