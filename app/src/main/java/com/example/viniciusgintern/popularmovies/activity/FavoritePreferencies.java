package com.example.viniciusgintern.popularmovies.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.viniciusgintern.popularmovies.model.MovieModel.Movie;
import com.google.gson.Gson;

import java.io.Serializable;

public class FavoritePreferencies implements Serializable {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String FILE_NAME = "favoriteMovies.preferences";

    public FavoritePreferencies(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(FILE_NAME,0);
        editor = preferences.edit();
        System.out.println(preferences);
    }

    public void saveMovieAsFavorite(Movie movie){
        Gson gson = new Gson();
        String json = gson.toJson(movie);
        editor.putString(movie.getId().toString(), json);
        editor.commit();
        Toast.makeText(context,"Salvo com sucesso", Toast.LENGTH_SHORT).show();
        //Log.i(preferences.contains(movie.getId().toString()));
    }

    public void getFavoriteMovies(){

    }
}
