package com.vlad_romanchuk.o_movies.model.source.repo;

import android.util.Log;

import com.vlad_romanchuk.o_movies.model.Genre;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public class GenresRepo extends BaseRepo implements IGenreRepo {

    private static final String TAG = "GenresRepo";

    private static GenresRepo instance = null;
    private static List<Genre> genres;

    private GenreService service;

    private interface GenreService {
        @GET("genre/movie/list")
        Call<GenreList> loadGenres();
    }

    public static GenresRepo getInstance() {
        if (instance == null) {
            instance = new GenresRepo();
        }
        return instance;
    }

    private GenresRepo() {
        super();
        genres = new ArrayList<>();
        service = retrofit.create(GenreService.class);
    }

    @Override
    public List<Genre> getGenres() {
        Call<GenreList> request = service.loadGenres();
        try {
            Log.d(TAG, "getGenres: " + request.request().url());
            Response<GenreList> response = request.execute();
            Log.d(TAG, "doInBackground: " + response.raw().toString());
            genres = response.body().genres;
            return genres;
        } catch (Exception e) {
            Log.d(TAG, "getGenres: " + e);
            return null;
        }
    }

    public class GenreList {

        private List<Genre> genres;

        public GenreList() {
        }

        public List<Genre> getGenres() {
            return genres;
        }
        public void setGenres(List<Genre> genres) {
            this.genres = genres;
        }
    }


}
