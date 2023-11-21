package com.example.hananapp.data.MySubject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;
@Dao
public interface MySubjectQuery {
    /**
     * اعادة جميع معطيات جدول المهمات
     * @return * قائمة من المهمات
     */
    @Query("SELECT * FROM MySubject")
    List<MySubject> getAllSubjects();
/**
 * ادخال مهمات
 */
@Insert
void insertSubject(MySubject... t); //ثلاثة نقاط تعني ادخال مجموعة
    /**
     * تعديل المهمات
     */
    @Update
    void updatesubject(MySubject... t);

    @Delete
    void deletesubject(MySubject... t);

    @Query("DELETE FROM MySubject WHERE key_id=:id ")
    void delTask(long id);
}
