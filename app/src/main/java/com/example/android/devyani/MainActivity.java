package com.example.android.devyani;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.android.devyani.Model.Area;
import com.parse.Parse;
import com.parse.ParseObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        replaceFragment(new Login(),true);
    }

    public void replaceFragment(Fragment frag, boolean addToBackStack){
        System.out.println("Recahed Replacing function");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentBox, frag);
        if (addToBackStack) {
            ft.addToBackStack(frag.toString());
        }
        ft.commit();
    }
}

        /*final EditText carNumEditText = (EditText) findViewById(R.id.carNumEditText);
        final EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        Button registerButton = (Button) findViewById(R.id.registerButton);

        Button loginButton =(Button) findViewById(R.id.loginButton) ;

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String carNum = carNumEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                System.out.println("CarNumber =" + carNum );
                System.out.println("Password = " + password);
                if(carNum.equals("MH1320") && password.equals("library"))
                {
                    Intent intent = new Intent(MainActivity.this, CampusParkingActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Invalid CarNumber or Password ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
*/