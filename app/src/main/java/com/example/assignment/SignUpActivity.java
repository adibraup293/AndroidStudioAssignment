package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    EditText nameEditText;
    EditText emailEditText;
    EditText monthlyIncomeEditText;
    SQLiteHelperUser sqliteHelperUser;

    public void registerUser(View view){
        String username =usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        Double monthlyIncome = Double.parseDouble(monthlyIncomeEditText.getText().toString());



        sqliteHelperUser.insertUserDetails(username,password,name,email,monthlyIncome);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sqliteHelperUser = new SQLiteHelperUser(SignUpActivity.this);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        monthlyIncomeEditText = findViewById(R.id.monthlyIncomeEditText);
    }
}
