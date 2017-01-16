package com.vlad_romanchuk.o_movies.model.source.repo;

import com.vlad_romanchuk.o_movies.model.Genre;

import java.util.List;

public interface IGenreRepo {

    interface LoadGenreCallbackI extends IBaseCallback {
        void onGenresLoaded(List<Genre> genres);
    }

    List<Genre> getGenres();

}
