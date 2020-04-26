package com.example.COVID.Vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.COVID.Modele.Countries;
import com.example.COVID.Modele.CovidApi;
import com.example.COVID.Modele.GlobalStats;
import com.example.COVID.Controleur.ListAdapter;
import com.example.COVID.R;
import com.example.COVID.Modele.RestAllCountry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<Countries> countries = new ArrayList<Countries>();
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String BASE_URL = "https://api.covid19api.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeApiCall();


    }
    private void ShowList(List<Countries> listCountries)
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        mAdapter = new ListAdapter(listCountries);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CovidApi Covid = retrofit.create(CovidApi.class);

        Call<RestAllCountry> CovidCountry = Covid.getAllCountry();
        CovidCountry.enqueue(new Callback<RestAllCountry>() {
            @Override
            public void onResponse(Call<RestAllCountry> call, Response<RestAllCountry> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    GlobalStats globalStats = response.body().getGlobalStats();
                    Countries global = new Countries("Global Stats", globalStats.getNewConfirmed(), globalStats.getTotalConfirmed(),globalStats.getNewDeaths(),globalStats.getTotalDeaths(),globalStats.getNewRecovered(),globalStats.getTotalRecovered());
                    countries.add(global);
                    for (Countries country : response.body().getCountries())
                    {
                        countries.add(country);
                    }
                    Toast.makeText(getApplicationContext(),"Api OK", Toast.LENGTH_SHORT).show();
                    ShowList(countries);
                }
                else
                    {
                    showError();

                }
            }

            @Override
            public void onFailure(Call<RestAllCountry> call, Throwable t) {
                showError();
            }
        });

    }

    private void showError() {
        Toast.makeText(this,"Api Error", Toast.LENGTH_SHORT).show();
    }
}
