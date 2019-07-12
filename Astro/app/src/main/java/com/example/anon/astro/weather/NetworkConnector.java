package com.example.anon.astro.weather;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.anon.astro.weather.Operation.USING_CITY_NAME;
import static com.example.anon.astro.weather.Operation.USING_COORDINATES;

public class NetworkConnector extends AsyncTask<Object,Void,String> {

    private static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String API_URL2 = "https://api.openweathermap.org/data/2.5/forecast";
    private static final String API_URL3 = "https://api.openweathermap.org/data/2.5/find";
    private static final String API_KEY = "0fac6356477877ad06c3e7c7f8c192ed";

    @Override
    protected String doInBackground(Object... objects) {
        StringBuilder response = new StringBuilder();
        HttpURLConnection connection = null;
        int responseCode = 0;
        try {
            URL url = getUrl((Operation) objects[0], (String) objects[1]);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User_Agent", USER_AGENT);
            responseCode = connection.getResponseCode();
        } catch (MalformedURLException e) {
            return "MalformedURLException";
        } catch (IOException e) {
            return "IOException";
        }
        if(responseCode == 404){ return "404-page-not-found"; }

        Log.d("NetworkConnector", "URL:"+connection.getURL().toString());
        Log.d("NetworkConnector", "Response Code:"+responseCode);

        BufferedReader in = null;
        try{
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null) response.append(inputLine);
            in.close();
        } catch (IOException e) {
            return "IOException";
        }
        return response.toString();
    }

    private URL getUrl(Operation operation, String value) throws MalformedURLException {
        final String CITY = "?q=";
        final String APPID = "&appid=";
        final String LATITUDE = "?lat=";
        final String LONGITUDE = "&lon=";
        final String CNT = "&cnt=1";

        if(operation == USING_CITY_NAME){
            return new URL(API_URL + CITY + value + APPID + API_KEY);
        }else if(operation == USING_COORDINATES){
            System.out.println("STRING: " + value);
            String[] coordinates = value.split(";");
            return new URL(API_URL3 + LATITUDE + coordinates[0] + LONGITUDE + coordinates[1] + CNT + APPID + API_KEY);
        }
        return new URL(API_URL2 + CITY + value + APPID + API_KEY);
    }
}
