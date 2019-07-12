package com.example.anon.astro.weather;

public class FiveDaysEntity {

    private String date;
    private Double temp;
    private Double pressure;
    private String description;
    private Double speed;

    public FiveDaysEntity(String date, Double temp, Double pressure, String description, Double speed) {
        this.date = date;
        this.temp = temp;
        this.pressure = pressure;
        this.description = description;
        this.speed = speed;
    }


    public String getDate() {
        return date;
    }

    public Double getTemp() {
        return temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public String getDescription() {
        return description;
    }

    public Double getSpeed() {
        return speed;
    }
}

