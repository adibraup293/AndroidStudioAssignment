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
    EditText editAddress;
    EditText editSizePerUnit;
    EditText editNumOfUnit;
    EditText editMonthlyRental;
    Residence residence;

    public void UpdateResidenceButton(View view){

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

        editAddress = findViewById(R.id.residenceAddressEditText);
        editNumOfUnit = findViewById(R.id.numUnitEditText);
        editSizePerUnit = findViewById(R.id.sizePerUnitEditText);
        editMonthlyRental = findViewById(R.id.monthlyRentalEditText);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        if (id != -1){
            residence = databaseHelper.getResidence(id);
                    editAddress.setText(residence.getAddress());
                    editNumOfUnit.setText(residence.getNumOfUnits());
                    editSizePerUnit.setText(residence.getSizePerUnit());
                    editMonthlyRental.setText("");
        }
    }
}
