package com.example.trialmvvm.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.trialmvvm.model.Cast;
import com.example.trialmvvm.model.CastResponse;
import com.example.trialmvvm.model.Genre;
import com.example.trialmvvm.model.GenreResponse;
import com.example.trialmvvm.model.Movie;
import com.example.trialmvvm.model.MovieResponse;
import com.example.trialmvvm.network.ApiEndpoints;
import com.example.trialmvvm.network.RetrofitService;
import com.example.trialmvvm.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieRepository {
    public static MovieRepository movieRepository;
    private RetrofitService service;
    private static final String TAG = "MovieRepository";

    private MovieRepository() {
        service = RetrofitService.getInstance();
    }

    public static MovieRepository getInstance() {
        if (movieRepository == null) {
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    public MutableLiveData<List<Movie>> getMovieCollection() {
        MutableLiveData<List<Movie>> listMovie = new MutableLiveData<>();

        service.getMovies().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listMovie.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });
        return listMovie;
    }


    public MutableLiveData<List<Genre>> getGenreCollection(int id) {
        MutableLiveData<List<Genre>> listGenres = new MutableLiveData<>();
        service.getGenres(Constants.Type.MOVIES,id).enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listGenres.postValue(response.body().getGenres());
                    }
                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                Log.e("Genre Error", t.getMessage());
            }
        });
        return listGenres;

    }

    public MutableLiveData<List<Cast>> getCastCollection(int id) {
        MutableLiveData<List<Cast>> listCasts = new MutableLiveData<>();
        service.getCasts(Constants.Type.MOVIES, id).enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getCasts().size());
                        listCasts.postValue(response.body().getCasts());
                    }
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listCasts;
    }


}
