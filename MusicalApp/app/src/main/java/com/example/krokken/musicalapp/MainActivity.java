package com.example.krokken.musicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView library;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        onClickListeners();
    }

    private void findViews(){
        library = findViewById(R.id.library_activity);
    }
    private void onClickListeners() {
        //Set a clickListener that View
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersList = new Intent(MainActivity.this, LibraryActivity.class);
                startActivity(numbersList);
            }
        });
    }
}
