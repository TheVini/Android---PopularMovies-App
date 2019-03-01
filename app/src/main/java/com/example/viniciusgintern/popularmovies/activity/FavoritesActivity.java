package com.example.viniciusgintern.popularmovies.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBar;
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

import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.RecyclerItemClickListener;
import com.example.viniciusgintern.popularmovies.adapter.FavoriteMoviesListAdapter;
import com.example.viniciusgintern.popularmovies.adapter.MoviesListAdapter;
import com.example.viniciusgintern.popularmovies.data.MoviesProvider;
import com.example.viniciusgintern.popularmovies.model.MovieModel.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoritesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerFavorites;
    private Retrofit retrofit;
    private FavoritePreferencies favoriteMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        recyclerFavorites = findViewById(R.id.recyclerFavorites);
        toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        //Cria a seta com clique na barra superior para voltar ao menu principal
        ActionBar myActionBar = getSupportActionBar();
        if(myActionBar != null){
            myActionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Define Layout
        int orientation = this.getResources().getConfiguration().orientation;
        int imageAmount = 2;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Código para o modo landscape
            imageAmount = 4;
        }
        //Inicialização do Grid do Layout
        recyclerFavorites.setLayoutManager(new GridLayoutManager(this,imageAmount));

        //Criação do objeto retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Carregamento dos dados do banco
        this.getDataFromBD();
    }

    //Método que executa a ação de voltar para o menu anterior
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            //onBackPressed();
            finish();
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

    //Aquisição de dados dos filmes do BD
    public void getDataFromBD() {
        //Trecho só para exibir os filmes que estão no banco
        Uri movies = Uri.parse("content://com.example.viniciusgintern.popularmovies.data.MoviesProvider/favorites");
        Cursor c = getContentResolver().query(movies,null,null,null, null) ;

        FavoriteMoviesListAdapter adapter = new FavoriteMoviesListAdapter(c);
        recyclerFavorites.setAdapter(adapter);

        clickEventsCaller(c);
    }

    //Listener para eventos de clique num filme
    private void clickEventsCaller(final Cursor cursor) {
        recyclerFavorites.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerFavorites,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                cursor.moveToPosition(position);
                                Toast.makeText(getApplicationContext(), cursor.getString(cursor.getColumnIndex(MoviesProvider.MOVIETITLE)),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);

                                System.out.println(cursor.getInt(cursor.getColumnIndex(MoviesProvider.MOVIERATE)));

                                Movie movieToSend = new Movie(cursor.getInt(cursor.getColumnIndex(MoviesProvider.MOVIEID)),
                                        cursor.getDouble(cursor.getColumnIndex(MoviesProvider.MOVIERATE)),
                                        cursor.getString(cursor.getColumnIndex(MoviesProvider.MOVIETITLE)),
                                        cursor.getString(cursor.getColumnIndex(MoviesProvider.MOVIEIMAGEADDRESS)),
                                        cursor.getString(cursor.getColumnIndex(MoviesProvider.MOVIEDESCRIPTION)),
                                        cursor.getString(cursor.getColumnIndex(MoviesProvider.MOVIEYEAR)),
                                        cursor.getString(cursor.getColumnIndex(MoviesProvider.MOVIEBACKDROPPATH)));

                                intent.putExtra("objeto",movieToSend);

                                startActivity(intent);

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                cursor.moveToPosition(position);
                                Toast.makeText(getApplicationContext(), cursor.getString(cursor.getColumnIndex(MoviesProvider.MOVIETITLE)),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }
}
