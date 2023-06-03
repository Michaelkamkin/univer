package com.example.university.Controller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.university.Models.MyDatabaseHelper;
import com.example.university.R;

import java.util.ArrayList;

public class ChooseProgram extends AppCompatActivity {
    private final ArrayList<String> programs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        ListView listViewPrograms = findViewById(R.id.listview_programs);
        String university = getIntent().getStringExtra("university");

        SQLiteDatabase db = new MyDatabaseHelper(this).getReadableDatabase();
        String[] projection = { "name" };
        String selection = "university_id = (SELECT _id FROM universities WHERE name = ?)";
        String[] selectionArgs = {university};
        Cursor cursor = db.query("programs", projection, selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            programs.add(name);
        }

        cursor.close();
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, programs);
        listViewPrograms.setAdapter(adapter);

        listViewPrograms.setOnItemClickListener((parent, view, position, id) -> {
            String program = (String) parent.getItemAtPosition(position);
            Intent intent = new Intent(ChooseProgram.this, ProgramDetailsActivity.class);
            intent.putExtra("program", program);
            startActivity(intent);
        });
    }
}