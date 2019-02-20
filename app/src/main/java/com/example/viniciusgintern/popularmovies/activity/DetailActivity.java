package com.example.viniciusgintern.popularmovies.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.model.MovieModel.Movie;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.mViewHolder.detailImage = findViewById(R.id.detailImage);
        this.mViewHolder.movieTitle = findViewById(R.id.movieTitle);
        this.mViewHolder.movieYear = findViewById(R.id.movieYear);
        this.mViewHolder.movieRate = findViewById(R.id.movieRate);
        this.mViewHolder.movieDescription = findViewById(R.id.movieDescription);
        this.mViewHolder.toolbar = findViewById(R.id.mainToolbar);
        this.mViewHolder.recyclerTrailers = findViewById(R.id.recyclerTrailers);
        setSupportActionBar(this.mViewHolder.toolbar);

        //Cria a seta com clique na barra superior para voltar ao menu principal
        ActionBar myActionBar = getSupportActionBar();
        if(myActionBar != null){
            myActionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Criação do objeto retrofit para recuperar trailers e reviews
        this.mViewHolder.retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Recuperar os dados enviados pela main
        Bundle dados = getIntent().getExtras();
        Movie movie = (Movie) dados.getSerializable("objeto");

        this.mViewHolder.movieTitle.setText(movie.getMovieTitle());
        this.mViewHolder.movieYear.setText(movie.getMovieYear().substring(0,4));
        this.mViewHolder.movieRate.setText(movie.getMovieRate().toString() + "/10");
        this.mViewHolder.movieDescription.setText(movie.getMovieDescription());
        Picasso.get().load("http://image.tmdb.org/t/p/w185/" + movie.getMovieImageAddress()).into(this.mViewHolder.detailImage);

        //Carregamento dos trailers pela API
        this.getMoviesFromApi();
    }

    //Método que executa a ação de voltar para o menu anterior
    //???Não entendi o funcionamento???
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    //Método para exibição do menu superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    private static class ViewHolder{
        ImageView detailImage;
        TextView movieTitle;
        TextView movieYear;
        TextView movieRate;
        TextView movieDescription;
        Toolbar toolbar;
        RecyclerView recyclerTrailers;
        Retrofit retrofit;
    }

    //Listagem dos trailers
    public void getMoviesFromApi(){

//        RetrofitService service = retrofit.create(RetrofitService.class);
//        Call<Result> call = service.getMovies(APIKey);
//
//
//        call.enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//                if (response.isSuccessful()) {
//                    Result result = response.body();
//                    if(result != null){
//                        //Define adapter
//                        MoviesListAdapter adapter = new MoviesListAdapter(result.getMovieList());
//                        recyclerMovies.setAdapter(adapter);
//
//                        System.out.println(result.getMovieList());
//                        clickEventsCaller(result.getMovieList());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//                Toast.makeText(MainActivity.this,
//                        "Não foi possível realizar a requisição",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
    }

}
