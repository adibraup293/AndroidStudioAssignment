package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserHomeActivity extends AppCompatActivity {

    public void openViewResidenceAplicant(View view){
        Intent viewResidenceApplicant = new Intent(UserHomeActivity.this, ViewResidenceApplicantActivity.class);
        startActivity(viewResidenceApplicant);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

    }
}
