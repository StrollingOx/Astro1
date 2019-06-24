package com.example.anon.astro;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.io.Serializable;

public class AstroCalc implements Serializable {
    AstroCalculator astroCalculator;

    public AstroCalc(AstroDateTime time, AstroCalculator.Location location) {
        astroCalculator = new AstroCalculator(time, location);
    }



    //SUN
    public AstroDateTime getSunriseTime(){
        return astroCalculator.getSunInfo().getSunrise();
    }
    public AstroDateTime getSunsetTime(){
        return astroCalculator.getSunInfo().getSunset();
    }
    public double getSunriseAzimuth(){
        return astroCalculator.getSunInfo().getAzimuthRise();
    }
    public double getSunsetAzimuth(){
        return astroCalculator.getSunInfo().getAzimuthSet();
    }
    public AstroDateTime getTwilightTime(){ return astroCalculator.getSunInfo().getTwilightEvening(); }
    public AstroDateTime getDuskTime(){ return astroCalculator.getSunInfo().getTwilightMorning(); }



    //MOON
    public AstroDateTime getMoonrise(){
        return astroCalculator.getMoonInfo().getMoonrise();
    }
    public AstroDateTime getMoonset(){
        return astroCalculator.getMoonInfo().getMoonset();
    }
    public double getAge(){ return astroCalculator.getMoonInfo().getAge(); }
    public double getIlumination(){
        return astroCalculator.getMoonInfo().getIllumination();
    }
    public AstroDateTime getNextNewMoon(){
        return astroCalculator.getMoonInfo().getNextNewMoon();
    }
    public AstroDateTime getNextFullMoon(){ return astroCalculator.getMoonInfo().getNextFullMoon(); }

    //LOCALE
    public void setLocation(AstroCalculator.Location location){
        astroCalculator.setLocation(location);
    }

    public void setDateTime(AstroDateTime astroDateTime){
        astroCalculator.setDateTime(astroDateTime);
    }
}
