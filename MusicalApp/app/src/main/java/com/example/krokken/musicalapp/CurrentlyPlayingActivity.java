package com.example.krokken.musicalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CurrentlyPlayingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_playing);
        setTitle(getString(R.string.currently_playing_title));
    }

}
