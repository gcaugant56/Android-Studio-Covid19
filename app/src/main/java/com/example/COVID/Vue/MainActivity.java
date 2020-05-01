package com.example.COVID.Vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    private SharedPreferences sharedPreference;
    private Gson gson;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreference = getSharedPreferences("json-Covid-19", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();
        makeApiCall();
        List<Countries> countries = getDataFromCache();

        if (countries != null) {
            ShowList(countries);
        } else {
            makeApiCall();
        }
    }

    private List<Countries> getDataFromCache() {
        String json = sharedPreference.getString("json-Covid-19", null);
        if (json == null) {
            return null;
        } else {
            Type listCountries = new TypeToken<List<Countries>>() {
            }.getType();
            return gson.fromJson(json, listCountries);
        }

    }

    private void ShowList(List<Countries> listCountries) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new ListAdapter(listCountries);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CovidApi Covid = retrofit.create(CovidApi.class);

        Call<RestAllCountry> CovidCountry = Covid.getAllCountry();
        CovidCountry.enqueue(new Callback<RestAllCountry>() {
            @Override
            public void onResponse(Call<RestAllCountry> call, Response<RestAllCountry> response) {
                String location = getlocation();
                Countries loc = null;
                if (response.isSuccessful() && response.body() != null) {
                    GlobalStats globalStats = response.body().getGlobalStats();
                    Countries global = new Countries("Slug", "Global Stats", globalStats.getNewConfirmed(), globalStats.getTotalConfirmed(), globalStats.getNewDeaths(), globalStats.getTotalDeaths(), globalStats.getNewRecovered(), globalStats.getTotalRecovered());
                    ArrayList<Countries> temp = new ArrayList<Countries>();
                    temp.add(global);
                    for (Countries country : response.body().getCountries()) {
                        if(country.getCountry().contains(location))
                        {
                             loc = new Countries(country.getCountry(),"Your location",country.getNewConfirmed(),country.getTotalConfirmed(),country.getNewDeath(),country.getTotalDeath(),country.getNewRecovered(),country.getTotalRecovered());
                        }
                        temp.add(country);
                    }
                    countries.add(loc);
                    for (Countries country : temp)
                    {
                        countries.add(country);
                    }

                    Toast.makeText(getApplicationContext(), "Api OK", Toast.LENGTH_SHORT).show();
                    saveList(countries);
                    ShowList(countries);
                } else {
                    showError();

                }
            }

            @Override
            public void onFailure(Call<RestAllCountry> call, Throwable t) {
                showError();
            }
        });

    }

    private void saveList(ArrayList<Countries> countries) {
        String jsonSaved = gson.toJson(countries);
        sharedPreference
                .edit()
                .putString("json-Covid-19", jsonSaved)
                .apply();
        Toast.makeText(getApplicationContext(), "Données sauvegardées", Toast.LENGTH_LONG).show();
    }

    private void showError() {
        Toast.makeText(this, "Api Error", Toast.LENGTH_SHORT).show();
    }

    private String getlocation() {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
                    );

        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            String provider = locationManager.getBestProvider(new Criteria(), true);
            Location locations = locationManager.getLastKnownLocation(provider);
            List<String> providerList = locationManager.getAllProviders();
            if (locations != null && providerList != null  && providerList.size() > 0) {
                double longitude = ((Location) locations).getLongitude();
                double latitude = locations.getLatitude();
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> listAddresses = geocoder.getFromLocation(latitude, longitude, 1);
                    if (null != listAddresses && listAddresses.size() > 0) {
                        return listAddresses.get(0).getCountryName();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;

    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,
                            "Permission was granted, :)",
                            Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(MainActivity.this,
                            "Permission denied",
                            Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}
