package com.example.hananapp.data.MytaskTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * فئة تمثل مهمة
 */
@Entity
public class MyTask {
    @PrimaryKey(autoGenerate = true)
    public long keyid;/**رقم المهمة*/
    public int importance;/**درجة الاهمية*/
    public String text;/**نص المهمة*/
    public boolean isCompleted;/**هل تمت العملية*/
    public long time;/**زمن بناء المهمة*/
    /**
     * رقم موضوع المهمة
     */
    public long subjId;
    /**
     * رقم المستعمل الذي اضاف المهمة
     */
    public long userId;
    public String shortTitle;


    @Override
    public String toString() {
        return "MytaskTable{" +
                "keyid=" + keyid +
                ", importance=" + importance +
                ", text='" + text + '\'' +
                ", isCompleted=" + isCompleted +
                ", time=" + time +
                ", subjId=" + subjId +
                ", userId=" + userId +
                '}';
    }
}
