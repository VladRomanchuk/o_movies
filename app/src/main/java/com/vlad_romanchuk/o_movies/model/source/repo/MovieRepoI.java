package com.vlad_romanchuk.o_movies.model.source.repo;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vlad_romanchuk.o_movies.model.Movie;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;

public class MovieRepoI extends BaseRepo implements IMoviesRepo {
    private static MovieRepoI instance;
    private GenresRepo genreRepo;
    private MovieService service;
    private static final String TAG = "MovieRepoI";



    public static MovieRepoI getInstance() {
        if (instance == null) {
            instance = new MovieRepoI();
        }
        return instance;
    }


    private MovieRepoI() {
        super();
        service = retrofit.create(MovieService.class);
        genreRepo = GenresRepo.getInstance();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getPopularMovies(final int page, @NonNull final LoadMoviesCallbackI callback) {
        new AsyncTask<Void, Void, Response<MovieList>>() {
            @Override
            protected Response<MovieList> doInBackground(Void... params) {
                Call<MovieList> call = service.getPopularMovies(page);

                try {
                    Response<MovieList> response = call.execute();
                    Log.d(TAG, "doInBackground: " + response.raw().toString());
                    return response;
                } catch (IOException e) {
                    callback.onError();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Response<MovieList> response) {
                if (response == null) {
                    callback.onError();
                    return;
                }
                callback.onMoviesLoaded(response.body(), response.raw());
            }
        }.execute();


    }

    @Override
    public void getMoviesByQuery(final String query, @NonNull final LoadMoviesCallbackI callback) {
        new AsyncTask<Void, Void, Response<MovieList>>() {
            @Override
            protected Response<MovieList> doInBackground(Void... params) {
                Call<MovieList> call = service.getMovies(query, 1);
                try {
                    Response<MovieList> response = call.execute();
                    return response;
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Response<MovieList> response) {
                if (response == null || response.body() == null || response.body().getResults() == null) {
                    callback.onError();
                } else {
                    callback.onMoviesLoaded(response.body(), response.raw());
                }
            }
        }.execute();
    }

    @Override
    public void getMovie(final String id, @NonNull final MovieCallbackI callback) {
        new AsyncTask<Void, Void, Movie>() {
            @Override
            protected Movie doInBackground(Void... params) {
                Call<Movie> call = service.getMovie(id);
                try {
                    Response<Movie> response = call.execute();
                    return response.body();
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Movie movie) {
                if (movie == null) callback.onError();
                else callback.onMovieLoaded(movie);
            }
        }.execute();
    }

    @Override
    public void getNextPage(final Request request, final LoadMoviesCallbackI callback) {
        HttpUrl.Builder builder = request.url().newBuilder();
        int nextPage = Integer.parseInt(request.url().queryParameter("page"));
        builder.setQueryParameter("page", ++nextPage + "");
        final Request newRequest = request.newBuilder().url(builder.build()).build();

        new AsyncTask<Void, Void, MovieList>() {
            okhttp3.Response response;

            @Override
            protected MovieList doInBackground(Void... params) {
                try {
                    response = client.newCall(newRequest).execute();
                    MovieList list = gson.fromJson(response.body().string(), MovieList.class);
                    return list;
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(MovieList movieList) {
                if (movieList == null) {
                    callback.onError();
                } else {
                    callback.onMoviesLoaded(movieList, response);
                }
            }
        }.execute();

    }

    public class MovieList {
        private Integer page;
        private List<Movie> results;

        public MovieList() {
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public List<Movie> getResults() {
            return results;
        }

        public void setResults(List<Movie> results) {
            this.results = results;
        }
    }


}
