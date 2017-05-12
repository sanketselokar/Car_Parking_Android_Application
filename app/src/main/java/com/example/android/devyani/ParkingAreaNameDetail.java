package com.example.android.devyani;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.devyani.Model.Area;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ParkingAreaNameDetail extends AppCompatActivity {


    TextView countValueTextView;
    TextView areaTextView;
    Button parkButton;
    Button vaccantButton;
    Button logoutButton;
    String buildingId="";
    int fixValue;
    int value;
    int emptyslots = 0;
    int counter = 0;
String areaName;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_area_name_detail);
        areaName = getIntent().getStringExtra("area");
        countValueTextView = (TextView) findViewById(R.id.countValueTextView);
        areaTextView = (TextView) findViewById(R.id.areaTextView);
        parkButton = (Button) findViewById (R.id.parkButton);
        vaccantButton = (Button) findViewById(R.id.vaccantButton);
        logoutButton =(Button)findViewById(R.id.logoutButton);

        fixValue = Integer.parseInt(countValueTextView.getText().toString());
        Intent intent = getIntent();
//        int result = intent.getIntExtra("area",5);
        //emptyslots = ParseUser.getCurrentUser().getInt("");

vaccantButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ParseQuery<Area> query = ParseQuery.getQuery("area");

        // Retrieve the object by id
        query.getInBackground(buildingId, new GetCallback<Area>() {
            public void done(Area a, ParseException e) {
                if (e == null) {
                    a.put("emptySlots",emptyslots+1);
                    a.saveInBackground();
                    getEmptySLots();
                }
            }});

        vaccantButton.setClickable(false);
        parkButton.setClickable(true);
    }
});
getEmptySLots();
             // countValueTextView.setText(emptyslots + "");
        areaTextView.setText(areaName);
parkButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ParseQuery<Area> query = ParseQuery.getQuery("area");

// Retrieve the object by id
        query.getInBackground(buildingId, new GetCallback<Area>() {
            public void done(Area a, ParseException e) {
                if (e == null) {
                    a.put("emptySlots",emptyslots-1);
                    a.saveInBackground();
                    getEmptySLots();
                }
            }});

    parkButton.setClickable(false);
        vaccantButton.setClickable(true);
    }
});




//        switch (result)
//        {
//            case 0: areaTextView.setText("McKemy ");
//                countValueTextView.setText("50");
//                break;
//
//            case 1: areaTextView.setText("Colden Hall");
//                countValueTextView.setText("38");
//                break;
//
//            case 2: areaTextView.setText("Station");
//                countValueTextView.setText("50");
//                break;
//
//            case 3: areaTextView.setText("library");
//                countValueTextView.setText("78");
//                break;
//
//
//            default:
//                countValueTextView.setText(emptyslots);
//                areaTextView.setText(areaName);
//                break;
//
//        }

    }
    public void getEmptySLots(){
        ParseQuery<Area> query =  new ParseQuery<Area>("area");
        System.out.println("areaName : "+areaName);
        query.whereEqualTo("areaName",areaName);

        // Making query to fetch in background
        query.findInBackground(new FindCallback<Area>() {

            public void done(List<Area> list, ParseException e) {
                if (e == null) {

                    System.out.println("Got results from parse server: "+ list.size());
                    for(Area r:list){
                        buildingId = r.getObjectId();
                        emptyslots = r.getInt("emptySlots");

                    }
                    countValueTextView.setText(emptyslots+"");
                } else {
                    // handle Parse Exception here
                    e.printStackTrace();
                }
            }
        });
    }
    public void parkAdd(View v)
    {
        if(counter == 0) {
            value = Integer.parseInt(countValueTextView.getText().toString());


                System.out.println("zzzValue1: " + value);
                value = value - 1;
                String countString = Integer.toString(value);
                countValueTextView.setText(countString);

            counter++;
        }

    }
    public void vaccantMinus(View v)
    {
        // value = Integer.parseInt(countValueTextView.getText().toString());
        //int fixValue = Integer.parseInt(countValueTextView.getText().toString());
        if(counter == 1) {
            System.out.println("aaaValue  & fix" + value + "  " + fixValue);
            //if (value == (fixValue - 1)) {
                value = value + 1;
                String countString = Integer.toString(value);
                countValueTextView.setText(countString);
            //}
            counter--;
        }
    }
    public void logoutMethod(View v)
    {
        ParseUser.logOut();
        Intent intent = new Intent(ParkingAreaNameDetail.this, MainActivity.class);
        startActivity(intent);
    }
}