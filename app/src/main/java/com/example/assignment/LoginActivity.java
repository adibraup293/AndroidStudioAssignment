package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    SQLiteHelperUser sqliteHelperUser;
    Button loginButton;

    public void LoginButton(View view){

        //Get values from edittext
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        //authenticate User
        User currentUser = sqliteHelperUser.Authenticate(new User(username,password));

        //check if authentication is successful or not
        if (currentUser !=null){
            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
            //User Logged in successfully
            
            Intent userHomeIntent = new Intent(LoginActivity.this, UserHomeActivity.class);
            startActivity(userHomeIntent);
        } else {
            Toast.makeText(this, "No user found", Toast.LENGTH_SHORT).show();
            //User Logged in failed
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

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginButton = findViewById(R.id.loginBtn);

        sqliteHelperUser = new SQLiteHelperUser(this);
    }
}
