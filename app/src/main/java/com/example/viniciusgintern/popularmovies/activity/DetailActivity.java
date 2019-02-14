package com.example.viniciusgintern.popularmovies.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.viniciusgintern.popularmovies.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.mViewHolder.detailImage = findViewById(R.id.detailImage);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(this.mViewHolder.detailImage);
    }

    private static class ViewHolder{
        ImageView detailImage;
    }

}
