package com.example.viniciusgintern.popularmovies.model.RretrofitService;

import com.example.viniciusgintern.popularmovies.model.MovieModel.MovieResult;

import retrofit2.Call;

public class RetrofitServiceClass implements RetrofitService {
    //Tentar juntar todas as interfaces nesta classe
    @Override
    public Call<MovieResult> getMovies(String APIKey) {
        return null;
    }
}
