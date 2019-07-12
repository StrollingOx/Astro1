package com.example.anon.astro.tools;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.anon.astro.R;
import com.example.anon.astro.weather.CityWeatherController;

@SuppressLint("ValidFragment")
public class InputDialogFragment extends DialogFragment{

    private EditText cityText;
    private CityWeatherController cityWeatherController;

    @SuppressLint("ValidFragment")
    public InputDialogFragment(CityWeatherController cityWeatherController) {
        this.cityWeatherController = cityWeatherController;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {




        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_box, null);
        cityText = (EditText) view.findViewById(R.id.userInputDialog);

        builder.setView(view)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        cityWeatherController.addCityByName(cityText.getText().toString().replace(" ","&"));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        InputDialogFragment.this.getDialog().cancel();
                    }
                });




        return builder.create();
    }



}