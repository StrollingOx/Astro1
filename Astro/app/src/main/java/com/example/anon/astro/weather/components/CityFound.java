package com.example.anon.astro.weather.components;

import com.example.anon.astro.weather.Weather;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityFound {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("coord")
    @Expose
    private Coord coord;

    @SerializedName("main")
    @Expose
    private Main main;

    @SerializedName("dt")
    @Expose
    private Integer dt;

    @SerializedName("wind")
    @Expose
    private Wind wind;

    @SerializedName("sys")
    @Expose
    private Sys sys;

    @SerializedName("rain")
    @Expose
    private Rain rain;

    //TODO: SNOW CLASS
    @SerializedName("snow")
    @Expose
    private Rain snow;

    @SerializedName("clouds")
    @Expose
    private Clouds clouds;

    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;



    public String getName() {
        return name;
    }
}
