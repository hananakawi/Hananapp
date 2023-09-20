package com.example.hananapp.data.Mytask;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MytaskQuery {
    @Query("SELECT * FROM MYTASKTABLE")
    List<MytaskTable> getAllTasks();
@Insert
    Void insertTask(MytaskTable... t); //ثلاثة نقاط تعني ادخال مجموعة
}
