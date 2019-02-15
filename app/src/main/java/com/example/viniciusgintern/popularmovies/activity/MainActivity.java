package com.example.viniciusgintern.popularmovies.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.RecyclerItemClickListener;
import com.example.viniciusgintern.popularmovies.adapter.MovieAdapter;
import com.example.viniciusgintern.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

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

        //Evento de click
        recyclerMovies.addOnItemTouchListener(
            new RecyclerItemClickListener(
                    getApplicationContext(),
                    recyclerMovies,
                    new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Movie movie = movies.get(position);
                            Toast.makeText(getApplicationContext(),movie.getMovieName(),Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {
                            Movie movie = movies.get(position);
                            Toast.makeText(getApplicationContext(),movie.getMovieName(),Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        }
                    }
            )
        );

        //Daqui em diante é codificação antiga
        //this.mViewHolder.image1.setOnClickListener(this);

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

        for(int i = 0; i<20;i++){
            movieList = new Movie("Filme1",
                    "150min",
                    "9.0/10",
                    "Very very very very very good",
                    "http://i.imgur.com/DvpvklR.png");
            this.movies.add(movieList);
        }
        movieList = new Movie("Filme2",
                "150min",
                "9.0/10",
                "Very very very very very good",
                "https://i.imgur.com/sq4AIWL.jpg");
        this.movies.add(movieList);

        movieList = new Movie("Filme3",
                "150min",
                "9.0/10",
                "Very very very very very good",
                "https://i.imgur.com/6CIFFZl.jpg");
        this.movies.add(movieList);
    }
}