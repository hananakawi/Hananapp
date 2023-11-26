package com.example.hananapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.hananapp.data.AppDatabase;
import com.example.hananapp.data.MySubject.MySubject;
import com.example.hananapp.data.MySubject.MySubjectQuery;
import com.example.hananapp.data.MytaskTable.MyTask;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AddTaskActivity extends AppCompatActivity {
    private Button btnCancelTask;
    private Button btnSaveTask;
    private SeekBar skbrImportance;
    private TextInputEditText etShortTitle;
    private TextInputEditText etText;
    private AutoCompleteTextView autoEtSubj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        autoEtSubj=findViewById(R.id.etSubject);
       initAutoEtSubjects();
        btnCancelTask=findViewById(R.id.btnCancelTask);
        btnSaveTask=findViewById(R.id.btnSaveTask);
        skbrImportance=findViewById(R.id.skbrImportance);
        etShortTitle=findViewById(R.id.etShortTitle);
        etText=findViewById(R.id. etText);

    }

    /**
     * استخراج اسماء المواضيع من جدول مواضيع وعرضه بالحقل من نوع
     * AutoCompleteTextView
     * طريقة التعامل معه شبيه بال"سبنر"
     */
    private void initAutoEtSubjects()
    {
        //مؤشر لقاعدة البيانات
        AppDatabase db=AppDatabase.getDB(getApplicationContext());
        //مؤشر لواجهة استعلامات جدول المواضيع
        MySubjectQuery subjectQuery = db.getMySubjectQuery();
        //مصدرالمعطيات:استخراج جميع المواضيع من الجدول
        List<MySubject> allSubjects=subjectQuery.getAllSubjects();
        //تجهيز الوسيط
        ArrayAdapter<MySubject> adapter=new ArrayAdapter<MySubject>(this, android.R.layout.simple_dropdown_item_1line);
     adapter.addAll(allSubjects);//اضافة جميع المعطيات للوسيط
        autoEtSubj.setAdapter(adapter);//ربط الحقل بالوسيط
        //معالجة حدث لعرض المواضيع عند الضغط على الحقل
        autoEtSubj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoEtSubj.showDropDown();
            }
        });
    }
    private void checkShortTitleAndText()
    {

        boolean isAllOk=true; // يحوي نتيجة فحص الحقول ان كانت سليمة

        String shortTitle=etShortTitle.getText().toString();
        String text=etText.getText().toString();
        String whichsubj=autoEtSubj.getText().toString();


        int importancee=skbrImportance.getProgress();


        if (shortTitle.length()<1)
        {
            isAllOk=false;
            etShortTitle.setError("short title is empty");
        }

        if (text.length()<1)
        {
            isAllOk=false;
            etText.setError("text is empty");
        }
        if (whichsubj.length()<1)
        {
            isAllOk=false;
            autoEtSubj.setError("you didn't chose the subject");

        }

        if (isAllOk)
        {
            Toast.makeText(this,"All ok",Toast.LENGTH_SHORT).show();
            AppDatabase db=AppDatabase.getDB(getApplicationContext());
            MySubjectQuery subjectQuery=db.getMySubjectQuery();


            if (subjectQuery.checkSubject(whichsubj)==null) // فحص هل الموضوع من قبل بالجدول
            {
                //بناء موضوع جديد واضافته
                MySubject subject=new MySubject();
                subject.title=whichsubj;
                subjectQuery.insertSubject(subject);
            }
            //استخراج id الموضوع لأننا بحاجة لرقمه التسلسلي

           MySubject subject= subjectQuery.checkSubject(whichsubj);


            MyTask task=new MyTask();
            task.importance=importancee;
            task.text=text;
            task.shortTitle=shortTitle;
            task.subjId=subject.getKeyid();//تحديد رقم الموضوع للمهة
            db.getMyTaskQuery().insertAll(task);//اضافة المهمة للجدول
            finish();
        }


    }
}