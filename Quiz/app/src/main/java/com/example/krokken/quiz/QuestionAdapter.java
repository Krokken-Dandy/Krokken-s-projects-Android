package com.example.krokken.quiz;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    private int mBackgroundColorResource, mForegroundColorResource, mPosition, mAllSubmitted, redColorValue, mCounterSize;
    private RadioButton[] answeredLeftButton, answeredRightButton, radioButton;
    private int[] score = new int[10], submitted = new int[10];
    private boolean[] mCheckBoxesChecked;
    private boolean[] unansweredQuestions = new boolean[10];
    private CheckBox[] checkBox;
    private ImageView[] answer;

    public QuestionAdapter(Activity context, ArrayList<Question> question, int backgroundColor, int foregroundColor) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        super(context, 0, question);
        mBackgroundColorResource = backgroundColor;
        mForegroundColorResource = foregroundColor;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Used to dynamically find the views in my @question_list_item
        mPosition = position - 1;
        redColorValue = R.color.red_color_value;
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.question_list_item, parent, false);
        }

        // Get the {@link Question} object located at this position in the list
        final Question currentQuestion = getItem(position);
        AllArtQuestionsActivity allArtQuestionsActivity = new AllArtQuestionsActivity();
        mCounterSize = allArtQuestionsActivity.ARRAY_SIZE;
        final ImageView questionImage = listItemView.findViewById(R.id.question_image);
        final TextView questionTextView = listItemView.findViewById(R.id.question_text_view);
        final TextView questionNumber = listItemView.findViewById(R.id.question_number);
        final RadioGroup radioGroupLeft = listItemView.findViewById(R.id.question_radio_left);
        final RadioGroup radioGroupRight = listItemView.findViewById(R.id.question_radio_right);
        final EditText editText = listItemView.findViewById(R.id.edittext_their_answer);
        final TextView editTextAnswer = listItemView.findViewById(R.id.edittext_correct_answer);
        final LinearLayout checkBox_linearLayout = listItemView.findViewById(R.id.checkboxes);
        editTextAnswer.setVisibility(View.GONE);
        radioButton = new RadioButton[mCounterSize];
        answeredLeftButton = new RadioButton[mCounterSize];
        answeredRightButton = new RadioButton[mCounterSize];
        checkBox = new CheckBox[mCounterSize];
        answer = new ImageView[mCounterSize];

        //Loop to initialize and create listeners for all the items used in the required layout
        for (int i = 0; i < mCounterSize; i++) {
            String sRadioButtonID = "radio_" + (i + 1);
            String sCheckBoxesID = "checkbox_" + (i + 1);
            String sAnswerID = "answer" + (i + 1) + "_correct";

            int radioButtonID = listItemView.getResources().getIdentifier(sRadioButtonID, "id", MainActivity.PACKAGE_NAME);
            int checkBoxID = listItemView.getResources().getIdentifier(sCheckBoxesID, "id", MainActivity.PACKAGE_NAME);
            int answerID = listItemView.getResources().getIdentifier(sAnswerID, "id", MainActivity.PACKAGE_NAME);

            radioButton[i] = listItemView.findViewById(radioButtonID);
            checkBox[i] = listItemView.findViewById(checkBoxID);
            answer[i] = listItemView.findViewById(answerID);

            //RadioButton clickListeners for checking answers
            radioButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String isThisCorrectLeft = "";
                    String isThisCorrectRight = "";
                    if (currentQuestion.getQuestionType() == 1) {
                        answeredLeftButton[mPosition] =
                                radioGroupLeft.findViewById(radioGroupLeft.getCheckedRadioButtonId());
                        submitted[position] = 1;
                        if (answeredLeftButton[position] != null) {
                            isThisCorrectLeft = answeredLeftButton[position].getText().toString();
                        }
                        if (isThisCorrectLeft.equals(currentQuestion.getCorrect1())) {
                            score[position] = 5;
                        } else {
                            score[position] = 0;
                        }

                    } else if (currentQuestion.getQuestionType() == 2) {
                        answeredLeftButton[mPosition] =
                                radioGroupLeft.findViewById(radioGroupLeft.getCheckedRadioButtonId());
                        submitted[position] = 1;
                        if (answeredLeftButton[position] != null) {
                            isThisCorrectLeft = answeredLeftButton[position].getText().toString();
                        }
                        answeredRightButton[position] =
                                radioGroupRight.findViewById(radioGroupRight.getCheckedRadioButtonId());
                        if (answeredRightButton[position] != null) {
                            isThisCorrectRight = answeredRightButton[position].getText().toString();
                        }
                        if (isThisCorrectLeft.equals(currentQuestion.getCorrect1()) &&
                                (isThisCorrectRight.equals(currentQuestion.getCorrect2()))) {
                            score[position] = 10;
                        } else {
                            score[position] = 0;
                        }
                    }
                }
            });

            //TODO Make the checkboxes work properly
            checkBox[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    submitted[position] = 1;
                    if (isChecked) {
                        currentQuestion.setAnswer(10);
                    } else {
                        currentQuestion.setAnswer(-1);
                    }
                }
            });

            for (int j = 0; j < checkBox_linearLayout.getChildCount(); j++) {
                View child = checkBox_linearLayout.getChildAt(j);
                if (child instanceof CheckBox) {
                    if (currentQuestion.getAnswered() == -1) {
                        ((CheckBox) child).setChecked(false);
                    } else if (currentQuestion.getAnswered() == 10) {
                        ((CheckBox) child).setChecked(true);
                    }
                }
            }

            //Will check if any radioButtons in the current position were already checked
            //Will recheck/uncheck the proper radio button when the views are reinflated
            if ((currentQuestion.getAnswered() == -1) || (currentQuestion.getAnswered() == 10)) {
                radioGroupLeft.clearCheck();
            } else if (currentQuestion.getAnswered() < 5) {
                ((RadioButton) radioGroupLeft.getChildAt(currentQuestion.getAnswered())).setChecked(true);
            }

            //Sets a value when a radio button is checked
            //so that it can be checked on reinflate later
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

        //Will check any editText answer inputs with the correct answer
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String theirAnswer = editText.getText().toString().toLowerCase();
                    if (v != null) {
                        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        editText.clearFocus();
                        submitted[position] = 1;
                    }
                    if (theirAnswer.equals(currentQuestion.getTheTextAnswer())) {
                        score[position] = 10;
                    }
                    return true;
                }
                return false;
            }
        });

        View textContainer = listItemView.findViewById(R.id.text_container);
        View foregroundContainer = listItemView.findViewById(R.id.foreground_container);
        int backgroundColor = ContextCompat.getColor(getContext(), mBackgroundColorResource);
        int foregroundColor = ContextCompat.getColor(getContext(), mForegroundColorResource);
        textContainer.setBackgroundColor(backgroundColor);
        foregroundContainer.setBackgroundColor(foregroundColor);

        //Will check if the question has an image or not
        if (currentQuestion.hasImage()) {
            // Get the image resource ID from the current Question object and
            // set the image to the question Image
            questionImage.setImageResource(currentQuestion.getImageResourceId());
            questionImage.setVisibility(View.VISIBLE);
        } else {
            questionImage.setVisibility(View.GONE);
        }

        //Gets the current question Number and displays it
        questionNumber.setText(currentQuestion.getQuestionNumber());
        questionTextView.setText(currentQuestion.getQuestions());
        //TODO See if there is a way to simplify this, such as an array for method name
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

        //Checks what question type the inflated question is to make sure the correct layout is shown
        //Type 1 uses 5 radio buttons and a question
        if (currentQuestion.getQuestionType() == 1) {
            editText.setVisibility(View.GONE);
            radioGroupLeft.setVisibility(View.VISIBLE);
            radioGroupRight.setVisibility(View.GONE);
            checkBox_linearLayout.setVisibility(View.GONE);
            //Type 2 uses 10 radio buttons in two groups and a question
        } else if (currentQuestion.getQuestionType() == 2) {
            editText.setVisibility(View.GONE);
            radioGroupLeft.setVisibility(View.VISIBLE);
            radioGroupRight.setVisibility(View.VISIBLE);
            checkBox_linearLayout.setVisibility(View.GONE);
            //Type 3 uses 10 checkboxes and a question
        } else if (currentQuestion.getQuestionType() == 3) {
            editText.setVisibility(View.GONE);
            radioGroupLeft.setVisibility(View.GONE);
            radioGroupRight.setVisibility(View.GONE);
            checkBox_linearLayout.setVisibility(View.VISIBLE);
            //Type 4 uses edit text to answer the question
        } else if (currentQuestion.getQuestionType() == 4) {
            editText.setVisibility(View.VISIBLE);
            radioGroupLeft.setVisibility(View.GONE);
            radioGroupRight.setVisibility(View.GONE);
            checkBox_linearLayout.setVisibility(View.GONE);
        }
        return listItemView;
    }

    //Returns a string for which questions have yet to be answered
    //Changes the color of text for unanswered questions
    private String finishCheck() {
        String checkQ = "";
        for (int i = 0; i < mCounterSize; i++) {
            if (submitted[i] != 1) {
                checkQ += "#" + (i + 1) + " ";
            } else {
                //TODO Make the correct text red
                unansweredQuestions[i] = true;
            }
        }
        return checkQ;
    }

    //Returns a count of answers that have been answered
    private int submittedCheck() {
        int submitCount = 0;
        for (int i = 0; i < mCounterSize; i++) {
            submitCount += submitted[i];
        }
        return submitCount;
    }

    //Returns their score
    private int calculateScore() {
        int playerScore = 0;
        for (int i = 0; i < score.length; i++) {
            playerScore += score[i];
        }
        return playerScore;
    }

    //Disables the buttons when the quiz is completed so answers can't be changed
    //TODO Make it work correctly
    private void disableQuiz() {
        for (int i = 0; i < mCounterSize; i++) {
            radioButton[i].setEnabled(false);
            checkBox[i].setEnabled(false);
        }
    }

    //Method to calculate the score when submit button is pressed
    public int getScore() {
        mAllSubmitted = submittedCheck();
        int totalScore = 5;
        String s = "Congratulations " + MainActivity.PLAYER_NAME + ", you've scored a " + totalScore + "%!";
        //Will display which questions still need to be answered

        if (mAllSubmitted < mCounterSize) {
            Toast.makeText(getContext(), (10 - mAllSubmitted) + " questions need to be finished.\n" + finishCheck(), Toast.LENGTH_LONG).show();
        } else {
//            disableQuiz();
//            showCorrect();
            Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
        }
        return totalScore;
    }
}


