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
    private String university;
    private ArrayList<String> programs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        listViewPrograms = findViewById(R.id.listview_programs);
        university = getIntent().getStringExtra("university");

        SQLiteDatabase db = new MyDatabaseHelper(this).getReadableDatabase();
        String[] projection = { "name" };
        String selection = "university_id = (SELECT _id FROM universities WHERE name = ?)";
        String[] selectionArgs = { university };
        Cursor cursor = db.query("programs", projection, selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            programs.add(name);
        }

        cursor.close();
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, programs);
        listViewPrograms.setAdapter(adapter);

        listViewPrograms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String program = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(ChooseProgram.this, ProgramDetailsActivity.class);
                intent.putExtra("program", program);
                startActivity(intent);
            }
        });
    }
}