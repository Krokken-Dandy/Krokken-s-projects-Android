package com.example.krokken.quiz;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;
    public static final String PLAYER_INPUT_NAME = "player input name";
    public static String PLAYER_NAME;

    EditText nameInput;
    Button beginButton;
    TextView artQuiz, testQuiz;
    View layoutOpening, layoutQuizes;

    //TODO Add graded score to main page with Reset Quiz button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        findViews();
        onClickListeners();

        //Will restore a saved state if we have one
        if (savedInstanceState != null) {
            PLAYER_NAME = savedInstanceState.getString(PLAYER_NAME);
        }

    }

    //Saves the player's name so it can be restored
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(PLAYER_INPUT_NAME, PLAYER_NAME);
    }

    //Find all the Main.xml views
    private void findViews() {
        artQuiz = findViewById(R.id.art_quiz);
        testQuiz = findViewById(R.id.test_quiz);
        layoutOpening = findViewById(R.id.layout_opening);
        layoutQuizes = findViewById(R.id.layout_quizes);
        nameInput = findViewById(R.id.name_input);
        beginButton = findViewById(R.id.begin_button);
    }

    //OnClickListeners for opening and Quiz Options
    private void onClickListeners() {
        //Chooses the
        nameInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    PLAYER_NAME = nameInput.getText().toString().trim();
                    if (v != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        nameInput.clearFocus();
                    }
                    return true;
                }
                return false;
            }
        });

        //Set a clickListener that View
        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PLAYER_NAME = nameInput.getText().toString().trim();
                if (TextUtils.isEmpty(PLAYER_NAME)) {
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_LONG).show();
                } else {
                    layoutOpening.setVisibility(View.GONE);
                    layoutQuizes.setVisibility(View.VISIBLE);
                }
            }
        });

        artQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent theArtQuiz = new Intent(MainActivity.this, AllArtQuestionsActivity.class);
                theArtQuiz.putExtra(PLAYER_INPUT_NAME, PLAYER_NAME);
                Log.v(PLAYER_NAME, PLAYER_NAME + " activity");
                startActivity(theArtQuiz);
            }
        });

        testQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent theTestQuiz = new Intent(MainActivity.this, TestQuizActivity.class);
                theTestQuiz.putExtra(PLAYER_INPUT_NAME, PLAYER_NAME);
                startActivity(theTestQuiz);
            }
        });
    }
}