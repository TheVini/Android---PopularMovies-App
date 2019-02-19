package com.example.viniciusgintern.popularmovies.model;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//public class Movie {
//
//    @SerializedName("page")
//    @Expose
//    private Integer page;
//    @SerializedName("total_results")
//    @Expose
//    private Integer totalResults;
//    @SerializedName("total_pages")
//    @Expose
//    private Integer totalPages;
//    @SerializedName("results")
//    @Expose
//    private List<Result> results = null;
//
//    public Integer getPage() {
//        return page;
//    }
//
//    public void setPage(Integer page) {
//        this.page = page;
//    }
//
//    public Integer getTotalResults() {
//        return totalResults;
//    }
//
//    public void setTotalResults(Integer totalResults) {
//        this.totalResults = totalResults;
//    }
//
//    public Integer getTotalPages() {
//        return totalPages;
//    }
//
//    public void setTotalPages(Integer totalPages) {
//        this.totalPages = totalPages;
//    }
//
//    public List<Result> getResults() {
//        return results;
//    }
//
//    public void setResults(List<Result> results) {
//        this.results = results;
//    }
//
//}

public class Movie implements Serializable {
    private String movieTitle;
    private String movieYear;
    private String movieDuration;
    private String movieRate;
    private String movieDescription;
    private String movieImageAddress;

    public Movie() {
    }

    public Movie(String movieTitle, String movieYear,String movieDuration, String movieRate, String movieDescription, String movieImageAddress) {
        this.movieTitle = movieTitle;
        this.movieYear = movieYear;
        this.movieDuration = movieDuration;
        this.movieRate = movieRate;
        this.movieDescription = movieDescription;
        this.movieImageAddress = movieImageAddress;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
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
