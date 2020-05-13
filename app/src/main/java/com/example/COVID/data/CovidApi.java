package com.example.COVID.data;

import com.example.COVID.Modele.RestAllCountry;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidApi {
    @GET("summary")
    Call<RestAllCountry> getAllCountry();
}
