package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminHomeActivity extends AppCompatActivity {

    public void openRegisterResidence(View view){
        Intent setupResidenceIntent = new Intent(AdminHomeActivity.this, SetUpResidenceActivity.class);
        startActivity(setupResidenceIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }
}
