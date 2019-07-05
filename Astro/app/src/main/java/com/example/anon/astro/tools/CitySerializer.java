package com.example.anon.astro.tools;

import com.example.anon.astro.weather.CityWeather;
import com.example.anon.astro.weather.components.CityId;
import com.example.anon.astro.weather.components.FiveDayForecast;
import com.google.gson.Gson;

public class CitySerializer {
    public static CityWeather cityDeserializer(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, CityWeather.class);
    }

    public static FiveDayForecast cityFiveDaysDeserializer(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, FiveDayForecast.class);
    }

    public static String cityNameSerializer(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, CityId.class).getName();
    }
}
