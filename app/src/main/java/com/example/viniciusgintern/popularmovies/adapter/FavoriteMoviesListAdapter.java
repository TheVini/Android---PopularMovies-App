package com.example.viniciusgintern.popularmovies.adapter;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.data.MoviesProvider;
import com.example.viniciusgintern.popularmovies.model.MovieModel.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteMoviesListAdapter extends RecyclerView.Adapter<FavoriteMoviesListAdapter.MyViewHolder>{

    private Cursor cursorMovies;

    public FavoriteMoviesListAdapter(Cursor cursorMovies){
        this.cursorMovies = cursorMovies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listItem = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_favorites_detalhe, viewGroup, false);
        return new FavoriteMoviesListAdapter.MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if(i==0){
            cursorMovies.moveToFirst();
        }
        Picasso.get().load("http://image.tmdb.org/t/p/w185/" + cursorMovies.getString(cursorMovies.getColumnIndex(MoviesProvider.MOVIEIMAGEADDRESS))).into(myViewHolder.movieImage);
        //System.out.println("http://image.tmdb.org/t/p/w185/" + cursorMovies.getString(cursorMovies.getColumnIndex(MoviesProvider.MOVIEIMAGEADDRESS)));
        cursorMovies.moveToNext();
    }

    @Override
    public int getItemCount() {
        return cursorMovies.getCount();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView movieImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movieImage);
        }
    }
}
