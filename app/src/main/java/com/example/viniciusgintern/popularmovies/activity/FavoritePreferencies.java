package com.example.viniciusgintern.popularmovies.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.example.viniciusgintern.popularmovies.model.MovieModel.Movie;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FavoritePreferencies {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String FILE_NAME = "favoriteMovies.preferences";

    public FavoritePreferencies(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(FILE_NAME,0);
        editor = preferences.edit();

/*        editor.clear();
        editor.commit();
        System.out.println("Tamanho do preferences " + preferences.getAll());*/
}

    public void saveMovieAsFavorite(Integer movieID){
        editor.putInt(movieID.toString(),movieID);
        editor.commit();

        System.out.println("Tamanho do preferences depois de acrescido: " + preferences.getAll());
    }

    public Boolean containMovieInFavList(Integer movieID){
        if(preferences.getAll().containsKey(movieID.toString())){
            return true;
        }
        return false;
    }

    public void removeMovieFromFavList(Integer movieID){
        editor.remove(movieID.toString());
        editor.commit();

        System.out.println("Tamanho do preferences depois de removido: " + preferences.getAll());
    }
}
