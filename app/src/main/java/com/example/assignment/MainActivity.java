package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.User.LoginActivity;
import com.example.assignment.User.SignUpActivity;

public class MainActivity extends AppCompatActivity {

    public void openSignUpPage(View view) {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
