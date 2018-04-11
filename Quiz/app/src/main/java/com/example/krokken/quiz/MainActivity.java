package com.example.krokken.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;

    EditText nameInput;
    Button beginButton;
    TextView artQuiz, testQuiz;
    View layoutOpening, layoutQuizes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PACKAGE_NAME = getApplicationContext().getPackageName();

        findViews();
        onClickListeners();

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
        //Set a clickListener that View
        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getName().matches("")) {
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
                startActivity(theArtQuiz);
            }
        });

        testQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent theTestQuiz = new Intent(MainActivity.this, TestQuizActivity.class);
                startActivity(theTestQuiz);
            }
        });
    }

    //Gets the name from the opening layout
    public String getName() {
        String name = nameInput.getText().toString();
        return name;
    }

    //OnClick for submit button. Checks which are correct
    //Notifies if the Quiz isn't finished or what score they received
//    public void submitAnswersButton(View v) {
//
//        questionOneCheck();
//        questionTwoCheck();
//        questionThreeCheck();
//        questionFourCheck();
//        questionFiveCheck();
//        questionSixCheck();
//        questionSevenCheck();
//        questionEightCheck();
//        questionNineCheck();
//        questionTenCheck();
//

    //Checks which answers are correct and applies a Red X or Green Checkmark to each question number
    //as well as placing Green Checkmarks on the correct answers
    //as well as placing Red Xs on the incorrectly marked answers
