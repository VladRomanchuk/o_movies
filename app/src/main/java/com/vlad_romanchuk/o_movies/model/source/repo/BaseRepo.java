package com.vlad_romanchuk.o_movies.model.source.repo;

import com.google.gson.Gson;
import com.vlad_romanchuk.o_movies.Omovies;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public abstract class BaseRepo {
    protected Gson gson;
    protected Retrofit retrofit;
    protected OkHttpClient client;

    protected BaseRepo() {
        gson = Omovies.getGson();
        retrofit = Omovies.getRetrofit();
        client = Omovies.getClient();
    }
}
