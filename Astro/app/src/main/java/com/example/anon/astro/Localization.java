package com.example.anon.astro;

import com.astrocalculator.AstroCalculator;

public class Localization {

    public static AstroCalculator.Location location = new AstroCalculator.Location(51.852592,19.432328);
    private static int refreshTime=3;

    public static Double getLatitude(){
        return location.getLatitude();
    }

    public static Double getLongitude(){
        return location.getLongitude();
    }

    public static void setLatitude(Double value){
        location.setLatitude(value);
    }

    public static void setLongitude(Double value){
        location.setLongitude(value);
    }

    public static void setRefreshTime(int value){
        refreshTime = value;
    }

    public static int getRefreshTime(){
        return refreshTime;
    }
}
