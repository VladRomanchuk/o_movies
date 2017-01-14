package com.vlad_romanchuk.o_movies.films;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlad_romanchuk.o_movies.R;
import com.vlad_romanchuk.o_movies.model.Movie;

import java.util.List;

public class MovieAdpter extends RecyclerView.Adapter<MovieAdpter.MovieHolder> {

    public List<Movie> movies;

    private LayoutInflater inflater;
    private Contract.Presenter presenter;

    public MovieAdpter(List<Movie> movies, Contract.Presenter presenter) {
        this.movies = movies;
        this.presenter = presenter;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return  new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        public MovieHolder(View itemView) {
            super(itemView);
        }
    }
}
