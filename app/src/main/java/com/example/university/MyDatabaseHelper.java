package com.example.university;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 7;

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
        db.execSQL("INSERT INTO universities(name, city_id, logo) VALUES('Итмо', 1, 'logo_itmo.jpg');");
        db.execSQL("INSERT INTO universities(name, city_id, logo) VALUES('Политех', 1, 'logo_politech.jpg');");
        db.execSQL("INSERT INTO universities(name, city_id, logo) VALUES('Спбгу', 1, 'logo_spbgu.jpg');");
        db.execSQL("INSERT INTO universities(name, city_id, logo) VALUES('Мфти', 2, 'logo_itmo.jpg');");
        db.execSQL("INSERT INTO universities(name, city_id, logo) VALUES('Мгу', 2, 'logo_itmo.jpg');");
        db.execSQL("INSERT INTO universities(name, city_id, logo) VALUES('Мгту', 2, 'logo_itmo.jpg');");
        // Вставляем начальные данные в таблицу programs
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Системное и прикладное программное обеспечение', 1, 100, 349.000, 4, 'информатика, русский, математика', 304);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Разработка программного обеспечения', 1, 135, 349.000, 4, 'информатика, русский, математика', 308);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Технологии разработки компьютерных игр', 1, 15, 299.000, 4, 'информатика, русский, математика', 276);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Математика и компьютерные науки', 2, 25, 220.000, 4, 'информатика, русский, математика', 243);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Информатика и вычислительная техника', 2, 83, 240.000, 4, 'информатика, русский, математика', 256);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Информационные системы и технологии', 2, 27, 240.000, 4, 'информатика, русский, математика', 260);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Программирование и информационные технологии', 3, 25, 231.400, 4, 'информатика, русский, математика', 232);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Современное программирование', 3, 30, 234.800, 4, 'информатика, русский, математика', 281);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Технологии программирования', 3, 65, 331.900, 4, 'информатика, русский, математика', 235);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Системное программирование и прикладная математика', 4, 25, 231.400, 4, 'информатика, русский, математика, физика', 378);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Прикладная математика и информатика', 4, 65, 331.900, 4, 'информатика, русский, математика', 294);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Математическое моделирование и компьютерные технологии', 4, 30, 234.800, 4, 'информатика, русский, математика', 286);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Прикладная математика и информатика', 5, 294, 391.050, 4, 'информатика, русский, математика,физика', 405);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Фундаментальная информатика и информационные технологии', 5, 19, 391.050, 4, 'информатика, русский, математика,физика', 402);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Информационные системы и технологии', 6, 56, 302.533, 4, 'информатика, русский, математика', 247);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Прикладная информатика', 6, 84, 302.533, 4, 'информатика, русский, математика', 277);");
        db.execSQL("INSERT INTO programs(name, university_id,budget_seats,tuition_free,duration,subjects,average_grade) VALUES('Программная инженерия', 6, 84, 302.533, 4, 'информатика, русский, математика', 291);");

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