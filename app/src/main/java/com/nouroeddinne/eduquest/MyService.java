package com.nouroeddinne.eduquest;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    SecreenReceiver secreenReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
        startForeground(1, getNotification());

        secreenReceiver = new SecreenReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(secreenReceiver, intentFilter);
        

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(secreenReceiver);
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }



    private Notification getNotification() {
        return new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setContentTitle("My Foreground Service")
                .setContentText("Running...")
                .setSmallIcon(R.drawable.ic_launcher_background) // Replace with your notification icon
                .build();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    "CHANNEL_ID",
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }


}