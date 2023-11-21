package com.example.hananapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hananapp.data.MytaskTable.MyTask;
import com.example.hananapp.data.MytaskTable.MytaskQuery;
import com.example.hananapp.data.MySubject.MySubjectQuery;
import com.example.hananapp.data.MySubject.MySubject;
import com.example.hananapp.data.usersTable.MyUser;
import com.example.hananapp.data.usersTable.MyuserQuery;


/*
نعريف الجداول ورقم النسخة
version
عند تغيير اي شيء يخص جدول او جداول علينا تغيير رقم  الاصدار ليتم بناء قاعدة البيانات من جديد
 */
@Database(entities = {MyUser.class, MySubject.class, MyTask.class},version = 1)
/**
 * الفئة المسؤولة عن بناء قاعدة البيانات لكل جداولها
 * وتوفر لنا كائن للتعامل مع قاعدة البيانات
 */

   public abstract class AppDatabase extends RoomDatabase

{
    /**
     * كائن للتعامل مع قاعدة البيانات
     */

    private static AppDatabase db;

    /**
     * يعيد كائن لعملبات جدول المستعملين
     * @return
     */
    public abstract MyuserQuery getMyUserQuery();

    /**
     *  يعيد كائن لعملبات جدول مواضيع
     * @return
     */
    public abstract MySubjectQuery getMySubjectQuery();

    /**
     *  يعيد كائن لعملبات جدول المهمات
     * @return
     */
    public abstract MytaskQuery getMyTaskQuery();

    /**
     * بناء قاعدة البيانات واعادة كائن يؤشر عليها
     * @param context
     * @return
     */
    public static AppDatabase getDB(Context context)
    {
        if(db==null)
        {
            db = Room.databaseBuilder(context,
                            AppDatabase.class, "database-name")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return db;
    }
}




