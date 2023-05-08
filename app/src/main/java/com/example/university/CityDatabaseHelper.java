package com.example.university;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CityDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "city.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "program";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_UNIVERSITY = "university";
    public static final String COLUMN_PROGRAM = "program";

    public CityDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_CITY + " TEXT, " +
                COLUMN_UNIVERSITY + " TEXT, " +
                COLUMN_PROGRAM + " TEXT)");

        insertProgram(sqLiteDatabase, "Спб", "Итмо", "Компьютерные системы и технологии");
        insertProgram(sqLiteDatabase, "Спб", "Итмо", "Разработка программного обеспечения");
        insertProgram(sqLiteDatabase, "Спб", "Итмо", "Компьютерные технологии в дизайне");
        insertProgram(sqLiteDatabase, "Спб", "Политех", "Математика и компьютерные науки");
        insertProgram(sqLiteDatabase, "Спб", "Политех", "Информатика и вычислительная техника");
        insertProgram(sqLiteDatabase, "Спб", "Политех", "Информационные системы и технологии");
        insertProgram(sqLiteDatabase, "Москва", "Мфти", "Прикладная математика и компьютерные технологии");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    private void insertProgram(SQLiteDatabase sqLiteDatabase, String city, String university, String program) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CITY, city);
        contentValues.put(COLUMN_UNIVERSITY, university);
        contentValues.put(COLUMN_PROGRAM, program);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public List<String> getUniversities(String city) {
        List<String> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT " + COLUMN_UNIVERSITY +
                " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_CITY + " = ?", new String[]{city});
        while (cursor.moveToNext()) {
            result.add(cursor.getString(0));
        }
        cursor.close();
        return result;
    }

    public List<String> getPrograms(String city, String university) {
        List<String> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_PROGRAM +
                " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_CITY + " = ? AND " + COLUMN_UNIVERSITY + " = ?", new String[]{city, university});
        while (cursor.moveToNext()) {
            result.add(cursor.getString(0));
        }
        cursor.close();
        return result;
    }
}