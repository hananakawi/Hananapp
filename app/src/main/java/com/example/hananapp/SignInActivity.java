package com.example.hananapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.view.textclassifier.TextClassificationSessionFactory;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class SignInActivity extends AppCompatActivity {

private TextInputEditText etE_mail;
private TextInputEditText etpassword;
private Button btnSignIn;
private Button  btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etE_mail=findViewById(R.id.etE_mail);
        etpassword=findViewById(R.id.etpassword);
        btnSignIn=findViewById(R.id. btnSignIn);
        btnSignUp=findViewById(R.id.btnSignUp);
    }
}