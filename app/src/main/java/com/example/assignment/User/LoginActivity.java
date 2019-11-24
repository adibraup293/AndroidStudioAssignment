package com.example.assignment.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Admin.AdminHomeActivity;
import com.example.assignment.DatabaseHelper;
import com.example.assignment.R;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    DatabaseHelper databaseHelper;
    Button loginButtonApplicant;
    Button loginButtonAdmin;
    RadioGroup radioGroup;
    RadioButton applicantRB;
    RadioButton adminRB;

    public void back(View view) {
        finish();
    }

    public void LoginButtonAdmin(View view) {

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        //authenticate User
        User currentUser = databaseHelper.loginUser(new User(username, password));

        //check if authentication is successful or not
        if (currentUser != null) {
            Toast.makeText(this, "Logging in as " + username, Toast.LENGTH_SHORT).show();
            //Officer Logged in successfully

            Intent adminHomeIntent = new Intent(LoginActivity.this, AdminHomeActivity.class);
            adminHomeIntent.putExtra("username", username);
            startActivity(adminHomeIntent);
            //

        } else {
            Toast.makeText(this, "No user found. Please create an account!", Toast.LENGTH_LONG).show();
            //User Logged in failed.
        }
    }


    public void LoginButtonApplicant(View view) {

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        
        //authenticate User
        User currentUser = databaseHelper.loginUser( new User(username,password));

        //check if authentication is successful or not
        if (currentUser != null) {
            Toast.makeText(this, "Logging in as " + username, Toast.LENGTH_SHORT).show();
            //User Logged in successfully

            Intent userHomeIntent = new Intent(LoginActivity.this, UserHomeActivity.class);
            userHomeIntent.putExtra("username", username);
            startActivity(userHomeIntent);

        } else {
            Toast.makeText(this, "No user found. Please create an account!", Toast.LENGTH_LONG).show();
            //User Logged in failed.
        }
    }

    public void openSignUpPage(View view)
    {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        radioGroup = findViewById(R.id.radioGroupLogin);
        applicantRB = findViewById(R.id.userBtn);
        adminRB = findViewById(R.id.adminBtn);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginButtonApplicant = findViewById(R.id.applicantLoginBtn);
        loginButtonAdmin = findViewById(R.id.adminLoginBtn);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                applicantRB.isSelected();
                if (checkedId == R.id.applicantBtn) {
                    usernameEditText.setVisibility(View.VISIBLE);
                    passwordEditText.setVisibility(View.VISIBLE);
                    loginButtonApplicant.setVisibility(View.VISIBLE);
                    loginButtonAdmin.setVisibility(View.INVISIBLE);

                } else if (checkedId == R.id.adminBtn) {
                    usernameEditText.setVisibility(View.VISIBLE);
                    passwordEditText.setVisibility(View.VISIBLE);
                    loginButtonApplicant.setVisibility(View.INVISIBLE);
                    loginButtonAdmin.setVisibility(View.VISIBLE);
                }
            }
        });


        databaseHelper = new DatabaseHelper(this);
    }
}
