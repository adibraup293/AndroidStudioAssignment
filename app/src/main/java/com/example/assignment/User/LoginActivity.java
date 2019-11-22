package com.example.assignment.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Admin.SQLiteHelperUserAdmin;
import com.example.assignment.Admin.AdminHomeActivity;
import com.example.assignment.Admin.UserAdmin;
import com.example.assignment.R;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    SQLiteHelperUser sqliteHelperUser;
    SQLiteHelperUserAdmin sqliteHelperUserAdmin;
    Button loginButton;

    public void back(View view) {
        finish();
    }

    public void LoginButton(View view){

        //Get values from edittext
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        //authenticate User
        User currentUser = sqliteHelperUser.Authenticate(new User(username,password));
        UserAdmin currentAdmin = sqliteHelperUserAdmin.AuthenticateAdmin(new UserAdmin(username,password));

        //check if authentication is successful or not
        if (currentUser !=null ){
            Toast.makeText(this, "Logging in as " + currentUser.getUsername(), Toast.LENGTH_SHORT).show();
            //User Logged in successfully

            Intent userHomeIntent = new Intent(LoginActivity.this, UserHomeActivity.class);
            userHomeIntent.putExtra("username", username);
            startActivity(userHomeIntent);

        } else if(currentAdmin !=null){
            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
            //Officer Logged in successfully

            Intent adminHomeIntent = new Intent(LoginActivity.this, AdminHomeActivity.class);
            adminHomeIntent.putExtra("username", username);
            startActivity(adminHomeIntent);
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

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginButton = findViewById(R.id.loginBtn);

        sqliteHelperUser = new SQLiteHelperUser(this);
        sqliteHelperUserAdmin = new SQLiteHelperUserAdmin(this);
    }
}