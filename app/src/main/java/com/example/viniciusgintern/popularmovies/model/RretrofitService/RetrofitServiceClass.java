package com.example.viniciusgintern.popularmovies.model.RretrofitService;

import com.example.viniciusgintern.popularmovies.model.MovieModel.Result;

import retrofit2.Call;

public class RetrofitServiceClass implements RetrofitService {
    //Tentar juntar todas as interfaces nesta classe
    @Override
    public Call<Result> getMovies(String APIKey) {
        return null;
    }
}
