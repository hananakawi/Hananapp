package com.example.hananapp.Mytask;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface MytaskQuery {
/**
 * واجهة استعلامات على جدول معطيات
 */



    /**
     * اعادة جميع رمعطيات الجدول
     * @return قائمةمن.المهمات
     */
    @Query("SELECT * FROM Mytask")
    List<Mytask> getAll();

    /**
     *
     * '@param userid_p
     * '@param isCompleted_p
     * '@return
     */
    @Query("SELECT * FROM Mytask WHERE userId=:userid_p AND isCompleted=:isCompleted_p "
            +"ORDER BY importance DESC")
    List<Mytask> taskOrderBy(long userid_p, boolean isCompleted_p);

    @Query("SELECT * FROM Mytask WHERE userId=:userid_p " +
            " ORDER BY time DESC " )
    List<Mytask> taskByTime(long userid_p);
    /**
     * ادخال مهمات
     * @param tasks مجموعة مهمات
     */
    @Insert
    void insertAll(Mytask... tasks);// تلت نقط يعني مجموعة

    /**
     * حذف مهمات
     * '@param user
     */
    @Delete
    void delete(Mytask user);

    @Query("Delete From Mytask WHERE keyid=:id ")
    void deleteTask(long id);


    @Insert
    void insert(Mytask task);

    /**
     * تعديل المهمات
     * '@param tasks
     */
    @Update
    void update(Mytask...tasks);

}
