package com.example.viniciusgintern.popularmovies.ControllerLayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.viniciusgintern.popularmovies.ModelLayer.MovieModel.MovieResult;
import com.example.viniciusgintern.popularmovies.ModelLayer.RetrofitService.RetrofitService;
import com.example.viniciusgintern.popularmovies.ViewLayer.MainActivity;
import com.example.viniciusgintern.popularmovies.ViewLayer.adapter.MoviesListAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BusinessClass {

    /*public void getMoviesFromApi(Retrofit retrofit, final RecyclerView recyclerView, final Context Activity, int requestType, Call<?> call){
        RetrofitService service = retrofit.create(RetrofitService.class);
        switch (requestType){
            case 1:
                call = service.getPopularMovies(Config.TMDBApiKey);
                break;
        }

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (response.isSuccessful()) {
                    MovieResult result = response.body();
                    if(result != null){
                        //Define adapter
                        MoviesListAdapter adapter = new MoviesListAdapter(result.getMovieList());
                        recyclerView.setAdapter(adapter);

                        //clickEventsCaller(result.getMovieList());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Toast.makeText(Activity,
                        "Não foi possível realizar a requisição",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
