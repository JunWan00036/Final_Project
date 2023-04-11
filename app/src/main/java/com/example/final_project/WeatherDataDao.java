package com.example.final_project;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WeatherDataDao {
    @Insert
    void insert(WeatherData weatherData);

    @Update
    void update(WeatherData weatherData);

    @Delete
    void delete(WeatherData weatherData);

    @Query("SELECT * FROM weatherdata")
    List<WeatherData> getAllWeatherData();
}
