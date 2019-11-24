package com.example.assignment.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Application.ViewApplicationsUserActivity;
import com.example.assignment.MainActivity;
import com.example.assignment.R;
import com.example.assignment.Residence.ViewResidenceApplicantActivity;

public class UserHomeActivity extends AppCompatActivity {

    String adminUsername;

    public void back(View view) {
        finish();
        Intent mainActivity = new Intent(UserHomeActivity.this, MainActivity.class);
        startActivity(mainActivity);
    }

    public void openViewResidenceApplicant(View view) {
        Intent viewResidenceApplicant = new Intent(UserHomeActivity.this, ViewResidenceApplicantActivity.class);
        viewResidenceApplicant.putExtra("username", adminUsername);
        startActivity(viewResidenceApplicant);
    }

    public void openViewApplicationsApplicant(View view) {
        Intent viewApplicationsApplicant = new Intent(UserHomeActivity.this, ViewApplicationsUserActivity.class);
        startActivity(viewApplicationsApplicant);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        adminUsername = getIntent().getStringExtra("username");
        TextView adminText = findViewById(R.id.applicantTextView);
        String welcomeMsg = "Welcome back " + adminUsername;
        adminText.setText(welcomeMsg);
    }
}
