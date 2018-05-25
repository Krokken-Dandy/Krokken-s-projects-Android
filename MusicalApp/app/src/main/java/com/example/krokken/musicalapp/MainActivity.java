package com.example.krokken.musicalapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView music_library;
    TextView podcast_library;
    ImageView settings;

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
        music_library = findViewById(R.id.music_library);
        podcast_library = findViewById(R.id.podcast_library);
        settings = findViewById(R.id.settings);
    }

    private void onClickListeners() {
        //Set a clickListener to enter music activity
        music_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MusicLibraryActivity = new Intent(MainActivity.this, MusicLibraryActivity.class);
                startActivity(MusicLibraryActivity);
            }
        });
        //Set a clickListener to enter podcast activity
        podcast_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PodcastLibraryActivity = new Intent(MainActivity.this, PodcastLibraryActivity.class);
                startActivity(PodcastLibraryActivity);
            }
        });
        //Set a clickListener to enter setting activity
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsActivity = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(SettingsActivity);
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
