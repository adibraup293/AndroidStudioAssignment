package com.example.assignment.Application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.DatabaseHelper;
import com.example.assignment.R;
import com.example.assignment.Residence.Residence;
import com.example.assignment.Residence.ViewResidenceAdminActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ViewApplicationsAdminActivity extends AppCompatActivity {

    ListView applicationsList;
    DatabaseHelper databaseHelper;

    @Override
    protected void onResume() {
        super.onResume();

        GetAllApplications();
    }

    public void GetAllApplications() {

        Intent intent = getIntent();
        int id = intent.getIntExtra("residenceID", -1);

        ArrayList<Applications> applicationsArrayList = databaseHelper.getAllApplicationByResID(id);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, applicationsArrayList);
        applicationsList.setAdapter(adapter);
        //List<Residence> residenceList = databaseHelper.GetAllResidences();
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, residenceList);
        //residenceList.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applications_admin);

        applicationsList = findViewById(R.id.applicationsListView);

        databaseHelper = new DatabaseHelper(ViewApplicationsAdminActivity.this);


    }
}
