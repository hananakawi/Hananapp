package com.example.hananapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import com.google.android.material.textfield.TextInputLayout;

public class AddTaskActivity extends AppCompatActivity {
    private Button btnCancelTask;
    private Button btnSaveTask;
    private SeekBar skbrImportance;
    private TextInputLayout etShortTitle;
    private TextInputLayout etText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        btnCancelTask=findViewById(R.id.btnCancelTask);
        btnSaveTask=findViewById(R.id.btnSaveTask);
        skbrImportance=findViewById(R.id.skbrImportance);
        etShortTitle=findViewById(R.id.etShortTitle);
        etText=findViewById(R.id. etText);

    }
}