package com.example.hananapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

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
    private void initSubjectSpnr() {
        AppDatabase db = AppDatabase.getDB(getApplicationContext());
        MySubjectQuery subjectQuery = db.getMySubjectQuery();//عمليات جدول المعطيات
        List<MySubject> allSubjects = subjectQuery.getAllSubjects();//استخراج جميع المعطيات
        //تجهيز الوسيط
        ArrayAdapter<String> subjectAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
        subjectAdapter.add("ALL");//تظهر اولا بالسبنر تعني عرض جميع المهمات
        for (MySubject subject : allSubjects) {//اضافة المواضيع للوسيط
            subjectAdapter.add(subject.title);
        }
        spnrSubject.setAdapter(subjectAdapter);//ربط السبنر بالوسيط
        //معالج حدث لاختيار موضوع بالسبنر
        spnrSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //استخراج الموضوع حسب رقمه الترتيبي
                String item = subjectAdapter.getItem(i);
                if (item.equals("ALL"))//هذه يعني عرض جميع المهام
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

    /**
     * تجهيز قائمة جميع المهمات وعرضها ب ليست فيو
     */
    private void initAllListView() {
        AppDatabase db = AppDatabase.getDB(getApplicationContext());
        MytaskQuery taskQuery = db.getMyTaskQuery();
        List<MyTask> allTasks = taskQuery.getAllTasks();
        ArrayAdapter<MyTask> tsksAdapter = new ArrayAdapter<MyTask>(this, android.R.layout.simple_list_item_1);
        tsksAdapter.addAll(allTasks);
        lstvTasks.setAdapter(tsksAdapter);
        lstvTasks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override//رقم العنصر الذي سبب الحدث
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showMenu(view,tsksAdapter.getItem(position));//رقم العنصر الذي سبب الحدث
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * تجهيز قائمة المهمات حسب رقم الموضوع
     *
     * @param Key_id*رقم الموضوع
     */
    private void initListViewBySubjId(long Key_id) {
        AppDatabase db = AppDatabase.getDB(getApplicationContext());
        MytaskQuery taskQuery = db.getMyTaskQuery();
        //يجب اضافة عملية اعيد جميع المهمات حسب رقم الموضوع
        List<MyTask> allTasks = taskQuery.getTasksBySubjId(Key_id);
        ArrayAdapter<MyTask> taksAdapter = new ArrayAdapter<MyTask>(this, android.R.layout.simple_list_item_1);
        taksAdapter.addAll(allTasks);
        lstvTasks.setAdapter(taksAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, AddTaskActivity.class);
                startActivity(i);
            }
        });
        srchV = findViewById(R.id.srchV);
        spnrSubject = findViewById(R.id.spnrSubject);
        lstvTasks = findViewById(R.id.lstvTasks);
        fabAdd = findViewById(R.id.fabAdd);


    }

    @Override//بناء قائمة
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override//معالجة حدث اختيار عنصر من القائمة
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itmSetting) {
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
        }
        if ((item.getItemId() == R.id.itmLogOut)) {
            Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show();
            showYesNoDialog();
        }
        if (item.getItemId() == R.id.itmAddTask) {
            Toast.makeText(MainActivity2.this, "Add", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity2.this, AddTaskActivity.class);
            startActivity(i);

        }

        return true;
    }

    /**
     * دالة مساعدة لفتح قائمة تتلقى بارمتر للكائن الذي سبب فتح القائمة
     */
    public void showMenu(View v,MyTask t) {
        //بناء قائمة popup menu
        PopupMenu popup = new PopupMenu(this, v);//الكائن الذي سبب فتح القائمة v
        //ملف القائمة
        popup.inflate(R.menu.popup_menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.itmEdit) {
                    Toast.makeText(MainActivity2.this, "Edit", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.itmDelete) {
                    Toast.makeText(MainActivity2.this, "Delete", Toast.LENGTH_SHORT).show();
                }

            return true;
        }
        });

        popup.show();//فتح وعرض القائمة
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("HA","onRestart");
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("HA","onStart");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("HA","onResume");
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        initSubjectSpnr();
        initAllListView();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("HA","onPause");
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("HA","onDestroy");
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("HA","onStop");
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    /**
     * بناء ديالوج
     */
    public void showYesNoDialog()
    {
      //تجهيز بناء شباك حوار"ديالوغ" يتلقى بارمتر مؤشر للنشاط (الاكتيفيتي)الحالي
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Log out");//تحديد العنوان
        builder.setMessage("Are you sure?");//تحدي فحوى شباك الحوار
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //معالجة حدث للموافقة
                Toast.makeText(MainActivity2.this, "Signing out", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        //الص على الزر ومعالجة الحدث
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //معالجة الحدث الموافقة
                Toast.makeText(MainActivity2.this, "not Signing out", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog= builder.create();//بناء شباك الحوار - ديالوغ
        dialog.show();//عرض الشباك
    }

}
