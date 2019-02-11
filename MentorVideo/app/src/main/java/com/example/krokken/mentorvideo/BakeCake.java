package com.example.krokken.mentorvideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BakeCake extends AppCompatActivity {

    Button bakeCakeButton;
    Button bakeCookieButton;
    TextView bakedText;

    //Cake ingredients
    int vegetableShorteningCake = 140;
    int granulatedSugarCake = 300;
    int flourCake = 300;
    int bakingPowderCake = 14;
    int saltCake = 1;
    int eggCake = 130;
    int milkCake = 180;
    int vanillaCake = 4;

    //Cookie ingredients
    int vegetableShorteningCookie = 140;
    int granulatedSugarCookie = 300;
    int flourCookie = 300;
    int bakingPowderCookie = 14;
    int saltCookie = 1;
    int eggCookie = 130;
    int milkCookie = 180;
    int vanillaCookie = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bakeCakeButton = findViewById(R.id.bake_cake);
        bakeCookieButton = findViewById(R.id.bake_cookies);
        bakedText = findViewById(R.id.baked_text);

        bakeCakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This will bake a cake when the user clicks the associated button
                bakedText.setText("The batter is ready and is " + bakeFunction(flourCake,
                        bakingPowderCake, saltCake, eggCake, milkCake, vanillaCake,
                        vegetableShorteningCake, granulatedSugarCake) + " grams.");
            }
        });

        bakeCakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This will bake a cake when the user clicks the associated button
                bakedText.setText("The batter is ready and is " + bakeFunction(flourCookie,
                        bakingPowderCookie, saltCookie, eggCookie, milkCookie, vanillaCookie,
                        vegetableShorteningCookie, granulatedSugarCookie) + " grams.");
            }
        });
    }

    private int bakeFunction(int flour, int bakingPowder, int salt, int egg, int milk, int vanilla,
                             int vegetableShortening, int granulatedSugar) {
        int[] dryIngredientsArray = {flour, bakingPowder, salt};
        int[] wetIngredientsArray = {egg, milk, vanilla};
        int[] combinedSugarArray = {vegetableShortening, granulatedSugar};

        int dryIngredients = combineIngredients(dryIngredientsArray);
        int wetIngredients = combineIngredients(wetIngredientsArray);
        int combinedSugar = combineIngredients(combinedSugarArray);

        int[] combinedBatterArray = {dryIngredients, wetIngredients, combinedSugar};

        return combineIngredients(combinedBatterArray);
    }

    private int combineIngredients(int[] ingredients) {
        int combinedIngredients = 0;

        for (int i = 0; i < ingredients.length; i++) {
            combinedIngredients += ingredients[i];
        }
        return combinedIngredients;
    }
}

