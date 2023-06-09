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
    private CheckBox checkBoxSpb;
    private CheckBox checkBoxMoscow;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_menu);

        checkBoxSpb = findViewById(R.id.checkbox_spb);
        checkBoxMoscow = findViewById(R.id.checkbox_moscow);
        buttonNext = findViewById(R.id.button_next);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxSpb.isChecked() && checkBoxMoscow.isChecked()) {

                    Toast.makeText(getApplicationContext(), "Можно выбрать только один город", Toast.LENGTH_SHORT).show();
                } else if (checkBoxSpb.isChecked()) {

                    Intent intent = new Intent(getApplicationContext(), ChooseUniversity.class);
                    intent.putExtra("city", "Спб");
                    startActivity(intent);
                } else if (checkBoxMoscow.isChecked()) {

                    Intent intent = new Intent(getApplicationContext(),ChooseUniversity.class);
                    intent.putExtra("city", "Москва");
                    startActivity(intent);
                } else {

                    Toast.makeText(getApplicationContext(), "Выберите город", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}