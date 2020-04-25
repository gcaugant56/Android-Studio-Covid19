package com.example.COVID.Modele;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidApi {
    @GET("summary")
    Call<RestAllCountry> getAllCountry();
}
