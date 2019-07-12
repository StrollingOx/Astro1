package com.example.anon.astro.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anon.astro.R;
import com.example.anon.astro.tools.FiveDaysAdapter;
import com.example.anon.astro.weather.FiveDaysEntity;
import com.example.anon.astro.weather.components.FiveDayForecast;

import java.util.ArrayList;
import java.util.List;

public class FragmentForecast extends Fragment {

    public static FiveDayForecast fiveDayForecast;
    private FiveDaysAdapter fiveDaysAdapter;
    private RecyclerView recyclerView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initLayout(inflater, container);

        fiveDaysAdapter = new FiveDaysAdapter(getFiveDaysList(), getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(fiveDaysAdapter);
        return view;
    }

    private void initLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        view = inflater.inflate(R.layout.forecast_layout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.forecast_RV);
    }

    public List<FiveDaysEntity> getFiveDaysList() {
        List<FiveDaysEntity> fiveDaysEntities = new ArrayList<>();
        String date = null, description = null;
        Double temp = null, pressure = null, speed = null;
        if(fiveDayForecast == null) return fiveDaysEntities;
        for (int i = 3; i < fiveDayForecast.getList().size(); i += 8) {
            date = fiveDayForecast.getList().get(i).getDtTxt();
            description = fiveDayForecast.getList().get(i).getWeather().get(0).getDescription();
            temp = fiveDayForecast.getList().get(i).getMain().getTemp();
            pressure = fiveDayForecast.getList().get(i).getMain().getPressure();
            speed = fiveDayForecast.getList().get(i).getWind().getSpeed();
            fiveDaysEntities.add(new FiveDaysEntity(date, temp, pressure, description, speed));
        }
        for(FiveDaysEntity e : fiveDaysEntities){
            System.out.println("- " + e.getDate() + " " + e.getDescription() + " " + e.getPressure() + e.getSpeed() + " " + e.getTemp());
        }
        return fiveDaysEntities;
    }

    public static void setFiveDayForecast(FiveDayForecast fiveDayForecast){
        FragmentForecast.fiveDayForecast = fiveDayForecast;
    }
}
