package com.example.hananapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.hananapp.data.AppDatabase;
import com.example.hananapp.data.MySubject.MySubject;
import com.example.hananapp.data.MySubject.MySubjectQuery;
import com.example.hananapp.data.MytaskTable.MyTask;
import com.example.hananapp.data.MytaskTable.MytaskQuery;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private FloatingActionButton fabAdd;
    private SearchView srchV;
    private Spinner spnrSubject;
    private ListView lstvTasks;

    /**
     * عملية تجهيز السبنر بالمواضيع
     */
    private void initSubjectSpnr()
    {
        AppDatabase db = AppDatabase.getDB(getApplicationContext());
        MySubjectQuery subjectQuery= db.getMySubjectQuery();//عمليات جدول المعطيات
        List<MySubject> allSubjects=subjectQuery.getAllSubjects();//استخراج جميع المعطيات
        //تجهيز الوسيط
        ArrayAdapter<String> subjectAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
        subjectAdapter.add("ALL");//تظهر اولا بالسبنر تعني عرض جميع المهمات
        for (MySubject subject: allSubjects){//اضافة المواضيع للوسيط
            subjectAdapter.add(subject.title);
        }
        spnrSubject.setAdapter(subjectAdapter);//ربط السبنر بالوسيط
    }
    /**تجهيز قائمة جميع المهمات وعرضها ب ليست فيو*/
    private void initAllListView(){
        AppDatabase db=AppDatabase.getDB(getApplicationContext());
        MytaskQuery taskQuery = db.getMyTaskQuery();
        List<MyTask> allTasks =
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        fabAdd=findViewById(R.id.fabAdd);
        srchV=findViewById(R.id.srchV);
        spnrSubject=findViewById(R.id.spnrSubject);
        lstvTasks=findViewById(R.id.lstvTasks);

    }
}