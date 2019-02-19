package com.example.viniciusgintern.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.activity.DetailActivity;
import com.example.viniciusgintern.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<Movie> moviesList;

    public MovieAdapter(List<Movie> moviesList){
        this.moviesList = moviesList;
    }

    //Inicialização do componente que exibe as imagens
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listItem = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_main_detalhe, viewGroup, false);

        return new MyViewHolder(listItem);
    }

    //Componente que exibe as imagens na main
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Movie movie = moviesList.get(i);
        System.out.println();
        Picasso.get().load("http://image.tmdb.org/t/p/w185/" + movie.getMovieImageAddress()).into(myViewHolder.movieImage);
    }

    //Componente que define o tamanho da lista que sera retornada
    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView movieImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movieImage);
        }

    }

}
