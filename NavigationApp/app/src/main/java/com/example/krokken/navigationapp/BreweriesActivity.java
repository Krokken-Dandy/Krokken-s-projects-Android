package com.example.krokken.navigationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BreweriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new BreweriesFragment())
                .commit();
    }
}
