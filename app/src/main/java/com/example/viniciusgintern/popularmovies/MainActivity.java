package com.example.viniciusgintern.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.mViewHolder.image1.setOnClickListener(this);

        this.mViewHolder.image1 = findViewById(R.id.imageView);
        this.mViewHolder.image2 = findViewById(R.id.imageView2);
        this.mViewHolder.image3 = findViewById(R.id.imageView3);
        this.mViewHolder.image4 = findViewById(R.id.imageView4);
        this.mViewHolder.image5 = findViewById(R.id.imageView5);
        this.mViewHolder.image6 = findViewById(R.id.imageView6);

        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(this.mViewHolder.image1);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(this.mViewHolder.image2);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(this.mViewHolder.image3);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(this.mViewHolder.image4);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(this.mViewHolder.image5);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(this.mViewHolder.image6);

        // Find the toolbar view inside the activity layout
        //Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        //setSupportActionBar(toolbar);

    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    */

    @Override
    public void onClick(View v){
        int id = v.getId();
        if(id==R.id.imageView){
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
        }
    }

    private static class ViewHolder{
        ImageView image1;
        ImageView image2;
        ImageView image3;
        ImageView image4;
        ImageView image5;
        ImageView image6;

    }

}
