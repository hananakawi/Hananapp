package com.example.hananapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hananapp.data.AppDatabase;
import com.example.hananapp.data.usersTable.MyuserQuery;
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
    public void onClickSignUP (View v)
    {
        //to open new activity from current to next activity
        Intent i= new Intent(SignInActivity.this,   SignUpActivity.class);
        startActivity(i);
    }
    private void checkEmailPass()
    {
        boolean isAllOk=true; // يحوي نتيجة فحص الحقول ان كانت سليمة

        // استخراج النص من حقل الايميل
        String email= etE_mail.getText().toString();

        // استخراج نص كلمة المرور
        String pass= etpassword.getText().toString();

        // فحص الايميل ان كان طوله أقل من 6 أو لا يحتوي على @ فهو خاطئ
        if (email.length()<6 || email.contains("@")== false)
        {
            // تعديل المتغير ليدل على أن الفحص يعطي نتيجة خاطئة
            isAllOk=false;
            // عرض ملاحظة خطأ على الشاشة داخل حقل البريد
            etE_mail.setError("wrong email");
        }
        if (pass.length()<8 || pass.contains(" ")== true)
        {
            isAllOk=false;
            etpassword.setError("Wrong password");
        }


        if (isAllOk)
        {
            Toast.makeText(this,"All ok", Toast.LENGTH_SHORT).show();
            // بناء قاعدة بيانات وارجاع مؤشر عليها
            AppDatabase db=AppDatabase.getDB(getApplicationContext());
            //مؤشر لكائن عمليات الجدول
            MyuserQuery userQuery = (MyuserQuery) db.getMyUserQuery();
            MyuserQuery MyUser = userQuery.checkEmailpassword(email, pass);
            if (MyUser==null){
                Toast.makeText(this, "wrong email or password", Toast.LENGTH_LONG).show();

            }
            else
            {
                Intent i=new Intent(SignInActivity.this, MainActivity2.class);
                startActivity(i);
                finish();
            }
        }
        }

    public void onClicktoSignin(View v)
    {
        checkEmailPass();
    }
}