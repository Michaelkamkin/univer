package com.example.university;

import static com.example.university.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

public class Menu extends AppCompatActivity {
    private CheckBox checkbox_itmo;
    private Button button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_menu);

        checkbox_itmo = findViewById(R.id.checkbox_itmo);
        button_next = findViewById(id.button_next);

        button_next.setOnClickListener(view -> {
            Intent intent = new Intent(Menu.this, programms.class);
            if (checkbox_itmo.isChecked()) {
intent.putExtra("data","Itmo");
                startActivity(intent);
            }
            startActivity(intent);
        });
    }
}