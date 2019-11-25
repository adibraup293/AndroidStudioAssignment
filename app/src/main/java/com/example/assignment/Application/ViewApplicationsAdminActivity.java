package com.example.assignment.Application;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Allocate.Allocation;
import com.example.assignment.Allocate.allocateHousing;
import com.example.assignment.DatabaseHelper;
import com.example.assignment.R;
import com.example.assignment.Residence.Residence;
import com.example.assignment.Residence.ViewResidenceAdminActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ViewApplicationsAdminActivity extends AppCompatActivity {

    ListView applicationsListView;
    DatabaseHelper databaseHelper;
    Applications applications;

    @Override
    protected void onResume() {
        super.onResume();

        GetAllApplications();
    }

    public void GetAllApplications(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("applicationID", -1);

        List<Applications> applicationsList = databaseHelper.getAllApplicationByResID(id);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, applicationsList);
        applicationsListView.setAdapter(adapter);
    }

    public void viewApplications(View view){
        if (applications != null) {
            Intent viewApplicationsIntent = new Intent(this, allocateHousing.class);
            viewApplicationsIntent.putExtra("applicationID", applications.getApplicationID());
            startActivity(viewApplicationsIntent);
        } else {
            Toast.makeText(this, "Cannot found Application", Toast.LENGTH_SHORT).show();
        }

    }

    public void backButton(View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applications_admin);

        databaseHelper = new DatabaseHelper(this);

        applicationsListView = findViewById(R.id.applicationsListView);

        applicationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                applications = (Applications) applicationsListView.getItemAtPosition(position);
                for (int i = 0; i < applicationsListView.getChildCount(); i++)
                    if (position == i){
                        applicationsListView.getChildAt(i).setBackgroundColor(Color.YELLOW);
                    } else{
                        applicationsListView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
            }
        });
        GetAllApplications();
    }
}
