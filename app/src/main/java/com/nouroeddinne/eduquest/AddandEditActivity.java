package com.nouroeddinne.eduquest;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import Model.Data;
import Utles.Utel;

public class AddandEditActivity extends AppCompatActivity {

    EditText note;
    ImageView back,save;
    MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addand_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        note = findViewById(R.id.editText_show_note);
        back = findViewById(R.id.imageView_back);
        save = findViewById(R.id.imageView_save_edit);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddandEditActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Data d = new Data(note.getText().toString(),1);
                myViewModel.insertNote(d);

                Intent intent = new Intent(AddandEditActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}