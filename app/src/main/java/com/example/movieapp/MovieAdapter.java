package com.example.movieapp;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
final int NUM_OF_MOVIES =50;
//Todo implement constructor for adapter
public MovieAdapter()
{
    super();
}
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return NUM_OF_MOVIES;
    }
    // Todo implement onCrete

//Todo Implement on bind view holder

//Todo: implement viewholder
public class MovieViewHolder extends RecyclerView.ViewHolder{
    MovieViewHolder(View view){
        super(view);
    }
}


}


