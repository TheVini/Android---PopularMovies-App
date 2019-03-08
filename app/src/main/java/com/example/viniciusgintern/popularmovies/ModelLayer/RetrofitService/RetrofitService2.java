package com.example.viniciusgintern.popularmovies.ModelLayer.RetrofitService;

import com.example.viniciusgintern.popularmovies.ModelLayer.TrailerModel.TrailerResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService2 {
    @GET("/3/movie/{id}/videos")
    Call<TrailerResult> getTrailers(@Path("id") int idMovie,
                                    @Query("api_key") String APIKey,
                                    @Query("language") String language,
                                    @Query("page") String page);
}
