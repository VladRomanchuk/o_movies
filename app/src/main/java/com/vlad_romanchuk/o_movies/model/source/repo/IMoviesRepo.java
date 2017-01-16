package com.vlad_romanchuk.o_movies.model.source.repo;

import android.support.annotation.NonNull;

import com.vlad_romanchuk.o_movies.model.Movie;

import okhttp3.Request;
import okhttp3.Response;

public interface IMoviesRepo {

    public interface LoadMoviesCallbackI extends IBaseCallback {
        void onMoviesLoaded(MovieRepoI.MovieList movies, Response response);
    }

    public interface MovieCallbackI extends IBaseCallback {
        void onMovieLoaded(Movie movie);
    }

    void getMovie(String id, @NonNull MovieCallbackI callback);

    void getNextPage(Request request, LoadMoviesCallbackI callback);

    void getPopularMovies(int page, @NonNull LoadMoviesCallbackI callback);

    void getMoviesByQuery(String query, @NonNull LoadMoviesCallbackI callback);
}
