package com.example.hananapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.view.textclassifier.TextClassificationSessionFactory;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class SignInActivity extends AppCompatActivity {

private TextInputEditText etE_mail;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etE_mail=findViewById(R.id.etE_mail);

    }
}