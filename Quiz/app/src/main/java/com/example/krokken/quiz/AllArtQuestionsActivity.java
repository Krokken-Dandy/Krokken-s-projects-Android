package com.example.krokken.quiz;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class AllArtQuestionsActivity extends AppCompatActivity {

    public static int ART_QUIZ_ARRAY_SIZE = 10;
    public static int ART_QUESTION_WITH_MOST_ANSWERS = 10;
    private static final String STATE_ITEMS = "items";

    int backgroundColor = R.color.art_dark_purple;
    int foregroundColor = R.color.art_light_purple;

    ArrayList<Question> questionsForArt;

    private String[] questions, answersQ1, answersQ2, answersQ4, answersQ5, answersQ6, answersQ7,
            answersQ8, answersQ9, answersQ10, correctQ2, correctQ5, correctQ9;
    private String correctQ1, correctQ3, correctQ4, correctQ6, correctQ7, correctQ8, correctQ10;
    private int[] questionNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        declaredVariables();
        questionsArrayList();
        
        //Will restore a saved state if we have one
        if (savedInstanceState != null) {
            questionsForArt = savedInstanceState.getSerializable(questionsForArt);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now
        outState.putSerializable(STATE_ITEMS, questionsForArt);
    }

    private void declaredVariables() {
        Resources res = getResources();
        questionNumbers = new int[ART_QUIZ_ARRAY_SIZE];
        for (int i = 0; i < questionNumbers.length; i++) {
            questionNumbers[i] = i + 1;
        }

        questions = res.getStringArray(R.array.art_question);
        answersQ1 = res.getStringArray(R.array.art_question_1_array);
        answersQ2 = res.getStringArray(R.array.art_question_2_array);
        answersQ4 = res.getStringArray(R.array.art_question_4_array);
        answersQ5 = res.getStringArray(R.array.art_question_5_array);
        answersQ6 = res.getStringArray(R.array.art_question_6_array);
        answersQ7 = res.getStringArray(R.array.art_question_7_array);
        answersQ8 = res.getStringArray(R.array.art_question_8_array);
        answersQ9 = res.getStringArray(R.array.art_question_9_array);
        answersQ10 = res.getStringArray(R.array.art_question_10_array);
        correctQ1 = res.getString(R.string.edvard_munch);
        correctQ2 = res.getStringArray(R.array.correct_array_q2);
        correctQ3 = res.getString(R.string.pablo_picasso);
        correctQ4 = res.getString(R.string.sistine_chapel_ceiling);
        correctQ5 = res.getStringArray(R.array.correct_array_q5);
        correctQ6 = res.getString(R.string.museum_of_decorative_arts);
        correctQ7 = res.getString(R.string.rembrandt_van_rijn);
        correctQ8 = res.getString(R.string.ren_magritte);
        correctQ9 = res.getStringArray(R.array.correct_array_q9);
        correctQ10 = res.getString(R.string.emperor_napoleon_iii);

    }

    //ArrayList for the Questions in Art Quiz
    public void questionsArrayList() {
        questionsForArt = new ArrayList<>();

        //QuestionType 1 is 5 Radio 1 answer
        //QuestionType 2 is 10 Radio 2 answers
        //QuestionType 3 is 10 CheckBoxes with 5 answers
        //QuestionType 4 is editText with single answer
        //Q1 follows constructor: questionType, questionNumber, questionImage,
        // mainQuestion, answer1, answer2, answer3, answer4, answer5, and the correctAnswer
        questionsForArt.add(new Question(1, questionNumbers[0], R.drawable.question_one_image,
                questions[0], answersQ1[0], answersQ1[1], answersQ1[2], answersQ1[3], answersQ1[4], correctQ1));

        //Q2 follows constructor: questionType, questionNumber, questionImage,
        // mainQuestion, answer1, answer2, answer3, answer4, answer5,
        // answer6, answer7, answer8, answer 9, answer 10, correctAnswer1,
        // correctAnswer2, correctAnswer3, correctAnswer4, correctAnswer5
        questionsForArt.add(new Question(3, questionNumbers[1], R.drawable.question_two_image,
                questions[1], answersQ2[0], answersQ2[1], answersQ2[2], answersQ2[3], answersQ2[4],
                answersQ2[5], answersQ2[6], answersQ2[7], answersQ2[8], answersQ2[9], correctQ2[0],
                correctQ2[1], correctQ2[2], correctQ2[3], correctQ2[4]));

        //Q3 follows constructor: questionType, questionNumber, questionImage,
        // mainQuestion, answer1, and the correctAnswer
        questionsForArt.add(new Question(4, questionNumbers[2], R.drawable.question_three_image,
                questions[2], correctQ3));

        //Q4 follows constructor: questionType, questionNumber, questionImage,
        // mainQuestion, answer1, answer2, answer3, answer4, answer5, and the correctAnswer
        questionsForArt.add(new Question(1, questionNumbers[3], R.drawable.question_four_image,
                questions[3], answersQ4[0], answersQ4[1], answersQ4[2], answersQ4[3], answersQ4[4], correctQ4));

        //Q5 follows constructor: questionType, questionNumber, mainQuestion,
        // answer1, answer2, answer3, answer4, answer5, correctAnswer1, and correctAnswer2
        questionsForArt.add(new Question(3, questionNumbers[4], questions[4], answersQ5[0],
                answersQ5[1], answersQ5[2], answersQ5[3], answersQ5[4], answersQ5[5], answersQ5[6],
                answersQ5[7], answersQ5[8], answersQ5[9], correctQ5[0], correctQ5[1]));

        //Q6 follows constructor: questionType, questionNumber, questionImage,
        // mainQuestion, answer1, answer2, answer3, answer4, answer5, and the correctAnswer
        questionsForArt.add(new Question(1, questionNumbers[5], R.drawable.question_six_image,
                questions[5], answersQ6[0], answersQ6[1], answersQ6[2], answersQ6[3], answersQ6[4], correctQ6));

        //Q7 follows constructor: questionType, questionNumber, questionImage,
        // mainQuestion, answer1, answer2, answer3, answer4, answer5, and the correctAnswer
        questionsForArt.add(new Question(1, questionNumbers[6], R.drawable.question_seven_image,
                questions[6], answersQ7[0], answersQ7[1], answersQ7[2], answersQ7[3], answersQ7[4], correctQ7));

        //Q8 follows constructor: questionType, questionNumber, questionImage,
        // mainQuestion, answer1, answer2, answer3, answer4, answer5, and the correctAnswer
        questionsForArt.add(new Question(1, questionNumbers[7], R.drawable.question_eight_image,
                questions[7], answersQ8[0], answersQ8[1], answersQ8[2], answersQ8[3], answersQ8[4], correctQ8));

        //Q9 follows constructor: questionType, questionNumber, mainQuestion, answer1,
        // answer2, answer3, answer4, answer5, correctAnswer1,
        // correctAnswer2, correctAnswer3, correctAnswer4, correctAnswer5
        questionsForArt.add(new Question(3, questionNumbers[8], questions[8], answersQ9[0],
                answersQ9[1], answersQ9[2], answersQ9[3], answersQ9[4], answersQ9[5], answersQ9[6],
                answersQ9[7], answersQ9[8], answersQ9[9], correctQ9[0], correctQ9[1], correctQ9[2], correctQ9[3], correctQ9[4]));

        //Q10 follows constructor: questionType, questionNumber, questionImage, mainQuestion, answer1,
        // answer2, answer3, answer4, answer5, and the correctAnswer
        questionsForArt.add(new Question(1, questionNumbers[9], questions[9], answersQ10[0],
                answersQ10[1], answersQ10[2], answersQ10[3], answersQ10[4], correctQ10));

        final QuestionAdapter numberAdapter = new QuestionAdapter(this, questionsForArt, backgroundColor, foregroundColor, ART_QUIZ_ARRAY_SIZE, ART_QUESTION_WITH_MOST_ANSWERS);
        final View linearLayout = findViewById(R.id.linear_layout);
        final ListView listView = findViewById(R.id.list);
        final Button submitButton = findViewById(R.id.submit_button);
        linearLayout.setBackgroundColor(getResources().getColor(backgroundColor));

        submitButton.setBackgroundColor(getResources().getColor(foregroundColor));
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberAdapter.getScore();
            }
        });
        listView.setAdapter(numberAdapter);
    }
}
