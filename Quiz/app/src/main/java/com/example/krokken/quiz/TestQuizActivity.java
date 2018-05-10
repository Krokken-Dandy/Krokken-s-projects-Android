package com.example.krokken.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class TestQuizActivity extends AppCompatActivity {
    public static int TEST_QUIZ_ARRAY_SIZE = 10;
    public static int TEST_QUESTION_WITH_MOST_ANSWERS = 10;

    int redColorValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        final ArrayList<Question> questionsForArt = new ArrayList<Question>();
        redColorValue = R.color.red_color_value;

        questionsForArt.add(new Question(3, 1, R.drawable.question_one_image, "a","a", "t", "r", "s", "o", "5","t", "r", "s", "o", "5", "s", "a", "b", "a"));
        questionsForArt.add(new Question(3, 2, R.drawable.question_one_image, "a","a", "t", "r", "s", "o", "5","t", "r", "s", "o", "5", "s", "a", "b", "a"));
        questionsForArt.add(new Question(3, 3, R.drawable.question_one_image, "a","a", "t", "r", "s", "o", "5","t", "r", "s", "o", "5", "s", "a", "b", "a"));
        questionsForArt.add(new Question(3, 4, R.drawable.question_one_image, "a","a", "t", "r", "s", "o", "5","t", "r", "s", "o", "5", "s", "a", "b", "a"));
        questionsForArt.add(new Question(3, 5, R.drawable.question_one_image, "a","a", "t", "r", "s", "o", "5","t", "r", "s", "o", "5", "s", "a", "b", "a"));
        questionsForArt.add(new Question(3, 6, R.drawable.question_one_image, "a","a", "t", "r", "s", "o", "5","t", "r", "s", "o", "5", "s", "a", "b", "a"));
        questionsForArt.add(new Question(3, 7, R.drawable.question_one_image, "a","a", "t", "r", "s", "o", "5","t", "r", "s", "o", "5", "s", "a", "b", "a"));
        questionsForArt.add(new Question(3, 8, R.drawable.question_one_image, "a","a", "t", "r", "s", "o", "5","t", "r", "s", "o", "5", "s", "a", "b", "a"));
        questionsForArt.add(new Question(3, 9, R.drawable.question_one_image, "a","a", "t", "r", "s", "o", "5","t", "r", "s", "o", "5", "s", "a", "b", "a"));
        questionsForArt.add(new Question(3, 10, R.drawable.question_one_image, "a","a", "t", "r", "s", "o", "5","t", "r", "s", "o", "5", "s", "a", "b", "a"));


        QuestionAdapter questionAdapter = new QuestionAdapter(this, questionsForArt, redColorValue, redColorValue, TEST_QUIZ_ARRAY_SIZE, TEST_QUESTION_WITH_MOST_ANSWERS);
        final ListView listView = findViewById(R.id.list);
        listView.setAdapter(questionAdapter);
    }

}
