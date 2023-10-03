package com.example.hananapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hananapp.Mytask.MytaskQuery;
import com.example.hananapp.data.usersTable.MyUser;

@Database(entities = {MyUser.class, MySubject.class, MyTask.class},version = 5)

 public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase db;
    public abstract MytaskQuery getMyUserQuery();
    public abstract MygetMySubjectQuery();
    public abstract MytaskQuery getMyTaskQuery();

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

{

}
