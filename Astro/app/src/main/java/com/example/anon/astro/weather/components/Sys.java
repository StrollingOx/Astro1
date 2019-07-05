package com.example.anon.astro.weather.components;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys  {

    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("sunrise")
    @Expose
    private Integer sunrise;
    @SerializedName("sunset")
    @Expose
    private Integer sunset;

}
