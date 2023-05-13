package com.example.university;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создаем таблицы в базе данных
        db.execSQL("CREATE TABLE cities ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);");
        db.execSQL("CREATE TABLE universities ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, city_id INTEGER NOT NULL, FOREIGN KEY(city_id) REFERENCES cities(_id));");
        db.execSQL("CREATE TABLE programs ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, university_id INTEGER NOT NULL, FOREIGN KEY(university_id) REFERENCES universities(_id));");
        // Вставляем начальные данные в таблицу cities
        db.execSQL("INSERT INTO cities(name) VALUES('Спб');");
        db.execSQL("INSERT INTO cities(name) VALUES('Москва');");
        // Вставляем начальные данные в таблицу universities
        db.execSQL("INSERT INTO universities(name, city_id) VALUES('Итмо', 1);");
        db.execSQL("INSERT INTO universities(name, city_id) VALUES('Политех', 1);");
        db.execSQL("INSERT INTO universities(name, city_id) VALUES('Спбгу', 1);");
        db.execSQL("INSERT INTO universities(name, city_id) VALUES('Мфти', 2);");
        db.execSQL("INSERT INTO universities(name, city_id) VALUES('Мгу', 2);");
        db.execSQL("INSERT INTO universities(name, city_id) VALUES('Мгту', 2);");
        // Вставляем начальные данные в таблицу programs
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Компьютерные системы и технологии', 1);");
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Разработка программного обеспечения', 1);");
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Компьютерные технологии в дизайне', 1);");
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Математика и компьютерные науки', 2);");
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Информатика и вычислительная техника', 2);");
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Информационные системы и технологии', 2);");
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Программирование и информационные технологии', 3);");
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Современное программирование', 3);");
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Технологии программирования', 3);");
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Системное программирование и прикладная математика', 4);");
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Математическое моделирование и компьютерные технологии', 4);");
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Прикладная математика и информатика', 5);");
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Фундаментальная информатика и информационные технологии', 5);");
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Информационные системы и технологии', 6);");
        db.execSQL("INSERT INTO programs(name, university_id) VALUES('Прикладная информатика', 6);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Обновление базы данных
        db.execSQL("DROP TABLE IF EXISTS cities;");
        db.execSQL("DROP TABLE IF EXISTS universities;");
        db.execSQL("DROP TABLE IF EXISTS programs;");
        onCreate(db);
    }
}