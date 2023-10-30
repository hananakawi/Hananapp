package com.example.hananapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.google.android.material.textfield.TextInputLayout;

public class EditTaskActivity extends AppCompatActivity {
    private Button btnUpdateTask;
    private Button btnCancleTask;
    private EditText tvImportance;
    private SeekBar skbImportance;
    private TextInputLayout etShortTitle;
    private TextInputLayout etText;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        tvImportance=findViewById(R.id.tvImportance);
        btnUpdateTask=findViewById(R.id.btnUpdateTask);
        btnCancleTask=findViewById(R.id.btnCancleTask);
        tvImportance=findViewById(R.id.tvImportance);
        skbImportance=findViewById(R.id.skbImportance);
        etShortTitle=findViewById(R.id.etShortTitle);
        etText=findViewById(R.id. etText);
    }
}