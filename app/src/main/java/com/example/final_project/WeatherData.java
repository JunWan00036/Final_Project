package com.example.final_project;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weatherdata")
public class WeatherData {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate=true)
    private int id;
    @ColumnInfo(name="name")
    private String cityName;
    @ColumnInfo(name="temperature")
    private double temperature;
    @ColumnInfo(name="icon")
    private String weatherCondition;
    @ColumnInfo(name="description")
    private String weatherIconUrl;



    public WeatherData(String cityName, double temperature, String weatherCondition, String weatherIconUrl) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.weatherCondition = weatherCondition;
        this.weatherIconUrl = weatherIconUrl;
    }
  /*  public WeatherData(String cityName, double temperature, String weatherCondition) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.weatherCondition = weatherCondition;

    }*/
    public String getCityName() {
        return cityName;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }
    public void setWeatherIconUrl(String weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }
    public String getWeatherIconUrl() { return weatherIconUrl; }
}

