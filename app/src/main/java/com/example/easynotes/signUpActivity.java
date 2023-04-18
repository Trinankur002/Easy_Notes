package com.example.easynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signUpActivity extends AppCompatActivity {

    private EditText mSignupEmailText, msignupPasswordText;
    private Button mSignupButton;
    private TextView mLoginSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        mSignupEmailText = findViewById(R.id.SignupEmailText);
        msignupPasswordText = findViewById(R.id.signupPasswordText);
        mSignupButton= findViewById(R.id.signUpButton);
        mLoginSwitch=findViewById(R.id.loginSwitch);

        mLoginSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(signUpActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mSignupEmailText.getText().toString().trim();
                String password = msignupPasswordText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
                }
                else if (password.length()<4){
                    Toast.makeText(getApplicationContext(),"Password can not be less than 4 character ",Toast.LENGTH_SHORT).show();
                }

                else {
//                     TODO : Register the user
                }
            }
        });
    }
}