package com.example.university;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ChooseUniversity extends AppCompatActivity {
    private final List<University> universitiesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universities);

        ListView listViewUniversities = findViewById(R.id.listview_universities);
        String city = getIntent().getStringExtra("city");

        SQLiteDatabase db = new MyDatabaseHelper(this).getReadableDatabase();
        String[] projection = { "name", "logo" };
        String selection = "city_id = (SELECT _id FROM cities WHERE name = ?)";
        String[] selectionArgs = {city};
        Cursor cursor = db.query("universities", projection, selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String logoFileName = cursor.getString(cursor.getColumnIndexOrThrow("logo"));
            University university = new University(name, logoFileName);
            universitiesList.add(university);

        }

        cursor.close();
        db.close();

        UniversityAdapter adapter = new UniversityAdapter(this, universitiesList);
        listViewUniversities.setAdapter(adapter);

        listViewUniversities.setOnItemClickListener((parent, view, position, id) -> {
            University university = (University) parent.getItemAtPosition(position);
            Intent intent = new Intent(ChooseUniversity.this, ChooseProgram.class);
            intent.putExtra("university", university.getName());
            startActivity(intent);
        });
    }
}
