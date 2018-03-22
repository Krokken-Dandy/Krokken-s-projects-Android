package com.example.krokken.quiz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class finalFragments extends Fragment {
//    TextView questionNumber, questionQuestion, quizTitle, knowArt, questionNumberName;
//    View layoutAnswer1, layoutAnswer2, layoutAnswer3, layoutAnswer4, layoutAnswer5,
//            layoutAnswer6, layoutAnswer7, layoutAnswer8, layoutAnswer9, layoutAnswer10,
//            layoutFinal1, layoutFinal2;
//    Button submitButton, beginButton;
//    ImageView openingImage;

    int qNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View question = inflater.inflate(R.layout.final_fragments_layouts, container, false);
        Bundle bundle = getArguments();
        String message = Integer.toString(bundle.getInt("count"));

//        beginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });

//        qNumber = bundle.getInt("count");
//        if (qNumber > 10) {
//            qNumber = 10;
//        }

        return question;
    }
}