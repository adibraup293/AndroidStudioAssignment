package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}
