package com.example.anon.astro.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anon.astro.AstroCalc;
import com.example.anon.astro.AstroDate;
import com.example.anon.astro.Localization;
import com.example.anon.astro.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FragmentMoon extends Fragment {

    private View view;
    private AstroCalc astroCalc;
    private TextView currentTime, moonrise, moonset;
    private TextView longitude, latitude;
    private TextView moonPhase, moonSMD;
    private TextView newMoon, fullMoon;

    final Handler clockHandler = new Handler();
    final Handler refreshInfoHandler = new Handler();
    final Handler onDataChangeHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initLayout(inflater, container);
        initAstroData();
        runClockThread();
        runOnDataChangeThread();
        runRefreshThread();


        return view;
    }

    @SuppressLint("SetTextI18n")
    private void getCurrentDate() {
        AstroDate.setYear(Integer.parseInt(new SimpleDateFormat("yyyy", Locale.US).format(new Date())));
        AstroDate.setMonth(Integer.parseInt(new SimpleDateFormat("MM", Locale.US).format(new Date())));
        AstroDate.setDay(Integer.parseInt(new SimpleDateFormat("dd", Locale.US).format(new Date())));
        AstroDate.setHour(Integer.parseInt(new SimpleDateFormat("HH", Locale.US).format(new Date())));
        AstroDate.setMinute(Integer.parseInt(new SimpleDateFormat("mm", Locale.US).format(new Date())));
        AstroDate.setSecond(Integer.parseInt(new SimpleDateFormat("ss", Locale.US).format(new Date())));
    }

    private void runOnDataChangeThread() {
        final int period = 100;
        final Runnable GPS = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {

                if(!latitude.getText().equals(Localization.getLatitude().toString())
                        || !longitude.getText().equals(Localization.getLatitude().toString())) {
                    updateLocationOnScreen();
                    astroCalc.setLocation(Localization.location);
                    getCurrentDate();
                    astroCalc.setDateTime(AstroDate.astroDateTime);
                    updateScreen();
                }

                onDataChangeHandler.postDelayed(this, period);
            }
        };
        onDataChangeHandler.postDelayed(GPS, period);
    }

    private void runRefreshThread() {
        final Runnable refresh = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                refreshInfoHandler.postDelayed(this, 60000*Localization.getRefreshTime());
                updateLocationOnScreen();
                astroCalc.setLocation(Localization.location);
                getCurrentDate();
                astroCalc.setDateTime(AstroDate.astroDateTime);
                updateScreen();


            }
        };
        refreshInfoHandler.postDelayed(refresh, 10);
    }

    private void runClockThread() {
        currentTime.setText(new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date()));
        final Runnable clock = new Runnable() {
            @Override
            public void run() {
                currentTime.setText(new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date()));
                clockHandler.postDelayed(this, 1000);
            }
        };
        clockHandler.postDelayed(clock, 1000);

    }

    @SuppressLint("SetTextI18n")
    private void updateLocationOnScreen() {
        latitude.setText(Localization.getLatitude().toString());
        longitude.setText(Localization.getLongitude().toString());
    }

    private void initAstroData() {
        astroCalc = new AstroCalc(AstroDate.astroDateTime, Localization.location);
        updateLocationOnScreen();
    }

    private void initLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        view = inflater.inflate(R.layout.moon_layout, container, false);
        currentTime = view.findViewById(R.id.moon_time);
        moonrise = view.findViewById(R.id.moon_moonrise_time);
        moonset = view.findViewById(R.id.moon_moonset_time);
        longitude = view.findViewById(R.id.moon_longitude);
        latitude = view.findViewById(R.id.moon_latitude);
        moonPhase = view.findViewById(R.id.moon_phase);
        moonSMD = view.findViewById(R.id.moon_smd);
        newMoon = view.findViewById(R.id.moon_new_moon_date);
        fullMoon = view.findViewById(R.id.moon_full_moon_date);

    }


    @SuppressLint("SetTextI18n")
    private void updateScreen(){
        moonrise.setText(astroCalc.getMoonrise().toString().substring(0,19));
        moonset.setText(astroCalc.getMoonset().toString().substring(0,19));
        moonPhase.setText(Double.toString(astroCalc.getIlumination()).substring(0,12));
        moonSMD.setText(Double.toString(astroCalc.getAge()).substring(0,12)); //NOT WORKING CORRECTLY
        newMoon.setText(astroCalc.getNextNewMoon().toString().substring(0,10));
        fullMoon.setText(astroCalc.getNextFullMoon().toString().substring(0,10));


    }
}
