package com.example.university;

import static com.example.university.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
    private CheckBox checkBoxSpb;
    private CheckBox checkBoxMoscow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_menu);

        checkBoxSpb = findViewById(R.id.checkbox_spb);
        checkBoxMoscow = findViewById(R.id.checkbox_moscow);
        Button buttonNext = findViewById(R.id.button_next);


        buttonNext.setOnClickListener(view -> {
            if (checkBoxSpb.isChecked() && checkBoxMoscow.isChecked()) {
                // Вывод ошибки, если выбрано два
                // города
                Toast.makeText(getApplicationContext(), "Можно выбрать только один город", Toast.LENGTH_SHORT).show();
            } else if (checkBoxSpb.isChecked()) {
                // Переход на Activity для
                // выбора университета в СПб
                // selectedCity = "Спб";

                Intent intent = new Intent(getApplicationContext(), ChooseUniversity.class);
                intent.putExtra("city", "Спб");
                startActivity(intent);
            } else if (checkBoxMoscow.isChecked()) {
                //selectedCity = "Москва";
                // Переход на Activity для выбора университета в Москве
                Intent intent = new Intent(getApplicationContext(), ChooseUniversity.class);
                intent.putExtra("city", "Москва");
                startActivity(intent);
            } else {
                // Вывод ошибки, если не выбран ни один город
                Toast.makeText(getApplicationContext(), "Выберите город", Toast.LENGTH_SHORT).show();
            }
        });

    }
}