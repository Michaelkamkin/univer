package com.example.university;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ProgramsActivity extends AppCompatActivity {

    private ListView listView;
    private CityDatabaseHelper databaseHelper;
    private List<String> programs;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);
        databaseHelper = new CityDatabaseHelper(this);

        listView = findViewById(R.id.list_view);

        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        String university = intent.getStringExtra("university");
        programs = getPrograms(city, university);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                programs
        );
        listView.setAdapter(adapter);
    }

    private List<String> getPrograms(String city, String university) {
        List<String> result = new ArrayList<>();
        if (city.equals("Спб") && university.equals("Итмо")) {
            result.add("Компьютерные системы и технологии");
            result.add("Разработка программного обеспечения");
            result.add("Компьютерные технологии в дизайне");
        } else if (city.equals("Спб") && university.equals("Политех")) {
            result.add("Математика и компьютерные науки");
            result.add("Информатика и вычислительная техника");
            result.add("Информационные системы и технологии");
        } else if (city.equals("Москва") && university.equals("Мфти")) {
            result.add("Прикладная математика и компьютерные технологии");
        }
        return result;
    }
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}