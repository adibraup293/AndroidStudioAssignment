package com.example.assignment.Application;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.DatabaseHelper;
import com.example.assignment.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CreateApplicationsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public void back(View view) {
        finish();
    }

    EditText applicationDate;
    private static final String[] paths = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    String reqMonth;
    EditText requiredYear;
    private Spinner requiredMonth;

    DatabaseHelper databaseHelper;

    public void createApplicationsButton(View view){

        Intent intent = getIntent();
        int resid = intent.getIntExtra("id", -1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());


        String selectMonth = String.valueOf(requiredMonth.getSelectedItem());
        int selectYear = Integer.parseInt(requiredYear.getText().toString());

        Applications applications = new Applications(currentDateandTime, selectMonth, selectYear, "New", resid);


        databaseHelper.addApplications(applications);

        Toast.makeText(this, "You have applied an application", Toast.LENGTH_SHORT).show();

        databaseHelper.close();
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (position) {
            case 0:
                reqMonth = "January";
                break;
            case 1:
                reqMonth = "February";
                break;
            case 2:
                reqMonth = "March";
                break;
            case 3:
                reqMonth = "April";
                break;
            case 4:
                reqMonth = "May";
                break;
            case 5:
                reqMonth = "June";
                break;
            case 6:
                reqMonth = "July";
                break;
            case 7:
                reqMonth = "August";
                break;
            case 8:
                reqMonth = "September";
                break;
            case 9:
                reqMonth = "October";
                break;
            case 10:
                reqMonth = "November";
                break;
            case 11:
                reqMonth = "December";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //TODO Auto-generated method stub
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_applications);

        requiredMonth = findViewById(R.id.monthEditText);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateApplicationsActivity.this,
                android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        requiredMonth.setAdapter(adapter);
        requiredMonth.setOnItemSelectedListener(this);

        requiredYear = findViewById(R.id.yearEditText);

        databaseHelper = new DatabaseHelper(CreateApplicationsActivity.this);
    }
}
