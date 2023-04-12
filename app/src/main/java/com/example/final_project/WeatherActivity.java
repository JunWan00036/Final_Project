package com.example.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.final_project.databinding.ActivityWeatherBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;
import com.example.final_project.databinding.ActivityWeatherBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class WeatherActivity extends AppCompatActivity  {
    private int position = 0;
    ActivityWeatherBinding  binding;
    private Executor thread = Executors.newSingleThreadExecutor();
    private RecyclerView.Adapter<WeatherDataViewHolder> myAdapter;
    private WeatherDataDao  mDAO;
    ArrayList<WeatherData> messages;
    WeatherViewModel weatherViewModel;


    ArrayList<WeatherDataDatabase> infos;
    // Override onCreateOptionsMenu to inflate the menu layout
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }
    // Override onOptionsItemSelected to handle menu item clicks
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.item_1:
                if (messages.size() > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(WeatherActivity.this);
                    builder
                            //.setMessage("Do you want to Delete this message : " + tv_message.getText().toString()).
                            .setTitle("Are you sure to delete the message?")
                            .setNegativeButton("no", (dialog, cl) -> {
                            })
                            .setPositiveButton("yes", (dialog, cl) -> {
                                WeatherData removedMessage = messages.get(position);
                                thread.execute(() -> {
                                    mDAO.delete(removedMessage);
                                });
                                runOnUiThread(() -> {
                                    messages.remove(position);
                                    myAdapter.notifyItemRemoved(position);
                                });
                                Snackbar.make(binding.getRoot(), "You deleted message #" , Snackbar.LENGTH_LONG)
                                        .setAction("Undo", c -> {
                                            thread.execute(() -> {
                                                mDAO.insert(removedMessage);
                                            });
                                            runOnUiThread(() -> {
                                                messages.add(position, removedMessage);
                                                myAdapter.notifyItemInserted(position);
                                            });
                                        }).show();
                                onBackPressed();
                            }).create().show();
                }
                break;
            case R.id.item_2:
//                if (item != null) {
//                    Toast.makeText(this, "message #" + tv_message.getText(), Toast.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(), "Version 1.0, created by Jun", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Version 1.0, created by Jun", Toast.LENGTH_LONG).show();

                return true;
//            break;

//                }
//                break;
            default:
                return false;
        }
        return true;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
binding.myrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(binding.toolbar);
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        if (messages == null) {
            weatherViewModel.weatherData.setValue(messages = new ArrayList<WeatherData>());
            Executor thread = Executors.newSingleThreadExecutor();
            WeatherDataDatabase db = Room.databaseBuilder(getApplicationContext(), WeatherDataDatabase.class, "database-name").build();
            mDAO = db.weatherDataDao();
            messages = weatherViewModel.weatherData.getValue();
            setContentView(binding.getRoot());//You can then load the RecyclerView
            thread.execute(() ->
            {
                messages.addAll(mDAO.getAllWeatherData()); //Once you get the data from database

                runOnUiThread(() -> binding.myrecyclerview.setAdapter(myAdapter)); //You can then load the RecyclerView
            });
        }
            weatherViewModel.selectedWeatherData.observe(this, (newValue) -> {
                WeatherDetailsFragment chatFragment = new WeatherDetailsFragment(newValue);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, chatFragment).addToBackStack("").commit();




                binding.myrecyclerview.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
                    @NonNull
                    @Override
                    public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        //if(messages.get(messages.size()-1).isSentButton()){
                        int ViewType;


                            //always inflates receive_message.xml
                        messages receiveMessagesBinding = ReceiveMessagesBinding.inflate(getLayoutInflater(),
                                    parent, false);
                            View root = receiveMessagesBinding.getRoot();
                            return new MyRowHolder(root);


                    }














class MyRowHolder extends RecyclerView.ViewHolder {

    public TextView messageText;
    public TextView timeText;




    public MyRowHolder(@NonNull View itemView) { //itewView will be the root of the layout, here constraintLayout was chosen;
        super(itemView);
        itemView.setOnClickListener(clk -> {

            int position = getAbsoluteAdapterPosition();
            int selectedIndex = position;
            WeatherDataDatabase selected = infos.get(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(WeatherActivity.this);
            builder.setTitle(getString(R.string.weather_view_title) + " " + selected+ " " + selected );
            builder.setMessage(new WeatherRoot(selected.getWeatherInfo()).toString());
            builder.setPositiveButton(R.string.close_button, (dialog, cl)->{

            });
            //builder.setNegativeButton("No", (dialog, cl)->{});
            builder.create().show();
            ChatMessage selected = messages.get(position);
               /* if (chatModel != null) {
                    chatModel.selectedMessage.postValue(selected);
                }*/
            chatModel.selectedMessage.postValue(selected);






        });
        messageText = itemView.findViewById(R.id.message);
        timeText = itemView.findViewById(R.id.time);

    }




}


    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Handle screen rotation here
        // You can get the new orientation from newConfig.orientation
    }






    //
