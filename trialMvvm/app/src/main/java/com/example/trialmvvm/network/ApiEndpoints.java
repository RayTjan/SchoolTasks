package com.example.trialmvvm.network;

import com.example.trialmvvm.model.CastResponse;
import com.example.trialmvvm.model.GenreResponse;
import com.example.trialmvvm.model.MovieResponse;
import com.example.trialmvvm.model.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndpoints {
    @GET("discover/movie")
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<TvShowResponse> getTvShows(@Query("api_key") String apiKey);

    @GET("{type}/{id}")
    Call<GenreResponse> getGenres(@Path("type") String type, @Path("id") int id,@Query("api_key") String apiKey);

    @GET("{type}/{id}/credits")
    Call<CastResponse> getCasts(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKey);

//    @GET("movie/{movie_id}")
//    Call<MovieResponse> getDetailMovie(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

}
