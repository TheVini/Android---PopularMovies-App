package com.example.viniciusgintern.popularmovies.model.RretrofitService;

import com.example.viniciusgintern.popularmovies.model.MovieModel.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("/3/movie/popular")
    Call<MovieResult> getMovies(@Query("api_key") String APIKey);

}
