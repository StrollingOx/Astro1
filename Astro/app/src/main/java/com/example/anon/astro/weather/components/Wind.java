package com.example.anon.astro.weather.components;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {@SerializedName("temp")
@Expose
private Double temp;
    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private int deg;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }
}
