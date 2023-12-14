package com.example.university.Controller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.university.Models.MyDatabaseHelper;
import com.example.university.R;

import java.util.ArrayList;

public class ChooseUniversity extends AppCompatActivity {
    private ListView listViewUniversities;
    private String city;
    private ArrayList<String> universities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universities);

        listViewUniversities = findViewById(R.id.listview_universities);
        city = getIntent().getStringExtra("city");

        SQLiteDatabase db = new MyDatabaseHelper(this).getReadableDatabase();
        String[] projection = { "name" };
        String selection = "city_id = (SELECT _id FROM cities WHERE name = ?)";
        String[] selectionArgs = { city };
        Cursor cursor = db.query("universities", projection, selection, selectionArgs, null, null, null);
        //отправляется запрос в БД

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            universities.add(name);
            // получение данных из БД
        }

        cursor.close();
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, universities);
        listViewUniversities.setAdapter(adapter);
        // составление списка из полученнных данных
        listViewUniversities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String university = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(ChooseUniversity.this, ChooseProgram.class);
                intent.putExtra("university", university);
                startActivity(intent);
                // обработка нажатий на элементы и передача данных в следующий класс
            }
        });
    }
}