package com.example.accountbook.backend;

import android.content.Context;
import android.util.Log;

import java.util.Date;

public class AccountInquery {
    static private AccountDataBase db;
    static public void init(Context context) {
        db = new AccountDataBase(context);
        Log.d(" - Inquiry", "Inquiry Layer initialized");
    }
    static public void insert(Date date, double money, boolean isIncome, String type, String info) {
        db.insertItem(date, money, isIncome, type, info);
    }
}
