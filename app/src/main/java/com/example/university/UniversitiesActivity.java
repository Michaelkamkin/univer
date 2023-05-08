package com.example.university;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class UniversitiesActivity extends AppCompatActivity {

    private ListView listView;

    private String city;
    private List<String> universities;
    private CityDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universities);

        listView = findViewById(R.id.list_view);

        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        databaseHelper = new CityDatabaseHelper(this);
        universities = databaseHelper.getUniversities(city);
        universities = getUniversities(city);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                universities
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showPrograms(city, universities.get(position));
            }
        });
    }

    private List<String> getUniversities(String city) {
        List<String> result = new ArrayList<>();
        if (city.equals("Спб")) {
            result.add("Итмо");
            result.add("Политех");
        } else {
            result.add("Мфти");
        }
        return result;
    }

    private void showPrograms(String city, String university) {
        Intent intent = new Intent(this, ProgramsActivity.class);
        intent.putExtra("city", city);
        intent.putExtra("university", university);
        startActivity(intent);
    }
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}