package com.nouroeddinne.eduquest;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import Model.Data;
import MyAdapter.Adapter;

public class DataActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    MyViewModel myViewModel;
    RecyclerView.Adapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerview = findViewById(R.id.recyclerview);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        myViewModel.getAllNotesByName().observe(DataActivity.this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> notes) {

//                for (Data d : notes){
//                    Log.d("TAG", "onChanged: "+d.getId()+" "+d.getNote());
//                }

                adapter = new Adapter(DataActivity.this,notes);
                recyclerview.setAdapter(adapter);
            }
        });



        myViewModel.getNoteById(1).observeForever(new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                if (data!=null){
                    Log.d("TAG1234", "onChanged: "+data.getId()+" "+data.getNote());
                }
            }
        });

        myViewModel.getNoteById(2).observeForever(new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                if (data!=null){
                    Log.d("TAG1234", "onChanged: "+data.getId()+" "+data.getNote());
                }
            }
        });

        myViewModel.getNoteById(3).observeForever(new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                if (data!=null){
                    Log.d("TAG1234", "onChanged: "+data.getId()+" "+data.getNote());
                }
            }
        });

        myViewModel.getNoteById(4).observeForever(new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                if (data!=null){
                    Log.d("TAG1234", "onChanged: "+data.getId()+" "+data.getNote());
                }
            }
        });

        myViewModel.getNoteById(5).observeForever(new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                if (data!=null){
                    Log.d("TAG1234", "onChanged: "+data.getId()+" "+data.getNote());
                }
            }
        });


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG1234", "onChanged: "+myViewModel.getCount());


            }
        });
        thread.start();




























    }






















}