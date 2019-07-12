package com.example.anon.astro.weather;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.anon.astro.exceptions.AlreadyInDatabaseException;

import java.util.LinkedList;
import java.util.List;

import static com.example.anon.astro.weather.DatabaseHelper.COLUMN_CITY_NAME_NAME;
import static com.example.anon.astro.weather.DatabaseHelper.COLUMN_JASON_5DFORECAST_NAME;
import static com.example.anon.astro.weather.DatabaseHelper.COLUMN_JASON_NAME;
import static com.example.anon.astro.weather.DatabaseHelper.TABLE_NAME;

public class CityWeatherDatabaseConnection {
    private DatabaseHelper db;

    public CityWeatherDatabaseConnection(Context context) {
        this.db = new DatabaseHelper(context);
    }

    public boolean addCity(String cityName, String json, String json5d) throws AlreadyInDatabaseException {
        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CITY_NAME_NAME, cityName);
        values.put(COLUMN_JASON_NAME, json);
        values.put(COLUMN_JASON_5DFORECAST_NAME, json5d);

        try {
            getWeatherForCity(cityName);
        }catch(Exception e){
            database.insert(TABLE_NAME, null, values);
            Log.d("Database", "Inserted: "+cityName);
            return true;
        }
        throw new AlreadyInDatabaseException();
    }

    public String getWeatherForCity(String cityName){
        SQLiteDatabase database = db.getReadableDatabase();

        String[]columns = {COLUMN_JASON_NAME};
        String selection = COLUMN_CITY_NAME_NAME + " = ?";
        String[] selectionArgs = {String.valueOf(cityName)};

        String sortOrder = COLUMN_CITY_NAME_NAME + " DESC";

        Cursor cursor = database.query(
                TABLE_NAME,       // FROM table
                columns,                        // SELECT columns (null for *)
                selection,                     // WHERE selection ...
                selectionArgs,                // ... = selectionArgs
                null,               // don't group
                null,               // don't filter
                sortOrder                 // self explanatory
        );

        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public String getForecastForCity(String cityName){
        SQLiteDatabase database = db.getReadableDatabase();

        String[]columns = {COLUMN_JASON_5DFORECAST_NAME};
        String selection = COLUMN_CITY_NAME_NAME + " = ?";
        String[] selectionArgs = {String.valueOf(cityName)};

        String sortOrder = COLUMN_CITY_NAME_NAME + " DESC";

        Cursor cursor = database.query(
                TABLE_NAME,       // FROM table
                columns,                        // SELECT columns (null for *)
                selection,                     // WHERE selection ...
                selectionArgs,                // ... = selectionArgs
                null,               // don't group
                null,               // don't filter
                sortOrder                 // self explanatory
        );

        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public boolean deleteCity(String cityName) {
        SQLiteDatabase database = db.getWritableDatabase();
        String selection = COLUMN_CITY_NAME_NAME + " LIKE ?";
        String[] selectionArgs = {cityName};
        return database.delete(TABLE_NAME, selection, selectionArgs) > 0;
    }

    public boolean updateWeather(String cityName, String json, String json2) {
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_JASON_NAME, json);
        values.put(COLUMN_JASON_5DFORECAST_NAME, json2);

        String selection = COLUMN_CITY_NAME_NAME + " LIKE ?";
        String[] selectionArgs = {cityName};

        int count = database.update(
                TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count > 0;
    }

    public List<String> getCities() {
        List<String> cities = new LinkedList<String>();
        String query = "SELECT " + COLUMN_CITY_NAME_NAME + " FROM " + TABLE_NAME;

        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        do {
            cities.add(cursor.getString(0));
        }
        while (cursor.moveToNext());
        return cities;
    }

}
