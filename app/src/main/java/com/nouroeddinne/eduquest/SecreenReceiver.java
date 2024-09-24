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
//                if (sharedPreferences.getBoolean("boxAlwaysShow",true)) {
//
//
//                    dtf = DateTimeFormatter.ofPattern("yyyy/mm/dd");
//                    LocalDateTime now = LocalDateTime.now();
//                    if (dateNow != null){
//
//                        if (!dateNow.equals(dtf.format(now)) || sharedPreferences.getInt("countShow",1)>=countShowInDay){
//
//                        }
//
//                    }else {
//                        dateNow = dtf.format(now);
//
//                        showWindow(context);
//                        countShowInDay++;
//                    }
//
//
//
//                    if (sharedPreferences.getInt("countShow",1)>=countShowInDay) {
//                        showWindow(context);
//                        countShowInDay++;
//                    }
//
//
//
//
//
//                }else{
//
//
//
//
//                }





                showWindow(context);


























            }

































//
//            if (sharedPreferences.getBoolean("state",true)){
//                Log.d("SecreenReceiver", "state true");
//
//                postion = sharedPreferences.getInt("progress",1);
//
//                if (sharedPreferences.getBoolean("boxSpiceficShow",false)){
//                    Log.d("SecreenReceiver", " 3 sharedPreferences.getBoolean(\"boxSpiceficShow\",false)");
//
//                    dtf = DateTimeFormatter.ofPattern("yyyy/mm/dd"); //2024/11/21 = 1
//                    LocalDateTime now = LocalDateTime.now();
//                    if (date != null){
//                        Log.d("SecreenReceiver", " 1 Data retrieved: do it data not null");
//
//                        if (!date.equals(dtf.format(now)) || sharedPreferences.getInt("countShow",1)>countShowInDay){
//                            Log.d("SecreenReceiver", " 2 date.equals(dtf.format(now)) && sharedPreferences.getInt(countShow,1)>=countShowInDay ");
//                            showWindow(context);
//                            postion--;
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putInt("progress",postion);
//                            editor.apply();
//                            countShowSameWindow++;
//                            if(!(sharedPreferences.getInt("countSameShow",1)>=countShowSameWindow)){
//                                postion++;
//                                SharedPreferences.Editor editor2 = sharedPreferences.edit();
//                                editor2.putInt("progress",postion);
//                                editor2.apply();
//                                countShowSameWindow = 1;
//                            }
//                        }
//
//                    }else {
//                        date = dtf.format(now);
//                        Log.d("SecreenReceiver", " 5 Data retrieved: do it data null");
//
//                        showWindow(context);
//                        postion--;
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putInt("progress",postion);
//                        editor.apply();
//                        countShowSameWindow++;
//                        if(!(sharedPreferences.getInt("countSameShow",1)>=countShowSameWindow)){
//                            postion++;
//                            SharedPreferences.Editor editor2 = sharedPreferences.edit();
//                            editor2.putInt("progress",postion);
//                            editor2.apply();
//                            countShowSameWindow = 1;
//                        }
//
//                    }
//
//
//                }else {
//
//                    showWindow(context);
//
//                }
//
//
//
//











//
//                if(sharedPreferences.getInt("countShow",1)>=countShowInDay){
//
//                    countShowInDay++;
//                    Log.d("SecreenReceiver", "Data retrieved: do it countShow >= countShowInDay");
//
//                    if (sharedPreferences.getBoolean("boxSpiceficShow",false)){
//
//                        showWindow(context);
//                        postion--;
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putInt("progress",postion);
//                        editor.apply();
//                        countShowSameWindow++;
//                        if(!(sharedPreferences.getInt("countSameShow",1)>=countShowSameWindow)){
//                            postion++;
//                            SharedPreferences.Editor editor2 = sharedPreferences.edit();
//                            editor2.putInt("progress",postion);
//                            editor2.apply();
//                            countShowSameWindow = 1;
//                        }
//
//                    }else {
//                        showWindow(context);
//                    }
//
//                }else{
//                    countShowInDay = 1;
//                    Log.d("SecreenReceiver", "Data retrieved: do it countShowInDay = 1");
//                }





























//                Log.d("Data123", postion+" "+countShowSameWindow+"                          "+sharedPreferences.getInt("countSameShow",1));




//            }
        }

    }


    public static boolean isScreenOn() {
        return state;
    }
    public static Data data() {
        return d;
    }



    public void showWindow(Context context){

        state = sharedPreferences.getBoolean("state",true);
        if (state){

            length = sharedPreferences.getInt("length",1);
            if (postion<=length){
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
                d=null;
                Intent i = new Intent(context, FloatingWindowService.class);
                context.startService(i);
            }

        }

    }























}
