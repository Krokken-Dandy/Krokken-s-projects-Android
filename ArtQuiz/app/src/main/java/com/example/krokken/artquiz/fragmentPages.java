package com.example.krokken.artquiz;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentPages extends Fragment {

    TextView questionTitle1, questionNumber, questionQuestion;
    View layoutAnswer1,layoutAnswer2,layoutAnswer3,layoutAnswer4,layoutAnswer5,
            layoutAnswer6,layoutAnswer7,layoutAnswer8,layoutAnswer9,layoutAnswer10,
            layoutFinal1,layoutFinal2;

    int qNumber;
   Resources res = getResources();
//    String[] questions = {res.getString(R.string.question_1),getString(R.string.question_2),getString(R.string.question_3),getString(R.string.question_4),
//            getString(R.string.question_5),getString(R.string.question_6),getString(R.string.question_7),getString(R.string.question_8),
//            getString(R.string.question_9),getString(R.string.question_10)});
    String[] questions = {"1","2"};

    public fragmentPages() {
        // Required empty public constructor
    }

//    String stringQuestionNumber;
//    CheckBox [] answers;
//    CheckBox c;
//    String [] sAnswers = {"today", "tomorrow","the day after"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View question = inflater.inflate(R.layout.page_fragment_layout, container, false);

        Bundle bundle = getArguments();
        String message = Integer.toString(bundle.getInt("count"));
        questionQuestion = question.findViewById(R.id.question);
        questionTitle1 = question.findViewById(R.id.question_title);
        questionNumber = question.findViewById(R.id.question_number);
        layoutAnswer1 = question.findViewById(R.id.layout_answer_1);
        layoutAnswer2 = question.findViewById(R.id.layout_answer_2);
        layoutAnswer3 = question.findViewById(R.id.layout_answer_3);
        layoutAnswer4 = question.findViewById(R.id.layout_answer_4);
        layoutAnswer5 = question.findViewById(R.id.layout_answer_5);
        layoutAnswer6 = question.findViewById(R.id.layout_answer_6);
        layoutAnswer7 = question.findViewById(R.id.layout_answer_7);
        layoutAnswer8 = question.findViewById(R.id.layout_answer_8);
        layoutAnswer9 = question.findViewById(R.id.layout_answer_9);
        layoutAnswer10 = question.findViewById(R.id.layout_answer_10);
        layoutFinal1 = question.findViewById(R.id.layout_final_1);
        layoutFinal2 = question.findViewById(R.id.layout_final_2);

        qNumber = bundle.getInt("count");
        if (qNumber > 10) {
            qNumber = 10;
        }
        questionNumber.setText("Question: " + qNumber + " of 10");
        questionQuestion();
        //answerAnswer();
        return question;
    }

    public void questionQuestion() {
        if (qNumber == 1) {
            visibility();
            layoutAnswer1.setVisibility(View.INVISIBLE);
            for (String q : questions) {
                questionQuestion.setText((questions[0]));
            }
        } else if (qNumber == 2) {
            visibility();
            layoutAnswer2.setVisibility(View.VISIBLE);
            for (String q : questions) {
                questionQuestion.setText((questions[1]));
            }
        } else if (qNumber == 3) {
            visibility();
            layoutAnswer3.setVisibility(View.VISIBLE);
            for (String q : questions) {
                questionQuestion.setText((questions[2]));
            }
        } else if (qNumber == 4) {
            visibility();
            layoutAnswer4.setVisibility(View.VISIBLE);
            for (String q : questions) {
                questionQuestion.setText((questions[3]));
            }

        } else if (qNumber == 5) {
            visibility();
            layoutAnswer5.setVisibility(View.VISIBLE);
            for (String q : questions) {
                questionQuestion.setText((questions[4]));
            }

        } else if (qNumber == 6) {
            visibility();
            layoutAnswer6.setVisibility(View.VISIBLE);
            for (String q : questions) {
                questionQuestion.setText((questions[5]));
            }

        } else if (qNumber == 7) {
            visibility();
            layoutAnswer7.setVisibility(View.VISIBLE);
            for (String q : questions) {
                questionQuestion.setText((questions[6]));
            }

        } else if (qNumber == 8) {
            visibility();
            layoutAnswer8.setVisibility(View.VISIBLE);
            for (String q : questions) {
                questionQuestion.setText((questions[7]));
            }

        } else if (qNumber == 9) {
            visibility();
            layoutAnswer9.setVisibility(View.VISIBLE);
            for (String q : questions) {
                questionQuestion.setText((questions[8]));
            }

        } else if (qNumber == 10) {
            visibility();
            layoutAnswer10.setVisibility(View.VISIBLE);
            for (String q : questions) {
                questionQuestion.setText((questions[9]));
            }

        } else if (qNumber == 11) {
            visibility();
            layoutFinal1.setVisibility(View.VISIBLE);
            for (String q : questions) {
                questionQuestion.setText((questions[10]));
            }

        } else if (qNumber == 12) {
            visibility();
            layoutFinal2.setVisibility(View.VISIBLE);
            for (String q : questions) {
                questionQuestion.setText((questions[11]));
            }

        } else {
            questionTitle1.setText("What went wrong?");
        }
    }

    public void visibility() {
        questionTitle1.setVisibility(View.GONE);
        layoutAnswer1.setVisibility(View.GONE);
        layoutAnswer2.setVisibility(View.GONE);
        layoutAnswer3.setVisibility(View.GONE);
        layoutAnswer4.setVisibility(View.GONE);
        layoutAnswer5.setVisibility(View.GONE);
        layoutAnswer6.setVisibility(View.GONE);
        layoutAnswer7.setVisibility(View.GONE);
        layoutAnswer8.setVisibility(View.GONE);
        layoutAnswer9.setVisibility(View.GONE);
        layoutAnswer10.setVisibility(View.GONE);
        layoutFinal1.setVisibility(View.GONE);
        layoutFinal2.setVisibility(View.GONE);
    }
//    public void answerAnswer(){
//        if (qNumber == 1) {
//           answers = new CheckBox[sAnswers.length];
//           for (int i=0;i<sAnswers.length;i++){
//               c.setText(sAnswers[i]);
//               answers[i]=c;
//           }
//
//        } else if (qNumber == 2) {
//            questionTitle.setText("What is tomorrow?");
//        } else {
//            questionTitle.setText("What is the day after that?");
//        }
//    }


}
