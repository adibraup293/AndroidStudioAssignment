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
import com.example.assignment.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewResidenceApplicantActivity extends AppCompatActivity {

    ListView residenceListView;
    SQLiteHelperResidence sqLiteHelperResidence;
    Residence residence;

    ArrayList<HashMap<String, String>> residenceList;

    @Override
    protected void onResume() {
        super.onResume();

        GetAllResidence();
    }

    public void GetAllResidence() {
        List<Residence> residenceList = sqLiteHelperResidence.GetAllResidences();
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

    // public void getAllResidence(){
    //    residence = null;
    //    List<Residence> residenceList = sqLiteHelperResidence.getAllResidence();
    //    ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, residenceList);
    //    residenceListView.setAdapter(adapter);
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_residence_applicant);

        sqLiteHelperResidence =new SQLiteHelperResidence(ViewResidenceApplicantActivity.this);
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
        // ListView lv = findViewById(R.id.residenceListView);
        //ListAdapter adapter = new SimpleAdapter(ViewResidenceApplicantActivity.this, residenceList, R.layout.residencelist_row
        //        ,new String[]{"Residence ID", "Address", "num of unit", "size per unit", "Monthly rental" },
        //        new int[]{R.id.residenceID, R.id.address, R.id.numofunits, R.id.sizeperunit, R.id.monthlyrental});

        //lv.setAdapter(adapter);
        //residenceListView = findViewById(R.id.residenceListView);

        //residenceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //    @Override
        //    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //        residence = (Residence) residenceListView.getItemAtPosition(position);

         //       for (int i = 0; i < residenceListView.getChildCount(); i++) {
         //           if (position == i) {
         //               residenceListView.getChildAt(i).setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
         //                       R.color.SelectedListItem));
         //           } else {
         //               residenceListView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
         //           }
         //       }
         //   }
        //});
        //getAllResidence();
    }
}
