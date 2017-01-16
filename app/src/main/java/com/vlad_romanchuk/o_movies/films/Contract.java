package com.vlad_romanchuk.o_movies.films;

import com.vlad_romanchuk.o_movies.BasePresenter;
import com.vlad_romanchuk.o_movies.BaseView;
import com.vlad_romanchuk.o_movies.model.Genre;
import com.vlad_romanchuk.o_movies.model.Movie;

import java.util.List;

public class Contract {

    interface View extends BaseView<Presenter>{
        void setTitle(String title);

        void showMovies(List<Movie> movies);
        void showMovies(Movie movie);
        void showMoreMovies (List<Movie> moreMovies);

        void setEmpty();
        void setError(String errorText);
    }

    interface Presenter extends BasePresenter{

        void loadMovies();
        void loadMovies(String movieId);
        void loadMore();
        void searchByNameMovie(String nameFilm);

    }
}
