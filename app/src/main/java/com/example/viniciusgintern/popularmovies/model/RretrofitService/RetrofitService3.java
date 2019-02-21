package com.example.viniciusgintern.popularmovies.model.RretrofitService;

import com.example.viniciusgintern.popularmovies.model.ReviewModel.ReviewResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService3 {
    @GET("/3/movie/{id}/reviews")
    Call<ReviewResult> getReviews(@Path("id") int idMovie,
                                  @Query("api_key") String APIKey,
                                  @Query("language") String language,
                                  @Query("page") String page);
}
