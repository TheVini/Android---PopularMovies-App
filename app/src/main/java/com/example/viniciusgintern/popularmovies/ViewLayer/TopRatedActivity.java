package com.example.viniciusgintern.popularmovies.ViewLayer;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.viniciusgintern.popularmovies.ControllerLayer.Config;
import com.example.viniciusgintern.popularmovies.ModelLayer.RetrofitService.RetrofitService;
import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.ViewLayer.adapter.MoviesListAdapter;
import com.example.viniciusgintern.popularmovies.ModelLayer.MovieModel.Movie;
import com.example.viniciusgintern.popularmovies.ModelLayer.MovieModel.MovieResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopRatedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerTopRated;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated);

        recyclerTopRated = findViewById(R.id.recyclerTopRatedMovies);
        toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        //Define Layout
        int orientation = this.getResources().getConfiguration().orientation;
        int imageAmount = 2;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Código para o modo landscape
            imageAmount = 4;
        }
        //Inicialização do Grid do Layout
        recyclerTopRated.setLayoutManager(new GridLayoutManager(this,imageAmount));

        //Criação do objeto retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Carregamento dos dados pela API
        this.getMoviesFromApi();
    }

    //Método para exibição do menu superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Método que executa a ação de ir para a tela de favoritos
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.FavMovies){
            Intent intent = new Intent(getApplicationContext(), FavoritesActivity.class);
            startActivity(intent);
        }
        else if (itemId == R.id.MostPopular){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        else if (itemId == R.id.TopRated){
            Intent intent = new Intent(getApplicationContext(), TopRatedActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //Aquisição de dados dos filmes
    public void getMoviesFromApi(){

        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<MovieResult> call = service.getTopRatedMovies(Config.TMDBApiKey);

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (response.isSuccessful()) {
                    MovieResult result = response.body();
                    if(result != null){
                        //Define adapter
                        MoviesListAdapter adapter = new MoviesListAdapter(result.getMovieList());
                        recyclerTopRated.setAdapter(adapter);

                        clickEventsCaller(result.getMovieList());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Toast.makeText(TopRatedActivity.this,
                        "Não foi possível realizar a requisição",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Listener para eventos de clique num filme
    private void clickEventsCaller(final List<Movie> movieList) {
        //Evento de click em cada imagem
        recyclerTopRated.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerTopRated,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //System.out.println(movieList.get(position).getMovieTitle());
                                Movie movie = movieList.get(position);
                                Toast.makeText(getApplicationContext(), movie.getMovieTitle(),Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                                intent.putExtra("objeto",movie);
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