//    }
    /*private EditText searchEditText;
    private TextView cityTextView;
    private WeatherViewModel weatherModel;
    private TextView temperatureTextView;
    private TextView weatherConditionTextView;
    private ImageView weatherIconImageView;
    private WeatherAPI weatherAPI;
    private TextView searchResultsTextView;

    private WeatherDetailsFragment weatherFragment;
    private RecyclerView weatherRecyclerView;
WeatherViewModel weatherViewModel;
WeatherData weatherData;
    private List<WeatherData> weatherDataList = new ArrayList<>();
    private ActivityWeatherBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
//        setSupportActionBar(binding.toolbar);
        WeatherDataAdapter adapter = new WeatherDataAdapter(weatherDataList);
//        adapter.setWeatherDataList(weatherDataList);
// or
//        adapter.updateData(weatherDataList);
//        adapter.notifyDataSetChanged();
        weatherRecyclerView = findViewById(R.id.my_recyclerview);
        weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        weatherRecyclerView.setAdapter(adapter);



        binding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        if (messages == null) {
            weatherViewModel.weatherData.setValue(messages = new ArrayList<WeatherData>());
            Executor thread = Executors.newSingleThreadExecutor();
            WeatherDataDatabase db = Room.databaseBuilder(getApplicationContext(), WeatherDataDatabase.class, "database-name").build();
            mDAO = db.weatherDataDao();
            messages = weatherViewModel.weatherData.getValue();
            setContentView(binding.getRoot());//You can then load the RecyclerView
            *//*loads buttons / text on screen *//*
            thread.execute(() ->
            {
                // mDAO = db.cmDAO();
                messages.addAll(mDAO.getAllWeatherData()); //Once you get the data from database

                runOnUiThread(() -> binding.myRecyclerview.setAdapter(myAdapter));

                *//*if(messages.size()-1>0) {
                    binding.theRecycleView.smoothScrollToPosition(messages.size() - 1);
                }*//*
            });
        }






//*****************************************************************************************
        *//*chatModel.selectedMessage.observe(this, (newMessageValue) -> {
            Log.i("tag", "onCreate: "+newMessageValue.getMessage());
            MessageDetailsFragment chatFragment = new MessageDetailsFragment(newMessageValue);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLocation ,chatFragment).addToBackStack("").commit();
        });*//*

        weatherViewModel.selectedWeatherData.observe(this, (newValue) -> {
            WeatherDetailsFragment chatFragment = new WeatherDetailsFragment(newValue);
            //chatFragment.getMessage(newValue);
                    *//*getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentLocation, chatFragment)
                            .commit();*//*
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, chatFragment).addToBackStack("").commit();



















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




                adapter.notifyDataSetChanged(); // Notify the adapter that data has changed
        weatherIconImageView = findViewById(R.id.weatherIconImageView);
        searchEditText = findViewById(R.id.search_edit_text);
        cityTextView = findViewById(R.id.city_text_view);
        temperatureTextView = findViewById(R.id.temperature_text_view);
        weatherConditionTextView = findViewById(R.id.weather_condition_text_view);
        searchResultsTextView = findViewById(R.id.search_results_text_view);




        WeatherDetailsFragment weatherFragment = new WeatherDetailsFragment(weatherData);
        weatherModel = new ViewModelProvider(this).get(WeatherViewModel.class);
      *//*  weatherModel.selectedWeatherData.observe(this, (newWeatherItemValue) -> {
            Log.i("tag", "onCreate: " + newWeatherItemValue.getName());
            weatherFragment = new WeatherDetailsFragment(newWeatherItemValue);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, weatherFragment).addToBackStack("").commit();
        });*//*




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
        });*/
       /* weatherRecyclerView.setAdapter(new WeatherDataAdapter());
        weatherRecyclerView.setAdapter(adapter);*/






/*
    @NonNull
    @Override
    public WeatherDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_weather, parent, false);
        return new WeatherDataViewHolder(itemView);
    }*/




 /*  // @SuppressLint("NotifyDataSetChanged")
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















*/




}

 public MyRowHolder(@NonNull View itemView) { //itewView will be the root of the layout, here constraintLayout was chosen;
                super(itemView);
                itemView.setOnClickListener(clk -> {


                    int position = getAbsoluteAdapterPosition();
                    weatherViewModel selected = weatherData.get(position);

                    weatherViewModel.selectedWeatherData.postValue(selected);

                });
                messageText = itemView.findViewById(R.id.cityName);
                timeText = itemView.findViewById(R.id.temperatureView);

            }
        }