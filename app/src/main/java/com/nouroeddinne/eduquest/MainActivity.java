package com.nouroeddinne.eduquest;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import Model.Data;
import MyAdapter.Adapter;

public class MainActivity extends AppCompatActivity {

    LinearLayout add,edit;
    Button reset;
    TextView textViewProgress,textViewTotal;
    public static boolean startFloatingWindowService = false;
    private static final int OVERLAY_PERMISSION_REQ_CODE = 1234;
    SharedPreferences sharedPreferences;
    int length;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        add =findViewById(R.id.linearAdd);
        edit =findViewById(R.id.linearEdit);
        reset =findViewById(R.id.button2);
        textViewProgress =findViewById(R.id.textView23);
        textViewTotal =findViewById(R.id.textView25);

        checkPermissionAndStartService();

        sharedPreferences = getApplication().getSharedPreferences("Data", Context.MODE_PRIVATE);

        textViewProgress.setText(String.valueOf(sharedPreferences.getInt("progress",1)-1));

        Intent service = new Intent(this, MyService.class);
        startForegroundService(service);



        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                length = myViewModel.getCount();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("length",length);
                editor.apply();
                textViewTotal.setText(String.valueOf(length));

            }
        });
        thread.start();


//        myViewModel.getNoteById(1).observeForever(new Observer<Data>() {
//            @Override
//            public void onChanged(Data data) {
//                Toast.makeText(MainActivity.this, ""+data.getNote().length(), Toast.LENGTH_SHORT).show();
//            }
//        });


//
//
//        myViewModel.getAllNotesByName().observe(MainActivity.this,new Observer<List<Data>>() {
//            @Override
//            public void onChanged(List<Data> data) {
//                for (Data d : data){
//                    Log.d("TAG", "onChanged: "+d.getId()+" "+d.getNote()+" "+d.getReplay());
//                }
//            }
//        });







        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddandEditActivity.class);
                startActivity(intent);
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DataActivity.class);
                startActivity(intent);
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("progress",1);
                editor.apply();

                textViewProgress.setText("0");

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu1) {
            Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }


    private void checkPermissionAndStartService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {
                startFloatingWindowService = true;
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
        } else {
            startFloatingWindowService = true;
        }
    }






























}