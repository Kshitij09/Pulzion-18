package com.pict.acm.pulzion18;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Pulzion18 extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
