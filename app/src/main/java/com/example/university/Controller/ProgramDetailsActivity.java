package com.example.university.Controller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.university.Models.MyDatabaseHelper;
import com.example.university.R;


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
        String[] projection = {"budget_seats", "paid_training", "duration", "subjects", "passing_grade"};
        String selection = "name = ?";
        String[] selectionArgs = {program};
        Cursor cursor = db.query("programs", projection, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()) {
            int budgetSeats = cursor.getInt(cursor.getColumnIndexOrThrow("budget_seats"));
            int paid_training = cursor.getInt(cursor.getColumnIndexOrThrow("paid_training"));
            int duration = cursor.getInt(cursor.getColumnIndexOrThrow("duration"));
            String subjects = cursor.getString(cursor.getColumnIndexOrThrow("subjects"));
            int passing_grade = cursor.getInt(cursor.getColumnIndexOrThrow("passing_grade"));
            budgetSeatsTextView.setText(String.valueOf(budgetSeats));
            tuitionFreeTextView.setText(String.valueOf(paid_training)+" "+"руб.");
            durationTextView.setText(String.valueOf(duration));
            subjectsTextView.setText(String.valueOf(subjects));
            averageGradeTextView.setText(String.valueOf(passing_grade));
        }
        cursor.close();
        db.close();
    }
}
