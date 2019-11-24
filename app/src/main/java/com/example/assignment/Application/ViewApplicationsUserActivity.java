package com.example.assignment.Application;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.R;

public class ViewApplicationsUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applications_user);

        String username = getIntent().getStringExtra("username");
        TextView userText = findViewById(R.id.textView1);
        String topMsg = "List of applications made by " + username;
        userText.setText(topMsg);
    }
}
