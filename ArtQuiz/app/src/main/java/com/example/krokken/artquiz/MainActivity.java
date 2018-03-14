package com.example.krokken.artquiz;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends FragmentActivity {
    EditText nameForOrder;
    ViewPager viewPager;
    TextView quizTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);
        quizTitle = findViewById(R.id.quiz_title);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);
        nameForOrder = findViewById(R.id.name_input);
        nameForOrder.setOnEditorActionListener(new action_listener());
    }


//    public void submitButton(View view){
//        FragmentManager fm = getSupportFragmentManager();
//        stringQuestionNumber = "Question " + " of " + 12;
//        questionDisplay.setText(stringQuestionNumber);
//    }

}