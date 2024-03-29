package com.example.assignment.User;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Admin.UserAdmin;
import com.example.assignment.DatabaseHelper;
import com.example.assignment.R;

public class SignUpActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    EditText nameEditText;
    EditText emailEditText;
    EditText monthlyIncomeEditText;
    RadioGroup radioGroup;
    RadioButton applicantBtn;
    RadioButton adminBtn;

    Button signUpBtn1;
    Button signUpBtn2;

    DatabaseHelper databaseHelper;

    public void addAdmin(View view) {

        if (adminBtn.isSelected()) {
            Toast.makeText(this, "Please select a radio button", Toast.LENGTH_SHORT).show();
        } else {
            UserAdmin userAdmin = new UserAdmin();

            userAdmin.setUsername(usernameEditText.getText().toString().trim());
            userAdmin.setUsertype(0);
            userAdmin.setPassword(passwordEditText.getText().toString().trim());
            userAdmin.setName(nameEditText.getText().toString().trim());

            databaseHelper.AddAdminUser(userAdmin);
            Toast.makeText(this, "Admin user created successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void addApplicant(View view) {

        if (applicantBtn.isSelected()) {
            Toast.makeText(this, "Please select a radio button", Toast.LENGTH_SHORT).show();
        } else {
            Applicant applicant = new Applicant();

            applicant.setUsername(usernameEditText.getText().toString().trim());
            applicant.setUsertype(1);
            applicant.setPassword(passwordEditText.getText().toString().trim());
            applicant.setName(nameEditText.getText().toString().trim());
            applicant.setEmail(emailEditText.getText().toString().trim());
            applicant.setMonthlyIncome(Double.parseDouble(monthlyIncomeEditText.getText().toString()));

            databaseHelper.AddApplicantUser(applicant);
            Toast.makeText(this, "Applicant user created successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    //public void registerUser(View view){
    //    String username =usernameEditText.getText().toString().trim();
    //    String password = passwordEditText.getText().toString().trim();
    //    String name = nameEditText.getText().toString().trim();
    //    String email = emailEditText.getText().toString().trim();
    //    Double monthlyIncome = Double.parseDouble(monthlyIncomeEditText.getText().toString());

    //    databaseHelper.insertUserDetails(username,password,name,email,monthlyIncome);
    //    Toast.makeText(this, "Applicant user created successfully", Toast.LENGTH_SHORT).show();
    //    finish();
    //}

    //public void registerAdmin(View view){
    //    String username =usernameEditText.getText().toString().trim();
    //    String password = passwordEditText.getText().toString().trim();
    //   String name = nameEditText.getText().toString().trim();

    //    databaseHelper.createUserAdminDetails(username,password,name);
    //    Toast.makeText(this, "Admin user created successfully", Toast.LENGTH_SHORT).show();
    //    finish();
    //}

    public void back(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        radioGroup = findViewById(R.id.radioGroup);
        applicantBtn = findViewById(R.id.applicantBtn);
        adminBtn = findViewById(R.id.adminBtn);

        signUpBtn1 = findViewById(R.id.signUpBtn);
        signUpBtn2 = findViewById(R.id.signUpBtn2);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                applicantBtn.isSelected();
                if (checkedId == R.id.applicantBtn) {
                    usernameEditText.setVisibility(View.VISIBLE);
                    passwordEditText.setVisibility(View.VISIBLE);
                    nameEditText.setVisibility(View.VISIBLE);
                    emailEditText.setVisibility(View.VISIBLE);
                    monthlyIncomeEditText.setVisibility(View.VISIBLE);
                    signUpBtn1.setVisibility(View.VISIBLE);
                    signUpBtn2.setVisibility(View.INVISIBLE);
                    signUpBtn2.setEnabled(false);

                } else if (checkedId == R.id.adminBtn) {
                    usernameEditText.setVisibility(View.VISIBLE);
                    passwordEditText.setVisibility(View.VISIBLE);
                    nameEditText.setVisibility(View.VISIBLE);
                    emailEditText.setVisibility(View.INVISIBLE);
                    monthlyIncomeEditText.setVisibility(View.INVISIBLE);
                    signUpBtn1.setVisibility(View.INVISIBLE);
                    signUpBtn1.setEnabled(false);
                    signUpBtn2.setVisibility(View.VISIBLE);
                }
            }
        });

        databaseHelper = new DatabaseHelper(SignUpActivity.this);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        monthlyIncomeEditText = findViewById(R.id.monthlyIncomeEditText);
    }


}
