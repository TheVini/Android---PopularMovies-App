package com.example.viniciusgintern.popularmovies.model;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie implements Serializable {

    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Double movieRate;
    @SerializedName("title")
    @Expose
    private String movieTitle;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    private String movieImageAddress;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("overview")
    @Expose
    private String movieDescription;
    @SerializedName("release_date")
    @Expose
    private String movieYear;

    private String movieDuration = "120 min";

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getMovieRate() {
        return movieRate;
    }

    public void setMovieRate(Double movieRate) {
        this.movieRate = movieRate;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getMovieImageAddress() {
        return movieImageAddress;
    }

    public void setMovieImageAddress(String movieImageAddress) {
        this.movieImageAddress = movieImageAddress;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
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

}

//public class Movie implements Serializable {
//    private String movieTitle;
//    private String movieYear;
//    private String movieDuration;
//    private String movieRate;
//    private String movieDescription;
//    private String movieImageAddress;
//
//    public Movie() {
//    }
//
//    public Movie(String movieTitle, String movieYear,String movieDuration, String movieRate, String movieDescription, String movieImageAddress) {
//        this.movieTitle = movieTitle;
//        this.movieYear = movieYear;
//        this.movieDuration = movieDuration;
//        this.movieRate = movieRate;
//        this.movieDescription = movieDescription;
//        this.movieImageAddress = movieImageAddress;
//    }
//
//    public String getMovieTitle() {
//        return movieTitle;
//    }
//
//    public void setMovieTitle(String movieTitle) {
//        this.movieTitle = movieTitle;
//    }
//
//    public String getMovieYear() {
//        return movieYear;
//    }
//
//    public void setMovieYear(String movieYear) {
//        this.movieYear = movieYear;
//    }
//
//    public String getMovieDuration() {
//        return movieDuration;
//    }
//
//    public void setMovieDuration(String movieDuration) {
//        this.movieDuration = movieDuration;
//    }
//
//    public String getMovieRate() {
//        return movieRate;
//    }
//
//    public void setMovieRate(String movieRate) {
//        this.movieRate = movieRate;
//    }
//
//    public String getMovieDescription() {
//        return movieDescription;
//    }
//
//    public void setMovieDescription(String movieDescription) {
//        this.movieDescription = movieDescription;
//    }
//
//    public String getMovieImageAddress() {
//        return movieImageAddress;
//    }
//
//    public void setMovieImageAddress(String movieImage) {
//        this.movieImageAddress = movieImageAddress;
//    }
//}