//    private void showCorrect() {
//        if (scoreOne == 0) {
//            numOneCorrect.setImageResource(R.drawable.red_x);
//            numOneCorrect.setVisibility(View.VISIBLE);
//            if (pabloQ1.isChecked()) {
//                incorrectOneA.setVisibility(View.VISIBLE);
//            } else if (shrikQ1.isChecked()) {
//                incorrectOneB.setVisibility(View.VISIBLE);
//            } else if (goghQ1.isChecked()) {
//                incorrectOneD.setVisibility(View.VISIBLE);
//            } else if (eyckQ1.isChecked()) {
//                incorrectOneE.setVisibility(View.VISIBLE);
//            }
//        } else {
//            numOneCorrect.setImageResource(R.drawable.green_checkmark);
//            numOneCorrect.setVisibility(View.VISIBLE);
//        }
//        if (scoreTwo == 0) {
//            numTwoCorrect.setImageResource(R.drawable.red_x);
//            numTwoCorrect.setVisibility(View.VISIBLE);
//            if (manetQ2.isChecked()) {
//                incorrectTwoA.setVisibility(View.VISIBLE);
//            }
//            if (picassoQ2.isChecked()) {
//                incorrectTwoC.setVisibility(View.VISIBLE);
//            }
//            if (goghQ2.isChecked()) {
//                incorrectTwoD.setVisibility(View.VISIBLE);
//            }
//            if (christovQ2.isChecked()) {
//                incorrectTwoI.setVisibility(View.VISIBLE);
//            }
//            if (pollockQ2.isChecked()) {
//                incorrectTwoJ.setVisibility(View.VISIBLE);
//            }
//            if (!monetQ2.isChecked()) {
//                monetQ2.setTextColor(redColorValue);
//            }
//            if (!cezanneQ2.isChecked()) {
//                cezanneQ2.setTextColor(redColorValue);
//            }
//            if (!cassattQ2.isChecked()) {
//                cassattQ2.setTextColor(redColorValue);
//            }
//            if (!morisotQ2.isChecked()) {
//                morisotQ2.setTextColor(redColorValue);
//            }
//            if (!renoirQ2.isChecked()) {
//                renoirQ2.setTextColor(redColorValue);
//            }
//        } else {
//            numTwoCorrect.setImageResource(R.drawable.green_checkmark);
//            numTwoCorrect.setVisibility(View.VISIBLE);
//        }
//        if (scoreThree == 0) {
//            numThreeCorrect.setImageResource(R.drawable.red_x);
//            numThreeCorrect.setVisibility(View.VISIBLE);
//            incorrectThree.setVisibility(View.VISIBLE);
//            questionThreeAnswer.setBackgroundColor(redColorValue);
//        } else {
//            numThreeCorrect.setImageResource(R.drawable.green_checkmark);
//            numThreeCorrect.setVisibility(View.VISIBLE);
//        }
//        if (scoreFour == 0) {
//            numFourCorrect.setImageResource(R.drawable.red_x);
//            numFourCorrect.setVisibility(View.VISIBLE);
//            if (venusQ4.isChecked()) {
//                incorrectFourA.setVisibility(View.VISIBLE);
//            } else if (baptistQ4.isChecked()) {
//                incorrectFourB.setVisibility(View.VISIBLE);
//            } else if (monaQ4.isChecked()) {
//                incorrectFourD.setVisibility(View.VISIBLE);
//            } else if (thinkerQ4.isChecked()) {
//                incorrectFourE.setVisibility(View.VISIBLE);
//            }
//        } else {
//            numFourCorrect.setImageResource(R.drawable.green_checkmark);
//            numFourCorrect.setVisibility(View.VISIBLE);
//        }
//        if ((scoreFive == 0) || (scoreFiveSetTwo == 0)) {
//            numFiveCorrect.setImageResource(R.drawable.red_x);
//            numFiveCorrect.setVisibility(View.VISIBLE);
//            if (woodQ5.isChecked()) {
//                incorrectFiveA.setVisibility(View.VISIBLE);
//            } else if (canvasQ5.isChecked()) {
//                incorrectFiveC.setVisibility(View.VISIBLE);
//            } else if (clothQ5.isChecked()) {
//                incorrectFiveD.setVisibility(View.VISIBLE);
//            } else if (ceilingQ5.isChecked()) {
//                incorrectFiveE.setVisibility(View.VISIBLE);
//            }
//            if (apostleQ5.isChecked()) {
//                incorrectFiveTwoF.setVisibility(View.VISIBLE);
//            } else if (josephQ5.isChecked()) {
//                incorrectFiveTwoG.setVisibility(View.VISIBLE);
//            } else if (legQ5.isChecked()) {
//                incorrectFiveTwoI.setVisibility(View.VISIBLE);
//            } else if (skylightQ5.isChecked()) {
//                incorrectFiveTwoJ.setVisibility(View.VISIBLE);
//            }
//        } else {
//            numFiveCorrect.setImageResource(R.drawable.green_checkmark);
//            numFiveCorrect.setVisibility(View.VISIBLE);
//        }
//        if (scoreSix == 0) {
//            numSixCorrect.setImageResource(R.drawable.red_x);
//            numSixCorrect.setVisibility(View.VISIBLE);
//            if (louvreQ6.isChecked()) {
//                incorrectSixB.setVisibility(View.VISIBLE);
//            } else if (momaQ6.isChecked()) {
//                incorrectSixC.setVisibility(View.VISIBLE);
//            } else if (nhmQ6.isChecked()) {
//                incorrectSixD.setVisibility(View.VISIBLE);
//            } else if (vaticanQ6.isChecked()) {
//                incorrectSixE.setVisibility(View.VISIBLE);
//            }
//        } else {
//            numSixCorrect.setImageResource(R.drawable.green_checkmark);
//            numSixCorrect.setVisibility(View.VISIBLE);
//        }
//        if (scoreSeven == 0) {
//            numSevenCorrect.setImageResource(R.drawable.red_x);
//            numSevenCorrect.setVisibility(View.VISIBLE);
//            if (degasQ7.isChecked()) {
//                incorrectSevenA.setVisibility(View.VISIBLE);
//            } else if (picassoQ7.isChecked()) {
//                incorrectSevenC.setVisibility(View.VISIBLE);
//            } else if (eyckQ7.isChecked()) {
//                incorrectSevenD.setVisibility(View.VISIBLE);
//            } else if (migardeQ7.isChecked()) {
//                incorrectSevenE.setVisibility(View.VISIBLE);
//            }
//        } else {
//            numSevenCorrect.setImageResource(R.drawable.green_checkmark);
//            numSevenCorrect.setVisibility(View.VISIBLE);
//        }
//        if (scoreEight == 0) {
//            numEightCorrect.setImageResource(R.drawable.red_x);
//            numEightCorrect.setVisibility(View.VISIBLE);
//            if (christovQ8.isChecked()) {
//                incorrectEightA.setVisibility(View.VISIBLE);
//            } else if (leonardoQ8.isChecked()) {
//                incorrectEightB.setVisibility(View.VISIBLE);
//            } else if (monetQ8.isChecked()) {
//                incorrectEightC.setVisibility(View.VISIBLE);
//            } else if (cezanneQ8.isChecked()) {
//                incorrectEightD.setVisibility(View.VISIBLE);
//            }
//        } else {
//            numEightCorrect.setImageResource(R.drawable.green_checkmark);
//            numEightCorrect.setVisibility(View.VISIBLE);
//        }
//        if (scoreNine == 0) {
//            numNineCorrect.setImageResource(R.drawable.red_x);
//            numNineCorrect.setVisibility(View.VISIBLE);
//            if (railwayQ9.isChecked()) {
//                incorrectNineB.setVisibility(View.VISIBLE);
//            }
//            if (annunciationQ9.isChecked()) {
//                incorrectNineF.setVisibility(View.VISIBLE);
//            }
//            if (portraitQ9.isChecked()) {
//                incorrectNineG.setVisibility(View.VISIBLE);
//            }
//            if (olympiaQ9.isChecked()) {
//                incorrectNineI.setVisibility(View.VISIBLE);
//            }
//            if (monaQ9.isChecked()) {
//                incorrectNineJ.setVisibility(View.VISIBLE);
//            }
//            if (!degasQ9A.isChecked()) {
//                degasQ9A.setTextColor(redColorValue);
//            }
//            if (!degasQ9B.isChecked()) {
//                degasQ9B.setTextColor(redColorValue);
//            }
//            if (!degasQ9C.isChecked()) {
//                degasQ9C.setTextColor(redColorValue);
//            }
//            if (!degasQ9D.isChecked()) {
//                degasQ9D.setTextColor(redColorValue);
//            }
//            if (!degasQ9E.isChecked()) {
//                degasQ9E.setTextColor(redColorValue);
//            }
//        } else {
//            numNineCorrect.setImageResource(R.drawable.green_checkmark);
//            numNineCorrect.setVisibility(View.VISIBLE);
//        }
//        if (scoreTen == 0) {
//            numTenCorrect.setImageResource(R.drawable.red_x);
//            numTenCorrect.setVisibility(View.VISIBLE);
//            if (georgeQ10.isChecked()) {
//                incorrectTenB.setVisibility(View.VISIBLE);
//            } else if (queenQ10.isChecked()) {
//                incorrectTenC.setVisibility(View.VISIBLE);
//            } else if (louisQ10.isChecked()) {
//                incorrectTenD.setVisibility(View.VISIBLE);
//            } else if (bonaparteQ10.isChecked()) {
//                incorrectTenE.setVisibility(View.VISIBLE);
//            }
//        } else {
//            numTenCorrect.setImageResource(R.drawable.green_checkmark);
//            numTenCorrect.setVisibility(View.VISIBLE);
//        }
//        correctOne.setVisibility(View.VISIBLE);
//        correctTwoB.setVisibility(View.VISIBLE);
//        correctTwoE.setVisibility(View.VISIBLE);
//        correctTwoF.setVisibility(View.VISIBLE);
//        correctTwoG.setVisibility(View.VISIBLE);
//        correctTwoH.setVisibility(View.VISIBLE);
//        correctFour.setVisibility(View.VISIBLE);
//        correctFive.setVisibility(View.VISIBLE);
//        correctFiveSetTwo.setVisibility(View.VISIBLE);
//        correctSix.setVisibility(View.VISIBLE);
//        correctSeven.setVisibility(View.VISIBLE);
//        correctEight.setVisibility(View.VISIBLE);
//        correctNineA.setVisibility(View.VISIBLE);
//        correctNineC.setVisibility(View.VISIBLE);
//        correctNineD.setVisibility(View.VISIBLE);
//        correctNineE.setVisibility(View.VISIBLE);
//        correctNineH.setVisibility(View.VISIBLE);
//        correctTen.setVisibility(View.VISIBLE);
//    }


