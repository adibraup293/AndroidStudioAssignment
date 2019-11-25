package com.example.assignment.Application;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.R;

public class ViewApplicationsUserActivity extends AppCompatActivity {

    ListView applicationsSubmitted;
    Applications applications;

    public void back(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applications_user);
    }
}
