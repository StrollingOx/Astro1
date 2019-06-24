package com.example.anon.astro;

import com.astrocalculator.AstroDateTime;

public class AstroDate {
    public static AstroDateTime astroDateTime = new AstroDateTime(1212,12,12,12,12,12,2,false);

    public static void setYear(int value){
        astroDateTime.setYear(value);
    }

    public static void setMonth(int value){
        astroDateTime.setMonth(value);
    }

    public static void setDay(int value){
        astroDateTime.setDay(value);
    }

    public static void setHour(int value){
        astroDateTime.setHour(value);
    }

    public static void setMinute(int value){
        astroDateTime.setMinute(value);
    }

    public static void setSecond(int value){
        astroDateTime.setSecond(value);
    }

}
