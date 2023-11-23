package com.example.hananapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
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
        //معالج حدث لاختيار موضوع بالسبنر
        spnrSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //استخراج الموضوع حسب رقمه الترتيبي
                String item = subjectAdapter.getItem(i);
                if(item.equals("ALL"))//هذه يعني عرض جميع المهام
                    initAllListView();
                else {
                    //استخراج كائن الموضوع الذي اخترناه لاستخراج رقمه
                    MySubject subject = subjectQuery.checkSubject(item);
                    //استدعاء العملية التي تجهز القائمة حسب رقم الموضوع
                    initListViewBySubjId(subject.getKeyid());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    /**تجهيز قائمة جميع المهمات وعرضها ب ليست فيو*/
    private void initAllListView(){
        AppDatabase db=AppDatabase.getDB(getApplicationContext());
        MytaskQuery taskQuery = db.getMyTaskQuery();
        List<MyTask> allTasks = taskQuery.getAllTasks();
        ArrayAdapter<MyTask>tsksAdapter=new ArrayAdapter<MyTask>(this, android.R.layout.simple_list_item_1);
        tsksAdapter.addAll(allTasks);
        lstvTasks.setAdapter(tsksAdapter);

    }

    /**
     * تجهيز قائمة المهمات حسب رقم الموضوع
     * @param  Key_id*رقم الموضوع
     */
    private void initListViewBySubjId(long Key_id)
    {
        AppDatabase db=AppDatabase.getDB(getApplicationContext());
        MytaskQuery taskQuery = db.getMyTaskQuery();
        //يجب اضافة عملية اعيد جميع المهمات حسب رقم الموضوع
        List<MyTask> allTasks = taskQuery.getTasksBySubjId(Key_id);
        ArrayAdapter<MyTask> taksAdapter=new ArrayAdapter<MyTask>(this, android.R.layout.simple_list_item_1);
        taksAdapter.addAll(allTasks);
        lstvTasks.setAdapter(taksAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        fabAdd=findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity2.this,.class);
                startActivity(i);
            }
        });
        srchV=findViewById(R.id.srchV);
        spnrSubject=findViewById(R.id.spnrSubject);
        lstvTasks=findViewById(R.id.lstvTasks);
        fabAdd=findViewById(R.id.fabAdd);


    }
}