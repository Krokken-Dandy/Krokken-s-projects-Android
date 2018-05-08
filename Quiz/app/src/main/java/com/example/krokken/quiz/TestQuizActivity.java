package com.example.krokken.quiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
        questionsForArt.add(new Question(3, 2, "aa", "ta", "ra", "sa", "oa", "pa", R.drawable.question_one_image, "t"));
        questionsForArt.add(new Question(3, 3, "ab", "tb", "rb", "sb", "ob", "pb", R.drawable.question_one_image, "a"));
        questionsForArt.add(new Question(1, 4, "ac", "tc", "rc", "sc", "oc", "pc", R.drawable.question_one_image, "o"));
        questionsForArt.add(new Question(3, 5, "ad", "td", "rd", "sd", "od", "pd", R.drawable.question_one_image,"r"));
        questionsForArt.add(new Question(3,6, "ae", "te", "re", "se", "oe", "pe", R.drawable.question_one_image, "s"));
        questionsForArt.add(new Question(3,7, "a", "t", "r", "s", "o", "p", R.drawable.question_one_image,"a"));
        questionsForArt.add(new Question(1,8, "a", "t", "r", "s", "o", "p", R.drawable.question_one_image,"t"));
        questionsForArt.add(new Question(3, 9, "a", "t", "r", "s", "o", "p", R.drawable.question_one_image, "s"));
        questionsForArt.add(new Question(3,10, "a", "t", "r", "s", "o", R.drawable.question_one_image,"o"));


        QuestionAdapter questionAdapter = new QuestionAdapter(this, questionsForArt, redColorValue, redColorValue, TEST_QUIZ_ARRAY_SIZE, TEST_QUESTION_WITH_MOST_ANSWERS);
        final ListView listView = findViewById(R.id.list);
        listView.setAdapter(questionAdapter);
    }

}
