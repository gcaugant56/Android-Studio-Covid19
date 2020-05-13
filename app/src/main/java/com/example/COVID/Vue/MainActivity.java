package com.example.COVID.Vue;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.COVID.Constants;
import com.example.COVID.Controleur.MainController;
import com.example.COVID.Modele.Countries;
import com.example.COVID.R;
import com.example.COVID.Singletons;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(this, Singletons.GetSharedPref(getApplicationContext()), Singletons.GetGson(),getApplicationContext());

        controller.onStart();

    }


    public void ShowList(List<Countries> listCountries)
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        mAdapter = new ListAdapter(listCountries);
        recyclerView.setAdapter(mAdapter);
    }
    public void showError() {
        Toast.makeText(this,"Api Error", Toast.LENGTH_SHORT).show();
    }


}

