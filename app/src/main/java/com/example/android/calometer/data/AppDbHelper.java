package com.example.android.calometer.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by subrafive on 20-08-2017.
 */

public class AppDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "calometer.db";

    public static final int DATABASE_VERSION = 1;

    public AppDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USER_AUTH_TABLE = "CREATE TABLE" + AppContract.UserEntry.TABLE_NAME + "("
                + AppContract.UserEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT"
                + AppContract.UserEntry.COLUMN_USER_NAME + "TEXT NOT NULL"
                + AppContract.UserEntry.COLUMN_USER_PASSWORD + "TEXT NOT NULL UNIQUE)";

        db.execSQL(SQL_CREATE_USER_AUTH_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
