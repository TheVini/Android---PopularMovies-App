package com.example.viniciusgintern.popularmovies.ViewLayer;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viniciusgintern.popularmovies.ControllerLayer.Config;
import com.example.viniciusgintern.popularmovies.ControllerLayer.MoviesProvider;
import com.example.viniciusgintern.popularmovies.ModelLayer.RetrofitService.RetrofitService;
import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.ViewLayer.adapter.ReviewsListAdapter;
import com.example.viniciusgintern.popularmovies.ViewLayer.adapter.TrailersListAdapter;
import com.example.viniciusgintern.popularmovies.ModelLayer.MovieModel.Movie;
import com.example.viniciusgintern.popularmovies.ModelLayer.ReviewModel.ReviewResult;
import com.example.viniciusgintern.popularmovies.ModelLayer.TrailerModel.Trailer;
import com.example.viniciusgintern.popularmovies.ModelLayer.TrailerModel.TrailerResult;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private String movieTrailerToShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.mViewHolder.main_toolbar = findViewById(R.id.main_toolbar);
        this.mViewHolder.detailImage = findViewById(R.id.detailImage);
        this.mViewHolder.main_backdrop = findViewById(R.id.main_backdrop);
        this.mViewHolder.movieYear = findViewById(R.id.movieYear);
        this.mViewHolder.movieRate = findViewById(R.id.movieRate);
        this.mViewHolder.movieDescription = findViewById(R.id.movieDescription);
        this.mViewHolder.recyclerTrailers = findViewById(R.id.recyclerTrailers);
        this.mViewHolder.recyclerReviews = findViewById(R.id.recyclerReviews);
        this.mViewHolder.favButton = findViewById(R.id.favButton);
        this.mViewHolder.favoriteMovies = new SharedPreferencies(getApplicationContext());

        //Cria a seta com clique na barra superior para voltar ao menu principal
        setSupportActionBar(this.mViewHolder.main_toolbar);
        ActionBar myActionBar = getSupportActionBar();
        if(myActionBar != null){
            myActionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Criação do objeto retrofit para recuperar trailers
        this.mViewHolder.retrofitTrailer = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Criação do objeto retrofit para recuperar reviews
        this.mViewHolder.retrofitReview = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Recuperar os dados enviados pela main
        Bundle dados = getIntent().getExtras();
        final Movie movie = (Movie) dados.getSerializable("objeto");

        this.mViewHolder.main_toolbar.setTitle(movie.getMovieTitle());
        this.mViewHolder.movieYear.setText(movie.getMovieYear().substring(0,4));
        this.mViewHolder.movieRate.setText(movie.getMovieRate().toString() + "/10");
        this.mViewHolder.movieDescription.setText(movie.getMovieDescription());
        Picasso.get().load("http://image.tmdb.org/t/p/w342/" + movie.getMovieImageAddress()).into(this.mViewHolder.detailImage);
        Picasso.get().load("http://image.tmdb.org/t/p/w500/" + movie.getMovieBackdropPath()).into(this.mViewHolder.main_backdrop);

        //Carregamento dos trailers pela API
        this.getTrailersFromApi(movie, Config.TMDBApiKey);
        //Carregamento dos reviews pela API
        this.getReviewsFromAPI(movie,Config.TMDBApiKey);

        //Definir texto do botão
        if(mViewHolder.favoriteMovies.containMovieInFavList(movie)){
            mViewHolder.favButton.setText("ALREADY FAVORITE");
        };

        //Listener do Botão de Favoritos
        this.mViewHolder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mViewHolder.favoriteMovies.containMovieInFavList(movie)){
                    //Remoção do BD
                    mViewHolder.favoriteMovies.removeMovieFromFavList(movie);
                    Toast.makeText(getApplicationContext(),"Removed from favorites",Toast.LENGTH_SHORT).show();
                    mViewHolder.favButton.setText("MAKE AS FAVORITE");

                    String[] mSelectionArgs = {movie.getMovieId().toString()};
                    Uri targetTable = Uri.parse("content://com.example.viniciusgintern.popularmovies.ControllerLayer.MoviesProvider/favorites/" + movie.getMovieId().toString());
                    getContentResolver().delete(targetTable,MoviesProvider.MOVIEID, mSelectionArgs);
                }
                else {
                    //Inserção no BD
                    mViewHolder.favoriteMovies.saveMovieAsFavorite(movie);
                    Toast.makeText(getApplicationContext(),"Saved as favorite",Toast.LENGTH_SHORT).show();
                    mViewHolder.favButton.setText("ALREADY FAVORITE");

                    ContentValues values = new ContentValues();
                    values.put(MoviesProvider.MOVIEID, movie.getMovieId());
                    values.put(MoviesProvider.MOVIETITLE , movie.getMovieTitle());
                    values.put(MoviesProvider.MOVIEYEAR, movie.getMovieYear());
                    values.put(MoviesProvider.MOVIERATE , movie.getMovieRate());
                    values.put(MoviesProvider.MOVIEDESCRIPTION , movie.getMovieDescription());
                    values.put(MoviesProvider.MOVIEIMAGEADDRESS , movie.getMovieImageAddress());
                    values.put(MoviesProvider.MOVIEBACKDROPPATH, movie.getMovieBackdropPath());
                    getContentResolver().insert(MoviesProvider.CONTENT_URI, values);
                }
            }
        });

    }

    //Método que executa a ação de voltar para o menu anterior
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_item_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "http://www.youtube.com/watch?v=" + movieTrailerToShare);
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "Share using"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Método para exibição do menu superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);

        return true;
    }

    private static class ViewHolder{
        ImageView detailImage;
        ImageView main_backdrop;
        TextView movieYear;
        TextView movieRate;
        TextView movieDescription;
        Toolbar main_toolbar;
        static RecyclerView recyclerTrailers;
        static RecyclerView recyclerReviews;
        Retrofit retrofitTrailer;
        Retrofit retrofitReview;
        Button favButton;
        SharedPreferencies favoriteMovies;
    }

    //Listagem dos trailers
    public void getTrailersFromApi(Movie movie, String APIKey){

        //Inicialização do Grid do Layout para Trailers
        this.mViewHolder.recyclerTrailers.setLayoutManager(new GridLayoutManager(this,1));
        this.mViewHolder.recyclerTrailers.addItemDecoration( new DividerItemDecoration(this, LinearLayout.VERTICAL));

        RetrofitService service = this.mViewHolder.retrofitTrailer.create(RetrofitService.class);
        Call<TrailerResult> call = service.getMovieTrailers( movie.getMovieId(), APIKey,"en-US", "1");

        call.enqueue(new Callback<TrailerResult>() {
            @Override
            public void onResponse(Call<TrailerResult> call, Response<TrailerResult> response) {
                if (response.isSuccessful()) {
                    TrailerResult result = response.body();
                    if(result != null){
                        //Define adapter
                        TrailersListAdapter adapter = new TrailersListAdapter(result.getResults());
                        mViewHolder.recyclerTrailers.setAdapter(adapter);

                        //Define o primeiro trailer como item para ser compartilhado
                        movieTrailerToShare = result.getResults().get(0).getKey();

                        clickEventsCaller(result.getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<TrailerResult> call, Throwable t) {
                Toast.makeText(DetailActivity.this,
                        "Não foi possível realizar a requisição",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Listagem dos reviews
    public void getReviewsFromAPI(Movie movie, String APIKey){
        //Inicialização do Grid do Layout para Reviews
        this.mViewHolder.recyclerReviews.setLayoutManager(new GridLayoutManager(this,1));
        this.mViewHolder.recyclerReviews.setHasFixedSize(true);
        this.mViewHolder.recyclerReviews.addItemDecoration( new DividerItemDecoration(this, LinearLayout.VERTICAL));

        RetrofitService service = this.mViewHolder.retrofitReview.create(RetrofitService.class);
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
                            mViewHolder.recyclerReviews.setAdapter(adapter);
                        }
                        else{
                            ReviewsListAdapter adapter = new ReviewsListAdapter();
                            mViewHolder.recyclerReviews.setAdapter(adapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ReviewResult> call, Throwable t) {
                Toast.makeText(DetailActivity.this,
                        "Não foi possível realizar a requisição",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Evento de clique para execução dos trailers
    private void clickEventsCaller(final List<Trailer> trailerList) {
        //Evento de click em cada imagem
        mViewHolder.recyclerTrailers.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        mViewHolder.recyclerTrailers,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //System.out.println(movieList.get(position).getMovieTitle());
                                System.out.println();
                                Trailer trailer = trailerList.get(position);

                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+trailer.getKey())));
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
}
