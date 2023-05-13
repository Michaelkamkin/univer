package com.example.university;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChooseUniversity extends AppCompatActivity {
    private ListView listViewUniversities;
    private ArrayAdapter<String> universitiesArrayAdapter;
    private List<University> universities;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universities);

        dbHelper = new MyDatabaseHelper(this);
        universities = new ArrayList<>();

        // Получаем город из Intent
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");

        // Получаем университеты данного города из базы данных
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT universities._id, universities.name, universities.city_id FROM universities INNER JOIN cities ON universities.city_id = cities._id WHERE cities.name = ?;", new String[]{city});
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex("_id");
            int nameIndex = cursor.getColumnIndex("name");
            int cityIdIndex = cursor.getColumnIndex("city_id");
            if (idIndex >= 0 && nameIndex >= 0 && cityIdIndex >= 0) {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                int cityId = cursor.getInt(cityIdIndex);
                University university = new University(id, name, cityId);
                universities.add(university);
            }
        }
        cursor.close();
        db.close();

        // Создаем адаптер для списка университетов
        universitiesArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getUniversityNames());
        listViewUniversities = findViewById(R.id.listview_universities);
        listViewUniversities.setAdapter(universitiesArrayAdapter);

        listViewUniversities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                University selectedUniversity = universities.get(i);
                Intent intent = new Intent(getApplicationContext(), ChooseProgram.class);
                intent.putExtra("university", selectedUniversity);
                startActivity(intent);
            }
        });
    }

    private List<String> getUniversityNames() {
        List<String> names = new ArrayList<>();
        for (University university : universities) {
            names.add(university.getName());
        }
        return names;
    }
}

