package com.example.krokken.mentorvideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button bakeCakeButton;
    Button bakeCookieButton;
    TextView bakedCakeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bakeCakeButton = findViewById(R.id.bake_cake);
        bakeCookieButton = findViewById(R.id.bake_cookies);
        bakedCakeText = findViewById(R.id.baked_text);

        bakeCakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This will bake a cake when the user clicks the associated button
                int vegetableShortening = 140;
                int granulatedSugar = 300;
                int flour = 300;
                int bakingPowder = 14;
                int salt = 1;
                int egg = 130;
                int milk = 180;
                int vanilla = 4;

                int dryIngredients;
                int combinedSugar;
                int wetIngredients;

                dryIngredients = flour + bakingPowder + salt;
                combinedSugar = vegetableShortening + granulatedSugar;
                wetIngredients = egg + milk + vanilla;

                int combinedBatter;

                combinedBatter = dryIngredients + combinedSugar;
                combinedBatter += wetIngredients;

                bakedCakeText.setText("The batter is ready and is " + combinedBatter + " grams.");
            }
        });

        bakeCookieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This will bake a cake when the user clicks the associated button
                int vegetableShortening = 140;
                int granulatedSugar = 300;
                int flour = 300;
                int bakingPowder = 14;
                int salt = 1;
                int egg = 130;
                int milk = 180;
                int vanilla = 4;

                int dryIngredients;
                int combinedSugar;
                int wetIngredients;

                dryIngredients = flour + bakingPowder + salt;
                combinedSugar = vegetableShortening + granulatedSugar;
                wetIngredients = egg + milk + vanilla;

                int combinedBatter;

                combinedBatter = dryIngredients + combinedSugar;
                combinedBatter += wetIngredients;

                bakedCakeText.setText("The batter is ready and is " + combinedBatter + " grams.");
            }
        });
    }
}

