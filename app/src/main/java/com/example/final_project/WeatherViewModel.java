package com.example.final_project;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class WeatherViewModel extends ViewModel {
    public MutableLiveData<ArrayList<WeatherData>> weatherData = new MutableLiveData< >();
    public MutableLiveData<WeatherData> selectedWeatherData = new MutableLiveData< >();
}
