package com.example.movieapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    final static String TAG = MovieAdapter.class.toString();
    final static String IMAGE_BASE = "https://image.tmdb.org/t/p/w185/";
    final int NUM_OF_MOVIES =20;
    List<Movie> list_of_movies= null;
    ListItemClickListener listItemClickListener;

    public MovieAdapter(List<Movie> list_of_movies,ListItemClickListener listItemClickListener)
    {
    super();
    this.list_of_movies = list_of_movies;
    this.listItemClickListener = listItemClickListener;
    }
    public interface ListItemClickListener{
    void onListItemClick(int index);
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForSingleItem = R.layout.single_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutIdForSingleItem,parent,false);
        return new MovieViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(position);
    }
    @Override
    public int getItemCount() {
        return NUM_OF_MOVIES;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView movie_poster;
        Context context;
        MovieViewHolder(View view){
            super(view);
            context = view.getContext();
            movie_poster = view.findViewById(R.id.movie_image);
            view.setOnClickListener(this); // because it extends View.OnClickListener
    }
    void bind(int index){
        Log.v(TAG,IMAGE_BASE+list_of_movies.get(index).getImageUrl());
        Picasso.get().load(IMAGE_BASE+list_of_movies.get(index).getImageUrl())
                .placeholder(R.drawable.movie_placeholder)
                .into(movie_poster);
    }
    @Override
    public void onClick(View v) {
        listItemClickListener.onListItemClick(getAdapterPosition());
    }
}
}


