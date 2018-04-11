package com.example.krokken.anartquiz;

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

    Toast needName, hint, checkQs, submitScore;

    int redColorValue, blackColorValue,
            scoreOne, scoreTwo, scoreThree, scoreFour, scoreFive, scoreFiveSetTwo, scoreSix,
            scoreSeven, scoreEight, scoreNine, scoreTen,
            checkOne = 1, checkTwo = 1, checkThree = 1, checkFour = 1, checkFive = 1,
            checkFive2 = 1, checkSix = 1, checkSeven = 1, checkEight = 1, checkNine = 1, checkTen = 1;

    EditText nameInput, questionThreeAnswer;

    Button submitButton, beginButton;

    CheckBox manetQ2, monetQ2, picassoQ2, goghQ2, cezanneQ2, cassattQ2, morisotQ2, renoirQ2, pollockQ2, christovQ2,
            degasQ9A, degasQ9B, degasQ9C, degasQ9D, degasQ9E, railwayQ9, annunciationQ9, portraitQ9, olympiaQ9, monaQ9;

    RadioButton pabloQ1, munchQ1, shrikQ1, goghQ1, eyckQ1,
            venusQ4, baptistQ4, sistineQ4, monaQ4, thinkerQ4,
            woodQ5, wallQ5, canvasQ5, clothQ5, ceilingQ5, apostleQ5, josephQ5, doorwayQ5, legQ5, skylightQ5,
            decorativeQ6, louvreQ6, momaQ6, nhmQ6, vaticanQ6,
            degasQ7, rembrandtQ7, picassoQ7, eyckQ7, migardeQ7,
            christovQ8, leonardoQ8, monetQ8, cezanneQ8, magritteQ8,
            napoleonQ10, georgeQ10, queenQ10, louisQ10, bonaparteQ10;

    TextView questionTitle, incorrectThree,

    questionOne, questionTwo, questionThree, questionFour, questionFive,
            questionSix, questionSeven, questionEight, questionNine, questionTen,
            questionNum1, questionNum2, questionNum3, questionNum4, questionNum5,
            questionNum6, questionNum7, questionNum8, questionNum9, questionNum10;

    ImageView openingImage, numOneCorrect, numTwoCorrect, numThreeCorrect, numFourCorrect,
            numFiveCorrect, numSixCorrect, numSevenCorrect, numEightCorrect, numNineCorrect, numTenCorrect,
            correctOne, correctTwoB, correctTwoE, correctTwoF, correctTwoG, correctTwoH, correctFour, correctFive,
            correctFiveSetTwo, correctSix, correctSeven, correctEight, correctNineA,
            correctNineC, correctNineD, correctNineE, correctNineH, correctTen,
            incorrectOneA, incorrectOneB, incorrectOneD, incorrectOneE, incorrectTwoA, incorrectTwoC,
            incorrectTwoD, incorrectTwoI, incorrectTwoJ, incorrectFourA, incorrectFourB,
            incorrectFourD, incorrectFourE, incorrectFiveA, incorrectFiveC, incorrectFiveD, incorrectFiveE,
            incorrectFiveTwoF, incorrectFiveTwoG, incorrectFiveTwoI, incorrectFiveTwoJ, incorrectSixB,
            incorrectSixC, incorrectSixD, incorrectSixE, incorrectSevenA, incorrectSevenC, incorrectSevenD,
            incorrectSevenE, incorrectEightA, incorrectEightB, incorrectEightC, incorrectEightD, incorrectNineB,
            incorrectNineF, incorrectNineG, incorrectNineI, incorrectNineJ, incorrectTenB, incorrectTenC, incorrectTenD, incorrectTenE;

    View layoutOpening, layoutRest;

    String[] numbers = {"#1", "#2", "#3", "#4", "#5", "#6", "#7", "#8", "#9", "#10"};
    String[] questions = {
            "This painting is credited to whom?"
            , "Which of the following considered themselves part of the Impressionist movement?"
            , "This painting is credited to whom?"
            , "The artist credited with this piece is also well known for which?"
            , "Leonardo da Vinci's last supper is painted on which of the following and what was intentionally added over 150 years later?"
            , "August Rodin was commissioned this piece, The Gates of Hell; it was intended for which museum?"
            , "This painting is credited to whom?"
            , "This is a parody of a famous painting by which artist?"
            , "Which of the following are popular works of art credited to Edgar Degas?"
            , "Salon des Refusés became legitimatized when which ruler decreed the paintings be shown for public judgement in another part of the Palace of Industry?"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redColorValue = Color.parseColor("#FF0000");
        blackColorValue = Color.parseColor("#757575");

        findViews();
    }

    public void findViews() {
        layoutRest = findViewById(R.id.layout_rest);
        questionThreeAnswer = findViewById(R.id.edittext_q3);
        correctAnswers();
        incorrectAnswers();
        numCorrect();
        findQuestions();
        findCheckBoxes();
        findButtons();
        openingViews();
        findQNumbers();
    }

    //Finds the views that place a Red X or Green Checkmark on the Question's Number
    private void numCorrect() {
        numOneCorrect = findViewById(R.id.num_1_correct);
        numTwoCorrect = findViewById(R.id.num_2_correct);
        numThreeCorrect = findViewById(R.id.num_3_correct);
        numFourCorrect = findViewById(R.id.num_4_correct);
        numFiveCorrect = findViewById(R.id.num_5_correct);
        numSixCorrect = findViewById(R.id.num_6_correct);
        numSevenCorrect = findViewById(R.id.num_7_correct);
        numEightCorrect = findViewById(R.id.num_8_correct);
        numNineCorrect = findViewById(R.id.num_9_correct);
        numTenCorrect = findViewById(R.id.num_10_correct);
    }

    //Finds views to show the Green Checkmark on all correct answers
    private void correctAnswers() {
        correctOne = findViewById(R.id.num_1_correctC);
        correctTwoB = findViewById(R.id.num_2_correctB);
        correctTwoE = findViewById(R.id.num_2_correctE);
        correctTwoF = findViewById(R.id.num_2_correctF);
        correctTwoG = findViewById(R.id.num_2_correctG);
        correctTwoH = findViewById(R.id.num_2_correctH);
        correctFour = findViewById(R.id.num_4_correctC);
        correctFive = findViewById(R.id.num_5_correctB);
        correctFiveSetTwo = findViewById(R.id.num_5_correctH);
        correctSix = findViewById(R.id.num_6_correctA);
        correctSeven = findViewById(R.id.num_7_correctB);
        correctEight = findViewById(R.id.num_8_correctE);
        correctNineA = findViewById(R.id.num_9_correctA);
        correctNineC = findViewById(R.id.num_9_correctC);
        correctNineD = findViewById(R.id.num_9_correctD);
        correctNineE = findViewById(R.id.num_9_correctE);
        correctNineH = findViewById(R.id.num_9_correctH);
        correctTen = findViewById(R.id.num_10_correctA);
    }

    //Finds views to show the Red X on incorrectly marked answers
    private void incorrectAnswers() {
        incorrectOneA = findViewById(R.id.num_1_incorrectA);
        incorrectOneB = findViewById(R.id.num_1_incorrectB);
        incorrectOneD = findViewById(R.id.num_1_incorrectD);
        incorrectOneE = findViewById(R.id.num_1_incorrectE);
        incorrectTwoA = findViewById(R.id.num_2_incorrectA);
        incorrectTwoC = findViewById(R.id.num_2_incorrectC);
        incorrectTwoD = findViewById(R.id.num_2_incorrectD);
        incorrectTwoI = findViewById(R.id.num_2_incorrectI);
        incorrectTwoJ = findViewById(R.id.num_2_incorrectJ);
        incorrectThree = findViewById(R.id.num_3_incorrectPP);
        incorrectFourA = findViewById(R.id.num_4_incorrectA);
        incorrectFourB = findViewById(R.id.num_4_incorrectB);
        incorrectFourD = findViewById(R.id.num_4_incorrectD);
        incorrectFourE = findViewById(R.id.num_4_incorrectE);
        incorrectFiveA = findViewById(R.id.num_5_incorrectA);
        incorrectFiveC = findViewById(R.id.num_5_incorrectC);
        incorrectFiveD = findViewById(R.id.num_5_incorrectD);
        incorrectFiveE = findViewById(R.id.num_5_incorrectE);
        incorrectFiveTwoF = findViewById(R.id.num_5_incorrectF);
        incorrectFiveTwoG = findViewById(R.id.num_5_incorrectG);
        incorrectFiveTwoI = findViewById(R.id.num_5_incorrectI);
        incorrectFiveTwoJ = findViewById(R.id.num_5_incorrectJ);
        incorrectSixB = findViewById(R.id.num_6_incorrectB);
        incorrectSixC = findViewById(R.id.num_6_incorrectC);
        incorrectSixD = findViewById(R.id.num_6_incorrectD);
        incorrectSixE = findViewById(R.id.num_6_incorrectE);
        incorrectSevenA = findViewById(R.id.num_7_incorrectA);
        incorrectSevenC = findViewById(R.id.num_7_incorrectC);
        incorrectSevenD = findViewById(R.id.num_7_incorrectD);
        incorrectSevenE = findViewById(R.id.num_7_incorrectE);
        incorrectEightA = findViewById(R.id.num_8_incorrectA);
        incorrectEightB = findViewById(R.id.num_8_incorrectB);
        incorrectEightC = findViewById(R.id.num_8_incorrectC);
        incorrectEightD = findViewById(R.id.num_8_incorrectD);
        incorrectNineB = findViewById(R.id.num_9_incorrectB);
        incorrectNineF = findViewById(R.id.num_9_incorrectF);
        incorrectNineG = findViewById(R.id.num_9_incorrectG);
        incorrectNineI = findViewById(R.id.num_9_incorrectI);
        incorrectNineJ = findViewById(R.id.num_9_incorrectJ);
        incorrectTenB = findViewById(R.id.num_10_incorrectB);
        incorrectTenC = findViewById(R.id.num_10_incorrectC);
        incorrectTenD = findViewById(R.id.num_10_incorrectD);
        incorrectTenE = findViewById(R.id.num_10_incorrectE);
    }

    public void openingViews() {
        layoutOpening = findViewById(R.id.layout_opening);
        openingImage = findViewById(R.id.opening_image);
        questionTitle = findViewById(R.id.question_title);
        nameInput = findViewById(R.id.name_input);
    }

    //Finds checkboxes for questions two and nine
    public void findCheckBoxes() {
        //Answers for Question Two
        manetQ2 = findViewById(R.id.manet_q2);
        monetQ2 = findViewById(R.id.monet_q2);
        picassoQ2 = findViewById(R.id.picasso_q2);
        goghQ2 = findViewById(R.id.gogh_q2);
        cezanneQ2 = findViewById(R.id.cezanne_q2);
        cassattQ2 = findViewById(R.id.cassatt_q2);
        morisotQ2 = findViewById(R.id.morisot_q2);
        renoirQ2 = findViewById(R.id.renoir_q2);
        pollockQ2 = findViewById(R.id.pollock_q2);
        christovQ2 = findViewById(R.id.christov_q2);
        //Answers for Question Nine
        degasQ9A = findViewById(R.id.degas_q9a);
        degasQ9B = findViewById(R.id.degas_q9b);
        degasQ9C = findViewById(R.id.degas_q9c);
        degasQ9D = findViewById(R.id.degas_q9d);
        degasQ9E = findViewById(R.id.degas_q9e);
        railwayQ9 = findViewById(R.id.railway_q9);
        annunciationQ9 = findViewById(R.id.annunciation_q9);
        portraitQ9 = findViewById(R.id.portrait_q9);
        olympiaQ9 = findViewById(R.id.olympia_q9);
        monaQ9 = findViewById(R.id.mona_q9);

    }

    //finds all the buttons that will be used in the Quiz
    public void findButtons() {
        beginButton = findViewById(R.id.begin_button);
        submitButton = findViewById(R.id.submit_button);
        //Radio for Question 1
        pabloQ1 = findViewById(R.id.pablo_q1);
        munchQ1 = findViewById(R.id.munch_q1);
        shrikQ1 = findViewById(R.id.shrik_q1);
        goghQ1 = findViewById(R.id.gogh_q1);
        eyckQ1 = findViewById(R.id.eyck_q1);
        //Radio for Question 4
        venusQ4 = findViewById(R.id.venus_q4);
        baptistQ4 = findViewById(R.id.baptist_q4);
        sistineQ4 = findViewById(R.id.sistine_q4);
        monaQ4 = findViewById(R.id.mona_q4);
        thinkerQ4 = findViewById(R.id.thinker_q4);
        //Radio for Question 5
        woodQ5 = findViewById(R.id.wood_q5);
        canvasQ5 = findViewById(R.id.canvas_q5);
        clothQ5 = findViewById(R.id.cloth_q5);
        ceilingQ5 = findViewById(R.id.ceiling_q5);
        apostleQ5 = findViewById(R.id.apostle_q5);
        josephQ5 = findViewById(R.id.mary_q5);
        wallQ5 = findViewById(R.id.wall_q5);
        doorwayQ5 = findViewById(R.id.doorway_q5);
        legQ5 = findViewById(R.id.table_q5);
        skylightQ5 = findViewById(R.id.skylight_q5);
        //Radio for Question 6
        decorativeQ6 = findViewById(R.id.decorative_q6);
        louvreQ6 = findViewById(R.id.louvre_q6);
        momaQ6 = findViewById(R.id.moma_q6);
        nhmQ6 = findViewById(R.id.nhm_q6);
        vaticanQ6 = findViewById(R.id.vatican_q6);
        //Radio for Question 7
        degasQ7 = findViewById(R.id.degas_q7);
        rembrandtQ7 = findViewById(R.id.rembrandt_q7);
        picassoQ7 = findViewById(R.id.picasso_q7);
        eyckQ7 = findViewById(R.id.eyck_q7);
        migardeQ7 = findViewById(R.id.migarde_q7);
        //Radio for Question 8
        christovQ8 = findViewById(R.id.christov_q8);
        leonardoQ8 = findViewById(R.id.leonardo_q8);
        monetQ8 = findViewById(R.id.monet_q8);
        cezanneQ8 = findViewById(R.id.cezanne_q8);
        magritteQ8 = findViewById(R.id.magritte_q8);
        //Radio for Question 10
        napoleonQ10 = findViewById(R.id.napoleon_q10);
        georgeQ10 = findViewById(R.id.george_q10);
        queenQ10 = findViewById(R.id.queen_q10);
        louisQ10 = findViewById(R.id.louis_q10);
        bonaparteQ10 = findViewById(R.id.bonaparte_q10);
    }

    //finds the views to post the questions
    public void findQuestions() {
        questionOne = findViewById(R.id.question_1);
        questionTwo = findViewById(R.id.question_2);
        questionThree = findViewById(R.id.question_3);
        questionFour = findViewById(R.id.question_4);
        questionFive = findViewById(R.id.question_5);
        questionSix = findViewById(R.id.question_6);
        questionSeven = findViewById(R.id.question_7);
        questionEight = findViewById(R.id.question_8);
        questionNine = findViewById(R.id.question_9);
        questionTen = findViewById(R.id.question_10);
    }

    //finds the views to post the question numbers
    public void findQNumbers() {
        questionNum1 = findViewById(R.id.question_number1);
        questionNum2 = findViewById(R.id.question_number2);
        questionNum3 = findViewById(R.id.question_number3);
        questionNum4 = findViewById(R.id.question_number4);
        questionNum5 = findViewById(R.id.question_number5);
        questionNum6 = findViewById(R.id.question_number6);
        questionNum7 = findViewById(R.id.question_number7);
        questionNum8 = findViewById(R.id.question_number8);
        questionNum9 = findViewById(R.id.question_number9);
        questionNum10 = findViewById(R.id.question_number10);
    }

    //Gets the name from the opening layout
    public String getName() {
        String name = nameInput.getText().toString();
        return name;
    }

    //onClick for the Let's Begin button in the opening layout
    public void beginButton(View v) {
        if (getName().matches("")) {
            needName.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show();
        } else {
            submitButton.setVisibility(View.VISIBLE);
            layoutOpening.setVisibility(View.GONE);
            layoutRest.setVisibility(View.VISIBLE);

            for (String q : questions) {
                questionOne.setText(questions[0]);
                questionTwo.setText(questions[1]);
                questionThree.setText(questions[2]);
                questionFour.setText(questions[3]);
                questionFive.setText(questions[4]);
                questionSix.setText(questions[5]);
                questionSeven.setText(questions[6]);
                questionEight.setText(questions[7]);
                questionNine.setText(questions[8]);
                questionTen.setText(questions[9]);
            }
            for (String n : numbers) {
                questionNum1.setText(numbers[0]);
                questionNum2.setText(numbers[1]);
                questionNum3.setText(numbers[2]);
                questionNum4.setText(numbers[3]);
                questionNum5.setText(numbers[4]);
                questionNum6.setText(numbers[5]);
                questionNum7.setText(numbers[6]);
                questionNum8.setText(numbers[7]);
                questionNum9.setText(numbers[8]);
                questionNum10.setText(numbers[9]);

            }
        }
    }

    //onClick for the hint option at top
    public void hintClickable(View v) {
        hint.makeText(this, "This is a Quiz, no hints!", Toast.LENGTH_LONG).show();
    }

    //OnClick for submit button. Checks which are correct
    //Notifies if the Quiz isn't finished or what score they received
    public void submitAnswersButton(View v) {

        questionOneCheck();
        questionTwoCheck();
        questionThreeCheck();
        questionFourCheck();
        questionFiveCheck();
        questionSixCheck();
        questionSevenCheck();
        questionEightCheck();
        questionNineCheck();
        questionTenCheck();

        //Checks if all questions were answered
        if (checkQuestionsTotal() > 0) {
            checkQs.makeText(this, "You've completed " + (11 - checkQuestionsTotal()) + "/10.\n" + finishCheck() + "needs to be finished.", Toast.LENGTH_LONG).show();
        } else {
            disableButtons();
            showCorrect();
            questionThreeAnswer.setFocusable(false);
            questionOne.setFocusable(true);
            questionOne.setFocusableInTouchMode(true);
            submitScore.makeText(this, getName() + ", you've scored: " + calculateScore(), Toast.LENGTH_LONG).show();
        }
    }

    //Checks which answers are correct and applies a Red X or Green Checkmark to each question number
    //as well as placing Green Checkmarks on the correct answers
    //as well as placing Red Xs on the incorrectly marked answers
    private void showCorrect() {
        if (scoreOne == 0) {
            numOneCorrect.setImageResource(R.drawable.red_x);
            numOneCorrect.setVisibility(View.VISIBLE);
            if (pabloQ1.isChecked()) {
                incorrectOneA.setVisibility(View.VISIBLE);
            } else if (shrikQ1.isChecked()) {
                incorrectOneB.setVisibility(View.VISIBLE);
            } else if (goghQ1.isChecked()) {
                incorrectOneD.setVisibility(View.VISIBLE);
            } else if (eyckQ1.isChecked()) {
                incorrectOneE.setVisibility(View.VISIBLE);
            }
        } else {
            numOneCorrect.setImageResource(R.drawable.green_checkmark);
            numOneCorrect.setVisibility(View.VISIBLE);
        }
        if (scoreTwo == 0) {
            numTwoCorrect.setImageResource(R.drawable.red_x);
            numTwoCorrect.setVisibility(View.VISIBLE);
            if (manetQ2.isChecked()) {
                incorrectTwoA.setVisibility(View.VISIBLE);
            }
            if (picassoQ2.isChecked()) {
                incorrectTwoC.setVisibility(View.VISIBLE);
            }
            if (goghQ2.isChecked()) {
                incorrectTwoD.setVisibility(View.VISIBLE);
            }
            if (christovQ2.isChecked()) {
                incorrectTwoI.setVisibility(View.VISIBLE);
            }
            if (pollockQ2.isChecked()) {
                incorrectTwoJ.setVisibility(View.VISIBLE);
            }
            if (!monetQ2.isChecked()) {
                monetQ2.setTextColor(redColorValue);
            }
            if (!cezanneQ2.isChecked()) {
                cezanneQ2.setTextColor(redColorValue);
            }
            if (!cassattQ2.isChecked()) {
                cassattQ2.setTextColor(redColorValue);
            }
            if (!morisotQ2.isChecked()) {
                morisotQ2.setTextColor(redColorValue);
            }
            if (!renoirQ2.isChecked()) {
                renoirQ2.setTextColor(redColorValue);
            }
        } else {
            numTwoCorrect.setImageResource(R.drawable.green_checkmark);
            numTwoCorrect.setVisibility(View.VISIBLE);
        }
        if (scoreThree == 0) {
            numThreeCorrect.setImageResource(R.drawable.red_x);
            numThreeCorrect.setVisibility(View.VISIBLE);
            incorrectThree.setVisibility(View.VISIBLE);
            questionThreeAnswer.setBackgroundColor(redColorValue);
        } else {
            numThreeCorrect.setImageResource(R.drawable.green_checkmark);
            numThreeCorrect.setVisibility(View.VISIBLE);
        }
        if (scoreFour == 0) {
            numFourCorrect.setImageResource(R.drawable.red_x);
            numFourCorrect.setVisibility(View.VISIBLE);
            if (venusQ4.isChecked()) {
                incorrectFourA.setVisibility(View.VISIBLE);
            } else if (baptistQ4.isChecked()) {
                incorrectFourB.setVisibility(View.VISIBLE);
            } else if (monaQ4.isChecked()) {
                incorrectFourD.setVisibility(View.VISIBLE);
            } else if (thinkerQ4.isChecked()) {
                incorrectFourE.setVisibility(View.VISIBLE);
            }
        } else {
            numFourCorrect.setImageResource(R.drawable.green_checkmark);
            numFourCorrect.setVisibility(View.VISIBLE);
        }
        if ((scoreFive == 0) || (scoreFiveSetTwo == 0)) {
            numFiveCorrect.setImageResource(R.drawable.red_x);
            numFiveCorrect.setVisibility(View.VISIBLE);
            if (woodQ5.isChecked()) {
                incorrectFiveA.setVisibility(View.VISIBLE);
            } else if (canvasQ5.isChecked()) {
                incorrectFiveC.setVisibility(View.VISIBLE);
            } else if (clothQ5.isChecked()) {
                incorrectFiveD.setVisibility(View.VISIBLE);
            } else if (ceilingQ5.isChecked()) {
                incorrectFiveE.setVisibility(View.VISIBLE);
            }
            if (apostleQ5.isChecked()) {
                incorrectFiveTwoF.setVisibility(View.VISIBLE);
            } else if (josephQ5.isChecked()) {
                incorrectFiveTwoG.setVisibility(View.VISIBLE);
            } else if (legQ5.isChecked()) {
                incorrectFiveTwoI.setVisibility(View.VISIBLE);
            } else if (skylightQ5.isChecked()) {
                incorrectFiveTwoJ.setVisibility(View.VISIBLE);
            }
        } else {
            numFiveCorrect.setImageResource(R.drawable.green_checkmark);
            numFiveCorrect.setVisibility(View.VISIBLE);
        }
        if (scoreSix == 0) {
            numSixCorrect.setImageResource(R.drawable.red_x);
            numSixCorrect.setVisibility(View.VISIBLE);
            if (louvreQ6.isChecked()) {
                incorrectSixB.setVisibility(View.VISIBLE);
            } else if (momaQ6.isChecked()) {
                incorrectSixC.setVisibility(View.VISIBLE);
            } else if (nhmQ6.isChecked()) {
                incorrectSixD.setVisibility(View.VISIBLE);
            } else if (vaticanQ6.isChecked()) {
                incorrectSixE.setVisibility(View.VISIBLE);
            }
        } else {
            numSixCorrect.setImageResource(R.drawable.green_checkmark);
            numSixCorrect.setVisibility(View.VISIBLE);
        }
        if (scoreSeven == 0) {
            numSevenCorrect.setImageResource(R.drawable.red_x);
            numSevenCorrect.setVisibility(View.VISIBLE);
            if (degasQ7.isChecked()) {
                incorrectSevenA.setVisibility(View.VISIBLE);
            } else if (picassoQ7.isChecked()) {
                incorrectSevenC.setVisibility(View.VISIBLE);
            } else if (eyckQ7.isChecked()) {
                incorrectSevenD.setVisibility(View.VISIBLE);
            } else if (migardeQ7.isChecked()) {
                incorrectSevenE.setVisibility(View.VISIBLE);
            }
        } else {
            numSevenCorrect.setImageResource(R.drawable.green_checkmark);
            numSevenCorrect.setVisibility(View.VISIBLE);
        }
        if (scoreEight == 0) {
            numEightCorrect.setImageResource(R.drawable.red_x);
            numEightCorrect.setVisibility(View.VISIBLE);
            if (christovQ8.isChecked()) {
                incorrectEightA.setVisibility(View.VISIBLE);
            } else if (leonardoQ8.isChecked()) {
                incorrectEightB.setVisibility(View.VISIBLE);
            } else if (monetQ8.isChecked()) {
                incorrectEightC.setVisibility(View.VISIBLE);
            } else if (cezanneQ8.isChecked()) {
                incorrectEightD.setVisibility(View.VISIBLE);
            }
        } else {
            numEightCorrect.setImageResource(R.drawable.green_checkmark);
            numEightCorrect.setVisibility(View.VISIBLE);
        }
        if (scoreNine == 0) {
            numNineCorrect.setImageResource(R.drawable.red_x);
            numNineCorrect.setVisibility(View.VISIBLE);
            if (railwayQ9.isChecked()) {
                incorrectNineB.setVisibility(View.VISIBLE);
            }
            if (annunciationQ9.isChecked()) {
                incorrectNineF.setVisibility(View.VISIBLE);
            }
            if (portraitQ9.isChecked()) {
                incorrectNineG.setVisibility(View.VISIBLE);
            }
            if (olympiaQ9.isChecked()) {
                incorrectNineI.setVisibility(View.VISIBLE);
            }
            if (monaQ9.isChecked()) {
                incorrectNineJ.setVisibility(View.VISIBLE);
            }
            if (!degasQ9A.isChecked()) {
                degasQ9A.setTextColor(redColorValue);
            }
            if (!degasQ9B.isChecked()) {
                degasQ9B.setTextColor(redColorValue);
            }
            if (!degasQ9C.isChecked()) {
                degasQ9C.setTextColor(redColorValue);
            }
            if (!degasQ9D.isChecked()) {
                degasQ9D.setTextColor(redColorValue);
            }
            if (!degasQ9E.isChecked()) {
                degasQ9E.setTextColor(redColorValue);
            }
        } else {
            numNineCorrect.setImageResource(R.drawable.green_checkmark);
            numNineCorrect.setVisibility(View.VISIBLE);
        }
        if (scoreTen == 0) {
            numTenCorrect.setImageResource(R.drawable.red_x);
            numTenCorrect.setVisibility(View.VISIBLE);
            if (georgeQ10.isChecked()) {
                incorrectTenB.setVisibility(View.VISIBLE);
            } else if (queenQ10.isChecked()) {
                incorrectTenC.setVisibility(View.VISIBLE);
            } else if (louisQ10.isChecked()) {
                incorrectTenD.setVisibility(View.VISIBLE);
            } else if (bonaparteQ10.isChecked()) {
                incorrectTenE.setVisibility(View.VISIBLE);
            }
        } else {
            numTenCorrect.setImageResource(R.drawable.green_checkmark);
            numTenCorrect.setVisibility(View.VISIBLE);
        }
        correctOne.setVisibility(View.VISIBLE);
        correctTwoB.setVisibility(View.VISIBLE);
        correctTwoE.setVisibility(View.VISIBLE);
        correctTwoF.setVisibility(View.VISIBLE);
        correctTwoG.setVisibility(View.VISIBLE);
        correctTwoH.setVisibility(View.VISIBLE);
        correctFour.setVisibility(View.VISIBLE);
        correctFive.setVisibility(View.VISIBLE);
        correctFiveSetTwo.setVisibility(View.VISIBLE);
        correctSix.setVisibility(View.VISIBLE);
        correctSeven.setVisibility(View.VISIBLE);
        correctEight.setVisibility(View.VISIBLE);
        correctNineA.setVisibility(View.VISIBLE);
        correctNineC.setVisibility(View.VISIBLE);
        correctNineD.setVisibility(View.VISIBLE);
        correctNineE.setVisibility(View.VISIBLE);
        correctNineH.setVisibility(View.VISIBLE);
        correctTen.setVisibility(View.VISIBLE);
    }

    //Disables all the questions for when anartquiz is 'submitted' and finished
    public void disableButtons() {
        submitButton.setEnabled(false);
        //answers for Question One
        pabloQ1.setEnabled(false);
        munchQ1.setEnabled(false);
        shrikQ1.setEnabled(false);
        goghQ1.setEnabled(false);
        eyckQ1.setEnabled(false);
        //answers for Question Two
        manetQ2.setEnabled(false);
        monetQ2.setEnabled(false);
        picassoQ2.setEnabled(false);
        goghQ2.setEnabled(false);
        cezanneQ2.setEnabled(false);
        cassattQ2.setEnabled(false);
        morisotQ2.setEnabled(false);
        renoirQ2.setEnabled(false);
        pollockQ2.setEnabled(false);
        christovQ2.setEnabled(false);
        //Question three EditText
        questionThreeAnswer.setEnabled(false);
        //Answers for Question Four
        venusQ4.setEnabled(false);
        baptistQ4.setEnabled(false);
        sistineQ4.setEnabled(false);
        monaQ4.setEnabled(false);
        thinkerQ4.setEnabled(false);
        //Answers for Question Five
        woodQ5.setEnabled(false);
        canvasQ5.setEnabled(false);
        clothQ5.setEnabled(false);
        ceilingQ5.setEnabled(false);
        apostleQ5.setEnabled(false);
        josephQ5.setEnabled(false);
        wallQ5.setEnabled(false);
        doorwayQ5.setEnabled(false);
        legQ5.setEnabled(false);
        skylightQ5.setEnabled(false);
        //Answers for Question Six
        decorativeQ6.setEnabled(false);
        louvreQ6.setEnabled(false);
        momaQ6.setEnabled(false);
        nhmQ6.setEnabled(false);
        vaticanQ6.setEnabled(false);
        //Answers for Question Seven
        degasQ7.setEnabled(false);
        rembrandtQ7.setEnabled(false);
        picassoQ7.setEnabled(false);
        eyckQ7.setEnabled(false);
        migardeQ7.setEnabled(false);
        //Answers for Question Eight
        christovQ8.setEnabled(false);
        leonardoQ8.setEnabled(false);
        monetQ8.setEnabled(false);
        cezanneQ8.setEnabled(false);
        magritteQ8.setEnabled(false);
        //Answers for Question Nine
        degasQ9A.setEnabled(false);
        degasQ9B.setEnabled(false);
        degasQ9C.setEnabled(false);
        degasQ9D.setEnabled(false);
        degasQ9E.setEnabled(false);
        railwayQ9.setEnabled(false);
        annunciationQ9.setEnabled(false);
        portraitQ9.setEnabled(false);
        olympiaQ9.setEnabled(false);
        monaQ9.setEnabled(false);
        //Answers for Question Ten
        napoleonQ10.setEnabled(false);
        georgeQ10.setEnabled(false);
        queenQ10.setEnabled(false);
        louisQ10.setEnabled(false);
        bonaparteQ10.setEnabled(false);
    }

    //Returns a string of questions that were unanswered
    private String finishCheck() {
        String checkQ = "";

        if (checkOne == 1) {
            checkQ += "#1 ";
        }
        if (checkTwo == 1) {
            checkQ += "#2 ";
        }
        if (checkThree == 1) {
            checkQ += "#3 ";
        }
        if (checkFour == 1) {
            checkQ += "#4 ";
        }
        if ((checkFive == 1) || (checkFive2 == 1)) {
            checkQ += "#5 ";
        }
        if (checkSix == 1) {
            checkQ += "#6 ";
        }
        if (checkSeven == 1) {
            checkQ += "#7 ";
        }
        if (checkEight == 1) {
            checkQ += "#8 ";
        }
        if (checkNine == 1) {
            checkQ += "#9 ";
        }
        if (checkTen == 1) {
            checkQ += "#10 ";
        }
        return checkQ;
    }

    //Checks if the anartquiz is finished
    public int checkQuestionsTotal() {
        int sum;
        sum = checkOne + checkTwo + checkThree + checkFour + checkFive + checkFive2 +
                checkSix + checkSeven + checkEight + checkNine + checkTen;
        return sum;
    }

    //Correct Answer Q1: Munch
    public void questionOneCheck() {
        if (pabloQ1.isChecked() || shrikQ1.isChecked() || goghQ1.isChecked() || eyckQ1.isChecked()) {
            scoreOne = 0;
            checkOne = 0;
        } else if (munchQ1.isChecked()) {
            scoreOne = 5;
            checkOne = 0;
        }

        if (checkOne == 1) {
            questionOne.setTextColor(redColorValue);

        } else {
            questionOne.setTextColor(blackColorValue);
        }
    }

    //Correct answers Q2: Monet, Cezanne, Cassatt, Renoir, Morisot
    public void questionTwoCheck() {
        if ((monetQ2.isChecked()) && (cezanneQ2.isChecked()) && (cassattQ2.isChecked()) && (renoirQ2.isChecked()) && (morisotQ2.isChecked())) {
            scoreTwo = 25;
            checkTwo = 0;
        }
        if ((monetQ2.isChecked()) || (cezanneQ2.isChecked()) || (cassattQ2.isChecked()) || (renoirQ2.isChecked()) || (morisotQ2.isChecked())) {
            checkTwo = 0;
        }
        if ((picassoQ2.isChecked()) || (goghQ2.isChecked()) || (pollockQ2.isChecked()) || (christovQ2.isChecked()) || (manetQ2.isChecked())) {
            scoreTwo = 0;
            checkTwo = 0;
        }
        if (checkTwo == 1) {
            questionTwo.setTextColor(redColorValue);
        } else {
            questionTwo.setTextColor(blackColorValue);
        }
    }

    //Correct answer Q3: Pablo Picasso
    public void questionThreeCheck() {
        String answerThree = questionThreeAnswer.getText().toString().toLowerCase();
        checkThree = 0;
        if (answerThree.matches("pablo picasso")) {
            scoreThree = 10;
        } else if (answerThree.matches("picasso")) {
            scoreThree = 10;
        } else if (answerThree.matches("pablo")) {
            scoreThree = 10;
        } else if (answerThree.matches("pablopicasso")) {
            scoreThree = 10;
        } else if (answerThree.matches("")) {
            checkThree = 1;
        } else {
            scoreThree = 0;
        }
        if (checkThree == 1) {
            questionThree.setTextColor(redColorValue);
        } else {
            questionThree.setTextColor(blackColorValue);
        }
    }

    //Correct Answer Q4: Sistine
    public void questionFourCheck() {
        if (venusQ4.isChecked() || baptistQ4.isChecked() || monaQ4.isChecked() || thinkerQ4.isChecked()) {
            scoreFour = 0;
            checkFour = 0;
        } else if (sistineQ4.isChecked()) {
            scoreFour = 5;
            checkFour = 0;
        }

        if (checkFour == 1) {
            questionFour.setTextColor(redColorValue);
        } else {
            questionFour.setTextColor(blackColorValue);
        }
    }

    //Correct Answers Q5: Wall & Doorway
    public void questionFiveCheck() {
        if (woodQ5.isChecked() || canvasQ5.isChecked() || clothQ5.isChecked() || ceilingQ5.isChecked()) {
            scoreFive = 0;
            checkFive = 0;
        } else if (wallQ5.isChecked()) {
            scoreFive = 5;
            checkFive = 0;
        }
        if (apostleQ5.isChecked() || josephQ5.isChecked() || legQ5.isChecked() || skylightQ5.isChecked()) {
            scoreFiveSetTwo = 0;
            checkFive2 = 0;
        } else if (doorwayQ5.isChecked()) {
            scoreFiveSetTwo = 5;
            checkFive2 = 0;
        }
        if ((checkFive == 1) || (checkFive2 == 1)) {
            questionFive.setTextColor(redColorValue);
        } else {
            questionFive.setTextColor(blackColorValue);
        }
    }

    //Correct Answers Q6: Decorative
    public void questionSixCheck() {
        if (louvreQ6.isChecked() || momaQ6.isChecked() || nhmQ6.isChecked() || vaticanQ6.isChecked()) {
            scoreSix = 0;
            checkSix = 0;
        } else if (decorativeQ6.isChecked()) {
            scoreSix = 5;
            checkSix = 0;
        }
        if (checkSix == 1) {
            questionSix.setTextColor(redColorValue);
        } else {
            questionSix.setTextColor(blackColorValue);
        }
    }

    //Correct Answers Q7: Rembrandt
    public void questionSevenCheck() {
        if (degasQ7.isChecked() || picassoQ7.isChecked() || eyckQ7.isChecked() || migardeQ7.isChecked()) {
            scoreSeven = 0;
            checkSeven = 0;
        } else if (rembrandtQ7.isChecked()) {
            scoreSeven = 5;
            checkSeven = 0;
        }
        if (checkSeven == 1) {
            questionSeven.setTextColor(redColorValue);
        } else {
            questionSeven.setTextColor(blackColorValue);
        }
    }

    //Correct Answers Q8: Magritte
    public void questionEightCheck() {
        if (christovQ8.isChecked() || leonardoQ8.isChecked() || monetQ8.isChecked() || cezanneQ8.isChecked()) {
            scoreEight = 0;
            checkEight = 0;
        } else if (magritteQ8.isChecked()) {
            scoreEight = 5;
            checkEight = 0;
        }
        if (checkEight == 1) {
            questionEight.setTextColor(redColorValue);
        } else {
            questionEight.setTextColor(blackColorValue);
        }
    }

    //Correct answers Q9: DegasA-E
    public void questionNineCheck() {
        if ((degasQ9A.isChecked()) && (degasQ9B.isChecked()) && (degasQ9C.isChecked()) && (degasQ9D.isChecked()) && (degasQ9E.isChecked())) {
            scoreNine = 25;
            checkNine = 0;
        }
        if ((degasQ9A.isChecked()) || (degasQ9B.isChecked()) || (degasQ9C.isChecked()) || (degasQ9D.isChecked()) || (degasQ9E.isChecked())) {
            checkNine = 0;
        }
        if ((railwayQ9.isChecked()) || (portraitQ9.isChecked()) || (annunciationQ9.isChecked()) || (monaQ9.isChecked()) || (olympiaQ9.isChecked())) {
            scoreNine = 0;
            checkNine = 0;
        }
        if (checkNine == 1) {
            questionNine.setTextColor(redColorValue);
        } else {
            questionNine.setTextColor(blackColorValue);
        }
    }

    //Correct Answers Q10: Napoleon III
    public void questionTenCheck() {
        if (georgeQ10.isChecked() || queenQ10.isChecked() || louisQ10.isChecked() || bonaparteQ10.isChecked()) {
            scoreTen = 0;
            checkTen = 0;
        } else if (napoleonQ10.isChecked()) {
            scoreTen = 5;
            checkTen = 0;
        }
        if (checkTen == 1) {
            questionTen.setTextColor(redColorValue);
        } else {
            questionTen.setTextColor(blackColorValue);
        }
    }

    //Calculates their score
    public String calculateScore() {
        String sum = "\n" + (scoreOne + scoreTwo + scoreThree + scoreFour + scoreFive + scoreFiveSetTwo + scoreSix +
                scoreSeven + scoreEight + scoreNine + scoreTen) + "%";
        return sum;
    }
}

