package com.example.android.devyani;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {

    ProgressDialog progressDialog;
    EditText carNumEditText;
    EditText mobNumEditText;
    EditText password;
    Button register;
    String carNumString = "";
    long mobNum;

    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false); progressDialog = new ProgressDialog(this.getContext());
        carNumEditText = (EditText) view.findViewById(R.id.carNumEditText);
        mobNumEditText = (EditText) view.findViewById(R.id.mobNumEditText);
        password = (EditText) view.findViewById(R.id.password);
        register = (Button) view.findViewById(R.id.register);





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carNumString = carNumEditText.getText().toString();


                if((mobNumEditText.getText().toString()).matches("[0-9]+"))
                {
                    mobNum = Long.parseLong(mobNumEditText.getText().toString());
                progressDialog.setMessage("Please Wait");
                progressDialog.setTitle("Registering");
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            parseRegister();
                            Intent i = new Intent(getActivity(), GoogleMapActivity.class);
                            startActivity(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                }
                else
                    alertDisplayer("Mobile No must be Numbers","Sorry");

            }
        });
        return view;
    }
    void parseRegister(){
        ParseUser user = new ParseUser();

        user.setUsername(carNumEditText.getText().toString());
        user.setPassword(password.getText().toString());
        user.put("carNumber", carNumString);


        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e)
            {
                if (e == null) {

                    progressDialog.dismiss();
                  /*  t_username.setText(ParseUser.getCurrentUser().getUsername());*/
                    saveNewUser();
                } else {
                    progressDialog.dismiss();
                    alertDisplayer("Register Fail", e.getMessage());
                }
            }
        });
    }
    void saveNewUser(){
        ParseUser user = ParseUser.getCurrentUser();

        //user.setEmail(email.getText().toString());
        user.setUsername(carNumEditText.getText().toString());
        user.setPassword(password.getText().toString());
        user.put("carNumber", carNumString);
        user.put("mobileNumber", mobNum);

        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                alertDisplayer("Registration Successful...","Welcome");

            }
        });

    }
    void alertDisplayer(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}


