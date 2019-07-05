package com.example.anon.astro.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anon.astro.weather.CityWeather;
import com.example.anon.astro.Localization;
import com.example.anon.astro.R;

public class FragmentBasicData extends Fragment {
    private View view;

    private TextView tvPlace;
    private TextView tvLongitude;
    private TextView tvLatitude;
    private TextView tvTemperature;
    private TextView tvPressure;
    private TextView tvDescription;

    private static CityWeather cityWeather;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       initLayout(inflater, container);
       initWeather();
       return view;
    }
    private void initLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        view = inflater.inflate(R.layout.basic_data_layout, container, false);
        tvPlace = view.findViewById(R.id.BWI_place);
        tvLongitude = view.findViewById(R.id.BWI_longitude);
        tvLatitude = view.findViewById(R.id.BWI_latitude);
        tvTemperature = view.findViewById(R.id.BWI_temperature);
        tvPressure = view.findViewById(R.id.BWI_pressure);
        tvDescription = view.findViewById(R.id.BWI_description);
    }

    @SuppressLint("SetTextI18n")
    private void initWeather() {
        if(cityWeather!=null){
            tvPlace.setText(cityWeather.getName());
            tvLongitude.setText(cityWeather.getCoord().getLon().toString());
            tvLatitude.setText(cityWeather.getCoord().getLat().toString());
            tvTemperature.setText(cityWeather.getMain().getTemp().toString()+ " °K");
            tvPressure.setText(cityWeather.getMain().getPressure() + " hPa");
            tvDescription.setText(cityWeather.getWeather().get(0).getDescription());
        }else{
            System.out.println("(FragmentBasicData)CITYWEATHER == NULL");
        }
    }

    public static void setCityWeather(CityWeather cityWeather) {
        FragmentBasicData.cityWeather = cityWeather;
    }
}
