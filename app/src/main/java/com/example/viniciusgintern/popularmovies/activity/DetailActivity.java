package com.example.viniciusgintern.popularmovies.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.mViewHolder.detailImage = findViewById(R.id.detailImage);
        this.mViewHolder.movieTitle = findViewById(R.id.movieTitle);
        this.mViewHolder.movieYear = findViewById(R.id.movieYear);
        this.mViewHolder.movieDuration = findViewById(R.id.movieDuration);
        this.mViewHolder.movieRate = findViewById(R.id.movieRate);
        this.mViewHolder.movieDescription = findViewById(R.id.movieDescription);
        this.mViewHolder.toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(this.mViewHolder.toolbar);

        //Cria a seta para voltar ao menu principal
        ActionBar myActionBar = getSupportActionBar();
        if(myActionBar != null){
            myActionBar.setDisplayHomeAsUpEnabled(true);
            //myActionBar.setIcon(R.drawable.ic_arrow_back_white_24dp);
        }

        //Recuperar os dados enviados pela main
        Bundle dados = getIntent().getExtras();
        Movie movie = (Movie) dados.getSerializable("objeto");

        this.mViewHolder.movieTitle.setText(movie.getMovieTitle());
        this.mViewHolder.movieYear.setText(movie.getMovieYear());
        this.mViewHolder.movieDuration.setText(movie.getMovieDuration());
        this.mViewHolder.movieRate.setText(movie.getMovieRate().toString());
        this.mViewHolder.movieDescription.setText(movie.getMovieDescription());

        Picasso.get().load(movie.getMovieImageAddress()).into(this.mViewHolder.detailImage);
    }

    //Método que executa a volta para o menu anterior
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
        TextView movieDuration;
        TextView movieRate;
        TextView movieDescription;
        Toolbar toolbar;
    }
}
