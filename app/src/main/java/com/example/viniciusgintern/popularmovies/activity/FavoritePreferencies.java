package com.example.viniciusgintern.popularmovies.activity;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.viniciusgintern.popularmovies.model.MovieModel.Movie;


public class FavoritePreferencies {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String FILE_NAME = "favoriteMovies.preferences";

    public FavoritePreferencies(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(FILE_NAME,0);
        editor = preferences.edit();

//        editor.clear();
//        editor.commit();
//        System.out.println("Tamanho do preferences " + preferences.getAll());
}

    public void saveMovieAsFavorite(Movie movie){
        editor.putInt(movie.getMovieId().toString(),movie.getMovieId());
        editor.commit();

        System.out.println("Tamanho do preferences depois de acrescido: " + preferences.getAll());

//        editor.clear();
//        editor.commit();
//        System.out.println("Tamanho do preferences " + preferences.getAll());

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

        System.out.println("Tamanho do preferences depois de removido: " + preferences.getAll());
    }
}
