package com.example.viniciusgintern.popularmovies.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.adapter.MovieAdapter;
import com.example.viniciusgintern.popularmovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerMovies;
    private List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerMovies = findViewById(R.id.recyclerMovies);

        //Define Layout
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerMovies.setLayoutManager(layoutManager);

        //Define adapter
        this.getMoviesFromApi();
        MovieAdapter adapter = new MovieAdapter(movies);
        recyclerMovies.setAdapter(adapter);


        //Daqui em diante é codificação antiga
        //this.mViewHolder.image1.setOnClickListener(this);
        /*this.mViewHolder.image1 = findViewById(R.id.imageView);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(this.mViewHolder.image1);
        */

        // Find the toolbar view inside the activity layout
        //Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        //setSupportActionBar(toolbar);

    }

    /*
    //Trecho para criação do menu superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    */

    public void getMoviesFromApi(){
        Movie movieList;

        movieList = new Movie("Filme1",
                "150min",
                "9.0/10",
                "Very very very very very good",R.drawable.imagemstarwars);
        this.movies.add(movieList);

        movieList = new Movie("Filme2",
                "150min",
                "9.0/10",
                "Very very very very very good",R.drawable.imagemstarwars);
        this.movies.add(movieList);
    }
}