package com.example.university;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 6;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создаем таблицы в базе данных
        db.execSQL("CREATE TABLE cities ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);");
        db.execSQL("CREATE TABLE universities (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, city_id INTEGER NOT NULL, logo TEXT, FOREIGN KEY(city_id) REFERENCES cities(_id));");
        db.execSQL("CREATE TABLE programs ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, university_id INTEGER NOT NULL,FOREIGN KEY(university_id) REFERENCES universities(_id));");
        db.execSQL("ALTER TABLE programs ADD COLUMN budget_seats INTEGER NOT NULL ;");
        db.execSQL("ALTER TABLE programs ADD COLUMN tuition_free INTEGER NOT NULL ;");
        db.execSQL("ALTER TABLE programs ADD COLUMN duration INTEGER NOT NULL ;");
        db.execSQL("ALTER TABLE programs ADD COLUMN subjects TEXT NOT NULL ;");
        db.execSQL("ALTER TABLE programs ADD COLUMN average_grade REAL NOT NULL;");
        // Вставляем начальные данные в таблицу cities
        db.execSQL("INSERT INTO cities(name) VALUES('Спб');");
        db.execSQL("INSERT INTO cities(name) VALUES('Москва');");
        // Вставляем начальные данные в таблицу universities
        db.execSQL("INSERT INTO universities(name, city_id, logo) VALUES('Итмо', 1, logo_itmo.jpg);");
        db.execSQL("INSERT INTO universities(name, city_id, logo) VALUES('Политех', 1, 'logo_politech.jpg');");
        db.execSQL("INSERT INTO universities(name, city_id, logo) VALUES('Спбгу', 1, 'logo_spbgu.jpg');");
        db.execSQL("INSERT INTO universities(name, city_id, logo) VALUES('Мфти', 2, 'logo_itmo.jpg');");
        db.execSQL("INSERT INTO universities(name, city_id, logo) VALUES('Мгу', 2, 'logo_itmo.jpg');");
        db.execSQL("INSERT INTO universities(name, city_id, logo) VALUES('Мгту', 2, 'logo_itmo.jpg');");
        // Вставляем начальные данные в таблицу programs
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Компьютерные системы и технологии', 1, 1000, 20000, 4, 'Предметы 1', 4.5);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Разработка программного обеспечения', 1, 100, 20000, 4, 'Предметы 1', 4.5);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Компьютерные технологии в дизайне', 1, 1006, 20000, 4, 'Предметы 1', 4.5);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Математика и компьютерные науки', 2, 100, 20000, 4, 'Предметы 1', 4.5);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Информатика и вычислительная техника', 2, 100, 20000, 4, 'Предметы 1', 4.5);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Информационные системы и технологии', 2, 100, 20000, 4, 'Предметы 1', 4.5);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Программирование и информационные технологии', 3, 100, 20000, 4, 'Предметы 1', 4.5);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Современное программирование', 3, 100, 20000, 4, 'Предметы 1', 4.5);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Технологии программирования', 3, 100, 20000, 4, 'Предметы 1', 4.5);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Системное программирование и прикладная математика', 4, 100, 20000, 4, 'Предметы 1', 4.5);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Математическое моделирование и компьютерные технологии', 4, 100, 20000, 4, 'Предметы 1', 4.5);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Прикладная математика и информатика', 5, 100, 20000, 4, 'Предметы 1', 4.5);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Фундаментальная информатика и информационные технологии', 5, 100, 20000, 4, 'Предметы 1', 4.5);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Информационные системы и технологии', 6, 100, 20000, 4, 'Предметы 1', 4.5);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Прикладная информатика', 6, 100, 20000, 4, 'Предметы 1', 4.5);");

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