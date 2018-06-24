package com.example.krokken.musicalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PlaylistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        setTitle(getString(R.string.playlist_title));
    }
}
