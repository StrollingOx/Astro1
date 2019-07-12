package com.example.anon.astro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.anon.astro.fragments.FragmentAdditionalData;
import com.example.anon.astro.fragments.FragmentBasicData;
import com.example.anon.astro.fragments.FragmentForecast;
import com.example.anon.astro.tools.PagerAdapter;
import com.example.anon.astro.fragments.DialogGeolocation;
import com.example.anon.astro.fragments.DialogRefreshTime;
import com.example.anon.astro.weather.CityWeather;
import com.example.anon.astro.weather.CityWeatherController;
import com.example.anon.astro.weather.components.FiveDayForecast;

public class MainActivity extends AppCompatActivity{
    //TODO: recycler view for cities
    //TODO: add new city
    //TODO: delete city
    //TODO: change units
    //TODO: forecast
    //TODO: tablet layout

    private int flag_connection = 0;
    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    private CityWeatherController cityWeatherController;
    private CityWeather cityWeather;
    private FiveDayForecast fiveDayForecast;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Permission.verifyStoragePermissions(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        initWeatherController();
        loadDefaultCity();

        //loadLastCity
        String city = preferences.getString("lastcity","Zgierz");
        cityWeather = cityWeatherController.getCurrentCityWeather(city);
        fiveDayForecast = cityWeatherController.getCurrentCityForecast(city);

        try {
            Localization.setLatitude(cityWeather.getCoord().getLat());
            Localization.setLongitude(cityWeather.getCoord().getLon());
        }catch(NullPointerException e)
        {
            Localization.setLatitude(0.0);
            Localization.setLongitude(0.0);
            flag_connection = 1;
        }

        FragmentBasicData.setCityWeather(cityWeather);
        FragmentAdditionalData.setCityWeather(cityWeather);
        FragmentForecast.setFiveDayForecast(fiveDayForecast);



        initToolbar();
        handleDeviceOrientation();
    }

    private void loadDefaultCity() {
        if (!preferences.contains("lastcity")) {
            preferences.edit().putString("lastcity","Zgierz").apply();
            cityWeatherController.addCityByName("Zgierz");
        }
    }

    private void initWeatherController() {
        cityWeatherController = CityWeatherController.getInstance(getBaseContext());
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void openGeolocationSetting(){
        DialogGeolocation dialog = new DialogGeolocation();
        dialog.show(getSupportFragmentManager(), "Geolocation dialog");
    }

    private void openRefreshTimeSetting(){
        DialogRefreshTime dialog = new DialogRefreshTime();
        dialog.show(getSupportFragmentManager(), "Resfresh Time dialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.setting_geolocation:
                openGeolocationSetting();
                return true;
            case R.id.setting_refresh_time:
                openRefreshTimeSetting();
                return true;
            case R.id.setting_refresh_weather:
                refreshWeatherData();
                return true;
            case R.id.setting_select_city:
                if(flag_connection != 1)
                    openSelectCity();
                else
                    Toast.makeText(this, "NO INTERNET CONNECTION", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.setting_change_unit:
                changeUnits();
                return true;
            default:
                return false;
        }
    }

    private void changeUnits() {
        if(preferences.getString("units", "0").equals("0")){
            preferences.edit().putString("units","1").apply();
        }else if(preferences.getString("units", "0").equals("1")){
            preferences.edit().putString("units","0").apply();
        }
}

    private void openSelectCity() {
        Intent myIntent = new Intent(this, SelectCityActivity.class);
        startActivity(myIntent);
    }

    private void refreshWeatherData() {
        cityWeatherController.updateData();

        String city = preferences.getString("lastcity","Zgierz");
        cityWeather = cityWeatherController.getCurrentCityWeather(city);
        fiveDayForecast = cityWeatherController.getCurrentCityForecast(city);

        Localization.setLatitude(cityWeather.getCoord().getLat());
        Localization.setLongitude(cityWeather.getCoord().getLon());

        FragmentBasicData.setCityWeather(cityWeather);
        FragmentAdditionalData.setCityWeather(cityWeather);
        FragmentForecast.setFiveDayForecast(fiveDayForecast);

        Toast.makeText(getBaseContext(), "The weather has been updated!", Toast.LENGTH_LONG).show();
        //Toast.makeText(this,"NOT YET IMPLEMENTED", Toast.LENGTH_SHORT).show();
    }


    private void handleDeviceOrientation() {
        int orientation = getResources().getConfiguration().orientation;

         if (isPhone()){
             mViewPager = (ViewPager)findViewById(R.id.pager);
             mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), orientation);
             mViewPager.setAdapter(mPagerAdapter);
         } else {
              // USE TABLET LAYOUT!
         }

    }

    private boolean isPhone() {
        return (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT
                && getResources().getConfiguration().screenWidthDp < 720)
                || (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
                && getResources().getConfiguration().screenHeightDp < 720);
    }

}
