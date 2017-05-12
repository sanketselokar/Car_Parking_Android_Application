package com.example.android.devyani;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {

    EditText carNumEditText;
    EditText passwordEditText;
    Button loginButton;
    Button register;
    //Button feedbackButton;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        register = (Button) view.findViewById(R.id.registerButton);

        //feedbackButton = (Button) view.findViewById(R.id.feedbackButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(new Register(),true);
            }
        });
        carNumEditText = (EditText) view.findViewById(R.id.carNumEditText);
        passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);

        loginButton =(Button) view.findViewById(R.id.loginButton) ;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String carNum = carNumEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                System.out.println("Number =" + carNum );
                System.out.println("Password = " + password);

                //start
                ParseUser.logInInBackground(carNum, password, new LogInCallback() {

                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e == null) {
                            Log.d("here" + user, "");
                            ((MainActivity)getActivity()). replaceFragment(new Register(),true);
                           // Intent i = new Intent(getActivity(), ParkingAreasActivity.class);
                            Intent i = new Intent(getActivity(), GoogleMapActivity.class);
                            getActivity().startActivity(i);

                        }else{
                            e.printStackTrace();
                        }

                    }
                });
                //end

            }
        });

        Button registerButton =(Button) view.findViewById(R.id.registerButton) ;

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ((MainActivity)getActivity()). replaceFragment(new Register(),true);

            }
        });


        Button feedbackButton =(Button) view.findViewById(R.id.feedbackButton);

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getActivity(), MailActivity.class);
                getActivity().startActivity(i);
            }
        });






        return view;
    }

}

