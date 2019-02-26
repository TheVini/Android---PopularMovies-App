package com.example.viniciusgintern.popularmovies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.model.MovieModel.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteMoviesListAdapter extends RecyclerView.Adapter<FavoriteMoviesListAdapter.MyViewHolder>{

    private List<Movie> favMoviesList;

    public FavoriteMoviesListAdapter(List<Movie> favMoviesList){
        this.favMoviesList = favMoviesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listItem = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_favorites, viewGroup, false);

        return new FavoriteMoviesListAdapter.MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Movie movie = favMoviesList.get(i);
        Picasso.get().load("http://image.tmdb.org/t/p/w185/" + movie.getMovieImageAddress()).into(myViewHolder.movieImage);
    }

    @Override
    public int getItemCount() {
        return favMoviesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView movieImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movieImage);
        }

    }


}
