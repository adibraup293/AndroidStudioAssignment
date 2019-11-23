package com.example.assignment.Residence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.assignment.Application.CreateApplicationsActivity;
import com.example.assignment.DatabaseHelper;
import com.example.assignment.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewResidenceApplicantActivity extends AppCompatActivity {

    ListView residenceListView;
    DatabaseHelper databaseHelper;
    Residence residence;

    public void back(View view) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        GetAllResidence();
    }

    public void GetAllResidence() {
        List<Residence> residenceList = databaseHelper.GetAllResidences();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, residenceList);
        residenceListView.setAdapter(adapter);
    }

    public void ApplyingButton(View view) {

        if (residence != null) {
            Intent intent = new Intent(this, CreateApplicationsActivity.class);
            intent.putExtra("id", residence.getResidenceID());
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_residence_applicant);

        databaseHelper = new DatabaseHelper(ViewResidenceApplicantActivity.this);
        residenceListView = findViewById(R.id.residenceListView);

        residenceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                residence = (Residence) residenceListView.getItemAtPosition(position);

                for (int i = 0; i < residenceListView.getChildCount(); i++) {
                    if (position == i) {
                        residenceListView.getChildAt(i).setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                                R.color.SelectedListItem));
                    } else {
                        residenceListView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
        GetAllResidence();
    }
}
