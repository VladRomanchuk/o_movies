package com.vlad_romanchuk.o_movies.model.source;

import android.support.annotation.NonNull;

import com.vlad_romanchuk.o_movies.model.Genre;
import com.vlad_romanchuk.o_movies.model.Movie;
import com.vlad_romanchuk.o_movies.model.source.repo.MovieRepo;

import okhttp3.Request;
import okhttp3.Response;

public interface MoviesRepo {

    public interface LoadMoviesCallback extends BaseCallback {
        void onMoviesLoaded(MovieRepo.MovieList movies, Response response);
    }

    public interface MovieCallback extends BaseCallback {
        void onMovieLoaded(Movie movie);
    }

    void getMovie(String id, @NonNull MovieCallback callback);

    void getNextPage(Request request, LoadMoviesCallback callback);

    void getPopularMovies(int page, @NonNull LoadMoviesCallback callback);

    void getMoviesByGenre(Genre genre, @NonNull LoadMoviesCallback callback);

    void getMoviesByQuery(String query, @NonNull LoadMoviesCallback callback);
}
