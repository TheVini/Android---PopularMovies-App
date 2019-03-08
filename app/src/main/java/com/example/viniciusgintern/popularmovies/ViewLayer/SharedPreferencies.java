package com.example.viniciusgintern.popularmovies.ViewLayer;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.viniciusgintern.popularmovies.ModelLayer.MovieModel.Movie;

//Camada de negócio - SharedPreferencies
public class SharedPreferencies {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String FILE_NAME = "favoriteMovies.preferences";

    public SharedPreferencies(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(FILE_NAME,0);
        editor = preferences.edit();
        }

    public void saveMovieAsFavorite(Movie movie){
        editor.putInt(movie.getMovieId().toString(),movie.getMovieId());
        editor.commit();
    }

    public Boolean containMovieInFavList(Movie movie){
        if(preferences.getAll().containsKey(movie.getMovieId().toString())){
            return true;
        }
        return false;
    }

    public void removeMovieFromFavList(Movie movie){
        editor.remove(movie.getMovieId().toString());
        editor.commit();
    }
}
