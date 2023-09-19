package com.example.hananapp.data.usersTable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Entity=Table=جدول
//عندما نريد ان نتعامل مع هذه الفئة كجدول معطيات
@Entity
public class MyUser {
    @PrimaryKey(autoGenerate = true)//تحديد الصفة كمفتاح رئيسي والذي ينتج بسكل تلقائي
    public long keyid;
    @ColumnInfo(name = "full_Name")//اعطاء اسم جديد للعامود -الصفة بالجدول
    public String fullName;
    public String email;//بحالة لم يعطي اسم للعامود يكون اسم الصفة هو اسم العامود
    public String phone;
    public String password;


    @Override
    public String
    toString() {
        return "MyUser{" +
                "keyid=" + keyid +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';


    }
}

