package com.example.assignment.Application;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.DatabaseHelper;
import com.example.assignment.R;

import java.util.List;

public class ViewApplicationsUserActivity extends AppCompatActivity {

    ListView applicationsSubmitted;
    Applications applications;
    DatabaseHelper databaseHelper;

    public void GetAllApplications(){
        List<Applications> applicationsList = databaseHelper.GetAllApplications();
        ArrayAdapter<Applications> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, applicationsList);
        applicationsSubmitted.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        GetAllApplications();
    }

    public void WithdrawApplicationBtn(View view){
        databaseHelper.WithdrawApplication();
        GetAllApplications();
    }

    public void back(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applications_user);

        databaseHelper = new DatabaseHelper(this);

        applicationsSubmitted = findViewById(R.id.applicationsListView);

        applicationsSubmitted.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                applications = (Applications) applicationsSubmitted.getItemAtPosition(position);
                for (int i = 0; i < applicationsSubmitted.getChildCount(); i++)
                    if (position == i){
                        applicationsSubmitted.getChildAt(i).setBackgroundColor(Color.YELLOW);
                    } else{
                        applicationsSubmitted.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
            }
        });
        GetAllApplications();
    }
}
