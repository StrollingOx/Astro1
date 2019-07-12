package com.example.anon.astro.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anon.astro.R;
import com.example.anon.astro.weather.CityWeatherController;

import java.util.List;

public class CitiesViewAdapter extends RecyclerView.Adapter {

    private List<String> list;
    private CityWeatherController cityWeatherController;
    private SharedPreferences sharedPref;
    private Context context;

    public CitiesViewAdapter(List<String> list, CityWeatherController cityWeatherController, Context context) {
        this.list = list;
        this.context=context;
        this.cityWeatherController = cityWeatherController;
        this.sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView cityName;
        private ImageView delete;
        private CardView cardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.fc_item_cardview);
            cityName = (TextView) itemView.findViewById(R.id.fc_item_name);
            delete = (ImageView) itemView.findViewById(R.id.fc_item_delete_city);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(list.size()!=1){
                        ifDeleteSelectedCitySelectFirst(position);
                        cityWeatherController.deleteCity(list.get(position));
                        notifyItemRemoved(position);
                    }
                    else {
                        Toast.makeText(context, "You can not delete the last city!", Toast.LENGTH_LONG).show();
                    }
                    notifyDataSetChanged();
                }
            });

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String tmp = list.get(position);
                    setSelectedCity(tmp);
                    notifyDataSetChanged();
                }
            });

        }

        private void ifDeleteSelectedCitySelectFirst(int position) {
            if(getSelectedCity().equals(list.get(position))){
                if(position==0){
                    setSelectedCity(list.get(1));
                }
                else {
                    setSelectedCity(list.get(0));
                }
            }
        }


    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_cities_item_layout, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).cityName.setText(list.get(position));
        if (list.get(position).equals(getSelectedCity()))
            ((MyViewHolder) holder).cardView.setCardBackgroundColor(Color.YELLOW);
        else
            ((MyViewHolder) holder).cardView.setCardBackgroundColor(Color.WHITE);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private String getSelectedCity(){
        return sharedPref.getString("lastcity","Zgierz");
    }

    private void setSelectedCity(String cityName){
        sharedPref.edit().putString("lastcity",cityName).apply();
    }



}