package com.geektech.wheather.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geektech.wheather.R;
import com.geektech.wheather.data.internet.RetrofitBuilder;
import com.geektech.wheather.data.pojo.CurrentWeather;
import com.geektech.wheather.data.pojo.ForecastWeather;
import com.geektech.wheather.ui.base.BaseActivty;
import com.geektech.wheather.ui.onBoard.OnBoardActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.geektech.wheather.BuildConfig.APP_ID;


public class MainActivity extends BaseActivty {

    private TextView tvCurrentWeather, tvCityName, tvMax, tvMin, tvDesc, tvPressure,
            tvHumidity, tvSunrise, tvSunset, tvCloudiness, tvWind;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private ForecastAdapter adapter;

    private static final String FORMAT ="HH:mm";

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupRecyclerView();
        loadCurrentWeather();
        loadForecastWeather();

    }

    private void setupRecyclerView() {
        adapter = new ForecastAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void loadForecastWeather() {
        RetrofitBuilder.getService().getForecastWeather(
                "Bishkek",
                APP_ID,
                "metric")
                .enqueue(new Callback<ForecastWeather>() {
                    @Override
                    public void onResponse(Call<ForecastWeather> call, Response<ForecastWeather> response) {
                        if (response.isSuccessful() && response.body() != null){
                            ForecastWeather forecastWeather = response.body();
                            adapter.update(response.body().getList());
                            tvCurrentWeather.setText(getString(R.string.celcius, forecastWeather.getList().get(0).getMain().getTemp().toString()));
                            tvMax.setText(getString(R.string.celcius, forecastWeather.getList().get(0).getMain().getTempMax().toString()));
                            tvMin.setText(getString(R.string.celcius, forecastWeather.getList().get(0).getMain().getTempMin().toString()));
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastWeather> call, Throwable t) {
                        Log.d("sdasad", "aadadddsdssf");
                    }
                });
    }

    private void loadCurrentWeather() {
        RetrofitBuilder
                .getService()
                .getCurrentWeather("Bishkek", APP_ID, "metric")
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.isSuccessful() && response.body() != null){
                            CurrentWeather weather = response.body();
                            setupValues(weather);
                            Glide.with(getApplicationContext())
                                    .load("http://openweathermap.org/img/wn/" + weather.getWeather().get(0)
                                            .getIcon() + "@2x.png").into(imageView);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void setupValues(CurrentWeather weather) {
        tvCurrentWeather.setText(
                getString(R.string.celcius,
                        weather.getMain().getTemp().toString()));
        tvCityName.setText(getString(R.string.country, weather.getName(), weather.getSys().getCountry()));
        tvMax.setText(weather.getMain().getTempMax().toString());
        tvMin.setText(weather.getMain().getTempMin().toString());
        tvDesc.setText(weather.getWeather().get(0).getDescription());
        tvWind.setText(getString(R.string.speedForWind, weather.getWind().getSpeed().toString()));
        tvPressure.setText(getString(R.string.pressures, weather.getMain().getPressure().toString()));
        tvHumidity.setText(weather.getMain().getHumidity().toString() + "%");
        tvCloudiness.setText(weather.getClouds().getAll().toString() + "%");
        tvSunrise.setText(parse(weather.getSys().getSunrise()));
        tvSunset.setText(parse(weather.getSys().getSunset()));
    }

    private void setupViews() {
        tvCurrentWeather = findViewById(R.id.tvCurrentWeather);
        tvCityName = findViewById(R.id.tvCityName);
        recyclerView = findViewById(R.id.recyclerview);
        tvMax = findViewById(R.id.tvWeatherMax);
        tvMin = findViewById(R.id.tvWeatherMin);
        tvDesc = findViewById(R.id.tvDesc);
        tvWind = findViewById(R.id.tvWind);
        tvPressure = findViewById(R.id.tvPressure);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvCloudiness = findViewById(R.id.tvCloudiness);
        tvSunrise = findViewById(R.id.tvSunrise);
        tvSunset = findViewById(R.id.tvSunset);
        imageView = findViewById(R.id.imageview);
    }

    private static String parse(Integer time){
        DateFormat dateFormat = new SimpleDateFormat(FORMAT, Locale.getDefault());
        Date date = new Date();
        date.setTime((long)time * 1000);
        return dateFormat.format(date.getTime());
    }
}
