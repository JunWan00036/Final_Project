package com.example.final_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.final_project.WeatherData;
import com.example.final_project.databinding.FragmentLayoutBinding;

import java.util.ArrayList;
import java.util.Locale;

public class WeatherDetailsFragment extends Fragment {
    public TextView cityTextView;
    public TextView forecastTextView;
    public TextView weatherTextView;
    public ImageView weatherIconImageView;
    WeatherData selected;
    public WeatherDetailsFragment() {


    }
    public WeatherDetailsFragment(WeatherData m) {

        selected = m;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        FragmentLayoutBinding binding;
        binding = FragmentLayoutBinding.inflate(inflater, container, false);

        ArrayList<WeatherData> weatherDataList = new ArrayList<>();
        // Get references to UI elements using the binding object
        weatherIconImageView = binding.weatherIconImageView;
        cityTextView = binding.cityName;
        weatherTextView = binding.temperatureView;
        forecastTextView = binding.weatherConditionTextView;

        // Set data to UI elements based on selected item from RecyclerView
        if (selected != null) {
            cityTextView.setText(selected.getCityName());
            weatherTextView.setText(String.format(Locale.getDefault(), "%.1f °C", selected.getTemperature()));
            forecastTextView.setText(selected.getWeatherCondition());
            // Set weather icon using selected.getWeatherIconUrl()
        }

        return binding.getRoot();

    }
}