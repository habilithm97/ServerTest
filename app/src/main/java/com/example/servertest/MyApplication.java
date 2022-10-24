package com.example.servertest;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {
    public static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }
}
