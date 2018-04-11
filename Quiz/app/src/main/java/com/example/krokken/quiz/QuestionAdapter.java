package com.example.krokken.quiz;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionAdapter extends ArrayAdapter<Question> {

    private static final String LOG_TAG = QuestionAdapter.class.getSimpleName();
    private int mBackgroundColorResource, mForegroundColorResource, mPosition, mAllSubmitted, redColorValue, counter = 10;
    private RadioButton[] answeredLeftButton, answeredRightButton, radioButton;
    private int[] score = new int[11], submitted = new int[11];
    private CheckBox[] checkBox;
    private ImageView[] answer;

    public QuestionAdapter(Activity context, ArrayList<Question> question, int backgroundColor, int foregroundColor) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        super(context, 0, question);
        mBackgroundColorResource = backgroundColor;
        mForegroundColorResource = foregroundColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Used to dynamically find the views  in my @question_list_item
        mPosition = position;
        redColorValue = R.color.red_color_value;
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.question_list_item, parent, false);
        }

        // Get the {@link Question} object located at this position in the list
        final Question currentQuestion = getItem(position);

        // Find the ImageView in the question_list_item.xml layout
        ImageView questionImage = listItemView.findViewById(R.id.question_image);
        final RadioGroup radioGroupLeft = listItemView.findViewById(R.id.question_radio_left);
        final RadioGroup radioGroupRight = listItemView.findViewById(R.id.question_radio_right);
        EditText editText = listItemView.findViewById(R.id.edittext_their_answer);
        TextView editTextAnswer = listItemView.findViewById(R.id.edittext_correct_answer);
        LinearLayout checkbox_linearLayout = listItemView.findViewById(R.id.checkboxes);

        editTextAnswer.setVisibility(View.GONE);

        radioButton = new RadioButton[counter];
        answeredLeftButton = new RadioButton[counter];
        answeredRightButton = new RadioButton[counter];
        checkBox = new CheckBox[counter];
        answer = new ImageView[counter];

        for (int i = 0; i < counter; i++) {
            String sRadioButtonID = "radio_" + (i + 1);
            String sCheckBoxesID = "checkbox_" + (i + 1);
            String sAnswerID = "answer" + (i + 1) + "_correct";

            int radioButtonID = listItemView.getResources().getIdentifier(sRadioButtonID, "id", MainActivity.PACKAGE_NAME);
            int checkBoxID = listItemView.getResources().getIdentifier(sCheckBoxesID, "id", MainActivity.PACKAGE_NAME);
            int answerID = listItemView.getResources().getIdentifier(sAnswerID, "id", MainActivity.PACKAGE_NAME);

            radioButton[i] = listItemView.findViewById(radioButtonID);
            checkBox[i] = listItemView.findViewById(checkBoxID);
            answer[i] = listItemView.findViewById(answerID);

            radioButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String isThisCorrectLeft = "", isThisCorrectRight = "";
                    if (currentQuestion.getQuestionType() == 1) {
                        answeredLeftButton[mPosition] = (RadioButton) radioGroupLeft.findViewById(radioGroupLeft.getCheckedRadioButtonId());
                        if (answeredLeftButton[mPosition] != null) {
                            isThisCorrectLeft = answeredLeftButton[mPosition].getText().toString();
                        }
                        if (isThisCorrectLeft.matches(currentQuestion.getCorrect1())) {
                            score[mPosition] = 5;
                        } else {
                            score[mPosition] = 0;
                        }

                    } else if (currentQuestion.getQuestionType() == 2) {
                        answeredLeftButton[mPosition] = (RadioButton) radioGroupLeft.findViewById(radioGroupLeft.getCheckedRadioButtonId());
                        if (answeredLeftButton[mPosition] != null) {
                            isThisCorrectLeft = answeredLeftButton[mPosition].getText().toString();
                        }
                        answeredRightButton[mPosition] = (RadioButton) radioGroupRight.findViewById(radioGroupRight.getCheckedRadioButtonId());
                        if (answeredRightButton[mPosition] != null) {
                            isThisCorrectRight = answeredRightButton[mPosition].getText().toString();
                        }

                        if (isThisCorrectLeft.matches(currentQuestion.getCorrect1()) && (isThisCorrectRight.matches(currentQuestion.getCorrect2()))) {
                            score[mPosition] = 10;
                        } else {
                            score[mPosition] = 0;
                        }
                    }
                    submitted[mPosition] = 1;

                }
            });

            if (currentQuestion.getAnswered() == -1) {
                radioGroupLeft.clearCheck();
            } else {
                ((RadioButton) radioGroupLeft.getChildAt(currentQuestion.getAnswered())).setChecked(true);
            }

            radioGroupLeft.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radio_1:
                            currentQuestion.setAnswer(0);
                            break;
                        case R.id.radio_2:
                            currentQuestion.setAnswer(1);
                            break;
                        case R.id.radio_3:
                            currentQuestion.setAnswer(2);
                            break;
                        case R.id.radio_4:
                            currentQuestion.setAnswer(3);
                            break;
                        case R.id.radio_5:
                            currentQuestion.setAnswer(4);
                            break;
                    }
                }
            });
        }


        View textContainer = listItemView.findViewById(R.id.text_container);
        View foregroundContainer = listItemView.findViewById(R.id.foreground_container);
        int backgroundColor = ContextCompat.getColor(getContext(), mBackgroundColorResource);
        int foregroundColor = ContextCompat.getColor(getContext(), mForegroundColorResource);
        textContainer.setBackgroundColor(backgroundColor);
        foregroundContainer.setBackgroundColor(foregroundColor);

        // Find the TextView in the question_list_item.xmlm.xml.xml layout with the ID miwok_text_view
        TextView questionNumber = listItemView.findViewById(R.id.question_number);
        // Get the version name from the current Word object and
        // set this text on the name TextView
        questionNumber.setText(currentQuestion.getQuestionNumber());

        if (currentQuestion.hasImage()) {
            // Get the image resource ID from the current Word object and
            // set the image to iconView
            questionImage.setImageResource(currentQuestion.getImageResourceId());
            questionImage.setVisibility(View.VISIBLE);
        } else {
            questionImage.setVisibility(View.GONE);
        }

        // Find the TextView in the question_list_item.xmlm.xml.xml layout with the ID miwok_text_view
        TextView questionTextView = listItemView.findViewById(R.id.question_text_view);
        // Get the version name from the current Word object and
        // set this text on the name TextView
        questionTextView.setText(currentQuestion.getQuestions());
        checkBox[0].setText(currentQuestion.getAnswer1());
        checkBox[1].setText(currentQuestion.getAnswer2());
        checkBox[2].setText(currentQuestion.getAnswer3());
        checkBox[3].setText(currentQuestion.getAnswer4());
        checkBox[4].setText(currentQuestion.getAnswer5());
        checkBox[5].setText(currentQuestion.getAnswer6());
        checkBox[6].setText(currentQuestion.getAnswer7());
        checkBox[7].setText(currentQuestion.getAnswer8());
        checkBox[8].setText(currentQuestion.getAnswer9());
        checkBox[9].setText(currentQuestion.getAnswer10());
        radioButton[0].setText(currentQuestion.getAnswer1());
        radioButton[1].setText(currentQuestion.getAnswer2());
        radioButton[2].setText(currentQuestion.getAnswer3());
        radioButton[3].setText(currentQuestion.getAnswer4());
        radioButton[4].setText(currentQuestion.getAnswer5());
        radioButton[5].setText(currentQuestion.getAnswer6());
        radioButton[6].setText(currentQuestion.getAnswer7());
        radioButton[7].setText(currentQuestion.getAnswer8());
        radioButton[8].setText(currentQuestion.getAnswer9());
        radioButton[9].setText(currentQuestion.getAnswer10());

        if (currentQuestion.getQuestionType() == 1) {
            editText.setVisibility(View.GONE);
            radioGroupLeft.setVisibility(View.VISIBLE);
            radioGroupRight.setVisibility(View.GONE);
            checkbox_linearLayout.setVisibility(View.GONE);

        } else if (currentQuestion.getQuestionType() == 2) {
            editText.setVisibility(View.GONE);
            radioGroupLeft.setVisibility(View.VISIBLE);
            radioGroupRight.setVisibility(View.VISIBLE);
            checkbox_linearLayout.setVisibility(View.GONE);

        } else if (currentQuestion.getQuestionType() == 3) {
            editText.setVisibility(View.GONE);
            radioGroupLeft.setVisibility(View.GONE);
            radioGroupRight.setVisibility(View.GONE);
            checkbox_linearLayout.setVisibility(View.VISIBLE);

        } else if (currentQuestion.getQuestionType() == 4) {
            editText.setVisibility(View.VISIBLE);
            radioGroupLeft.setVisibility(View.GONE);
            radioGroupRight.setVisibility(View.GONE);
            checkbox_linearLayout.setVisibility(View.GONE);
        }

        mAllSubmitted = submitted[1] + submitted[2] + submitted[3] + submitted[4] + submitted[5] +
                submitted[6] + submitted[7] + submitted[8] + submitted[9] + submitted[10];

        return listItemView;
    }

    //Returns a string of questions that were unanswered
    private String finishCheck() {
        String checkQ = "";
        submitted[0] = 1;
        for (int i = 0; i<counter; i++){
            if (submitted[i] != 1){
                checkQ += "#" + i + " ";
                radioButton[i].setTextColor(redColorValue);
            }
        }
        return checkQ;
    }

    public int getScore() {
        int totalScore = (score[1] + score[2] + score[3] + score[4] + score[5] + score[6] + score[7] + score[8] + score[9] + score[10]);
        String s = "Congratulations, you've scored a" + totalScore +"%!";

        //Checks if all questions were answered
        if (mAllSubmitted < 10) {
            Toast.makeText(getContext(), (10 - mAllSubmitted) + "/10 " + "need to be finished.\n" + "Questions: " + finishCheck(), Toast.LENGTH_LONG).show();
        } else {
//            disableButtons();
//            showCorrect();
//            questionThreeAnswer.setFocusable(false);
//            questionOne.setFocusable(true);
//            questionOne.setFocusableInTouchMode(true);
            Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
        }
        return totalScore;
    }
}


