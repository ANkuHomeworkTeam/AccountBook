package com.example.accountbook.backend;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class AccountInquiry {
    private AccountDataBase db;
    public AccountInquiry(Context context) {
        db = new AccountDataBase(context);
        Log.d(" - Inquiry", "Inquiry Layer initialized");
    }
    // 增加账单
    public void insert(Date date, double money, boolean isIncome, String type, String info) {
        db.insertItem(date, money, isIncome, type, info);
    }
    // 修改账单, 根据id改
    public void modify(int id, Date date, double money, boolean isIncome, String type, String info) {
        db.modifyItem(id, date, money, isIncome, type, info);
    }
    // 删除账单，根据id删
    public void delete(int id) {
        db.deleteItem(id);
    }
    // 返回所有
    ArrayList<AccountItem> inquiryAll() {
        return db.inquiryItems(null, null);
    }

}
