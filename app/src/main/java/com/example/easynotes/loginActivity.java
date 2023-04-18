package com.example.easynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    private EditText mLoginEmailText, mLoginPasswordText;
    private Button mLoginButton;
    private TextView mSignupSwitch, mForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        mLoginEmailText = findViewById(R.id.loginEmailText);
        mLoginPasswordText = findViewById(R.id.loginPasswordText);
        mLoginButton= findViewById(R.id.loginButton);
        mSignupSwitch = findViewById(R.id.signUpSwitch);
        mForgotPassword = findViewById(R.id.forgetPasswordBtn);

        mSignupSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this,signUpActivity.class);
                startActivity(intent);
            }
        });

        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this,ForgotPassword.class);
                startActivity(intent);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mLoginEmailText.getText().toString().trim();
                String password = mLoginPasswordText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
                }
                else {
//                     TODO : verify the user
                }
            }
        });
    }
}