package com.example.university;

import static com.example.university.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

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
            if (checkbox_itmo.isChecked()) {
                Intent intent = new Intent(Menu.this, City.class);
                startActivity(intent);
            }
        });
    }
}