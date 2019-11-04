package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent hostIntent = getIntent();
        ((TextView)findViewById(R.id.d_title)).setText( hostIntent.getStringExtra("title"));
        ((TextView)findViewById(R.id.d_plot)).setText( hostIntent.getStringExtra("plot"));
        ImageView movie_poster = ((ImageView)findViewById(R.id.d_poster));
        Picasso.get().load(MovieAdapter.IMAGE_BASE + hostIntent.getStringExtra("imageUrl"))
                .placeholder(R.drawable.movie_placeholder)
                .into(movie_poster);
        ((TextView)findViewById(R.id.d_rating)).setText( ""+hostIntent.getDoubleExtra("rating",0.0));
        ((TextView)findViewById(R.id.d_release)).setText(hostIntent.getStringExtra("releaseDate"));

    }
}
