/**
 * 数据库操作
 * 1711326 hrl
 */

package com.example.accountbook.backend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.security.keystore.KeyInfo;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AccountDataBase extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "MyAccountDataBase";
    static final int DATABASE_VERSION = 1;

    static final String TABLE_ACCOUNT_BOOK = "account_book";

    static final String KEY_ID = "_id";
    static final String KEY_DATE = "date";
    static final String KEY_MONEY = "money";
    static final String KEY_I_OR_E = "in_out";
    static final String KEY_TYPE = "type";
    static final String KEY_INFO = "info";

    static String castWhereClause(String key) {
        return key + "=?";
    }

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
        String sqlCreateScript = "CREATE TABLE IF NOT EXISTS "
                + TABLE_ACCOUNT_BOOK +" ( " +
                KEY_ID      + " INTEGER PRIMARY KEY, " +  // _id   -> 主键
                KEY_DATE    + " DATE," +              // date  -> 日期
                KEY_MONEY   + " DOUBLE," +              // money -> 收支
                KEY_I_OR_E  + " INTEGER," +            // in_out-> 收入或者支出类型 1是收入
                KEY_TYPE    + " VARCHAR," +              // tile  -> 类型
                KEY_INFO    + " TEXT" +                  // 备注
                ")";
        db.execSQL(sqlCreateScript);
    }

    @Deprecated
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(" - DataBase", "Call Upgrade. It has not been implemented yet.");
    }

    public void insertItem(java.util.Date uDate, double money, boolean isIncome, String type, String info) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        values.put(KEY_DATE, sDate.toString());
        values.put(KEY_MONEY, money);
        values.put(KEY_I_OR_E, isIncome? 1 : 0);
        values.put(KEY_TYPE, type);
        values.put(KEY_INFO, info);
        database.insert(TABLE_ACCOUNT_BOOK, null, values);
    }


    public void modifyItem(int id, java.util.Date uDate, double money, boolean isIncome, String type, String info) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues v = new ContentValues();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        v.put(KEY_DATE, sDate.toString());
        v.put(KEY_MONEY, money);
        v.put(KEY_I_OR_E, isIncome? 1 : 0);
        v.put(KEY_TYPE, type);
        v.put(KEY_INFO, info);
        db.update(TABLE_ACCOUNT_BOOK, v, castWhereClause(KEY_ID), new String[]{Integer.toString(id)});
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_ACCOUNT_BOOK, castWhereClause(KEY_ID), new String[] {
                Integer.toString(id)
        });
    }

    public ArrayList<AccountItem> inquiryItems(String section, String[] sectionArgs) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(TABLE_ACCOUNT_BOOK, null, section, sectionArgs, null, null, KEY_DATE);
        ArrayList<AccountItem> lists = new ArrayList<>();
        do {
            int id      = c.getInt(c.getColumnIndex(KEY_ID));
            String dateStr = c.getString(c.getColumnIndex(KEY_DATE));
            Date date;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            } catch (Exception e) {
                e.printStackTrace();
                date = new Date();
            }
            double money = c.getDouble(c.getColumnIndex(KEY_MONEY));
            boolean isIncome = c.getInt(c.getColumnIndex(KEY_I_OR_E)) == 1 ? true : false;
            String type = c.getString(c.getColumnIndex(KEY_TYPE));
            String info = c.getString(c.getColumnIndex(KEY_INFO));
            lists.add(new AccountItem(id, date, money, isIncome, type, info));
        } while (c.moveToNext());
        return lists;
    }
}
