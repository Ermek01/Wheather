package com.geektech.wheather.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.geektech.wheather.R;
import com.geektech.wheather.ui.base.BaseActivty;
import com.geektech.wheather.ui.onBoard.OnBoardActivity;

public class MainActivity extends BaseActivty {

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
