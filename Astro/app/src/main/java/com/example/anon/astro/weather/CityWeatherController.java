package com.example.anon.astro.weather;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.anon.astro.exceptions.AlreadyInDatabaseException;
import com.example.anon.astro.tools.CitySerializer;

import java.util.List;

public class CityWeatherController {

    private Context context;
    private CityWeatherAPIConnection apiConnection;
    private CityWeatherDatabaseConnection databaseConnection;
    private List<String> cities;

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

    public void addCityByName(String cityName){
        String json = null;
        String json5d = null;
        try {
            json = apiConnection.getTodayWeatherByCityName(cityName);
            json5d = apiConnection.getFiveDaysWeatherByCityName(cityName);
            databaseConnection.addCity(CitySerializer.cityNameSerializer(json), json, json5d);
            updateCities();
            Log.d("CityWeatherController", "City added!");
            Toast.makeText(context, "City added successfully", Toast.LENGTH_SHORT).show();
            //TODO: City exists in database / City doesn't exist / Connection fail
        } catch (Exception e){
            Toast.makeText(context, "City was NOT added!", Toast.LENGTH_SHORT).show();
        } catch (AlreadyInDatabaseException e) {
            Log.d("Info", CitySerializer.cityNameSerializer(json) + "already in database");
        }
    }

    public List<String> getCitiesList() {
        updateCities();
        return cities;
    }

    public void deleteCity(String cityName){
        databaseConnection.deleteCity(cityName);
        updateCities();
    }

    public void setCities() {cities = databaseConnection.getCities();}

    public boolean updateData(){
        for(String cityName : cities){
            try{
                databaseConnection.updateWeather(cityName,apiConnection.getTodayWeatherByCityName(cityName), apiConnection.getFiveDaysWeatherByCityName(cityName));
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    private void updateCities(){
        cities.clear();
        cities.addAll(databaseConnection.getCities());
    }

}
