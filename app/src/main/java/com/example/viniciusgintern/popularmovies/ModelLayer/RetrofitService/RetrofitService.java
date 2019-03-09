package com.example.viniciusgintern.popularmovies.ModelLayer.RetrofitService;

import com.example.viniciusgintern.popularmovies.ModelLayer.MovieModel.MovieResult;
import com.example.viniciusgintern.popularmovies.ModelLayer.ReviewModel.ReviewResult;
import com.example.viniciusgintern.popularmovies.ModelLayer.TrailerModel.TrailerResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("/3/movie/popular")
    Call<MovieResult> getPopularMovies(@Query("api_key") String APIKey);

    @GET("/3/movie/{id}/videos")
    Call<TrailerResult> getMovieTrailers(@Path("id") int idMovie,
                                    @Query("api_key") String APIKey,
                                    @Query("language") String language,
                                    @Query("page") String page);

    @GET("/3/movie/{id}/reviews")
    Call<ReviewResult> getMovieReviews(@Path("id") int idMovie,
                                       @Query("api_key") String APIKey,
                                       @Query("language") String language,
                                       @Query("page") String page);

    @GET("/3/movie/top_rated")
    Call<MovieResult> getTopRatedMovies(@Query("api_key") String APIKey);
}
