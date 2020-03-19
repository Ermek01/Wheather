package com.geektech.wheather.ui.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.geektech.wheather.R;
import com.geektech.wheather.data.PreferenceHelper;
import com.geektech.wheather.data.service.ServiceBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ServiceActivity extends AppCompatActivity {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        setupView();
        setupListeners();
    }

    private void setupListeners() {
        fab.setOnClickListener(v -> {
            if (PreferenceHelper.whetherPressed()){
                actionService(true);
                fab.setImageDrawable(getResources().getDrawable(R.drawable.icon_stop));
                PreferenceHelper.setIsPressed(false);
            }
            else {
                actionService(false);
                fab.setImageDrawable(getResources().getDrawable(R.drawable.icon_play));
                PreferenceHelper.setIsPressed(true);
            }
        });
    }

    private void setupView() {
        fab = findViewById(R.id.btn_fab);
    }

    private void actionService(boolean isActivated) {
        if (isActivated){
            Intent intent = new Intent(this, ServiceBuilder.class);
            intent.putExtra("isActivated", isActivated);
            startService(intent);
        }

    }
}
