package com.example.krokken.quiz;

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

import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    Toast needName;
    int scoreOne, scoreTwo, scoreThree, scoreFour, scoreFive, scoreFive2, scoreSix, scoreSeven, scoreEight, scoreNine, scoreTen;


    EditText nameInput, questionThreeAnswer;

    CheckBox manetQ2, monetQ2, picassoQ2, goghQ2, cezanneQ2, cassattQ2, morisotQ2, renoirQ2, pollockQ2, christovQ2,
            degasQ9A, degasQ9B, degasQ9C, degasQ9D, degasQ9E, railwayQ9, annunciationQ9, portraitQ9, olympiaQ9, monaQ9;

    RadioButton pabloQ1, munchQ1, shrikQ1, goghQ1, eyckQ1,
            venusQ4, baptistQ4, sistineQ4, monaQ4, thinkerQ4,
            woodQ5, wallQ5, canvasQ5, clothQ5, ceilingQ5, apostleQ5, maryQ5, doorwayQ5, tableQ5, skylightQ5,
            decorativeQ6, louvreQ6, momaQ6, nhmQ6, vaticanQ6,
            degasQ7, rembrandtQ7, picassoQ7, eyckQ7, migardeQ7,
            christovQ8, leonardoQ8, monetQ8, cezanneQ8, magritteQ8,
            napoleonQ10, georgeQ10, queenQ10, louisQ10, bonaparteQ10;

    TextView nameOutput, knowArt, questionTitle,
            questionOne, questionTwo, questionThree, questionFour, questionFive,
            questionSix, questionSeven, questionEight, questionNine, questionTen,
            questionNum1, questionNum2, questionNum3, questionNum4, questionNum5,
            questionNum6, questionNum7, questionNum8, questionNum9, questionNum10;

    ImageView openingImage;

    Button submitButton;

    View layoutOpening, layoutRest;

    String[] numbers = {"#1", "#2", "#3", "#4", "#5", "#6", "#7", "#8", "#9", "#10"};
    String[] questions = {
            "Which artist is credited with this painting?"
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

        findViews();
    }

    public void findViews() {
        nameInput = findViewById(R.id.name_input);
        nameOutput = findViewById(R.id.name_output);
        layoutRest = findViewById(R.id.layout_rest);

        questionThreeAnswer = findViewById(R.id.edittext_q3); //Answer Pablo Picasso

        findQuestions();
        findCheckBoxes();
        findButtons();
        openingViews();
        findQNumbers();

    }

    public void openingViews() {
        knowArt = findViewById(R.id.know_art);
        layoutOpening = findViewById(R.id.layout_opening);
        openingImage = findViewById(R.id.opening_image);
        questionTitle = findViewById(R.id.question_title);

    }

    public void findCheckBoxes() {
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


    public void findButtons() {
        submitButton = findViewById(R.id.submit_button);

        pabloQ1 = findViewById(R.id.pablo_q1);
        munchQ1 = findViewById(R.id.munch_q1);
        shrikQ1 = findViewById(R.id.shrik_q1);
        goghQ1 = findViewById(R.id.gogh_q1);
        eyckQ1 = findViewById(R.id.eyck_q1);

        venusQ4 = findViewById(R.id.venus_q4);
        baptistQ4 = findViewById(R.id.baptist_q4);
        sistineQ4 = findViewById(R.id.sistine_q4);
        monaQ4 = findViewById(R.id.mona_q4);
        thinkerQ4 = findViewById(R.id.thinker_q4);

        woodQ5 = findViewById(R.id.wood_q5);
        canvasQ5 = findViewById(R.id.canvas_q5);
        clothQ5 = findViewById(R.id.cloth_q5);
        ceilingQ5 = findViewById(R.id.ceiling_q5);
        apostleQ5 = findViewById(R.id.apostle_q5);
        maryQ5 = findViewById(R.id.mary_q5);
        wallQ5 = findViewById(R.id.wall_q5);
        doorwayQ5 = findViewById(R.id.doorway_q5);
        tableQ5 = findViewById(R.id.table_q5);
        skylightQ5 = findViewById(R.id.skylight_q5);

        decorativeQ6 = findViewById(R.id.decorative_q6);
        louvreQ6 = findViewById(R.id.louvre_q6);
        momaQ6 = findViewById(R.id.moma_q6);
        nhmQ6 = findViewById(R.id.nhm_q6);
        vaticanQ6 = findViewById(R.id.vatican_q6);

        degasQ7 = findViewById(R.id.degas_q7);
        rembrandtQ7 = findViewById(R.id.rembrandt_q7);
        picassoQ7 = findViewById(R.id.picasso_q7);
        eyckQ7 = findViewById(R.id.eyck_q7);
        migardeQ7 = findViewById(R.id.migarde_q7);

        christovQ8 = findViewById(R.id.christov_q8);
        leonardoQ8 = findViewById(R.id.leonardo_q8);
        monetQ8 = findViewById(R.id.monet_q8);
        cezanneQ8 = findViewById(R.id.cezanne_q8);
        magritteQ8 = findViewById(R.id.magritte_q8);

        napoleonQ10 = findViewById(R.id.napoleon_q10);
        georgeQ10 = findViewById(R.id.george_q10);
        queenQ10 = findViewById(R.id.queen_q10);
        louisQ10 = findViewById(R.id.louis_q10);
        bonaparteQ10 = findViewById(R.id.bonaparte_q10);

    }


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


    public String getName() {
        String name = nameInput.getText().toString();
        return name;

    }

    public void nameSet(String name) {
        nameOutput.setText("Art Quiz for: " + name);

    }

    public void beginButton(View v) {
        if (getName().matches("")) {
            needName.makeText(this, "Gimme yo name", Toast.LENGTH_LONG).show();

        } else {
            nameSet(getName());

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

    public void submitAnswersButton(View v){
        questionTwoCheck();
        questionThreeCheck();
        questionNineCheck();
        nameOutput.setText(calculateScore());

       // layoutRest.setVisibility(View.GONE);
    }

    //Correct Answers Q1: Munch
    public void onQuestionOneRadio(View view) {
        // Is the button now checked?
        boolean munch = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.eyck_q1:
                if (munch)
                    scoreOne = 0;
                    break;
            case R.id.gogh_q1:
                if (munch)
                    scoreOne = 0;
                    break;
            case R.id.munch_q1:
                if (munch)
                    scoreOne = 5;
                    break;
            case R.id.pablo_q1:
                if (munch)
                    scoreOne = 0;
                    break;
            case R.id.shrik_q1:
                if (munch)
                    scoreOne = 0;
                    break;
        }
    }

    //Correct answers Q2: Monet, Cezanne, Cassatt, Renoir, Morisot
    public void questionTwoCheck(){

    }

    public void questionThreeCheck(){
        String answerThree = questionThreeAnswer.getText().toString().toLowerCase();

        if (answerThree.matches("pablo picasso")){
            scoreThree = 5;
        } else if (answerThree.matches("picasso")) {
            scoreThree = 5;
        } else if (answerThree.matches("pablo")) {
            scoreThree = 5;
        } else if (answerThree.matches("pablopicasso")) {
            scoreThree = 5;
        } else {
            scoreThree = 0;
        }
    }

    //Correct Answers Q4: Sistine
    public void onQuestionFourRadio(View view) {
        // Is the button now checked?
        boolean munch = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.baptist_q4:
                if (munch)
                    scoreFour = 0;
                break;
            case R.id.mona_q4:
                if (munch)
                    scoreFour = 0;
                break;
            case R.id.sistine_q4:
                if (munch)
                    scoreFour = 5;
                break;
            case R.id.thinker_q4:
                if (munch)
                    scoreFour = 0;
                break;
            case R.id.venus_q4:
                if (munch)
                    scoreFour = 0;
                break;
        }
    }
    //Correct Answers Q5: Wall & Doorway
    public void onQuestionFiveRadio(View view) {
        // Is the button now checked?
        boolean munch = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.canvas_q5:
                if (munch)
                    scoreFive = 0;
                break;
            case R.id.ceiling_q5:
                if (munch)
                    scoreFive = 0;
                break;
            case R.id.wall_q5:
                if (munch)
                    scoreFive = 5;
                break;
            case R.id.wood_q5:
                if (munch)
                    scoreFive = 0;
                break;
            case R.id.cloth_q5:
                if (munch)
                    scoreFive = 0;
                break;
        }
    }

    public void onQuestionFiveRadio2(View view) {
        // Is the button now checked?
        boolean munch = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.apostle_q5:
                if (munch)
                    scoreFive2 = 0;
                break;
            case R.id.table_q5:
                if (munch)
                    scoreFive2 = 0;
                break;
            case R.id.doorway_q5:
                if (munch)
                    scoreFive2 = 5;
                break;
            case R.id.mary_q5:
                if (munch)
                    scoreFive2 = 0;
                break;
            case R.id.skylight_q5:
                if (munch)
                    scoreFive2 = 0;
                break;
        }
    }

    //Correct Answers Q6: Decorative
    public void onQuestionSixRadio(View view) {
        // Is the button now checked?
        boolean munch = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.louvre_q6:
                if (munch)
                    scoreSix = 0;
                break;
            case R.id.moma_q6:
                if (munch)
                    scoreSix = 0;
                break;
            case R.id.decorative_q6:
                if (munch)
                    scoreSix = 5;
                break;
            case R.id.nhm_q6:
                if (munch)
                    scoreSix = 0;
                break;
            case R.id.vatican_q6:
                if (munch)
                    scoreSix = 0;
                break;
        }
    }

    //Correct Answers Q7: Rembrandt
    public void onQuestionSevenRadio(View view) {
        // Is the button now checked?
        boolean munch = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.degas_q7:
                if (munch)
                    scoreSeven = 0;
                break;
            case R.id.eyck_q7:
                if (munch)
                    scoreSeven = 0;
                break;
            case R.id.rembrandt_q7:
                if (munch)
                    scoreSeven = 5;
                break;
            case R.id.picasso_q7:
                if (munch)
                    scoreSeven = 0;
                break;
            case R.id.migarde_q7:
                if (munch)
                    scoreSeven = 0;
                break;
        }
    }
    //Correct Answers Q8: Magritte
    public void onQuestionEightRadio(View view) {
        // Is the button now checked?
        boolean munch = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.cezanne_q8:
                if (munch)
                    scoreOne = 0;
                break;
            case R.id.christov_q8:
                if (munch)
                    scoreOne = 0;
                break;
            case R.id.magritte_q8:
                if (munch)
                    scoreOne = 5;
                break;
            case R.id.leonardo_q8:
                if (munch)
                    scoreOne = 0;
                break;
            case R.id.monet_q8:
                if (munch)
                    scoreOne = 0;
                break;
        }
    }
    //Correct answers Q9: DegasA-E
    public void questionNineCheck(){

    }

    //Correct Answers Q10: Napoleon III
    public void onQuestionTenRadio(View view) {
        // Is the button now checked?
        boolean munch = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.queen_q10:
                if (munch)
                    scoreTen = 0;
                break;
            case R.id.louis_q10:
                if (munch)
                    scoreTen = 0;
                break;
            case R.id.napoleon_q10:
                if (munch)
                    scoreTen = 5;
                break;
            case R.id.george_q10:
                if (munch)
                    scoreTen = 0;
                break;
            case R.id.bonaparte_q10:
                if (munch)
                    scoreTen = 0;
                break;
        }
    }

    public String calculateScore(){
        int sum = scoreOne + scoreTwo + scoreThree + scoreFour + scoreFive + scoreFive2 + scoreSix +
                scoreSeven + scoreEight + scoreNine + scoreTen;
        String score = "" + sum;
        return score;
    }

}
