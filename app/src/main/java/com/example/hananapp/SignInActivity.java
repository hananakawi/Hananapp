package com.example.hananapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hananapp.data.AppDatabase;
import com.example.hananapp.data.usersTable.MyUser;
import com.example.hananapp.data.usersTable.MyuserQuery;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private TextInputEditText etE_mail;
    private TextInputEditText etpassword;
    private Button btnSignIn;
    private Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etE_mail = findViewById(R.id.etE_mail);
        etpassword = findViewById(R.id.etpassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
    }

    public void onClickSignUP(View v) {
        //to open new activity from current to next activity
        Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(i);
    }

    private void checkEmailPass() {
        boolean isAllOk = true; // يحوي نتيجة فحص الحقول ان كانت سليمة

        // استخراج النص من حقل الايميل
        String email = etE_mail.getText().toString();

        // استخراج نص كلمة المرور
        String pass = etpassword.getText().toString();

        // فحص الايميل ان كان طوله أقل من 6 أو لا يحتوي على @ فهو خاطئ
        if (email.length() < 6 || email.contains("@") == false) {
            // تعديل المتغير ليدل على أن الفحص يعطي نتيجة خاطئة
            isAllOk = false;
            // عرض ملاحظة خطأ على الشاشة داخل حقل البريد
            etE_mail.setError("wrong email");
        }
        if (pass.length() < 8 || pass.contains(" ") == true) {
            isAllOk = false;
            etpassword.setError("Wrong password");
        }


        if (isAllOk) {
            Toast.makeText(this, "All ok", Toast.LENGTH_SHORT).show();
            // بناء قاعدة بيانات وارجاع مؤشر عليها
            AppDatabase db = AppDatabase.getDB(getApplicationContext());
            //مؤشر لكائن عمليات الجدول
            MyuserQuery userQuery = (MyuserQuery) db.getMyUserQuery();
            MyUser myUser = userQuery.checkEmailPassword(email, pass);
            if (myUser == null) {
                Toast.makeText(this, "wrong email or password", Toast.LENGTH_LONG).show();

            } else {
                Intent i = new Intent(SignInActivity.this, MainActivity2.class);
                startActivity(i);
                finish();
            }
        }
    }
        private void checkEmailPass_FB()
        {
            boolean isAllOk = true; // يحوي نتيجة فحص الحقول ان كانت سليمة

            // استخراج النص من حقل الايميل
            String Email = etE_mail.getText().toString();

            // استخراج نص كلمة المرور
            String password = etpassword.getText().toString();

            // فحص الايميل ان كان طوله أقل من 6 أو لا يحتوي على @ فهو خاطئ
            if (Email.length() < 5 || Email.contains("@") == false) {
                // تعديل المتغير ليدل على أن الفحص يعطي نتيجة خاطئة
                isAllOk = false;
                // عرض ملاحظة خطأ على الشاشة داخل حقل البريد
                etE_mail.setError("wrong email");
            }
            if (password.length() < 8 || password.contains(" ") == true) {
                isAllOk = false;
                etpassword.setError("Wrong password");
            }


            if (isAllOk) {
                Toast.makeText(this, "All ok", Toast.LENGTH_SHORT).show();
                FirebaseAuth auth = FirebaseAuth.getInstance();//بناء كائن لعملية التسجيل
                auth.signInWithEmailAndPassword(Email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())//هل العملية ناجحة
                        {
                            Toast.makeText(SignInActivity.this, "Signing In succeeded", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignInActivity.this, MainActivity2.class);
                            startActivity(i);

                        } else {
                            Toast.makeText(SignInActivity.this, "Signing In failed", Toast.LENGTH_SHORT).show();
                            etE_mail.setError(task.getException().getMessage());//ظهور رسالة الخاطئة من السحابة

                        }
                    }
                });
            }
        }


    public void onClicktoSignin(View v) {
        checkEmailPass();
    }

    public void onClicktoSigninfirebase(View v) {
        checkEmailPass_FB();
    }
    public void onClickSignUPfirebase(View v) {
        //to open new activity from current to next activity
        Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(i);
    }
}
