package com.example.android.devyani;

import android.app.Application;

import com.example.android.devyani.Model.Area;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Sanket on 4/5/2017.
 */

public class ParkingApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Area.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("6tge5HhPPcFIO4jZPWcAgjiPuq4OMOanlQJqPYE1")
                .clientKey("LM6bz3ElBmh7UlW5Bmk4pwSylVqMg7Ek7wEj5dgG")
                .server("https://parseapi.back4app.com/").build()
        );
    }
}
