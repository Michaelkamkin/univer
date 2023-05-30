package com.example.university;

import static com.example.university.R.id.budget_seats_text_view;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ProgramDetailsActivity extends AppCompatActivity {
    private TextView budgetSeatsTextView;
    private TextView tuitionFreeTextView;
    private TextView durationTextView;
    private TextView subjectsTextView;
    private TextView averageGradeTextView;
    private String program;
    // переменные для данных
    private long selectedProgramId;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_details);

        // связываем переменные с элементами пользовательского интерфейса
        budgetSeatsTextView = findViewById(R.id.budget_seats_text_view);
        tuitionFreeTextView = findViewById(R.id.tuition_free_text_view);
        durationTextView = findViewById(R.id.duration_text_view);
        subjectsTextView = findViewById(R.id.subjects_text_view);
        averageGradeTextView = findViewById(R.id.average_grade_text_view);

        // получаем выбранную программу
        program = getIntent().getStringExtra("program");

        // открываем базу данных
        SQLiteDatabase db = new MyDatabaseHelper(this).getReadableDatabase();
        String[] projection = {"budget_seats", "tuition_free", "duration", "subjects", "average_grade"};
        String selection = "name = ?";
        String[] selectionArgs = {program};
        Cursor cursor = db.query("programs", projection, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()) {
            int budgetSeats = cursor.getInt(cursor.getColumnIndexOrThrow("budget_seats"));
            int tuitionFree = cursor.getInt(cursor.getColumnIndexOrThrow("tuition_free"));
            int duration = cursor.getInt(cursor.getColumnIndexOrThrow("duration"));
            String subjects = cursor.getString(cursor.getColumnIndexOrThrow("subjects"));
            float averageGrade = cursor.getFloat(cursor.getColumnIndexOrThrow("average_grade"));
            budgetSeatsTextView.setText(String.valueOf(budgetSeats));
            tuitionFreeTextView.setText(String.valueOf(tuitionFree));
            durationTextView.setText(String.valueOf(duration));
            subjectsTextView.setText(String.valueOf(subjects));
            averageGradeTextView.setText(String.valueOf(averageGrade));
        }
        cursor.close();
        db.close();
    }
}