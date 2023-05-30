package com.example.university;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "university_db";
    private static final String CITIES_TABLE = "cities";
    private static final String PROGRAMS_TABLE = "programs";

    // Строка создания таблицы "города"
    private static final String CREATE_CITIES_TABLE = "CREATE TABLE " +
            CITIES_TABLE + " (id INTEGER PRIMARY KEY, name TEXT)";
    // Строка создания таблицы "программы"
    private static final String CREATE_PROGRAMS_TABLE = "CREATE TABLE " +
            PROGRAMS_TABLE + " (id INTEGER PRIMARY KEY, name TEXT, " +
            "city_id INTEGER, budget_seats INTEGER, tuition_fee INTEGER, " +
            "duration INTEGER, subjects TEXT, average_grade REAL)";


    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CITIES_TABLE);
        db.execSQL(CREATE_PROGRAMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Вы можете добавить конструкцию выполняемую при обновлении базы данных здесь, если это необходимо
    }

    // 2. Populate the "cities" table with two rows: "Спб" and "Москва".
    public void populateCities() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", "Спб");
        db.insert(CITIES_TABLE, null, values);

        values.put("name", "Москва");
        db.insert(CITIES_TABLE, null, values);

        db.close();
    }

    // 3. Заполните таблицу "программы" 15 строками, по 3 для каждого университета.
    public void populatePrograms() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", "Программа 1");
        values.put("city_id", 1);
        values.put("budget_seats", 10);
        values.put("tuition_fee", 100000);
        values.put("duration", 4);
        values.put("subjects", "Математика, Физика, Информатика");
        values.put("average_grade", 4.5);
        db.insert(PROGRAMS_TABLE, null, values);
        values.put("name", "Программа 2");
        values.put("city_id", 1);
        values.put("budget_seats", 10);
        values.put("tuition_fee", 100000);
        values.put("duration", 4);
        values.put("subjects", "Математика, Физика, Информатика");
        values.put("average_grade", 4.5);
        db.insert(PROGRAMS_TABLE, null, values);

        // ... Для каждой из 15 строк тело метода выглядит аналогично
        db.close();
    }
}