package com.example.accountbook.backend;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AccountDataBase extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "MyAccountDataBase";
    static final int DATABASE_VERSION = 1;
    // Constructor
    public AccountDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(" - DataBase", "Call constructor");
    }
    public AccountDataBase(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(" - DataBase", "Call onCreate");
        String sqlCreateScript = "CREATE TABLE IF NOT EXISTS account_book ( " +
                "_id INTEGER PRIMARY KEY, " +  // _id   -> 主键
                "date DATE," +              // date  -> 日期
                "money DOUBLE," +              // money -> 收支
                "in_out INTEGER," +            // in_out-> 收入或者支出类型 1是收入
                "type VARCHAR," +              // tile  -> 类型
                "info TEXT" +                  // 备注
                ")";
        db.execSQL(sqlCreateScript);
    }

    @Deprecated
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(" - DataBase", "Call Upgrade. It has not been implemented yet.");
    }

}
