package com.example.university;

import static com.example.university.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Menu extends AppCompatActivity {
    private CheckBox checkboxItmo;
    private CheckBox checkboxPolytech;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_menu);

        checkboxItmo = findViewById(R.id.checkbox_itmo);
        checkboxPolytech = findViewById(R.id.checkbox_polytech);
        buttonNext = findViewById(R.id.button_next);

        checkboxItmo.setOnCheckedChangeListener((checkbox, isChecked) -> {
            if (isChecked) {
                DatabaseHelper.getNames().addOnSuccessListener(names -> {
                    for (String name : names) {
                        Log.d("Firebase", "Name: " + name);
                    }
                });
            }
        });

        checkboxPolytech.setOnCheckedChangeListener((checkbox, isChecked) -> {

        });

        buttonNext.setOnClickListener(view -> {

        });
    }
}


