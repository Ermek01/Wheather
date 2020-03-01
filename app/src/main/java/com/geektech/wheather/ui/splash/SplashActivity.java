package com.geektech.wheather.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.geektech.wheather.R;
import com.geektech.wheather.data.PreferenceHelper;
import com.geektech.wheather.ui.base.BaseActivty;
import com.geektech.wheather.ui.main.MainActivity;
import com.geektech.wheather.ui.onBoard.OnBoardActivity;

public class SplashActivity extends BaseActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        chooseScreen();
    }

    private void chooseScreen(){
        if (PreferenceHelper.isShown()){
            startActivity(new Intent(this, MainActivity.class));
        }
        else {
            PreferenceHelper.setIsShow(true);
            startActivity(new Intent(this, OnBoardActivity.class));
        }
    }
}
