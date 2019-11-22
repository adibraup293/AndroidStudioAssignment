package com.example.assignment.Residence;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.assignment.R;

import java.util.List;

public class ViewResidenceAdminActivity extends AppCompatActivity {

    ListView residenceListView;
    SQLiteHelperResidence sqLiteHelperResidence;
    Residence residence;
    AlertDialog.Builder alert;

    public void back(View view) {
        finish();
    }

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

    public void editButton(View view) {
        if (residence != null) {
            Intent editIntent = new Intent(this, EditResidenceActivity.class);
            editIntent.putExtra("id", residence.getResidenceID());
            startActivity(editIntent);
        } else {
            Toast.makeText(this, "Cannot found Residence", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteResidenceButton(View view){
        if (residence != null) {

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sqLiteHelperResidence.DeleteResidence(residence);
                    GetAllResidence();
                }
            });

            alert.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_residence_admin);

        alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Residence");
        alert.setMessage("Are you sure you want to delete?");

        sqLiteHelperResidence =new SQLiteHelperResidence(ViewResidenceAdminActivity.this);
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
