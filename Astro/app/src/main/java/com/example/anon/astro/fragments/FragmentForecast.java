package com.example.anon.astro.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anon.astro.R;

public class FragmentForecast extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initLayout(inflater, container);
        return view;
    }

    private void initLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        view = inflater.inflate(R.layout.forecast_layout, container, false);
    }
}
