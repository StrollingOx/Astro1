package com.example.anon.astro.weather;

import android.util.Log;

public class CityWeatherAPIConnection {

    public String getTodayWeatherByCityName(String cityName) throws Exception {
        return sendRequest(Operation.USING_CITY_NAME, cityName);
    }

    public String getFiveDaysWeatherByCityName(String cityName) throws Exception {
        return sendRequest(Operation.GET_FORECAST, cityName);
    }

    private String sendRequest(Operation operation, String value) throws Exception{
        NetworkConnector networkConnector = new NetworkConnector();
        String response = networkConnector.execute(operation,value).get();
        switch(response){
            case "MalformedURLException":
                throw new Exception("MalformedURLException");
            case "IOException":
                throw new Exception("IOException");
            case "404-page-not-found":
                throw new Exception("404-page-not-found");

            default:
                Log.d("Success", "API responded");
                return response;
        }
    }


}
