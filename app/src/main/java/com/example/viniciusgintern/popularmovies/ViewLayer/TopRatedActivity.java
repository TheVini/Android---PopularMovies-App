package com.example.viniciusgintern.popularmovies.ViewLayer;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.viniciusgintern.popularmovies.ControllerLayer.BusinessClass;
import com.example.viniciusgintern.popularmovies.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopRatedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerTopRated;
    private Retrofit retrofit;
    private ProjectSharedPreferences projectSharedPreferences;
    private BusinessClass businessClass;

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
        //this.getMoviesFromApi();
        businessClass = new BusinessClass();
        businessClass.getMoviesFromAPI(retrofit,recyclerTopRated, this.getApplicationContext(), 2);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences preferences = getSharedPreferences("favoriteMovies.preferences",0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("LastActivity",2);
        editor.commit();
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

        //Seleção da activity através do menu superior direito
        businessClass.switchActivity(itemId,getApplicationContext());

        return super.onOptionsItemSelected(item);
    }
}
