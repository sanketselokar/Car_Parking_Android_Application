package com.example.android.devyani;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ParkingAreasActivity extends AppCompatActivity {

    public String[] area = {"McKemy", "Colden Hall" , "Station", "B.D. Owens", "Admin Bldg"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_areas);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, area);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

// newly added
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch(position)
                {
                    case 0:  Intent newActivity = new Intent(ParkingAreasActivity.this, ParkingAreaNameDetail.class);
                        newActivity.putExtra("area",0);
                        startActivity(newActivity);
                        break;
                    case 1:  Intent newActivity1 = new Intent(ParkingAreasActivity.this, ParkingAreaNameDetail.class);
                        newActivity1.putExtra("area",1);
                        startActivity(newActivity1);
                        break;
                    case 2:  Intent newActivity2 = new Intent(ParkingAreasActivity.this, ParkingAreaNameDetail.class);
                        newActivity2.putExtra("area",2);
                        startActivity(newActivity2);
                        break;
                    case 3:  Intent newActivity3 = new Intent(ParkingAreasActivity.this, ParkingAreaNameDetail.class);
                        newActivity3.putExtra("area",3);
                        startActivity(newActivity3);
                        break;
                    case 4:  Intent newActivity4 = new Intent(ParkingAreasActivity.this, ParkingAreaNameDetail.class);
                        newActivity4.putExtra("area",4);
                        startActivity(newActivity4);
                        break;
                }
            }

        });
//end
        Button logoutButton =(Button) findViewById(R.id.logoutButton) ;

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ParkingAreasActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
