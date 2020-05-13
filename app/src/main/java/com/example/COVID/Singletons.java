package com.example.COVID;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.COVID.Modele.RestAllCountry;
import com.example.COVID.data.CovidApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {
    private static Gson gson;
    private static CovidApi api;
    private static SharedPreferences sharedPreferences;

    public static Gson GetGson()
    {
        if(gson == null)
        {
            gson =  new GsonBuilder()
                    .setLenient()
                    .create();
        }
            return gson;
    }

    public static CovidApi GetApi()
    {
        if(api == null)
        {
            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GetGson()))
                .build();
             api = retrofit.create(CovidApi.class);
        }

        return api;
    }
    public static SharedPreferences GetSharedPref(Context context)
    {
        if(sharedPreferences == null)
        {
            sharedPreferences = context.getSharedPreferences("COVID", Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }
}
