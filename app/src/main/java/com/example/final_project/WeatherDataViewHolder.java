/*
package com.example.final_project;


import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//WeatherViewModel weatherViewModel;
    public TextView cityTextView;
    public TextView temperatureTextView;
    public TextView weatherConditionTextView;
    public ImageView weatherIconImageView;
    WeatherViewModel weatherViewModel;
     List<WeatherData> weatherDataList = new ArrayList<>();

    public WeatherDataViewHolder(@NonNull View itemView) {
        super(itemView);
//        weatherViewModel = new ViewModelProvider((this).get(WeatherViewModel.class);
        // Set click listener on itemView
        itemView.setOnClickListener(this);
        cityTextView = itemView.findViewById(R.id.cityTextView);
        temperatureTextView = itemView.findViewById(R.id.weatherTextView);
        weatherConditionTextView = itemView.findViewById(R.id.weatherConditionTextView);
        weatherIconImageView = itemView.findViewById(R.id.weatherIconImageView);
         itemView.setOnClickListener(clk -> {
            int position = getAbsoluteAdapterPosition();
            // find the selected chat message
//            WeatherData selected = weatherDataList.get(position);
             if (position >= 0 && position < weatherDataList.size()) {
                 // Check if position is within bounds of weatherDataList
                 WeatherData selected = weatherDataList.get(position);
                 clickListener.onItemClick(weatherDataList.get(position));
                 weatherViewModel.selectedWeatherData.postValue(selected);
             }
            // post that value to the selectedMessage variable
//            weatherViewModel.selectedWeatherData.postValue(selected);
        });
    }
    */
/*@Override
    public void onClick(View v) {
        // Get clicked item position
        int position = getAdapterPosition();

        // Check if double-clicked
        if (SystemClock.elapsedRealtime() - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
            if (position != RecyclerView.NO_POSITION && clickListener != null) {
                clickListener.onItemDoubleClick(weatherDataList.get(position));
            }
        }
        lastClickTime = SystemClock.elapsedRealtime();
    }*//*



}
*/


package com.example.final_project;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/*
public class WeatherDataViewHolder extends RecyclerView.ViewHolder  {
    public TextView cityTextView;
    public TextView temperatureTextView;
    public TextView weatherConditionTextView;
    public ImageView weatherIconImageView;
    WeatherViewModel weatherViewModel;
    List<WeatherData> weatherDataList = new ArrayList<>();
    // Set click listener for RecyclerView items

    private static final long DOUBLE_CLICK_TIME_DELTA = 300; // Define double-click time interval
    private long lastClickTime = 0; // Track last click time
    private AdapterView.OnItemClickListener clickListener; // Add this line

    public WeatherDataViewHolder(@NonNull View itemView) {
        super(itemView);
        this.clickListener = clickListener;
        this.weatherViewModel = new ViewModelProvider((ViewModelStoreOwner) itemView.getContext()).get(WeatherViewModel.class);
        // Set click listener on itemView
        itemView.setOnClickListener(this);
        cityTextView = itemView.findViewById(R.id.cityTextView);
        temperatureTextView = itemView.findViewById(R.id.weatherTextView);
        weatherConditionTextView = itemView.findViewById(R.id.weatherConditionTextView);
        weatherIconImageView = itemView.findViewById(R.id.weatherIconImageView);
    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Get clicked item position
        position = getAbsoluteAdapterPosition();

        // Check if double-clicked
        if (SystemClock.elapsedRealtime() - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
            if (position != RecyclerView.NO_POSITION && clickListener != null) {
//                clickListener.onItemClick(weatherDataList.get());
                WeatherData selected = weatherDataList.get(position);

                clickListener.onItemClick(parent, view, position, id);
                weatherViewModel.selectedWeatherData.postValue(selected);
            }
        }
        lastClickTime = SystemClock.elapsedRealtime();
    }
}
*/
public class WeatherDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView cityTextView;
    public TextView temperatureTextView;
    public TextView weatherConditionTextView;
    public ImageView weatherIconImageView;
    WeatherViewModel weatherViewModel;
    List<WeatherData> weatherDataList = new ArrayList<>();
    // Set click listener for RecyclerView items
    private AdapterView.OnItemClickListener itemClickListener;
    private static final long DOUBLE_CLICK_TIME_DELTA = 300; // Define double-click time interval
    private long lastClickTime = 0; // Track last click time
    private AdapterView.OnItemClickListener clickListener; // Add this line
  /*  public WeatherDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_weather, parent, false);
        return new WeatherDataViewHolder(itemView); // Pass clickListener to the constructor
    }*/

    public WeatherDataViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item click event here
                if (itemClickListener != null) {
                    int position = getAbsoluteAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        itemClickListener.onItemClick(null, itemView, position, getItemId());
                    }
                }
            }
        });
        // this.clickListener = clickListener;

        //this.weatherViewModel = new ViewModelProvider((ViewModelStoreOwner) itemView.getContext()).get(WeatherViewModel.class);
        // Set click listener on itemView
        itemView.setOnClickListener(this);
        cityTextView = itemView.findViewById(R.id.city_text_view);
        temperatureTextView = itemView.findViewById(R.id.temperature_text_view);
        weatherConditionTextView = itemView.findViewById(R.id.weather_condition_text_view);
        weatherIconImageView = itemView.findViewById(R.id.weatherIconImageView);
    }

    @Override
    public void onClick(View v) {
        // Get clicked item position
        int position = getAbsoluteAdapterPosition();

        // Check if double-clicked
        if (SystemClock.elapsedRealtime() - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
            if (position != RecyclerView.NO_POSITION && clickListener != null) {
                WeatherData selected = weatherDataList.get(position);
                itemClickListener.onItemClick(null, itemView, position, getItemId());
                clickListener.onItemClick(null, v, position, 0); // Pass null for parent and 0 for id
                weatherViewModel.selectedWeatherData.postValue(selected);
            }
        }
        lastClickTime = SystemClock.elapsedRealtime();
    }

    void bind(WeatherData weatherData) {
        // Bind data to the views in the ViewHolder
        cityTextView.setText(weatherData.getCityName());
        temperatureTextView.setText(Double.toString(weatherData.getTemperature()));
    }

    /*    @Override
        public WeatherDataViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_weather, parent, false);
            WeatherDataViewHolder viewHolder = new WeatherDataViewHolder(itemView);
            viewHolder.setItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    // Handle item click event here
                }
            });
            return viewHolder;
        }*/

    public WeatherDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_weather, parent, false);
        return new WeatherDataViewHolder(itemView);
    }

    public void setItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}