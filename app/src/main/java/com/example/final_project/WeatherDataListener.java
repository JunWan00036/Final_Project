package com.example.final_project;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

public interface WeatherDataListener {
    @NonNull
    WeatherDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    void onDataReceived(WeatherData weatherData);
    void onError(String errorMessage);
}
