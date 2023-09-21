package com.example.hananapp.data.Mytask;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * واجهة استعلامات على جدول معطيات
 */
@Dao
public interface MytaskQuery {
    /**
     * اعادة جميع معطيات جدول المهمات
     * @return * قائمة من المهمات
     */
    @Query("SELECT * FROM MYTASKTABLE")
    List<MytaskTable> getAllTasks();

    /**
     *
     * @param userid_p
     * @return
     */
    @Query("SELECT * FROM MytaskTable WHERE userId=:userid_p" +
            " ORDER BY time DESC")
    List<MytaskTable> getAllTaskOrderBy(long userid_p);

    /**
     * استخراج المهمات حسب المستعمل واذا انتهت ام لا ومرتبة تنازلية حسب الاهمية
     * @param userid_p*رقم المستعمل
     * @param isCompleted_p*هل تمت العملية ام لا
     * @return
     */

@Query("SELECT * FROM MytaskTable WHERE userId=:userid_p AND isCompleted=:isCompleted_p" +
        " ORDER BY importance DESC")
    List<MytaskTable> getAllTaskOrderBy(long userid_p,boolean isCompleted_p);
    /**
     * ادخال مهمات
     * @param t*مجموعة مهممات
     */
    @Insert
    Void insertTask(MytaskTable... t); //ثلاثة نقاط تعني ادخال مجموعة

    /**
     * تعديل المهمات
     * @param t * مجموعة مهمات للتعديل (التعديل حسب المفتاح الرئيسي)
     */
    @Update
    Void updateTask(MytaskTable... t);

    /**
     * حذف مهمة او مهمات
     * @param t * حذف المهمات (حسب المفتاح الرئيسي)
     */
    @Delete
    Void deleteTask(MytaskTable... t);

    @Query("DELETE FROM MYTASKTABLE WHERE keyid=:id ")
    Void delTask(long id);
}



