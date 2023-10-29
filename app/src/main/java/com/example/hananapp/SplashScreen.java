package com.example.hananapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hananapp.data.AppDatabase;
import com.example.hananapp.data.MySubject.MySubjectQuery;
import com.example.hananapp.data.MySubject.mySubject;

public class SplashScreen extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        Log.d("HA","onCreate");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        //هو كائن مرئي يعرض رسالة نصصية على الشاشة لفترة قصيرة ثم تختفي تلقائيا
        //بناء قاعدة بينات وارجاع موشر عليها
        AppDatabase db=AppDatabase.getDB(getApplicationContext());
        //مؤشر للجدول
        MySubjectQuery subjectQuery = db.getMySubjectQuery();
       // بناء كائن من نوع الجدول وتحديد قيم الصفات
         mySubject s1=new mySubject();
         s1.title=("math");
        mySubject s2=new mySubject();
        s2.title="computers";
        //اضافة كائن للجدول
      subjectQuery.insertSubject(s1);
      subjectQuery.insertSubject(s2);

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
}
