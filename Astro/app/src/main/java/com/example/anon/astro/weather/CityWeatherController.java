package com.example.anon.astro.weather;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.anon.astro.exceptions.AlreadyInDatabaseException;
import com.example.anon.astro.tools.CitySerializer;
import com.example.anon.astro.weather.components.FiveDayForecast;

import java.util.List;

public class CityWeatherController {

    private Context context;
    private CityWeatherAPIConnection apiConnection;
    private CityWeatherDatabaseConnection databaseConnection;
    public static List<String> cities;

    public CityWeatherController(Context context) {
        this.context = context;
        this.apiConnection = new CityWeatherAPIConnection();
        this.databaseConnection = new CityWeatherDatabaseConnection(context);
    }

    public CityWeather getCurrentCityWeather(String cityName){
        CityWeather cityWeather = null;
        try{
            cityWeather = CitySerializer.cityDeserializer(apiConnection.getTodayWeatherByCityName(cityName));
            Log.d("Info", "Loading data from API!");
            return cityWeather;
        }catch(Exception e){
            Log.d("Info", "Loading data from local database!");
            Toast.makeText(context, "Connection issue. Loading from database...", Toast.LENGTH_SHORT).show();
            return CitySerializer.cityDeserializer(databaseConnection.getWeatherForCity(cityName));
        }
    }

    public FiveDayForecast getCurrentCityForecast(String cityName){
        FiveDayForecast fiveDayForecast = null;
        try{
            fiveDayForecast = CitySerializer.cityFiveDaysDeserializer(apiConnection.getFiveDaysWeatherByCityName(cityName));
            Log.d("Info", "Loading data from API!");
            return fiveDayForecast;
        }catch(Exception e){
            Log.d("Info", "Loading data from local database!");
            Toast.makeText(context, "Connection issue. Loading from database...", Toast.LENGTH_SHORT).show();
            return CitySerializer.cityFiveDaysDeserializer(databaseConnection.getForecastForCity(cityName));
        }
    }

    public void addCityByName(String cityName){
        String json = null;
        String json5d = null;
        try {
            json = apiConnection.getTodayWeatherByCityName(cityName);
            json5d = apiConnection.getFiveDaysWeatherByCityName(cityName);
            databaseConnection.addCity(CitySerializer.cityNameSerializer(json), json, json5d);
            //updateCities();
            Log.d("CityWeatherController", "City added!");
            Toast.makeText(context, "City added successfully", Toast.LENGTH_SHORT).show();
            cities.add(cityName);
            //TODO: City doesn't exist / Connection fail
        } catch (Exception e){
            Toast.makeText(context, "City was NOT added!", Toast.LENGTH_SHORT).show();
        } catch (AlreadyInDatabaseException e) {
            Log.d("Info", CitySerializer.cityNameSerializer(json) + "already in database");
        }
    }
    ///////////////////////////
    ///////////////////////////

    public List<String> getCitiesList() {
        updateCityList();
        return cities;
    }

    public void deleteCity(String cityName) {
        databaseConnection.deleteCity(cityName);
        updateCityList();
    }

    public void setCityEntity(){
        cities = databaseConnection.getCities();
    }

    public boolean updateData() {
        String cityName = "";
        for (String s : cities) {
            try {
                databaseConnection.updateWeather(s, apiConnection.getTodayWeatherByCityName(s),apiConnection.getFiveDaysWeatherByCityName(s));
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    private void updateCityList() {
        cities.clear();
        cities.addAll(databaseConnection.getCities());
    }

}
