package com.example.final_project;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.final_project.databinding.ActivityWeatherBinding;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class WeatherActivity extends AppCompatActivity implements WeatherDataListener {
    private EditText searchEditText;
    private TextView cityTextView;

    private TextView temperatureTextView;
    private TextView weatherConditionTextView;
    private ImageView weatherIconImageView;
    private WeatherAPI weatherAPI;
    private TextView searchResultsTextView;
    private RecyclerView weatherRecyclerView;
WeatherViewModel weatherViewModel;
    private List<WeatherData> weatherDataList = new ArrayList<>();
    private ActivityWeatherBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        WeatherDataAdapter adapter = new WeatherDataAdapter();
        weatherRecyclerView = findViewById(R.id.my_recyclerview);
        weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        weatherRecyclerView.setAdapter(adapter);

        WeatherViewModel weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
      weatherViewModel.selectedWeatherData.observe(this, (newMessageValue) -> {
//            String weatherData = newMessageValue;
            WeatherDetailsFragment chatFragment = new WeatherDetailsFragment(newMessageValue);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, chatFragment)
                    .addToBackStack("")
                    .commit();
                }
        );


      /*  public void setSelectedWeatherData(WeatherData weatherData)
        {
            selectedWeatherData.setValue(weatherData);
        }*/

  /*      weatherViewModel.selectedMessage.observe(this, (newMessageValue) -> {
            // Assuming newMessageValue is a JSON string representing WeatherData
            try {
                // Deserialize the JSON string into a WeatherData object
                Gson gson = new Gson();
                WeatherData weatherData = gson.fromJson(newMessageValue, WeatherData.class);

                // Pass the WeatherData object to the WeatherDetailsFragment constructor
                WeatherDetailsFragment chatFragment = new WeatherDetailsFragment(weatherData);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.detail_fragment, chatFragment)
                        .addToBackStack("")
                        .commit();
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        });*/





    /*    weatherViewModel.selectedMessage.observe(this, (newMessageValue) -> {
            // Assuming newMessageValue is a JSON string representing WeatherData
            try {
                // Parse the JSON string into a WeatherData object
                WeatherData weatherData = new WeatherData();
                JSONObject jsonObject = new JSONObject(newMessageValue);
                // Extract and set the necessary data from the JSON object
                weatherData.setTemperature(jsonObject.getDouble("temperature"));
//                weatherData.setHumidity(jsonObject.getDouble("humidity"));
                // Set other properties as needed
                // ...

                // Pass the WeatherData object to the WeatherDetailsFragment constructor
                WeatherDetailsFragment chatFragment = new WeatherDetailsFragment(weatherData);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.detail_fragment, chatFragment)
                        .addToBackStack("")
                        .commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });*/

                adapter.notifyDataSetChanged(); // Notify the adapter that data has changed
        weatherIconImageView = findViewById(R.id.weatherIconImageView);
        searchEditText = findViewById(R.id.search_edit_text);
        cityTextView = findViewById(R.id.city_text_view);
        temperatureTextView = findViewById(R.id.temperature_text_view);
        weatherConditionTextView = findViewById(R.id.weather_condition_text_view);
        searchResultsTextView = findViewById(R.id.search_results_text_view);






        weatherAPI = new WeatherAPI(this);

      searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String cityName = searchEditText.getText().toString();
                    weatherAPI.getWeatherDataForCity(cityName, (WeatherDataListener) WeatherActivity.this);
                    return true;
                }
                return false;
            }

        });


        WeatherData savedWeatherData = weatherAPI.getSavedWeatherData();
        if (savedWeatherData != null) {
            updateWeatherData(savedWeatherData);
        }
        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                String cityName = searchEditText.getText().toString();
                weatherAPI.getWeatherDataForCity(cityName, (WeatherDataListener) WeatherActivity.this);
            }
        });
        Button saveButton = findViewById(R.id.Savebutton);
        saveButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                String cityName = searchEditText.getText().toString();
                weatherAPI.getWeatherDataForCity(cityName, (WeatherDataListener) WeatherActivity.this);

            }
        });
       /* weatherRecyclerView.setAdapter(new WeatherDataAdapter());
        weatherRecyclerView.setAdapter(adapter);*/





    }

    @NonNull
    @Override
    public WeatherDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_weather, parent, false);
        return new WeatherDataViewHolder(itemView);
    }




   // @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onDataReceived(WeatherData weatherData) {
        weatherDataList.add(weatherData);
//        updateWeatherData(weatherData);
        weatherRecyclerView.getAdapter().notifyDataSetChanged();
        ((WeatherDataAdapter) weatherRecyclerView.getAdapter()).updateData(weatherDataList);
       updateWeatherData(weatherData);
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void updateWeatherData(WeatherData weatherData) {
        cityTextView.setText(weatherData.getCityName());
        temperatureTextView.setText(String.format(Locale.getDefault(), "%.1f °C", weatherData.getTemperature()));
        weatherConditionTextView.setText(weatherData.getWeatherCondition());

//        String searchResultsText = String.format(Locale.getDefault(), "Search results for \"%s\":\n%s", searchEditText.getText().toString(), weatherData.getWeatherCondition());
//        searchResultsTextView.setText(searchResultsText);

        // Display the weather icon
        String iconUrl = weatherData.getWeatherIconUrl();
        if (iconUrl != null && !iconUrl.isEmpty()) {
            Glide.with(this).load(iconUrl).into(weatherIconImageView);
        }
    }


    private void getWeatherDataFromApi(String cityName) {
        WeatherAPI weatherAPI = new WeatherAPI(this); // assuming "this" refers to the context of WeatherActivity
        weatherAPI.getWeatherDataForCity(cityName, this);
    }

        // ...






}