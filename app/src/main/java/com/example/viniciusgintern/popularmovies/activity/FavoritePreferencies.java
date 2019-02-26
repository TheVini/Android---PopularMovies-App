package com.example.viniciusgintern.popularmovies.activity;

import android.content.Context;
import android.content.SharedPreferences;

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

    public void saveMovieAsFavorite(Movie movie){
        Gson gson = new Gson();
        String json = gson.toJson(movie);
        editor.putString(movie.getId().toString(), json);
        editor.commit();
    }

    /*public List<Movie> getFavoriteMovies(){

        String jsonString = preferences.getAll().values().toString();
        Type type = new TypeToken< List < Movie >>() {}.getType();
        favMovieList = new Gson().fromJson(jsonString, type);
        if(favMovieList != null) {
            return favMovieList;
        }
        else{
            return new ArrayList<>();
        }

        //System.out.println(jsonString);
        //System.out.println(jsonString.length());

        *//*JsonParser parser = new JsonParser();
        JsonArray jsonArray = (JsonArray) parser.parse(jsonString);
        Iterator<JsonElement> iterator = jsonArray.iterator();
        List<Object> list = new ArrayList<>();
        while(iterator.hasNext()){
            list.add(new Gson().fromJson(iterator.next(), Movie.class));
        }

        //System.out.println("O tipo da variável é " + list.getClass().getName());
        System.out.println(list);

        List<Movie> favMovieList = new ArrayList<>();

        if(list != null || list.size()>0) {
            for (Object movie : list
            ) {
                favMovieList.add((Movie) movie);
            }
            return favMovieList;
        }
        else{
            System.out.println("Lista vazia " + Collections.EMPTY_LIST);
            return Collections.EMPTY_LIST;
        }*//*
    }*/

    /*public Boolean containMovieInFavList(Movie movie){
        Boolean Target = false;
        List<Movie> favoriteList = getFavoriteMovies();
        for (Movie favMovie: favoriteList
             ) {
            if(favMovie.getId().intValue() == movie.getId().intValue() ){
                Target = true;
            }
        }

        return Target;
    }*/

    /*public void removeMovieFromFavList(Movie movie){
        List<Movie> favoriteList = getFavoriteMovies();
        List<Movie> filteredList = new ArrayList<>();

        for (Movie favMovie: favoriteList
        ) {
            if((favMovie.getId().intValue() != movie.getId().intValue() )){
                filteredList.add(favMovie);
            }
        }
        editor.clear();
        editor.commit();

        for (Movie favMovie: filteredList
        ) {
            saveMovieAsFavorite(favMovie);
        }
    }*/
}
