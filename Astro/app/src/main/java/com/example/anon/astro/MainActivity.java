package com.example.anon.astro;

import android.content.res.Configuration;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.anon.astro.fragments.FragmentBasicData;
import com.example.anon.astro.tools.PagerAdapter;
import com.example.anon.astro.fragments.DialogGeolocation;
import com.example.anon.astro.fragments.DialogRefreshTime;
import com.example.anon.astro.weather.CityWeather;
import com.example.anon.astro.weather.CityWeatherController;

public class MainActivity extends AppCompatActivity{

    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    private CityWeatherController cityWeatherController;
    private CityWeather cityWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Permission.verifyStoragePermissions(this);

        initWeatherController();
        setDefaultCityTEST();
        initToolbar();
        handleDeviceOrientation();
    }

    private void setDefaultCityTEST() {
        cityWeatherController.addCityByName("Zgierz");
        cityWeather = cityWeatherController.getCurrentCityWeather("Zgierz");
        FragmentBasicData.setCityWeather(cityWeather);
    }

    private void initWeatherController() {
        cityWeatherController = new CityWeatherController(this);
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
            default:
                return false;
        }
    }

    private void refreshWeatherData() {
        Toast.makeText(this,"NOT YET IMPLEMENTED", Toast.LENGTH_SHORT).show();
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
