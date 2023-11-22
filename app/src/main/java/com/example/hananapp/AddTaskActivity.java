package com.example.hananapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.hananapp.data.AppDatabase;
import com.example.hananapp.data.MySubject.MySubject;
import com.example.hananapp.data.MySubject.MySubjectQuery;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AddTaskActivity extends AppCompatActivity {
    private Button btnCancelTask;
    private Button btnSaveTask;
    private SeekBar skbrImportance;
    private TextInputLayout etShortTitle;
    private TextInputLayout etText;
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
       // ArrayAdapter<MySubjectQuery> adapter=new ArrayAdapter<>()
    }
}