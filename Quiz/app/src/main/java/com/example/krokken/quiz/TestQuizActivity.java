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

    int redColorValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        final ArrayList<Question> questionsForArt = new ArrayList<Question>();
        redColorValue = R.color.red_color_value;

        //numbersForMiwok.add("One");
        questionsForArt.add(new Question(1,"1", "a", "t", "r", "s", "o", "5", R.drawable.question_one_image, "s"));
        questionsForArt.add(new Question(1, "2", "a", "t", "r", "s", "o", "5", R.drawable.question_one_image, "t"));
        questionsForArt.add(new Question(1, "1", "a", "t", "r", "s", "o", "5", R.drawable.question_one_image, "a"));
        questionsForArt.add(new Question(1, "4", "a", "t", "r", "s", "o", "5", R.drawable.question_one_image, "o"));
        questionsForArt.add(new Question(1, "1", "a", "t", "r", "s", "o", "5", R.drawable.question_one_image,"r"));
        questionsForArt.add(new Question(1,"1", "a", "t", "r", "s", "o", "5", R.drawable.question_one_image, "s"));
        questionsForArt.add(new Question(1,"1", "a", "t", "r", "s", "o", "5", R.drawable.question_one_image,"a"));
        questionsForArt.add(new Question(1,"1", "a", "t", "r", "s", "o", "5", R.drawable.question_one_image,"t"));
        questionsForArt.add(new Question(1, "9", "a", "t", "r", "s", "o", "5", R.drawable.question_one_image, "s"));
        questionsForArt.add(new Question(1,"1", "a", "t", "r", "s", "o", "5", R.drawable.question_one_image,"o"));

        QuestionAdapter questionAdapter = new QuestionAdapter(this, questionsForArt, redColorValue, redColorValue);
        final ListView listView = findViewById(R.id.list);
        listView.setAdapter(questionAdapter);

    }

}
