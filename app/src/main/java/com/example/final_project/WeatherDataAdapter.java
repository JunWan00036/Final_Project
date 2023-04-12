package com.example.final_project;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WeatherDataAdapter extends RecyclerView.Adapter<WeatherDataViewHolder> {



    public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    private List<WeatherData> weatherDataList = new ArrayList<>();

    private AdapterView.OnItemClickListener itemClickListener; // Declare itemClickListener
  @NonNull
    @Override
    public WeatherDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_weather, parent, false);
        WeatherDataViewHolder viewHolder = new WeatherDataViewHolder(itemView);
        viewHolder.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Handle item click event here
            }
        });
        return new WeatherDataViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull WeatherDataViewHolder holder, int position) {
        WeatherData weatherData = weatherDataList.get(position);

        if (holder.cityTextView != null) {
            holder.cityTextView.setText(weatherData.getCityName());
        }

        if (holder.temperatureTextView != null) {
            holder.temperatureTextView.setText(String.format(Locale.getDefault(), "%.1f °C", weatherData.getTemperature()));
        }

        if (holder.weatherConditionTextView != null) {
            holder.weatherConditionTextView.setText(weatherData.getWeatherCondition());
        }


        String iconUrl = weatherData.getWeatherIconUrl();
        if (iconUrl != null && !iconUrl.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(iconUrl)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(holder.weatherIconImageView);
        }
/*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherDetailsFragment miscellaneousfragment = new WeatherDetailsFragment();
                FragmentManager fragmentManager =currentfragment.getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment, miscellaneousfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
*/

    }

    @Override
    public int getItemCount() {
        return weatherDataList.size();
    }

    public void setWeatherDataList(List<WeatherData> weatherDataList) {
        this.weatherDataList = weatherDataList;
        notifyDataSetChanged();
    }
    public void updateData(List<WeatherData> newData) {
        weatherDataList.clear();
        weatherDataList.addAll(newData);
        notifyDataSetChanged(); // Call notifyDataSetChanged() after updating the data
    }


}
