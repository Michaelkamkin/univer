package com.example.university;

import static com.example.university.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
    private CheckBox checkboxSpb;
    private CheckBox checkboxMoscow;
    private Button buttonNext;

    private boolean isSpbChecked = false;
    private boolean isMoscowChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_menu);

        checkboxSpb = findViewById(id.checkbox_spb);
        checkboxMoscow = findViewById(id.checkbox_moscow);
        buttonNext = findViewById(R.id.button_next);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSpbChecked && isMoscowChecked) {
                    showError("Можно выбрать только один город");
                } else if (isSpbChecked) {
                    showUniversities("Спб");
                } else if (isMoscowChecked) {
                    showUniversities("Москва");
                } else {
                    showError("Выберите город");
                }
            }
        });

        checkboxSpb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSpbChecked = isChecked;
            }
        });

        checkboxMoscow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isMoscowChecked = isChecked;
            }
        });
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        checkboxSpb.setChecked(false);
        checkboxMoscow.setChecked(false);
        isSpbChecked = false;
        isMoscowChecked = false;
    }

    private void showUniversities(String city) {
        Intent intent = new Intent(this, UniversitiesActivity.class);
        intent.putExtra("city", city);
        startActivity(intent);
    }
}