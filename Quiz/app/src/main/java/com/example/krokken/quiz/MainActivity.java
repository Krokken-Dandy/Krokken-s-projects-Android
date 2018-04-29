package com.example.krokken.quiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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
    //TODO
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
    private void findViews(){
        artQuiz = findViewById(R.id.art_quiz);
        testQuiz = findViewById(R.id.test_quiz);
        layoutOpening = findViewById(R.id.layout_opening);
        layoutQuizes = findViewById(R.id.layout_quizes);
        nameInput = findViewById(R.id.name_input);
        beginButton = findViewById(R.id.begin_button);
    }

    //OnClickListeners for opening and Quiz Options
    private void onClickListeners(){
        //Chooses the
        nameInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    PLAYER_NAME = nameInput.getText().toString();
                    if (v != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        nameInput.clearFocus();
                    }
                    Log.v(PLAYER_INPUT_NAME, PLAYER_NAME);
                    return true;
                }
                return false;
            }
        });

        //Set a clickListener that View
        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PLAYER_NAME.matches("")) {
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_LONG).show();
                } else {
                    layoutOpening.setVisibility(View.GONE);
                    layoutQuizes.setVisibility(View.VISIBLE);
                }
            }
        });

        artQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                Intent theArtQuiz = new Intent(MainActivity.this, AllArtQuestionsActivity.class);
                theArtQuiz.putExtra(PLAYER_INPUT_NAME, PLAYER_NAME);
                Log.v(PLAYER_NAME, PLAYER_NAME + " activity");
                startActivity(theArtQuiz);
            }
        });

        testQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent theTestQuiz = new Intent(MainActivity.this, TestQuizActivity.class);
                theTestQuiz.putExtra(PLAYER_INPUT_NAME, PLAYER_NAME);
                startActivity(theTestQuiz);
            }
        });
    }


    //OnClick for submit button. Checks which are correct
    //Notifies if the Quiz isn't finished or what score they received

    //Checks which answers are correct and applies a Red X or Green Checkmark to each question number
    //as well as placing Green Checkmarks on the correct answers
    //as well as placing Red Xs on the incorrectly marked answers


//    //Correct answers Q2: Monet, Cezanne, Cassatt, Renoir, Morisot
//    public void questionTwoCheck() {
//        if ((monetQ2.isChecked()) && (cezanneQ2.isChecked()) && (cassattQ2.isChecked()) && (renoirQ2.isChecked()) && (morisotQ2.isChecked())) {
//            scoreTwo = 25;
//            checkTwo = 0;
//        }
//        if ((monetQ2.isChecked()) || (cezanneQ2.isChecked()) || (cassattQ2.isChecked()) || (renoirQ2.isChecked()) || (morisotQ2.isChecked())) {
//            checkTwo = 0;
//        }
//        if ((picassoQ2.isChecked()) || (goghQ2.isChecked()) || (pollockQ2.isChecked()) || (christovQ2.isChecked()) || (manetQ2.isChecked())) {
//            scoreTwo = 0;
//            checkTwo = 0;
//        }
//        if (checkTwo == 1) {
//            questionTwo.setTextColor(redColorValue);
//        } else {
//            questionTwo.setTextColor(blackColorValue);
//        }
//    }
//

//    //Correct answers Q9: DegasA-E
//    public void questionNineCheck() {
//        if ((degasQ9A.isChecked()) && (degasQ9B.isChecked()) && (degasQ9C.isChecked()) && (degasQ9D.isChecked()) && (degasQ9E.isChecked())) {
//            scoreNine = 25;
//            checkNine = 0;
//        }
//        if ((degasQ9A.isChecked()) || (degasQ9B.isChecked()) || (degasQ9C.isChecked()) || (degasQ9D.isChecked()) || (degasQ9E.isChecked())) {
//            checkNine = 0;
//        }
//        if ((railwayQ9.isChecked()) || (portraitQ9.isChecked()) || (annunciationQ9.isChecked()) || (monaQ9.isChecked()) || (olympiaQ9.isChecked())) {
//            scoreNine = 0;
//            checkNine = 0;
//        }
//        if (checkNine == 1) {
//            questionNine.setTextColor(redColorValue);
//        } else {
//            questionNine.setTextColor(blackColorValue);
//        }
//    }

//    //Calculates their score
//    public String calculateScore() {
//        String sum = "\n" + (scoreOne + scoreTwo + scoreThree + scoreFour + scoreFive + scoreFiveSetTwo + scoreSix +
//                scoreSeven + scoreEight + scoreNine + scoreTen) + "%";
//        return sum;
//    }
//
//    public void resetButton(View view){
//        Intent i = getBaseContext().getPackageManager()
//                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(i);
//
//    }
//
//    public void resetApp(View view) {
//        Intent intent = getIntent();
//        finish();
//        startActivity( intent );
//    }

}

