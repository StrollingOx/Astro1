package com.example.anon.astro.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anon.astro.Localization;
import com.example.anon.astro.MainActivity;
import com.example.anon.astro.R;
import com.example.anon.astro.weather.CityWeatherController;
import com.example.anon.astro.weather.components.City;

public class DialogGeolocation extends AppCompatDialogFragment {
    private CityWeatherController cityWeatherController;
    private EditText mLongitude, mLatitude;
    private View view;
    private String valueLongitude, valueLatitude;
    private SharedPreferences preferences;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        initDialog();



        return buildDialog(builder);
    }

    private boolean isInputCorrect(String lon, String lat){
        if(lon.equals("") || lat.equals("")) {
            Toast.makeText(getActivity(), "Longitude or Latitude empty", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(Double.parseDouble(lon) < (-180.0)  ||
                Double.parseDouble(lon) > 180       ||
                Double.parseDouble(lat) < (-90)     ||
                Double.parseDouble(lat) > 90 ){
            Toast.makeText(getActivity(), "-90<=latitude<=90 and -180<=longitude<=180 only!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Dialog buildDialog(AlertDialog.Builder builder) {
        builder
                .setView(view)
                .setTitle("Change geolocation")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        valueLatitude = mLatitude.getText().toString();
                        valueLongitude = mLongitude.getText().toString();
                        if(isInputCorrect(valueLatitude, valueLongitude)){
                            Localization.setLatitude(Double.parseDouble(valueLatitude));
                            Localization.setLongitude(Double.parseDouble(valueLongitude));
                            Toast.makeText(getActivity(), "Geolocation changed successfully", Toast.LENGTH_SHORT).show();
                            function();
                        }else {Toast.makeText(getActivity(), "Geolocation did not change!", Toast.LENGTH_SHORT).show();}

                    }
                });
        return builder.create();
    }

    private void function() {
        String cityFound = cityWeatherController.getCityNameByGeolocation(Double.parseDouble(valueLatitude), Double.parseDouble(valueLongitude));
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        preferences.edit().putString("lastcity", cityFound).apply();
        cityWeatherController.addCityByName(cityFound);
    }

    private void initDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_geolocation,null);

        mLongitude = view.findViewById(R.id.dialog_geolocation_longitude);
        mLatitude = view.findViewById(R.id.dialog_geolocation_latitude);

        cityWeatherController = CityWeatherController.getInstance();
    }
}
