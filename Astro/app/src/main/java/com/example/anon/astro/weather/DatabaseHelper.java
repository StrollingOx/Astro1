package com.example.anon.astro.weather;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AstroDatabase.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "cities";
    public static final String COLUMN_ID_NAME = "id";
    public static final String COLUMN_CITY_NAME_NAME = "city_name";
    public static final String COLUMN_JASON_NAME = "json";
    public static final String COLUMN_JASON_5DFORECAST_NAME= "json_5d";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME +
                " (" +
                COLUMN_ID_NAME                  + " INTEGER PRIMARY KEY," +
                COLUMN_CITY_NAME_NAME           + " TEXT," +
                COLUMN_JASON_NAME               + " TEXT," +
                COLUMN_JASON_5DFORECAST_NAME    + " TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
