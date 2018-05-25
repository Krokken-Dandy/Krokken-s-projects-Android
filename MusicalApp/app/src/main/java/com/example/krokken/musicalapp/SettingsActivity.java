package com.example.krokken.musicalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class SettingsActivity extends AppCompatActivity {

    CheckBox continuousPlay;
    CheckBox randomPlay;
    Settings settings = new Settings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(getString(R.string.settings_title));
        continuousPlay = findViewById(R.id.play_continuous);
        randomPlay = findViewById(R.id.play_random);
        checkSettings();
        onClickListeners();
    }

    private void onClickListeners() {
        continuousPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = continuousPlay.isChecked();
                settings.setContinuousPlay(checked);
            }
        });
        randomPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = randomPlay.isChecked();
                settings.setRandomPlay(checked);
            }
        });
    }

    private void checkSettings(){
        if (settings.getRandomPlay()) {
            randomPlay.setChecked(true);
        }

        if (settings.getContinuousPlay()) {
            continuousPlay.setChecked(true);
        }
    }
}
