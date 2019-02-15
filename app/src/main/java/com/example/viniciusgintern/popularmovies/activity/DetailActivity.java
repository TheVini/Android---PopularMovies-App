package com.example.viniciusgintern.popularmovies.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.mViewHolder.detailImage = findViewById(R.id.detailImage);
        this.mViewHolder.movieName = findViewById(R.id.movieName);
        this.mViewHolder.movieDuration = findViewById(R.id.movieDuration);
        this.mViewHolder.movieRate = findViewById(R.id.movieRate);
        this.mViewHolder.movieDescription = findViewById(R.id.movieDescription);

        //Recuperar os dados enviados pela main
        Bundle dados = getIntent().getExtras();
        Movie movie = (Movie) dados.getSerializable("objeto");

        this.mViewHolder.movieName.setText(movie.getMovieName());
        this.mViewHolder.movieDuration.setText(movie.getMovieDuration());
        this.mViewHolder.movieRate.setText(movie.getMovieRate());
        this.mViewHolder.movieDescription.setText(movie.getMovieDescription());

        Picasso.get().load(movie.getMovieImageAddress()).into(this.mViewHolder.detailImage);
    }

    private static class ViewHolder{
        ImageView detailImage;
        TextView movieName;
        TextView movieDuration;
        TextView movieRate;
        TextView movieDescription;
    }
}
