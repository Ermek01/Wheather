package com.geektech.wheather.ui.base;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

abstract public class BaseActivty extends AppCompatActivity {

    protected void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
