package com.example.viniciusgintern.popularmovies.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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
    private Toolbar toolbar;
    private List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        recyclerMovies = findViewById(R.id.recyclerMovies);

        //Define Layout
        int orientation = this.getResources().getConfiguration().orientation;
        int imageAmount = 2;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // code for landscape mode
            imageAmount = 4;
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,imageAmount);
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

                            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);

                            intent.putExtra("objeto",movie);

                            startActivity(intent);
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
    }

    //Trecho de exibição do menu superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void getMoviesFromApi(){
        Movie movieList;

        String text = "Nulla id turpis nisl. Vestibulum fringilla massa eu lacus bibendum, eget semper nisi rutrum. In imperdiet elementum elit sed ultricies. Nam interdum sollicitudin lectus sed fringilla. Aenean et iaculis libero, quis euismod justo. Phasellus tristique varius leo in sagittis. Fusce sodales eleifend fringilla. Integer efficitur risus nec enim tincidunt dignissim. Fusce sed elit blandit, scelerisque felis et, mollis tellus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc quis felis tortor. Nulla mattis augue a cursus semper.";
        text = text + text + text + text + text + text + text + text + text + text + text + text;
        for(int i = 0; i<5;i++){
            movieList = new Movie("A arte",
                    "2018",
                    "150min",
                    "9.0/10",
                    text,
                    "http://i.imgur.com/DvpvklR.png");
            this.movies.add(movieList);
            movieList = new Movie("A Moto - Parte 1",
                    "2018",
                    "150min",
                    "9.0/10",
                    text,
                    "https://i.imgur.com/sq4AIWL.jpg");
            this.movies.add(movieList);

            movieList = new Movie("A Moto - Parte 2",
                    "2018",
                    "150min",
                    "9.0/10",
                    text,
                    "https://i.imgur.com/6CIFFZl.jpg");
            this.movies.add(movieList);

            movieList = new Movie("The chemistry",
                    "2018",
                    "150min",
                    "9.0/10",
                    text,
                    "https://i.imgur.com/Mv8ZLmW.jpg");
            this.movies.add(movieList);
        }
    }
}