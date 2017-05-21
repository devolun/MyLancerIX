package com.mylancerix;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {
	
    public static final String DATABASE_NAME = "database.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "service";
    
    public static final String COLUMN_ID = "_id";
    public static final String PART_NAME_COLUMN = "part_name";
    public static final String PART_CATALOG_COLUMN = "part_catalog";
	public static final String PART_OLD_CHANGE_COLUMN = "old_change";    
    public static final String PART_SERVICE_CHANGE_COLUMN = "service_change";
	public static final String PART_NEXT_CHANGE_COLUMN = "next_change";

    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + TABLE_NAME + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + PART_NAME_COLUMN
            + " text not null, " + PART_CATALOG_COLUMN
            + " text not null, " + PART_SERVICE_CHANGE_COLUMN + " integer, " + PART_OLD_CHANGE_COLUMN
            + " integer, " + PART_NEXT_CHANGE_COLUMN
            + " integer);";
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Запишем в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF IT EXISTS " + TABLE_NAME);
        // Создаём новую таблицу
        onCreate(db);
    }
}