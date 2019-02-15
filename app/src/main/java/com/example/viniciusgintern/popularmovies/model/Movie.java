package com.example.viniciusgintern.popularmovies.model;

import android.widget.ImageView;

public class Movie {
    private String movieName;
    private String movieDuration;
    private String movieRate;
    private String movieDescription;
    private String movieImageAddress;

    public Movie() {
    }

    public Movie(String movieName, String movieDuration, String movieRate, String movieDescription, String movieImageAddress) {
        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.movieRate = movieRate;
        this.movieDescription = movieDescription;
        this.movieImageAddress = movieImageAddress;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getMovieRate() {
        return movieRate;
    }

    public void setMovieRate(String movieRate) {
        this.movieRate = movieRate;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieImageAddress() {
        return movieImageAddress;
    }

    public void setMovieImageAddress(String movieImage) {
        this.movieImageAddress = movieImageAddress;
    }
}
