package com.example.anon.astro.tools;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anon.astro.R;
import com.example.anon.astro.weather.CityWeatherController;

import java.util.List;

public class CitiesViewAdapter extends RecyclerView.Adapter<CitiesViewAdapter.ViewHolder> {

    private static List<String> listOfCities;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onDeleteClick(int position);
        void onSelectClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvCityName;
        ImageView ivDeleteCity;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener)
        {
            super(itemView);
            listOfCities = CityWeatherController.cities;
            tvCityName = itemView.findViewById(R.id.fc_item_name);
            ivDeleteCity = itemView.findViewById(R.id.fc_item_delete_city);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) //position must be valid
                        {
                            listener.onSelectClick(position);
                        }
                    }
                }
            });

            ivDeleteCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public CitiesViewAdapter(List<String> listOfcities) {
        this.listOfCities = listOfcities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_cities_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(view, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.tvCityName.setText(listOfCities.get(i));
    }

    @Override
    public int getItemCount() {
        return listOfCities.size();
    }
}
