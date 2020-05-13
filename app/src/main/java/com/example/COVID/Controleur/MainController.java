package com.example.COVID.Controleur;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.COVID.Constants;
import com.example.COVID.Singletons;
import com.example.COVID.Modele.Countries;
import com.example.COVID.Modele.GlobalStats;
import com.example.COVID.Modele.RestAllCountry;
import com.example.COVID.Vue.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {
    private ArrayList<Countries> countries = new ArrayList<Countries>();
    private SharedPreferences sharedPreference;
    private Gson gson;
    private MainActivity view;
    private Context context;
    public MainController( MainActivity view, SharedPreferences sharedPreference, Gson gson, Context context) {
        this.sharedPreference = sharedPreference;
        this.gson = gson;
        this.view = view;
        this.context = context;
    }

    public void onStart()
    {

        List<Countries> countries = getDataFromCache();

        if(countries != null)
        {
            view.ShowList(countries);
        }
        else
        {
            makeApiCall();
        }
    }
    private void makeApiCall()
    {
        Call<RestAllCountry> CovidCountry = Singletons.GetApi().getAllCountry();

        CovidCountry.enqueue(new Callback<RestAllCountry>() {
            @Override
            public void onResponse(Call<RestAllCountry> call, Response<RestAllCountry> response) {
                String location = getlocation();
                boolean find = false;
                if(response.isSuccessful() && response.body() != null)
                {
                    GlobalStats globalStats = response.body().getGlobalStats();
                    Countries global = new Countries("Slug","Global Stats", globalStats.getNewConfirmed(), globalStats.getTotalConfirmed(),globalStats.getNewDeaths(),globalStats.getTotalDeaths(),globalStats.getNewRecovered(),globalStats.getTotalRecovered());
                    ArrayList<Countries> temp = new ArrayList<Countries>();
                    temp.add(global);
                    for (Countries country : response.body().getCountries()) {
                        if(location != null )
                        {
                            if(country.getCountry().contains(location) && find == false)
                            {
                                Countries loc = new Countries(country.getCountry(),"Your location",country.getNewConfirmed(),country.getTotalConfirmed(),country.getNewDeath(),country.getTotalDeath(),country.getNewRecovered(),country.getTotalRecovered());
                                countries.add(loc);
                                find = true;
                            }

                        }
                        temp.add(country);
                    }
                    for (Countries country : temp)
                    {
                        countries.add(country);
                    }

                    Toast.makeText(view.getApplicationContext(),"Api OK", Toast.LENGTH_SHORT).show();
                    saveList(countries);
                    view.ShowList(countries);
                }
                else
                {
                    view.showError();

                }
            }

            @Override
            public void onFailure(Call<RestAllCountry> call, Throwable t) {
                view.showError();
            }
        });

    }
    private List<Countries> getDataFromCache()
    {
        String json = sharedPreference.getString("COVID",null);
        if(json == null)
        {
            return null;
        }
        else
        {
            Type listCountries = new TypeToken<List<Countries>>(){}.getType();
            return gson.fromJson(json, listCountries);
        }

    }
    private void saveList(ArrayList<Countries> countries) {
        String jsonSaved = gson.toJson(countries);
        sharedPreference
                .edit()
                .putString(Constants.CovidKey,jsonSaved)
                .apply();
        Toast.makeText(view.getApplicationContext(),"Data saved",Toast.LENGTH_LONG).show();
    }


    private String getlocation() {


        if (ActivityCompat.checkSelfPermission(view, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(view,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    Constants.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            );

        }
        if (ActivityCompat.checkSelfPermission(view, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            String provider = locationManager.getBestProvider(new Criteria(), true);
            Location locations = locationManager.getLastKnownLocation(provider);
            if (locations != null) {
                double longitude = ((Location) locations).getLongitude();
                double latitude = locations.getLatitude();
                Geocoder geocoder = new Geocoder(view.getApplicationContext(), Locale.getDefault());
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


    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case Constants.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(view,
                            "Permission was granted, :)",
                            Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(view,
                            "Permission denied",
                            Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}
