package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class CreateApplicationsActivity extends AppCompatActivity {

    EditText applicationDate;
    EditText requiredMonth;
    EditText requiredYear;
    Residence residence;

    SQLiteHelperApplications sqLiteHelperApplications;

    public void createApplicationsButton(View view){
        Applications applications = new Applications();
        applications.setApplicationDate(applicationDate.getText().toString().trim());
        applications.setRequiredMonth(requiredMonth.getText().toString().trim());
        applications.setRequiredYear(Integer.parseInt(requiredYear.getText().toString()));

        sqLiteHelperApplications.addApplications(applications);

        Toast.makeText(this, "You have applied an application", Toast.LENGTH_SHORT).show();

        sqLiteHelperApplications.close();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_applications);

        applicationDate = findViewById(R.id.currentDateEditText);
        requiredMonth = findViewById(R.id.monthEditText);
        requiredYear = findViewById(R.id.yearEditText);

        sqLiteHelperApplications = new SQLiteHelperApplications(this);

        //
        //Intent intent = getIntent();
        //int id = intent.getIntExtra("id", -1);

        //if (id != -1){


         //   contact = databaseHandler.GetContact(id);
        //    nameEditText.setText(contact.getName());
         //   phoneNumberEditText.setText(contact.getPhoneNumber());
        //}
    }
}
