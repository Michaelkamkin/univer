package com.example.university;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Spb extends AppCompatActivity {

Button button_itmo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spb);
        Button button_itmo = findViewById(R.id.button_itmo);
        button_itmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri websiteUri = Uri.parse("https://itmo.ru");
                Intent intent = new Intent(Intent.ACTION_VIEW, websiteUri);
                startActivity(intent);
            }
        });
    }
}