package com.example.viniciusgintern.popularmovies.ModelLayer.RetrofitService;

import com.example.viniciusgintern.popularmovies.ModelLayer.MovieModel.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService4 {
    @GET("/3/movie/top_rated")
    Call<MovieResult> getMovies(@Query("api_key") String APIKey);
}
