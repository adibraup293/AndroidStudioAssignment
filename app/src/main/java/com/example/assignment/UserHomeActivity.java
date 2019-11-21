package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserHomeActivity extends AppCompatActivity {

    public void back(View view) {
        finish();
        Intent mainActivity = new Intent(UserHomeActivity.this, MainActivity.class);
        startActivity(mainActivity);
    }

    public void openViewResidenceAplicant(View view){
        Intent viewResidenceApplicant = new Intent(UserHomeActivity.this, ViewResidenceApplicantActivity.class);
        startActivity(viewResidenceApplicant);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        String adminUsername = getIntent().getStringExtra("username");
        TextView adminText = findViewById(R.id.applicantTextView);
        String welcomeMsg = "Welcome back " + adminUsername;
        adminText.setText(welcomeMsg);
    }
}
