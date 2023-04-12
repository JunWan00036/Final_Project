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

    public WeatherDetailsFragment(WeatherData m) {

        selected = m;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      super.onCreateView(inflater, container, savedInstanceState);
ArrayList<WeatherData> weatherDataList = new ArrayList<>();
    /*    FragmentLayoutBinding binding=FragmentLayoutBinding.inflate(inflater);


/*binding.cityTextView.setText(selected.getCityName());
        binding.weatherTextView.setText(String.format(Locale.getDefault(), "%.1f °C", selected.getTemperature()));
        binding.weatherConditionTextView.setText(selected.getWeatherCondition());*//*

        //binding.weatherIconImageView.setImageIcon(selected.getWeatherIconUrl());

//        return binding.getRoot();
*/
        // View view = inflater.inflate(R.layout.item_city_weather, container, false); // Replace with your fragment layout

        FragmentLayoutBinding binding= FragmentLayoutBinding.inflate(inflater);
        View view = binding.getRoot();
        // Get references to UI elements
//        weatherIconImageView = view.findViewById(R.id.weatherIconImageView);
//        cityTextView = view.findViewById(R.id.cityTextView);
//        weatherTextView = view.findViewById(R.id.weatherTextView);
//        forecastTextView = view.findViewById(R.id.weatherConditionTextView);
        binding.cityTextView.setText(selected.getCityName());
        binding.temperatureTextView.setText(String.format(Locale.getDefault(), "%.1f °C", selected.getTemperature()));
        binding.weatherConditionTextView.setText(selected.getWeatherCondition());
        //return view;
        return binding.getRoot();
        //binding.weatherIconImageView.setImageIcon(selected.getWeatherIconUrl());


        // Set data to UI elements based on selected item from RecyclerView
//        WeatherData cityWeatherData = getSelectedCityWeatherData(); // Replace with your logic to get selected item from RecyclerView
//        if (cityWeatherData != null) {
//            cityTextView.setText(cityWeatherData.getCityName());
//            weatherTextView.setText(String.format(Locale.getDefault(), "%.1f °C", cityWeatherData.getTemperature()));
//            forecastTextView.setText(cityWeatherData.getWeatherCondition());
        // weatherIconImageView.setImageIcon(cityWeatherData.getWeatherIconUrl());
    }

    // return view;




}