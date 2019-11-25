package com.example.assignment.Residence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.assignment.DatabaseHelper;
import com.example.assignment.R;

public class EditResidenceActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText editResidenceName;
    EditText editAddress;
    EditText editSizePerUnit;
    EditText editNumOfUnit;
    EditText editMonthlyRental;
    Residence residence;

    public void UpdateResidenceButton(View view){

        residence.setResidenceName(editResidenceName.getText().toString().trim());
        residence.setAddress(editAddress.getText().toString().trim());
        residence.setNumOfUnits(Integer.parseInt(editNumOfUnit.getText().toString()));
        residence.setSizePerUnit(Integer.parseInt(editSizePerUnit.getText().toString()));
        residence.setMonthlyRental(Double.parseDouble(editMonthlyRental.getText().toString()));

        databaseHelper.UpdateResidence(residence);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_residence);

        databaseHelper = new DatabaseHelper(EditResidenceActivity.this);

        editResidenceName = findViewById(R.id.editResidenceNameEditText);
        editAddress = findViewById(R.id.residenceEditAddressEditText);
        editNumOfUnit = findViewById(R.id.editNumOfUnitsEditText);
        editSizePerUnit = findViewById(R.id.editSizePerUnitEditText);
        editMonthlyRental = findViewById(R.id.editMonthlyRentalEditText);

        Intent intent = getIntent();
        int id = intent.getIntExtra("residenceID", -1);

        if (id != -1){
            residence = databaseHelper.getResidence(id);
            editResidenceName.setText("");
            editAddress.setText(residence.getAddress());
            editNumOfUnit.setText("");
            editSizePerUnit.setText("");
            editMonthlyRental.setText("");
        }
    }
}
