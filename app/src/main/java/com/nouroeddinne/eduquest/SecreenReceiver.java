package com.nouroeddinne.eduquest;


import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Database.Reposetry;
import Model.Data;

public class SecreenReceiver extends BroadcastReceiver{
    private static boolean state ;
    private static final String CHANNEL_ID = "ScreenNotificationChannel";
    public static int postion ;

    public static int length ;
    static Data d;
    SharedPreferences sharedPreferences;
    DateTimeFormatter dtf;

    public static int countShowInDay = 1;
    int countShowSameWindow = 1;

    String dateNow = null;
    String dateYesterDay = null;

    String TimeStart = null;
    String TimeEnd = null;

    public SecreenReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {

            sharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
            if (sharedPreferences.getBoolean("state",true)){

                postion = sharedPreferences.getInt("progress",1);
                showWindow(context);

            }

        }

    }



    public static Data data() {
        return d;
    }



    public void showWindow(Context context){

        state = sharedPreferences.getBoolean("state",true);
        if (state){

            length = sharedPreferences.getInt("length",1);
            if (postion<=length){
                Log.d("SecreenReceiver", "Data retrieved: postion <= length "+length);
                Reposetry repository = new Reposetry((Application) context.getApplicationContext());
                repository.getNoteById(postion).observeForever(new Observer<Data>() {
                    @Override
                    public void onChanged(Data data) {
                        if (data != null) {
                            Log.d("SecreenReceiver", "Data retrieved: "+data.getId()+data.getNote());
                            d=data;
                            Intent i = new Intent(context, FloatingWindowService.class);
                            context.startService(i);
                        } else {
                            Log.d("SecreenReceiver", "No data found for ID: " + postion);
                            postion++;
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("progress",postion);
                            editor.apply();

                        }
                    }
                });
            }else {

                if (length>0){
                    Log.d("SecreenReceiver", "Data retrieved: postion > length "+length);
                    d=null;
                    Intent i = new Intent(context, FloatingWindowService.class);
                    context.startService(i);
                }else {
                    Log.d("SecreenReceiver", "Data retrieved: postion < length "+length);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("state",false);
                    editor.apply();
                }



            }

        }

    }























}
