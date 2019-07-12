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

import com.example.anon.astro.R;
import com.example.anon.astro.weather.CityWeather;

public class FragmentAdditionalData extends Fragment {
    private View view;

    private TextView tvWindSpeed;
    private TextView tvWindDegree;
    private TextView tvCloudiness;
    private TextView tvHumidity;

    private static CityWeather cityWeather;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initLayout(inflater, container);
        initWeather();
        return view;
    }

    private void initLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        view = inflater.inflate(R.layout.additional_data_layout, container, false);
        tvWindSpeed = view.findViewById(R.id.AWI_speed);
        tvWindDegree = view.findViewById(R.id.AWI_degree);
        tvCloudiness = view.findViewById(R.id.AWI_cloudiness);
        tvHumidity = view.findViewById(R.id.AWI_humidity);
    }

    @SuppressLint("SetTextI18n")
    private void initWeather() {
        if(cityWeather!=null){
            tvWindSpeed.setText(cityWeather.getWind().getSpeed() + " km/h");
            if(cityWeather.getWind().getDeg() == null)
                tvWindDegree.setText("0°");
            else
                tvWindDegree.setText(cityWeather.getWind().getDeg() + "°");
            tvCloudiness.setText(cityWeather.getClouds().getAll() + "%");
            tvHumidity.setText(cityWeather.getMain().getHumidity()  + "%");
        }else{
            System.out.println("(FragmentAdditionalData)CITYWEATHER == NULL");
        }
    }

    public static void setCityWeather(CityWeather cityWeather) {
        FragmentAdditionalData.cityWeather = cityWeather;
    }
}
