package com.example.anon.astro;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.anon.astro.tools.CitiesViewAdapter;
import com.example.anon.astro.tools.LinearLayoutManagerWrapper;
import com.example.anon.astro.weather.CityWeatherController;

import java.util.List;

public class SelectCityActivity extends AppCompatActivity {
    //TODO: NOT FINISHED
    private CitiesViewAdapter citiesViewAdapter;
    private RecyclerView citiesRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button bAdd;
    private Button bRemoveAll;
    private List<String> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_cities_layout);
        cities = CityWeatherController.cities;
        initRecyclerView();
        initButtons();

        citiesViewAdapter.setOnItemClickListener(new CitiesViewAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deleteCity();
            }

            @Override
            public void onSelectClick(int position) {
                selectCity();
            }
        });
    }

    private void initButtons() {
        //TODO: NEED IMPLEMENTATION
    }

    private void selectCity() {
        //TODO: NEED IMPLEMENTATION
    }

    private void deleteCity() {
        //TODO: NEED IMPLEMENTATION
    }

    private void initRecyclerView() {
        citiesRecyclerView = findViewById(R.id.fc_recycler_view);
        layoutManager = new LinearLayoutManagerWrapper(this, LinearLayoutManager.VERTICAL, false);
        citiesViewAdapter = new CitiesViewAdapter(cities);
        citiesRecyclerView.setLayoutManager(layoutManager);
        citiesRecyclerView.setAdapter(citiesViewAdapter);

    }

}
