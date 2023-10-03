package com.example.hananapp.data.MySubject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hananapp.Mytask.MytaskTable;

import java.util.List;
@Dao
public interface MySubjectQuery {
    /**
     * اعادة جميع معطيات جدول المهمات
     * @return * قائمة من المهمات
     */
    @Query("SELECT * FROM mySubject")
    List<MytaskTable> getAllsubjects();
/**
 * ادخال مهمات
 */
@Insert
Void insertubject(MySubjectQuery... t); //ثلاثة نقاط تعني ادخال مجموعة
    /**
     * تعديل المهمات
     */
    @Update
    Void updatesubject(MySubjectQuery... t);

    @Delete
    Void deletesubject(MySubjectQuery... t);

    @Query("DELETE FROM MYTASKTABLE WHERE keyid=:id ")
    Void delTask(long id);
}
