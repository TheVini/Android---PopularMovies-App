package com.example.viniciusgintern.popularmovies;

import com.example.viniciusgintern.popularmovies.model.Movie;
import com.example.viniciusgintern.popularmovies.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("/3/movie/popular")
    Call<Result> getMovies(@Query("api_key") String APIKey);
}
