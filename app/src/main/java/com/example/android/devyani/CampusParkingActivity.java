package com.example.android.devyani;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CampusParkingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_parking);

        Button logoutButton =(Button) findViewById(R.id.logoutButton) ;
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CampusParkingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickParkingAreas(View v)
    {
        Intent intent = new Intent(CampusParkingActivity.this, ParkingAreasActivity.class);
        startActivity(intent);
    }
    public void onClickMyAccount(View v)
    {
        Intent intent = new Intent(CampusParkingActivity.this, MyAccountActivity.class);
        startActivity(intent);

    }
  }