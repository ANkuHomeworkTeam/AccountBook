package com.example.accountbook.backend;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountDataBase extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "MyAccountDataBase";
    static final int DATABASE_VERSION = 1;
    // Constructor
    public AccountDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public AccountDataBase(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateScript = "";
        db.execSQL(sqlCreateScript);
    }

    @Deprecated
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
