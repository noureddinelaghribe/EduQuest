package com.nouroeddinne.eduquest;

import static com.nouroeddinne.eduquest.SecreenReceiver.d;
import static com.nouroeddinne.eduquest.SecreenReceiver.postion;
import static com.nouroeddinne.eduquest.SecreenReceiver.countShowInDay;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import Model.Data;

public class FloatingWindowService extends Service {

    private WindowManager windowManager;
    private View floatingView;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        sharedPreferences = getApplication().getSharedPreferences("Data", Context.MODE_PRIVATE);
        createFloatingView();
    }

    private void createFloatingView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        floatingView = inflater.inflate(R.layout.window, null);

        // Set up the layout parameters
        final WindowManager.LayoutParams params;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);
        } else {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);
        }

        // Specify the position of the floating window
        params.gravity = Gravity.TOP ;
        params.x = 0;
        params.y = 100;

        // Add the view to the window
        windowManager.addView(floatingView, params);

        // Set up the close button
        ImageView closeButton = floatingView.findViewById(R.id.imageView3);
        ImageView AgainButton2 = floatingView.findViewById(R.id.imageView4);
        ImageView imageData = floatingView.findViewById(R.id.imageView5);
        TextView text = floatingView.findViewById(R.id.textView19);

        if (SecreenReceiver.data()!=null){
            text.setText(d.getNote());
        }else{
            text.setText("\uD83C\uDF89 Congratulations on completing your learning journey! Celebrate this achievement and look forward to new challenges!");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("state",false);
            editor.apply();
        }

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countShowInDay++;
                postion++;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("progress",postion);
                editor.apply();
                stopSelf();
            }
        });

        AgainButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSelf();
            }
        });


    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (floatingView != null) windowManager.removeView(floatingView);
    }
}
