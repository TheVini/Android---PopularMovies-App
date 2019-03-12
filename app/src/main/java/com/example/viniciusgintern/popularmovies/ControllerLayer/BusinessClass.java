package com.example.viniciusgintern.popularmovies.ControllerLayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.viniciusgintern.popularmovies.ModelLayer.MovieModel.Movie;
import com.example.viniciusgintern.popularmovies.ModelLayer.MovieModel.MovieResult;
import com.example.viniciusgintern.popularmovies.ModelLayer.RetrofitService.RetrofitService;
import com.example.viniciusgintern.popularmovies.ModelLayer.ReviewModel.ReviewResult;
import com.example.viniciusgintern.popularmovies.ModelLayer.TrailerModel.Trailer;
import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.ViewLayer.DetailActivity;
import com.example.viniciusgintern.popularmovies.ViewLayer.FavoritesActivity;
import com.example.viniciusgintern.popularmovies.ViewLayer.MainActivity;
import com.example.viniciusgintern.popularmovies.ViewLayer.RecyclerItemClickListener;
import com.example.viniciusgintern.popularmovies.ViewLayer.ProjectSharedPreferences;
import com.example.viniciusgintern.popularmovies.ViewLayer.TopRatedActivity;
import com.example.viniciusgintern.popularmovies.ViewLayer.adapter.MoviesListAdapter;
import com.example.viniciusgintern.popularmovies.ViewLayer.adapter.ReviewsListAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BusinessClass {

    //Seleção de Activity
    public void switchActivity(int itemId, Context projectContext){
        if (itemId == R.id.FavMovies){
            Intent intent = new Intent(projectContext, FavoritesActivity.class);
            projectContext.startActivity(intent);
        }
        else if (itemId == R.id.MostPopular){
            Intent intent = new Intent(projectContext, MainActivity.class);
            projectContext.startActivity(intent);
        }
        else if (itemId == R.id.TopRated){
            Intent intent = new Intent(projectContext, TopRatedActivity.class);
            projectContext.startActivity(intent);
        }
    }

    //Aquisição de dados dos filmes
    public void getMoviesFromAPI(Retrofit retrofit, final RecyclerView recyclerView, final Context ProjectContext, int requestType){
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<MovieResult> call = null;
        switch (requestType){
            case 1:
                //Caso seja filmes populares
                call = service.getPopularMovies(Config.TMDBApiKey);
                break;
            case 2:
                //Caso seja os filmes Top
                call = service.getTopRatedMovies(Config.TMDBApiKey);
                break;
        }

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (response.isSuccessful()) {
                    MovieResult result = response.body();
                    if(result != null){
                        MoviesListAdapter adapter = new MoviesListAdapter(result.getMovieList());
                        recyclerView.setAdapter(adapter);

                        clickEventsCallerForMovies(result.getMovieList(),recyclerView,ProjectContext);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Toast.makeText(ProjectContext,
                        "Não foi possível realizar a requisição",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Listagem dos reviews
    public void getReviewsFromAPI(Retrofit retrofit, final RecyclerView recyclerView, final Context ProjectContext , Movie movie, String APIKey){
        //Inicialização do Grid do Layout para Reviews
        recyclerView.setLayoutManager(new GridLayoutManager(ProjectContext,1));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration( new DividerItemDecoration(ProjectContext, LinearLayout.VERTICAL));

        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<ReviewResult> call = service.getMovieReviews(movie.getMovieId(),APIKey, "en-US", "1");

        call.enqueue(new Callback<ReviewResult>() {
            @Override
            public void onResponse(Call<ReviewResult> call, Response<ReviewResult> response) {
                if (response.isSuccessful()) {
                    ReviewResult result = response.body();
                    if(result != null){
                        if(result.getResults().size() != 0){
                            //Define adapter
                            ReviewsListAdapter adapter = new ReviewsListAdapter(result.getResults());
                            recyclerView.setAdapter(adapter);
                        }
                        else{
                            ReviewsListAdapter adapter = new ReviewsListAdapter();
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ReviewResult> call, Throwable t) {
                Toast.makeText(ProjectContext,
                        "Não foi possível realizar a requisição",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Evento de clique para execução dos trailers
    public void clickEventsCallerForTrailers(final List<Trailer> trailerList, final RecyclerView recyclerView, final Context ProjectContext) {
        //Evento de click em cada imagem
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        ProjectContext,
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //System.out.println(movieList.get(position).getMovieTitle());
                                System.out.println();
                                Trailer trailer = trailerList.get(position);

                                ProjectContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+trailer.getKey())));
                                Log.i("Video", "Video Playing....");
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            }
                        }
                )
        );
    }

    //Listener para eventos de clique num filme
    public void clickEventsCallerForMovies(final List<Movie> movieList, final RecyclerView recyclerView, final Context ProjectContext) {
        //Evento de click em cada imagem
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        ProjectContext,
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Movie movie = movieList.get(position);
                                Toast.makeText(ProjectContext, movie.getMovieTitle(),Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(ProjectContext, DetailActivity.class);
                                intent.putExtra("objeto",movie);
                                ProjectContext.startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Movie movie = movieList.get(position);
                                Toast.makeText(ProjectContext,movie.getMovieTitle(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }

    //Listener para eventos de clique num filme extraido do BD na activity dos favoritos
    public void clickEventsCallerForMoviesFromBD(final Cursor cursor, RecyclerView recyclerView, final Context ProjectContext) {
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        ProjectContext,
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                cursor.moveToPosition(position);
                                Toast.makeText(ProjectContext, cursor.getString(cursor.getColumnIndex(MoviesProvider.MOVIETITLE)),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ProjectContext, DetailActivity.class);

                                System.out.println(cursor.getInt(cursor.getColumnIndex(MoviesProvider.MOVIERATE)));

                                Movie movieWhichDetailsWillBeDisplayed = new Movie(cursor.getInt(cursor.getColumnIndex(MoviesProvider.MOVIEID)),
                                        cursor.getDouble(cursor.getColumnIndex(MoviesProvider.MOVIERATE)),
                                        cursor.getString(cursor.getColumnIndex(MoviesProvider.MOVIETITLE)),
                                        cursor.getString(cursor.getColumnIndex(MoviesProvider.MOVIEIMAGEADDRESS)),
                                        cursor.getString(cursor.getColumnIndex(MoviesProvider.MOVIEDESCRIPTION)),
                                        cursor.getString(cursor.getColumnIndex(MoviesProvider.MOVIEYEAR)),
                                        cursor.getString(cursor.getColumnIndex(MoviesProvider.MOVIEBACKDROPPATH)));

                                intent.putExtra("objeto",movieWhichDetailsWillBeDisplayed);

                                ProjectContext.startActivity(intent);

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                cursor.moveToPosition(position);
                                Toast.makeText(ProjectContext, cursor.getString(cursor.getColumnIndex(MoviesProvider.MOVIETITLE)),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }
}
