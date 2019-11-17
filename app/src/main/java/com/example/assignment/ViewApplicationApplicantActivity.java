package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewApplicationApplicantActivity extends AppCompatActivity {

    ListView residenceListView;
    ArrayList<HashMap<String, String>> residenceList;
    SQLiteHelperResidence sqLiteHelperResidence;
    Residence residence;

    //public void getAllResidence(){
    //    residence = null;
    //    List<Residence> residenceList = sqLiteHelperResidence.getAllResidence();
    //    ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, residenceList);
    //    residenceListView.setAdapter(adapter);
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application_applicant);
        SQLiteHelperResidence db = new SQLiteHelperResidence(this);
        residenceList = db.getResidence();
        ListView lv = findViewById(R.id.residenceListView);
        ListAdapter adapter = new SimpleAdapter(ViewApplicationApplicantActivity.this, residenceList, R.layout.residencelist_row
                ,new String[]{"Residence ID", "Address", "Num of units", "Size per unit", "Monthly rental"},
                new int[]{R.id.residenceID, R.id.residenceAddress, R.id.numUnits, R.id.sizePerUnit, R.id.monthlyRental});

        lv.setAdapter(adapter);
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
