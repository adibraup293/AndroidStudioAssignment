package com.example.assignment.Allocate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Application.Applications;
import com.example.assignment.DatabaseHelper;
import com.example.assignment.R;

public class allocateHousing extends AppCompatActivity {

    TextView showApplicantName;
    TextView showDateOfApplication;
    TextView showRequiredMonth;
    TextView showRequiredYear;
    TextView showStatus;
    TextView showResidenceName;
    TextView showUnitNumber;
    TextView showDuration;

    DatabaseHelper databaseHelper;
    Applications applications;

    public void acceptAllocate(View view){
        applications.setStatus("Accept");


    }

    public void declineAllocate(View view){
        applications.setStatus("Decline");
    }

    public void waitlistAllocate(View view){
        applications.setStatus("Waitlist");
    }

    public void back(View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_housing);

        databaseHelper = new DatabaseHelper(this);

        showApplicantName = findViewById(R.id.nameShow);
        showDateOfApplication = findViewById(R.id.dateShow);
        showRequiredMonth = findViewById(R.id.monthShow);
        showRequiredYear = findViewById(R.id.yearShow);
        showStatus = findViewById(R.id.statusShow);
        showResidenceName = findViewById(R.id.residenceShow);
        showUnitNumber = findViewById(R.id.unitShow);
        showDuration = findViewById(R.id.durationShow);

        Intent intent = getIntent();
        int id = intent.getIntExtra("ApplicationID", -1);

        if (id != -1) {

            applications = databaseHelper.getApplicantsApplication(id);

            showStatus.setText(applications.getStatus());
            showRequiredMonth.setText(applications.getRequiredMonth());
            showRequiredYear.setText(applications.getRequiredYear());
            showDateOfApplication.setText(applications.getApplicationDate());
        }
    }
}
