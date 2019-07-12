package com.example.anon.astro;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.anon.astro.tools.CitiesViewAdapter;
import com.example.anon.astro.tools.InputDialogFragment;
import com.example.anon.astro.weather.CityWeatherController;

import java.util.List;

public class SelectCityActivity extends AppCompatActivity {

    private CitiesViewAdapter citiesViewAdapter;
    private CityWeatherController cityWeatherController;
    private List<String> cityWeatherList;

    private DialogFragment dialogFragment;
    private Button bAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_cities_layout);
        bAdd = (Button) findViewById(R.id.fc_button_add);

        cityWeatherController = CityWeatherController.getInstance();
        cityWeatherController.setCityEntity();
        cityWeatherList = cityWeatherController.getCitiesList();
        dialogFragment =  new InputDialogFragment(cityWeatherController);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCity(v);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.fc_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        citiesViewAdapter = new CitiesViewAdapter(cityWeatherList,cityWeatherController,getBaseContext());
        recyclerView.setAdapter(citiesViewAdapter);
        citiesViewAdapter.notifyDataSetChanged();

    }

    public void addCity(View view){
        dialogFragment.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

}
