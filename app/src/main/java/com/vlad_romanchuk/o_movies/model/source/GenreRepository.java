package com.vlad_romanchuk.o_movies.model.source;

import com.vlad_romanchuk.o_movies.model.Genre;

import java.util.List;

public interface GenreRepository {

    interface LoadGenreCallback extends BaseCallback{
        void onGenresLoaded(List<Genre> genres);
    }

    List<Genre> getGenres();

}
