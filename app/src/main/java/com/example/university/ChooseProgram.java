package com.example.university;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ChooseProgram extends AppCompatActivity {
    private ListView listViewPrograms;
    private ArrayAdapter<String> programsArrayAdapter;
    private List<Program> programs;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        dbHelper = new MyDatabaseHelper(this);
        programs = new ArrayList<>();

        // Получаем университет из Intent
        Intent intent = getIntent();
        University university = (University) intent.getSerializableExtra("university");

        // Получаем программы данного университета из базы данных
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM programs WHERE university_id = ?;", new String[]{String.valueOf(university.getId())});
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex("_id");
            int nameIndex = cursor.getColumnIndex("name");
            int universityIdIndex = cursor.getColumnIndex("university_id");

            if (idIndex >= 0 && nameIndex >= 0 && universityIdIndex >= 0) {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                int universityId = cursor.getInt(universityIdIndex);
                Program program = new Program(id, name, universityId);
                programs.add(program);
            }
        }
        cursor.close();
        db.close();

        // Создаем адаптер для списка программ
        programsArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getProgramNames());
        listViewPrograms = findViewById(R.id.listview_programs);
        listViewPrograms.setAdapter(programsArrayAdapter);

        listViewPrograms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Открытие ссылки в браузере
                String url = "https://bmstu.ru/bachelor/majors/prikladnaa-informatika-090303";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    private List<String> getProgramNames() {
        List<String> names = new ArrayList<>();
        for (Program program : programs) {
            names.add(program.getName());
        }
        return names;
    }
}