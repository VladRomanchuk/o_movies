package com.vlad_romanchuk.o_movies.description;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.vlad_romanchuk.o_movies.model.Movie;

public class DescriptionActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "com.vlad_romanchuk.o_movies.EXTRA_MOVIE";

    private Movie movie;
    private Toolbar toolbar;

    private ImageView posterImage;

    private TextView adultText;
    private TextView overviewText;
    private TextView rateText;
    private TextView budgetText;
    private TextView languageText;
    private TextView genreText;

    public static void showIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, DescriptionActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        context.startActivity(intent);
    }

}
