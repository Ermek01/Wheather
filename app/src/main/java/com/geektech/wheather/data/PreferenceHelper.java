package com.geektech.wheather.data;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private static final String PREFERENCE_NAME = "weather";
    private static final String IS_SHOWN = "weather";
    private static SharedPreferences preferences;
    private static final String IS_PRESSED = "isPressed";

    public PreferenceHelper(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isShown(){
        return preferences.getBoolean(IS_SHOWN, false);
    }

    public static void setIsShow(boolean isShown){
        preferences.edit().putBoolean(IS_SHOWN, isShown).apply();
    }

    public static boolean whetherPressed() {
        return preferences.getBoolean(IS_PRESSED, false);
    }

    public static void setIsPressed(boolean isPressed){
        preferences.edit().putBoolean(IS_PRESSED, isPressed).apply();
    }
}
