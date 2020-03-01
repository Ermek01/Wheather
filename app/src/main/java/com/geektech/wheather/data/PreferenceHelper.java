package com.geektech.wheather.data;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private static final String PREFERENCE_NAME = "weather";
    private static final String IS_SHOWN = "weather";
    private static SharedPreferences preferences;

    public PreferenceHelper(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isShown(){
        return preferences.getBoolean(IS_SHOWN, false);
    }

    public static void setIsShow(boolean isShown){
        preferences.edit().putBoolean(IS_SHOWN, isShown).apply();
    }
}
