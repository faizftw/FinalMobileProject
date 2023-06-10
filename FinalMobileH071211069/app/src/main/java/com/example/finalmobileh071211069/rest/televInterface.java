package com.example.finalmobileh071211069.rest;

import com.example.finalmobileh071211069.televModel.televResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface televInterface {
    @GET("/3/tv/{category}")
    Call<televResponse> getTelev(
            @Path("category") String category,
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page
    );
}
