package com.example.university;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProgramDetailsActivity extends AppCompatActivity {
    private TextView budgetSeatsTextView;
    private TextView tuitionFreeTextView;
    private TextView durationTextView;
    private TextView subjectsTextView;
    private TextView averageGradeTextView;
    private String program;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_details);

        budgetSeatsTextView = findViewById(R.id.budget_seats_text_view);
        tuitionFreeTextView = findViewById(R.id.tuition_free_text_view);
        durationTextView = findViewById(R.id.duration_text_view);
        subjectsTextView = findViewById(R.id.subjects_text_view);
        averageGradeTextView = findViewById(R.id.average_grade_text_view);

        program = getIntent().getStringExtra("program");

        db = new MyDatabaseHelper(this).getReadableDatabase();
        String[] projection = {"budget_seats", "tuition_free", "duration", "subjects", "average_grade"};
        String selection = "name = ?";
        String[] selectionArgs = {program};
        Cursor cursor = db.query("programs", projection, selection, selectionArgs, null, null, null);

        if (cursor.moveToNext()) {
            int budgetSeats = cursor.getInt(cursor.getColumnIndexOrThrow("budget_seats"));
            int tuitionFree = cursor.getInt(cursor.getColumnIndexOrThrow("tuition_free"));
            int duration = cursor.getInt(cursor.getColumnIndexOrThrow("duration"));
            String subjects = cursor.getString(cursor.getColumnIndexOrThrow("subjects"));
            float averageGrade = cursor.getFloat(cursor.getColumnIndexOrThrow("average_grade"));

            budgetSeatsTextView.setText(getString(R.string.budget_seats, budgetSeats));
            tuitionFreeTextView.setText(getString(R.string.tuition_free, tuitionFree));
            durationTextView.setText(getString(R.string.duration, duration));
            subjectsTextView.setText(getString(R.string.subjects, subjects));
            averageGradeTextView.setText(getString(R.string.average_grade, averageGrade));
        }

        cursor.close();
        db.close();
    }
}
