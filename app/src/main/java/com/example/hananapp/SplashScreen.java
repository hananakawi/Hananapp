package com.example.hananapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hananapp.data.AppDatabase;
import com.example.hananapp.data.MySubject.MySubjectQuery;
import com.example.hananapp.data.MySubject.MySubject;

public class SplashScreen extends AppCompatActivity
{
private Button btnGo;
private TextView tvWelcomeToHananApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        btnGo=findViewById(R.id.btnGo);
        tvWelcomeToHananApp=findViewById(R.id.tvWelcomeToHananApp);
        Log.d("HA","onCreate");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        //هو كائن مرئي يعرض رسالة نصصية على الشاشة لفترة قصيرة ثم تختفي تلقائيا
        //بناء قاعدة بينات وارجاع موشر عليها
        AppDatabase db=AppDatabase.getDB(getApplicationContext());
        //مؤشر للجدول
        MySubjectQuery subjectQuery = db.getMySubjectQuery();
       // بناء كائن من نوع الجدول وتحديد قيم الصفات
         MySubject s1=new MySubject();
         s1.title=("math");
        MySubject s2=new MySubject();
        s2.title="computers";
        //اضافة كائن للجدول
      subjectQuery.insertSubject(s1);
      subjectQuery.insertSubject(s2);
        Handler h=new Handler();
        Runnable r=new Runnable() {
            public void run()
            {
                //to open new activity from current to next
                Intent i= new Intent(SplashScreen.this, SignInActivity.class);
                startActivity(i);
                //to close current activity
                finish();
            }
        };
        h.postDelayed(r,3000);
    }






}
