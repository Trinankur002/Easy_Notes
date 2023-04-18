package com.example.easynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    private EditText ForgetPasswordEmail;
    private Button PasswordRecoveryButton;
    private TextView BackToLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getSupportActionBar().hide();

        ForgetPasswordEmail=findViewById(R.id.forgetPasswordEmail);
        PasswordRecoveryButton=findViewById(R.id.passwordRecoveryButton);
        BackToLogin=findViewById(R.id.backToLogin);

        BackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ForgotPassword.this,loginActivity.class);
                startActivity(intent);
            }
        });

        PasswordRecoveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ForgetPasswordEmail.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter your registered email first",Toast.LENGTH_SHORT).show();
                }

                else {
//                    TODO :send a mail to user
                }
            }
        });

    }
}