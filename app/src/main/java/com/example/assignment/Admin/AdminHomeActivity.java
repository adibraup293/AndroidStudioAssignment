package com.example.assignment.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.MainActivity;
import com.example.assignment.R;
import com.example.assignment.Residence.SetUpResidenceActivity;
import com.example.assignment.Residence.ViewResidenceAdminActivity;

public class AdminHomeActivity extends AppCompatActivity {

    public void back(View view) {
        finish();
        Intent mainActivity = new Intent(AdminHomeActivity.this, MainActivity.class);
        startActivity(mainActivity);
    }

    public void openRegisterResidence(View view){
        Intent setupResidenceIntent = new Intent(AdminHomeActivity.this, SetUpResidenceActivity.class);
        startActivity(setupResidenceIntent);
    }

    public void openViewApplications(View view){
        Intent viewApplicationAsAdminIntent = new Intent(AdminHomeActivity.this, ViewResidenceAdminActivity.class);
        startActivity(viewApplicationAsAdminIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        String adminUsername = getIntent().getStringExtra("username");
        TextView adminText = findViewById(R.id.adminTextView);
        String welcomeMsg = "Welcome back " + adminUsername;
        adminText.setText(welcomeMsg);
    }
}
