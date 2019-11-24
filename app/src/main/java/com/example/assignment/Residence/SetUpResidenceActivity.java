package com.example.assignment.Residence;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.DatabaseHelper;
import com.example.assignment.R;

public class SetUpResidenceActivity extends AppCompatActivity {

    EditText residenceNameEditText;
    EditText residenceAddressEditText;
    EditText residenceNumOfUnits;
    EditText residenceSizePerUnit;
    EditText residenceMonthlyRental;

    DatabaseHelper databaseHelper;

    public void back(View view) {
        finish();
    }

    public void AddResidenceButton(View view){
        Residence residence = new Residence();
        residence.setResidenceName(residenceNameEditText.getText().toString().trim());
        residence.setAddress(residenceAddressEditText.getText().toString().trim());
        residence.setNumOfUnits(Integer.parseInt(residenceNumOfUnits.getText().toString()));
        residence.setSizePerUnit(Integer.parseInt(residenceSizePerUnit.getText().toString()));
        residence.setMonthlyRental(Double.parseDouble(residenceMonthlyRental.getText().toString()));

        databaseHelper.addResidence(residence);

        Toast.makeText(this, "Residence successfully created", Toast.LENGTH_SHORT).show();

        databaseHelper.close();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_residence);

        databaseHelper = new DatabaseHelper(SetUpResidenceActivity.this);

        residenceNameEditText = findViewById(R.id.residenceNameEditText);
        residenceAddressEditText = findViewById(R.id.residenceAddressEditText);
        residenceNumOfUnits = findViewById(R.id.numUnitEditText);
        residenceSizePerUnit = findViewById(R.id.sizePerUnitEditText);
        residenceMonthlyRental = findViewById(R.id.monthlyRentalEditText);

    }
}