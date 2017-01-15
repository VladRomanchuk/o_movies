package com.vlad_romanchuk.o_movies.films;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.vlad_romanchuk.o_movies.R;
import com.vlad_romanchuk.o_movies.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    public List<Movie> movieList;

    private LayoutInflater inflater;
    private Contract.Presenter presenter;

    public MovieAdapter(List<Movie> movieList, Contract.Presenter presenter) {
        this.movieList = movieList;
        this.presenter = presenter;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Context context = holder.itemView.getContext();

        final Movie movie = movieList.get(position);

        holder.nameFilm.setText(movie.getTitle());
        holder.rating.setText(movie.getVoteAverage().toString());

        Glide
                .with(context)
                .load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath())
                .asBitmap()
                .into(new BitmapImageViewTarget(holder.pictureFilm) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getView().getResources(), resource);
                        drawable.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getView().getResources().getDisplayMetrics()));
                        setDrawable(drawable);
                    }
                });

        holder.aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadMovies(movie.getId() + "");
            }
        });


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {

        private ImageView pictureFilm;
        private TextView rating;
        private TextView nameFilm;
        private Button aboutButton;
        private Button trailerButton;

        public MovieHolder(View itemView) {
            super(itemView);
            pictureFilm = (ImageView) itemView.findViewById(R.id.image_film);

            rating = (TextView) itemView.findViewById(R.id.rating_text_view);
            nameFilm = (TextView) itemView.findViewById(R.id.film_name);

            aboutButton = (Button) itemView.findViewById(R.id.about_button);
            trailerButton = (Button) itemView.findViewById(R.id.trailer_button);
        }
    }
}

