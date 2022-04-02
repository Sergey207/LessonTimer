package com.example.lessontimer;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "events";
    public static final String[] COMMANDS_TO_CREATE = {
            "PRAGMA foreign_keys = off;",
            "BEGIN TRANSACTION;",
            "CREATE TABLE events (start_time TIME NOT NULL, end_time TIME NOT NULL, monday BOOLEAN DEFAULT (0), tuesday BOOLEAN DEFAULT (0), wednesday BOOLEAN DEFAULT (0), thursday BOOLEAN DEFAULT (0), friday BOOLEAN DEFAULT (0), saturday BOOLEAN DEFAULT (0), sunday BOOLEAN DEFAULT (0));",
            "CREATE TABLE weekdays (id INTEGER UNIQUE NOT NULL, name STRING NOT NULL UNIQUE, short_name STRING UNIQUE NOT NULL);",
            "INSERT INTO weekdays (id, name, short_name) VALUES (1, 'Понедельник', 'ПН');",
            "INSERT INTO weekdays (id, name, short_name) VALUES (2, 'Вторник', 'ВТ');",
            "INSERT INTO weekdays (id, name, short_name) VALUES (3, 'Среда', 'СР');",
            "INSERT INTO weekdays (id, name, short_name) VALUES (4, 'Четверг', 'ЧТ');",
            "INSERT INTO weekdays (id, name, short_name) VALUES (5, 'Пятница', 'ПТ');",
            "INSERT INTO weekdays (id, name, short_name) VALUES (6, 'Суббота', 'СБ');",
            "INSERT INTO weekdays (id, name, short_name) VALUES (7, 'Воскресенье', 'ВС');",
            "COMMIT TRANSACTION;",
            "PRAGMA foreign_keys = on;"
    };

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String cmd : COMMANDS_TO_CREATE) {
            db.execSQL(cmd);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(Resources.getSystem().getString(R.string.drop_table));
        this.onCreate(db);
    }
}
