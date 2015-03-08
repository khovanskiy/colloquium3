package com.example.colloquium3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tasksdb";
    private static final int DATABASE_VERSION = 1;

    private static Database instance = null;
    private SQLiteDatabase connection;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new Database(context);
        }
    }

    public static Database gi() {
        return instance;
    }

    public void exec(String sql) {
        try {
            connection = getWritableDatabase();
            connection.execSQL(sql);
        } catch (Exception e) {
            Console.print("Message: " + e.toString() + " connection = " + connection);
        }
    }

    public Cursor query(String sql) {
        try {
            connection = getWritableDatabase();
            Cursor c = connection.rawQuery(sql, null);
            return c;
        } catch (Exception e) {
            Console.print("Message: " + e.toString() + " connection = " + connection);
            return null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Console.print("onCreate");
        db.execSQL("create table tasks(id_task integer primary key autoincrement, name text not null)");
        db.execSQL("create table subtasks(id_subtask integer primary key autoincrement, id_task integer, title text not null, type integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
