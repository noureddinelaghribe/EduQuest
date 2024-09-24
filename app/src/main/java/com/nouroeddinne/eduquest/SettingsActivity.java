package com.nouroeddinne.eduquest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Switch switch1;
    CheckBox boxAlways,boxSpicefic,boxAlwaysShow,boxSpiceficShow;
    ImageView mins1,mins2,plus1,plus2;
    TextView countShow,countSameShow,timeFrom,timeTo;
    LinearLayout linearTime,lineatCounrShow;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        switch1 = findViewById(R.id.switch1);
        mins1 = findViewById(R.id.ImageView);
        mins2 = findViewById(R.id.ImageView15);
        plus1 = findViewById(R.id.ImageView2);
        plus2 = findViewById(R.id.ImageView16);
        countShow = findViewById(R.id.textView13);
        countSameShow = findViewById(R.id.textView22);
        timeFrom = findViewById(R.id.textView16);
        timeTo = findViewById(R.id.textView18);
        boxAlways = findViewById(R.id.checkBox1);
        boxSpicefic = findViewById(R.id.checkBox2);
        boxAlwaysShow = findViewById(R.id.checkBox3);
        boxSpiceficShow = findViewById(R.id.checkBox4);
        linearTime = findViewById(R.id.linearTime);
        lineatCounrShow = findViewById(R.id.lineatCounrShow);

        sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        linearTime.setVisibility(View.GONE);
        lineatCounrShow.setVisibility(View.GONE);
        countShow.setText(String.valueOf(sharedPreferences.getInt("countShow",1)));
        countSameShow.setText(String.valueOf(sharedPreferences.getInt("countSameShow",1)));

        switch1.setChecked(sharedPreferences.getBoolean("state",true));
        boxAlwaysShow.setChecked(sharedPreferences.getBoolean("boxAlwaysShow",true));
        boxSpiceficShow.setChecked(sharedPreferences.getBoolean("boxSpiceficShow",false));
        boxAlways.setChecked(sharedPreferences.getBoolean("boxAlways",true));
        boxSpicefic.setChecked(sharedPreferences.getBoolean("boxSpicefic",false));

        if (sharedPreferences.getBoolean("boxSpiceficShow",false)){
            lineatCounrShow.setVisibility(View.VISIBLE);
        }

        if (sharedPreferences.getBoolean("boxSpicefic",false)){
            linearTime.setVisibility(View.VISIBLE);
        }

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("state",isChecked);
                editor.apply();
            }
        });

        plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countShow.setText(String.valueOf(Integer.valueOf(countShow.getText().toString())+1));
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("countShow",Integer.valueOf(countShow.getText().toString()));
                editor.apply();
            }
        });

        mins1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(countShow.getText().toString())>1){
                    countShow.setText(String.valueOf(Integer.valueOf(countShow.getText().toString())-1));
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("countShow",Integer.valueOf(countShow.getText().toString()));
                    editor.apply();
                }
            }
        });

        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countSameShow.setText(String.valueOf(Integer.valueOf(countSameShow.getText().toString())+1));
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("countSameShow",Integer.valueOf(countSameShow.getText().toString()));
                editor.apply();
            }
        });

        mins2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(countSameShow.getText().toString())>1){
                    countSameShow.setText(String.valueOf(Integer.valueOf(countSameShow.getText().toString())-1));
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("countSameShow",Integer.valueOf(countSameShow.getText().toString()));
                    editor.apply();
                }
            }
        });


        boxAlwaysShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boxSpiceficShow.setChecked(!isChecked);
                if (isChecked) {
                    lineatCounrShow.setVisibility(View.GONE);
                }else {
                    lineatCounrShow.setVisibility(View.VISIBLE);
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("boxAlwaysShow",isChecked);
                editor.putBoolean("boxSpiceficShow",!isChecked);
                editor.apply();
            }
        });


        boxSpiceficShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boxAlwaysShow.setChecked(!isChecked);
                if (isChecked) {
                    lineatCounrShow.setVisibility(View.VISIBLE);
                }else {
                    lineatCounrShow.setVisibility(View.GONE);
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("boxSpiceficShow",isChecked);
                editor.putBoolean("boxAlwaysShow",!isChecked);
                editor.apply();
            }
        });


        boxAlways.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boxSpicefic.setChecked(!isChecked);
                if (isChecked) {
                    linearTime.setVisibility(View.GONE);
                }else {
                    linearTime.setVisibility(View.VISIBLE);
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("boxAlways",isChecked);
                editor.putBoolean("boxSpicefic",!isChecked);
                editor.apply();
            }
        });


        boxSpicefic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boxAlways.setChecked(!isChecked);
                if (isChecked) {
                    linearTime.setVisibility(View.VISIBLE);
                }else {
                    linearTime.setVisibility(View.GONE);
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("boxSpicefic",isChecked);
                editor.putBoolean("boxAlways",!isChecked);
                editor.apply();
            }
        });
















    }
}