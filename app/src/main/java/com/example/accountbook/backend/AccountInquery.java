package com.example.accountbook.backend;

import android.content.Context;
import android.util.Log;

public class AccountInquery {
    static private AccountDataBase db;
    static public void init(Context context) {
        db = new AccountDataBase(context);
        Log.d(" - Inquiry", "Inquiry Layer initialized");
    }
}
