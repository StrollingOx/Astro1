package com.example.anon.astro.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anon.astro.R;
import com.example.anon.astro.weather.FiveDaysEntity;

import java.util.List;

public class FiveDaysAdapter extends RecyclerView.Adapter {

    private List<FiveDaysEntity> fiveDaysEntities;
    private SharedPreferences sharedPref;

    public FiveDaysAdapter(List<FiveDaysEntity> fiveDaysEntities, Context context) {
        this.fiveDaysEntities = fiveDaysEntities;
        this.sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView date;
        private TextView temp;
        private TextView press;
        private TextView desc;
        private TextView speed;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.ITEM_DATE);
            temp = (TextView) itemView.findViewById(R.id.item_temperature);
            press = (TextView) itemView.findViewById(R.id.item_pressure);
            desc = (TextView) itemView.findViewById(R.id.item_description);
            speed = (TextView) itemView.findViewById(R.id.item_speed);
            cardView = (CardView) itemView.findViewById(R.id.item_cardview);
        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).date.setText(fiveDaysEntities.get(position).getDate().split(" ")[0]);
        ((MyViewHolder) holder).temp.setText(convertUnit(fiveDaysEntities.get(position).getTemp().toString()));
        ((MyViewHolder) holder).press.setText(fiveDaysEntities.get(position).getPressure().toString());
        ((MyViewHolder) holder).desc.setText(fiveDaysEntities.get(position).getDescription());
        ((MyViewHolder) holder).speed.setText(fiveDaysEntities.get(position).getSpeed().toString()+ " km/h");

    }

    @Override
    public int getItemCount() {
        return fiveDaysEntities.size();
    }

    private String convertUnit(String temp){

        if(sharedPref.getString("units", "0").equals("0")){
            return Math.round(Double.parseDouble(temp)-273.15) + " °C";
        }
        return temp + " K";
    }

}
