package com.vlad_romanchuk.o_movies.description;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vlad_romanchuk.o_movies.R;
import com.vlad_romanchuk.o_movies.model.Movie;

public class DescriptionActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "com.vlad_romanchuk.o_movies.EXTRA_MOVIE";

    private Movie movie;
    private Toolbar toolbar;

    private ImageView filmImage;

    private TextView adultText;
    private TextView budgetText;
    private TextView genreText;
    private TextView rateText;
    private TextView languageText;
    private TextView overviewText;
    private TextView nameText;
    private TextView url;

    public static void showIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, DescriptionActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        difineView();

        if (movie.getGenres().get(0).getName() != null) {
            genreText.setText(movie.getGenres().get(0).getName());
        } else {
            genreText.setVisibility(View.GONE);
        }
        Glide.with(this).load("http://image.tmdb.org/t/p/w500/" + movie.getPosterPath()).into(filmImage);
        nameText.setText(movie.getTitle());
        if (movie.getAdult()) adultText.setVisibility(View.VISIBLE);
        overviewText.setText(movie.getOverview());

        rateText.setBackground(ContextCompat.getDrawable(getApplication().getApplicationContext(), movie.getVoteAverage() > 6.0D ?  R.drawable.shape_rating : R.drawable.shape_silver));
        rateText.setText(movie.getVoteAverage().toString());
        budgetText.setText("Budget: " + movie.getBudget());
        languageText.setText("Original language: " + movie.getOriginalLanguage());
        url.setText("Home page: " + movie.getHomepage());
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(movie.getHomepage()));
                startActivity(intent);
            }
        });
    }

    private void difineView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        filmImage = (ImageView) findViewById(R.id.film_image);
        adultText = (TextView) findViewById(R.id.text_adult);
        nameText = (TextView) findViewById(R.id.film_name);
        overviewText = (TextView) findViewById(R.id.text_descript);
        rateText = (TextView) findViewById(R.id.rate);
        budgetText = (TextView) findViewById(R.id.budget);
        languageText = (TextView) findViewById(R.id.language);
        genreText = (TextView) findViewById(R.id.genre);
        url = (TextView)findViewById(R.id.url);
    }
}
