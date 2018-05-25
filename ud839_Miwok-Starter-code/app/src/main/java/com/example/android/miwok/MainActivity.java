/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView numbers, family, colors, phrases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        findTextViews();
        onClickListeners();
    }

    //Find TextViews of opening screen
    private void findTextViews(){
        numbers = (TextView) findViewById(R.id.numbers);
        family = (TextView) findViewById(R.id.family);
        colors = (TextView) findViewById(R.id.colors);
        phrases = (TextView) findViewById(R.id.phrases);

    }

    //OnClickListeners for opening TextViews
    private void onClickListeners(){
        //Set a clickListener that View
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersList = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersList);
            }
        });

        //Set a clickListener that View
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familyList = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(familyList);
            }
        });

        //Set a clickListener that View
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent colorsList = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsList);
            }
        });

        //Set a clickListener that View
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phrasesList = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phrasesList);
            }
        });
    }
}
