package com.geektech.wheather;

import android.app.Application;

import com.geektech.wheather.data.PreferenceHelper;

public class WeatherApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new PreferenceHelper(this);
    }
}
