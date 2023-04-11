package com.example.final_project;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {WeatherData.class}, version = 1)
public abstract class WeatherDataDatabase extends RoomDatabase {
    public abstract WeatherDataDao weatherDataDao();

    // Singleton pattern to ensure only one instance of the database is created
    private static volatile WeatherDataDatabase INSTANCE;
    public static synchronized WeatherDataDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WeatherDataDatabase.class, "weather_data_db")
                    .build();
        }
        return INSTANCE;
    }
}
