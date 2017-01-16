package com.vlad_romanchuk.o_movies.films;

import android.support.annotation.NonNull;

import com.vlad_romanchuk.o_movies.model.Movie;
import com.vlad_romanchuk.o_movies.model.source.repo.IMoviesRepo;
import com.vlad_romanchuk.o_movies.model.source.repo.MovieRepoI;

import okhttp3.Response;

public class Presenter implements Contract.Presenter {

    private static final String TAG= "Presenter";

    private IMoviesRepo repo;
    private Contract.View view;

    private okhttp3.Response currentResponse;

    public Presenter(@NonNull Contract.View view) {
        this.view = view;
        repo = MovieRepoI.getInstance();
        view.setPresenter(this);
    }

    @Override
    public void run() {
        loadMovies();
    }


    @Override
    public void loadMovies() {
        repo.getPopularMovies(1, new IMoviesRepo.LoadMoviesCallbackI() {
            @Override
            public void onError() {
                view.setEmpty();
            }

            @Override
            public void onMoviesLoaded(MovieRepoI.MovieList movies, Response response) {
                view.showMovies(movies.getResults());
                currentResponse = response;
            }
        });

    }

    @Override
    public void loadMovies(String movieId) {
        repo.getMovie(movieId, new IMoviesRepo.MovieCallbackI() {
            @Override
            public void onMovieLoaded(Movie movie) {
                view.showMovies(movie);
            }

            @Override
            public void onError() {
                view.setError("No film found");
            }
        });

    }

    @Override
    public void loadMore() {
        repo.getNextPage(currentResponse.request(), new IMoviesRepo.LoadMoviesCallbackI() {
            @Override
            public void onMoviesLoaded(MovieRepoI.MovieList movies, Response response) {
                view.showMoreMovies(movies.getResults());
                currentResponse = response;
            }

            @Override
            public void onError() {
                view.setError("Can't load more item");
            }
        });

    }

    @Override
    public void searchByNameMovie(final String nameFilm) {
        repo.getMoviesByQuery(nameFilm, new IMoviesRepo.LoadMoviesCallbackI() {
            @Override
            public void onMoviesLoaded(MovieRepoI.MovieList movies, Response response) {
                view.showMovies(movies.getResults());
                currentResponse = response;
            }

            @Override
            public void onError() {
                view.setEmpty();
            }
        });
    }
}
