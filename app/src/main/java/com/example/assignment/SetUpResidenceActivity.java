package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SetUpResidenceActivity extends AppCompatActivity {

    EditText residenceAddressEditText;
    EditText residenceNumOfUnits;
    EditText residenceSizePerUnit;
    EditText residenceMonthlyRental;

    SQLiteHelperResidence sqLiteHelperResidence;

    public void addResidence (View view){
        String address =residenceAddressEditText.getText().toString().trim();
        int numOfUnits = Integer.parseInt(residenceNumOfUnits.getText().toString());
        int sizePerUnit = Integer.parseInt(residenceSizePerUnit.getText().toString());
        Double monthlyRental = Double.parseDouble(residenceMonthlyRental.getText().toString());

        if (address.matches("")){
            Toast.makeText(this, "Please enter address!", Toast.LENGTH_SHORT).show();
        } else {
            sqLiteHelperResidence.insertResidence(address, numOfUnits, sizePerUnit, monthlyRental);
            Toast.makeText(this, "Residence successfully created", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_residence);

        sqLiteHelperResidence = new SQLiteHelperResidence(SetUpResidenceActivity.this);

        residenceAddressEditText = findViewById(R.id.residenceAddressEditText);
        residenceNumOfUnits = findViewById(R.id.numUnitEditText);
        residenceSizePerUnit = findViewById(R.id.sizePerUnitEditText);
        residenceMonthlyRental = findViewById(R.id.monthlyRentalEditText);
    }
}
