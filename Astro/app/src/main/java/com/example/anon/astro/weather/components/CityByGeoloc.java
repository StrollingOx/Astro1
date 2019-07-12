package com.example.anon.astro.weather.components;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityByGeoloc{
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("cod")
    @Expose
    private String cod;

    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("list")
    @Expose
    private java.util.List<CityFound> list = null;

    public List<CityFound> getList() {
        return list;
    }
}
