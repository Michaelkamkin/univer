package com.example.university;

import static com.example.university.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    Button button_choose_university,button_calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_menu);

         button_choose_university = findViewById(id.button_choose_university);
        button_calculate = findViewById(id.button_calculate);
        button_choose_university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, City.class);
                startActivity(intent);
            }
        });

        View button_calculate = findViewById(id.button_calculate);
        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}