package com.example.viniciusgintern.popularmovies.ModelLayer.RetrofitService;

import com.example.viniciusgintern.popularmovies.ModelLayer.MovieModel.MovieResult;

import retrofit2.Call;

public class RetrofitServiceClass implements RetrofitService {
    //Tentar juntar todas as interfaces nesta classe
    @Override
    public Call<MovieResult> getMovies(String APIKey) {
        return null;
    }
}
