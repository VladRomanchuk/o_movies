package com.vlad_romanchuk.o_movies.model.source.repo;

import com.vlad_romanchuk.o_movies.model.Movie;
import com.vlad_romanchuk.o_movies.model.source.repo.MovieRepoI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {
    @GET("movie/popular")
    Call<MovieRepoI.MovieList> getPopularMovies(@Query("page") Integer page);

    @GET("search/movie")
    Call<MovieRepoI.MovieList> getMovies(@Query("query") String query, @Query("page") Integer page);

    @GET("movie/{movieId}")
    Call<Movie> getMovie(@Path("movieId") String movieId);

}
