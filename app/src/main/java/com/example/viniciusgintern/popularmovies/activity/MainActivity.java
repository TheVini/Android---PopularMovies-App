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
import android.widget.Toast;

import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.RecyclerItemClickListener;
import com.example.viniciusgintern.popularmovies.model.RretrofitService.RetrofitService;
import com.example.viniciusgintern.popularmovies.adapter.MoviesListAdapter;
import com.example.viniciusgintern.popularmovies.model.MovieModel.Movie;
import com.example.viniciusgintern.popularmovies.model.MovieModel.MovieResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerMovies;
    private Toolbar toolbar;
    private List<Movie> movieList = new ArrayList<>();
    private Retrofit retrofit;

    /***************************************/
    public final String APIKey = "Inserir a APIKey aqui";
    /***************************************/

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
            //Código para o modo landscape
            imageAmount = 4;
        }
        //Inicialização do Grid do Layout
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,imageAmount);
        recyclerMovies.setLayoutManager(layoutManager);

        //Criação do objeto retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Carregamento dos dados pela API
        this.getMoviesFromApi();
    }

    //Trecho de exibição do menu superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Aquisição de dados dos filmes
    public void getMoviesFromApi(){

        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<MovieResult> call = service.getMovies(APIKey);

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (response.isSuccessful()) {
                    MovieResult result = response.body();
                    if(result != null){
                        //Define adapter
                        MoviesListAdapter adapter = new MoviesListAdapter(result.getMovieList());
                        recyclerMovies.setAdapter(adapter);

                        //System.out.println(result.getMovieList());
                        clickEventsCaller(result.getMovieList());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Não foi possível realizar a requisição",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickEventsCaller(final List<Movie> movieList) {
        //Evento de click em cada imagem
        recyclerMovies.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerMovies,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //System.out.println(movieList.get(position).getMovieTitle());
                                System.out.println();
                                Movie movie = movieList.get(position);
                                Toast.makeText(getApplicationContext(), movie.getMovieTitle(),Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);

                                intent.putExtra("objeto",movie);
                                intent.putExtra("APIKey",APIKey);

                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Movie movie = movieList.get(position);
                                Toast.makeText(getApplicationContext(),movie.getMovieTitle(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }
}