package com.example.congressounirp.model;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseConnection {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public  DatabaseReference conectCloudDataBase (Context context){
        FirebaseApp.initializeApp(context.getApplicationContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        return databaseReference;
    }
}