package com.example.movieapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

public class MovieLoader extends AsyncTaskLoader<List<Movie>> {
    public MovieLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<Movie> loadInBackground() {
        Log.v("TAG","Loader started");
        return  NetworkUtils.getMovies(MainActivity.TOP_RATED);
    }
}
