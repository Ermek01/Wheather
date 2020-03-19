package com.geektech.wheather.data.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.geektech.wheather.R;
import com.google.firebase.messaging.RemoteMessage;


public class NotificationHelper {

    private static String CHANNEL_ID = "Service";
    private static String ACTION_CLOSE = "ACTION_CLOSE";

    public static void showNotification(Context context, RemoteMessage remoteMessage){
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        NotificationCompat.Builder builder = createNotification(context);

        builder.setContentText(remoteMessage.getNotification().getBody());
        builder.setContentTitle(remoteMessage.getNotification().getTitle());

        managerCompat.notify(1, builder.build());
    }


    public static NotificationCompat.Builder createNotification(Context context){
        createNotificationChannel(context);

        Intent intent = new Intent(context, ServiceBuilder.class);
        intent.setAction(ACTION_CLOSE);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .addAction(R.mipmap.ic_launcher, "STOP", pendingIntent)
                .setContentTitle("re jk kj bjd b")
                .setContentText("bdnbkfosrprbr")
                .setSmallIcon(R.mipmap.ic_launcher);
        return builder;
    }


    private static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "rringrngr";
            String description = "rgnorngrg";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