//
//    //Checks if the quiz is finished
//    public int checkQuestionsTotal() {
//        int sum;
//        sum = checkOne + checkTwo + checkThree + checkFour + checkFive + checkFive2 +
//                checkSix + checkSeven + checkEight + checkNine + checkTen;
//        return sum;
//    }
//
//    //Correct Answer Q1: Munch
//    public void questionOneCheck() {
//        if (pabloQ1.isChecked() || shrikQ1.isChecked() || goghQ1.isChecked() || eyckQ1.isChecked()) {
//            scoreOne = 0;
//            checkOne = 0;
//        } else if (munchQ1.isChecked()) {
//            scoreOne = 5;
//            checkOne = 0;
//        }
//
//        if (checkOne == 1) {
//            questionOne.setTextColor(redColorValue);
//
//        } else {
//            questionOne.setTextColor(blackColorValue);
//        }
//    }
//
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
//    //Correct answer Q3: Pablo Picasso
//    public void questionThreeCheck() {
//        String answerThree = questionThreeAnswer.getText().toString().toLowerCase();
//        checkThree = 0;
//        if (answerThree.matches("pablo picasso")) {
//            scoreThree = 10;
//        } else if (answerThree.matches("picasso")) {
//            scoreThree = 10;
//        } else if (answerThree.matches("pablo")) {
//            scoreThree = 10;
//        } else if (answerThree.matches("pablopicasso")) {
//            scoreThree = 10;
//        } else if (answerThree.matches("")) {
//            checkThree = 1;
//        } else {
//            scoreThree = 0;
//        }
//        if (checkThree == 1) {
//            questionThree.setTextColor(redColorValue);
//        } else {
//            questionThree.setTextColor(blackColorValue);
//        }
//    }
//
//    //Correct Answer Q4: Sistine
//    public void questionFourCheck() {
//        if (venusQ4.isChecked() || baptistQ4.isChecked() || monaQ4.isChecked() || thinkerQ4.isChecked()) {
//            scoreFour = 0;
//            checkFour = 0;
//        } else if (sistineQ4.isChecked()) {
//            scoreFour = 5;
//            checkFour = 0;
//        }
//
//        if (checkFour == 1) {
//            questionFour.setTextColor(redColorValue);
//        } else {
//            questionFour.setTextColor(blackColorValue);
//        }
//    }
//
//    //Correct Answers Q5: Wall & Doorway
//    public void questionFiveCheck() {
//        if (woodQ5.isChecked() || canvasQ5.isChecked() || clothQ5.isChecked() || ceilingQ5.isChecked()) {
//            scoreFive = 0;
//            checkFive = 0;
//        } else if (wallQ5.isChecked()) {
//            scoreFive = 5;
//            checkFive = 0;
//        }
//        if (apostleQ5.isChecked() || josephQ5.isChecked() || legQ5.isChecked() || skylightQ5.isChecked()) {
//            scoreFiveSetTwo = 0;
//            checkFive2 = 0;
//        } else if (doorwayQ5.isChecked()) {
//            scoreFiveSetTwo = 5;
//            checkFive2 = 0;
//        }
//        if ((checkFive == 1) || (checkFive2 == 1)) {
//            questionFive.setTextColor(redColorValue);
//        } else {
//            questionFive.setTextColor(blackColorValue);
//        }
//    }
//
//    //Correct Answers Q6: Decorative
//    public void questionSixCheck() {
//        if (louvreQ6.isChecked() || momaQ6.isChecked() || nhmQ6.isChecked() || vaticanQ6.isChecked()) {
//            scoreSix = 0;
//            checkSix = 0;
//        } else if (decorativeQ6.isChecked()) {
//            scoreSix = 5;
//            checkSix = 0;
//        }
//        if (checkSix == 1) {
//            questionSix.setTextColor(redColorValue);
//        } else {
//            questionSix.setTextColor(blackColorValue);
//        }
//    }
//
//    //Correct Answers Q7: Rembrandt
//    public void questionSevenCheck() {
//        if (degasQ7.isChecked() || picassoQ7.isChecked() || eyckQ7.isChecked() || migardeQ7.isChecked()) {
//            scoreSeven = 0;
//            checkSeven = 0;
//        } else if (rembrandtQ7.isChecked()) {
//            scoreSeven = 5;
//            checkSeven = 0;
//        }
//        if (checkSeven == 1) {
//            questionSeven.setTextColor(redColorValue);
//        } else {
//            questionSeven.setTextColor(blackColorValue);
//        }
//    }
//
//    //Correct Answers Q8: Magritte
//    public void questionEightCheck() {
//        if (christovQ8.isChecked() || leonardoQ8.isChecked() || monetQ8.isChecked() || cezanneQ8.isChecked()) {
//            scoreEight = 0;
//            checkEight = 0;
//        } else if (magritteQ8.isChecked()) {
//            scoreEight = 5;
//            checkEight = 0;
//        }
//        if (checkEight == 1) {
//            questionEight.setTextColor(redColorValue);
//        } else {
//            questionEight.setTextColor(blackColorValue);
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
//
//    //Correct Answers Q10: Napoleon III
//    public void questionTenCheck() {
//        if (georgeQ10.isChecked() || queenQ10.isChecked() || louisQ10.isChecked() || bonaparteQ10.isChecked()) {
//            scoreTen = 0;
//            checkTen = 0;
//        } else if (napoleonQ10.isChecked()) {
//            scoreTen = 5;
//            checkTen = 0;
//        }
//        if (checkTen == 1) {
//            questionTen.setTextColor(redColorValue);
//        } else {
//            questionTen.setTextColor(blackColorValue);
//        }
//    }
//
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

