package com.geektech.wheather.data.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.geektech.wheather.data.service.NotificationHelper;

public class ServiceBuilder extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction() == null){
            startForeground(1, NotificationHelper.createNotification(this).build());
        }
        else {
            stopSelf();
        }
        return START_STICKY;
    }
}
