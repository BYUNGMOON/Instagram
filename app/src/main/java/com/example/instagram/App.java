package com.example.instagram;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("R4a1PSbXWLjDLkESQgw36wYTg11f0HFlPpprqyK2")
                // if defined
                .clientKey("QX3SCV9R6Wp6QwnVVOeyHP91WmNwnQyuyh5fRlNn")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}