package com.example.krokken.questionlistexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<GroupItem> LIST_GROUP = new ArrayList<>();
        for (int i = 0; i<3; i++){
            GroupItem groupItem = new GroupItem();
            groupItem.setGroup_name("Group " + (i+1));

            List<QuestionItem> LIST_QUESTION = new ArrayList<>();
            for (int j = 0; j < 4; j++){
                QuestionItem questionItem = new QuestionItem();
                questionItem.setQuestionName("Question " + (j+1));
                LIST_QUESTION.add(questionItem);
            }
            groupItem.setLIST_QUESTION(LIST_QUESTION);
            LIST_GROUP.add(groupItem);
        }


        ListView listView = (ListView) findViewById(R.id.listview);
        QuestionAdapter adapter = new QuestionAdapter(MainActivity.this, LIST_GROUP);
        listView.setAdapter(adapter);
    }
}