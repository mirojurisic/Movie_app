package com.example.movieapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener, LoaderManager.LoaderCallbacks<List<Movie>>  {
    List<Movie> arrayList = null;
    MovieAdapter movieAdapter;
    RecyclerView recyclerView;
    TextView errorTv;
    ProgressBar loadingView;
    static boolean TOP_RATED = true;

    @Override
    public void onListItemClick(int index) {
       // Toast.makeText(this,""+index,Toast.LENGTH_SHORT).show();
        Intent details =new Intent(this,MovieDetails.class);
        details.putExtra("title",arrayList.get(index).getTitle());
        details.putExtra("imageUrl",arrayList.get(index).getImageUrl());
        details.putExtra("plot",arrayList.get(index).getPlot());
        details.putExtra("rating",arrayList.get(index).getRating());
        details.putExtra("releaseDate",arrayList.get(index).getReleaseDate());
        startActivity(details);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rc_view);
        errorTv = findViewById(R.id.error_message);
        loadingView = (ProgressBar) findViewById(R.id.loading);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        //LoaderManager loaderManager = getSupportLoaderManager();

        if (!isConnectedToInternet())
            showNoInternetView();
        else {
            getLoaderManager().initLoader(0, null, this).forceLoad();
        }
    }
    boolean isConnectedToInternet(){
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
    void updateUI()
    {
        if(arrayList!=null) {
            movieAdapter = new MovieAdapter(arrayList, this); // because it implement MovieAdapter.ListItemClickListener
            recyclerView.setAdapter(movieAdapter);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.most_popular:
                TOP_RATED = false;
                if (!isConnectedToInternet())
                    showNoInternetView();
                else {
                    showMovieView();
                    getLoaderManager().initLoader(0, null, this).forceLoad();
                }
                return true;
            case R.id.top_rated:
                TOP_RATED = true;
                if (!isConnectedToInternet())
                    showNoInternetView();
                else {
                    showMovieView();
                    Log.v("TAG", "missing loader");
                    getLoaderManager().initLoader(0, null, this).forceLoad();
                }
                return true;
            case R.id.settings:
                Intent details =new Intent(this,Settings.class);
                startActivity(details);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showMovieView() {
        errorTv.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }
    private void showErrorView() {
        recyclerView.setVisibility(View.INVISIBLE);
        errorTv.setVisibility(View.VISIBLE);
        errorTv.setText("ERROR");
    }
    private void showNoInternetView() {
        recyclerView.setVisibility(View.INVISIBLE);
        errorTv.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        errorTv.setText("No internet connection");
    }


    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        loadingView.setVisibility(View.VISIBLE);
        return new MovieLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        Log.v("TAG","Loader finished");
        loadingView.setVisibility(View.GONE);
        Log.v("TAG","size of data "+data.size());
        arrayList = data;
        if(arrayList==null || arrayList.isEmpty())
            showErrorView();
        else
            showMovieView();
        updateUI();
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {

    }
}

