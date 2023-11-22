package com.example.hananapp.data.MySubject;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class MySubject
{

    @PrimaryKey(autoGenerate = true)
   public long key_id;
    public String title;


    public String getTitle() {
        return title;
    }

    public long getKeyid() {
        return key_id;
    }

    @Override
    public String toString() {
        return "mySubject{" +
                "key_id=" + key_id +
                ", title='" + title + '\'' +
                '}';
    }
}
