package com.example.krokken.musicalapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView musicLibrary;
    TextView podcastLibrary;
    TextView playlists;
    TextView currentlyPlaying;
    ImageView settings;
    ImageView store;
    ImageView trending;
    ImageView chart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.audio_app_title));
        findViews();
        onClickListeners();
        requestPermissionCheck();
    }

    private void findViews() {
        musicLibrary = findViewById(R.id.music_library);
        podcastLibrary = findViewById(R.id.podcast_library);
        playlists = findViewById(R.id.playlists);
        currentlyPlaying = findViewById(R.id.currently_playing);
        settings = findViewById(R.id.settings);
        store = findViewById(R.id.store);
        trending = findViewById(R.id.trending);
        chart = findViewById(R.id.chart);
    }

    private void onClickListeners() {
        //Set a clickListener to enter music activity
        musicLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicLibraryActivity = new Intent(MainActivity.this, MusicLibraryActivity.class);
                startActivity(musicLibraryActivity);
            }
        });
        //Set a clickListener to enter podcast activity
        podcastLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent podcastLibraryActivity = new Intent(MainActivity.this, PodcastLibraryActivity.class);
                startActivity(podcastLibraryActivity);
            }
        });
        //Set a clickListener to enter music activity
        playlists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playlistsActivity = new Intent(MainActivity.this, PlaylistActivity.class);
                startActivity(playlistsActivity);
            }
        });
        //Set a clickListener to enter podcast activity
        currentlyPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent currentlyPlayingActivity = new Intent(MainActivity.this, CurrentlyPlayingActivity.class);
                startActivity(currentlyPlayingActivity);
            }
        });
        //Set a clickListener to enter setting activity
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsActivity = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsActivity);
            }
        });
        //Set a clickListener to open browser with google's music store
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getString(R.string.url_music_store);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        //Set a clickListener to open google's trending website
        trending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getString(R.string.url_trending);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        //Set a clickListener to open google's music store, showing top songs
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getString(R.string.url_chart);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    private void requestPermissionCheck() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            requestExternalStoragePermission();
        }
    }

    private void requestExternalStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle(R.string.permission_required_dialog_title)
                    .setMessage(R.string.permission_required_dialog_text)
                    .setPositiveButton(R.string.permission_required_positive_button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        }
                    })
                    .setNegativeButton(R.string.permission_required_negative_button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }
}
