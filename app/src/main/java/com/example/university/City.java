package com.example.university;


import static com.example.university.R.id.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class City extends AppCompatActivity {

    private FirebaseFirestore db;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        db = FirebaseFirestore.getInstance();
        container = findViewById(R.id.container);

        db.collection("Itmo").document("name")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    String itmo = documentSnapshot.getString("Итмо");
                    addButtonToContainer(itmo);
                });
    }

    private void addButtonToContainer(String text) {
        Button button = new Button(this);
        button.setText(text);
        container.addView(button);
    }
}