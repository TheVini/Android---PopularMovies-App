package com.example.viniciusgintern.popularmovies.ViewLayer;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.viniciusgintern.popularmovies.ControllerLayer.BusinessClass;
import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.ViewLayer.adapter.FavoriteMoviesListAdapter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoritesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerFavorites;
    private Retrofit retrofit;
    private ProjectSharedPreferences favoriteMovies;
    private BusinessClass businessClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        recyclerFavorites = findViewById(R.id.recyclerFavorites);
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
        recyclerFavorites.setLayoutManager(new GridLayoutManager(this,imageAmount));

        //Criação do objeto retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Carregamento dos dados do banco
        this.getDataFromBD();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences preferences = getSharedPreferences("favoriteMovies.preferences",0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("LastActivity",1);
        editor.commit();
    }

    //Método que executa a ação de voltar para o menu anterior
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        //Seleção da activity através do menu superior direito
        BusinessClass businessClass = new BusinessClass();
        businessClass.switchActivity(itemId,getApplicationContext());

        return super.onOptionsItemSelected(item);
    }

    //Método para exibição do menu superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Aquisição de dados dos filmes do BD e exibição
    public void getDataFromBD() {
        //Trecho só para exibir os filmes que estão no banco
        Uri movies = Uri.parse("content://com.example.viniciusgintern.popularmovies.ControllerLayer.MoviesProvider/favorites");
        Cursor c = getContentResolver().query(movies,null,null,null, null) ;

        FavoriteMoviesListAdapter adapter = new FavoriteMoviesListAdapter(c);
        recyclerFavorites.setAdapter(adapter);

        businessClass.clickEventsCallerForMoviesFromBD(c,recyclerFavorites,getApplicationContext());
    }
}
