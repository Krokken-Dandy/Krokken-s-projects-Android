package com.example.krokken.theartquiz;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;


        import android.content.Context;
        import android.content.res.Resources;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
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
//
//        nameForOrder = findViewById(R.id.name_input);
//        viewPager = findViewById(R.id.view_pager);
//        quizTitle = findViewById(R.id.quiz_title);

        setContentView(R.layout.activity_main);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);

    }
}


