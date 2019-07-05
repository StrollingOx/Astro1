package com.example.anon.astro.weather.components;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityId {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
