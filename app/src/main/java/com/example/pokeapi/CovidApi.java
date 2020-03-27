package com.example.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidApi {
    @GET("summary")
    Call<RestAllCountry> getAllCountry();
}
