package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener {
    ArrayList<Movie> arrayList = null;
    MovieAdapter movieAdapter;
    RecyclerView recyclerView;
    TextView errorTv;
    ProgressBar loadingView;

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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2 );

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        // top rated is default settings
        movieAdapter = new MovieAdapter(getMovieList(true),this); // because it implement MovieAdapter.ListItemClickListener
        recyclerView.setAdapter(movieAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    //Todo make sure this makes sense once the data is being fetched correctly
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.most_popular:
                movieAdapter = new MovieAdapter(getMovieList(false), this); // because it implement MovieAdapter.ListItemClickListener
                recyclerView.setAdapter(movieAdapter);
                return true;
            case R.id.top_rated:
                // COMPLETED (14) Pass in this as the ListItemClickListener to the GreenAdapter constructor
                movieAdapter = new MovieAdapter(getMovieList(true), this); // because it implement MovieAdapter.ListItemClickListener
                recyclerView.setAdapter(movieAdapter);
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
    }
    // Todo networking part is missing completelly
    // Todo fetch data from api
    // Todo display images using library Picasso
    // Todo parse Json and store into array

    public List<Movie> getMovieList(boolean topRated)
    {
        arrayList = new ArrayList<>();
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"1900" ));
        arrayList.add(new Movie("Mission impossible","www.bing.cn","he can jump from plane to sea",3.5,"1993" ));
        arrayList.add(new Movie("Top gun","www.bing.cn","flying very very fast",3.5,"1995" ));
        arrayList.add(new Movie("Aircon","www.bing.cn","bad guys on plane",3.5,"2000" ));
        arrayList.add(new Movie("Up","www.bing.cn","flying house",3.5,"2001" ));
        arrayList.add(new Movie("Aladin","www.bing.cn","magin carpet",3.5,"2015" ));
        arrayList.add(new Movie("Mr.Smith","www.bing.cn","parents are spies",3.5,"2019" ));
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"yesterday" ));
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"yesterday" ));
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"yesterday" ));
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"yesterday" ));
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"yesterday" ));
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"yesterday" ));
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"yesterday" ));
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"yesterday" ));
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"yesterday" ));
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"yesterday" ));
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"yesterday" ));
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"yesterday" ));
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"yesterday" ));
        arrayList.add(new Movie("James Bond","www.bing.cn","he kills all the bad guys",3.5,"yesterday" ));
        return arrayList;
    }
}
//Todo use progress bar in AsyncTask

//    protected void onPreExecute() {
//        super.onPreExecute();
//        mLoadingIndicator.setVisibility(View.VISIBLE);
//    }