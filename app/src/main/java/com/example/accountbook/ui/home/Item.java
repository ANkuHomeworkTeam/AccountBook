package com.example.accountbook.ui.home;

public class Item {
    String year;
    String month;
    String day;
    String hour_minute;
    String type;
    String expend_or_income;
    String money;
    public Item(String year,String month,String day,String hour_minute,boolean e_or_i,String money)
    {
        this.year=year;
        this.month=month;
        this.day=day;
        this.hour_minute=hour_minute;
        if(e_or_i)
            this.expend_or_income="支出";
        else
            this.expend_or_income="收入";
        this.money=money;
    }
    public String getYear()
    {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getHour_minute() {
        return hour_minute;
    }

    public String getExpend_or_income() {
        return expend_or_income;
    }

    public String getMoney() {
        return money;
    }

    public String getType() {
        return type;
    }
}
