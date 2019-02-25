package com.example.viniciusgintern.popularmovies.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.viniciusgintern.popularmovies.adapter.MoviesListAdapter;
import com.example.viniciusgintern.popularmovies.model.MovieModel.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FavoritePreferencies {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String FILE_NAME = "favoriteMovies.preferences";

    public FavoritePreferencies(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(FILE_NAME,0);
        editor = preferences.edit();
    }

    public void saveMovieAsFavorite(Movie movie){
        Gson gson = new Gson();
        String json = gson.toJson(movie);
        editor.putString(movie.getId().toString(), json);
        editor.commit();
        //getFavoriteMovies();

    }

    public void getFavoriteMovies(){

        String json = preferences.getAll().values().toString();
        System.out.println(json);

/*        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Movie.class, new MoviesListAdapter());*/

/*        Type listType = new TypeToken<ArrayList<Movie>>(){}.getType();
        Collection<Movie> movieList = new Gson().fromJson(json, listType);
        assertThat(movieList, instanceOf(Movie.class));*/

/*        Movie[] movieList = new Gson().fromJson(json, Movie[].class);
        for (Movie movie:movieList
             ) {
            System.out.println(movie.getMovieTitle());
        }*/
    }
}
