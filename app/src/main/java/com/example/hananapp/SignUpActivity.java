package com.example.hananapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {
    private Button btnCancel;
    private Button btnSave;
    private TextInputLayout etE_mail;
    private TextInputLayout etpassword;
    private TextInputLayout etrepassword;
    private TextInputLayout etname;
    private TextInputLayout etphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnCancel=findViewById(R.id.btnCancel);
    }
}