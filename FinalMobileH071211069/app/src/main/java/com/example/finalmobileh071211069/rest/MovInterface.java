package com.example.finalmobileh071211069.rest;

import com.example.finalmobileh071211069.movieModel.MovieResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovInterface {
    @GET("/3/movie/{category}")
    Call<MovieResponse> getMovie(
            @Path("category") String category,
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page
    );

}
