package com.example.cloudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.j256.ormlite.android.AndroidConnectionSource;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AndroidConnectionSource(databaseHelper);
        databaseHelper = new DatabaseHelper(getBaseContext());
        startService(new Intent(this, HeartBeatServices.class));
    }
}