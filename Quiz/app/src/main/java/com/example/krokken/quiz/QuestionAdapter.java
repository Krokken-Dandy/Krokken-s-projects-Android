package com.example.krokken.quiz;

import android.app.Activity;
import android.content.res.Resources;
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
    //The color that will be the main background color based on the Quiz chosen
    private int mBackgroundColorResource;

    //The color that will be the main foreground color based on the Quiz chosen
    private int mForegroundColorResource;

    //Red color that is used to show unfinished or incorrect questions
    private int redColorValue;

    //The size of the question array for the Quiz chosen
    private int mCounterSize;

    //References the group of radio buttons on the left of the question, if needed
    private RadioGroup radioGroupLeft;

    //References the group of radio buttons on the right of the question, if needed
    private RadioGroup radioGroupRight;

    //Array used to collect the mScoredQuestions of the user as they finish questions
    private int[] mScoredQuestions;

    //Array used to track which questions have been mSubmittedQuestions
    private int[] mSubmittedQuestions;

    //Array used to track the boolean of the checkboxes
    private boolean[] mCheckBoxesChecked;

    //Array used to track the boolean of questions; answered/unanswered
    private boolean[] mUnansweredQuestions;

    public QuestionAdapter(Activity context, ArrayList<Question> question,
                           int backgroundColor, int foregroundColor, int arraySize) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        super(context, 0, question);
        mBackgroundColorResource = backgroundColor;
        mForegroundColorResource = foregroundColor;
        mCounterSize = arraySize;
        mScoredQuestions = new int[mCounterSize];
        mSubmittedQuestions = new int[mCounterSize];
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        //Used to dynamically find the views in my @question_list_item
        redColorValue = R.color.red_color_value;
        View listItemView = convertView;
        final ViewHolder item;
        final Question currentQuestion = getItem(position);

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.question_list_item, parent, false);
            item = new ViewHolder();
            item.questionImage = listItemView.findViewById(R.id.question_image);
            item.questionTextView = listItemView.findViewById(R.id.question_text_view);
            item.questionNumber = listItemView.findViewById(R.id.question_number);
            item.editText = listItemView.findViewById(R.id.edittext_their_answer);
            item.editTextAnswer = listItemView.findViewById(R.id.edittext_correct_answer);
            item.checkBox_linearLayout = listItemView.findViewById(R.id.checkboxes);

            //Initializing arrays to the size of the Quiz's question array
            item.radioButton = new RadioButton[mCounterSize];
            item.answeredLeftButton = new RadioButton[mCounterSize];
            item.answeredRightButton = new RadioButton[mCounterSize];
            item.checkBox = new CheckBox[mCounterSize];
            item.answer = new ImageView[mCounterSize];

            //Loop to find, create, and initialize the Radio button and Check boxes that will be used
            for (int i = 0; i < mCounterSize; i++) {
                String sRadioButtonID = "radio_" + (i + 1);
                String sCheckBoxesID = "checkbox_" + (i + 1);
                String sAnswerID = "answer" + (i + 1) + "_correct";

                item.radioButtonID = listItemView.getResources()
                        .getIdentifier(sRadioButtonID, "id", MainActivity.PACKAGE_NAME);
                item.checkBoxID = listItemView.getResources()
                        .getIdentifier(sCheckBoxesID, "id", MainActivity.PACKAGE_NAME);
                item.answerID = listItemView.getResources()
                        .getIdentifier(sAnswerID, "id", MainActivity.PACKAGE_NAME);

                item.radioButton[i] = listItemView.findViewById(item.radioButtonID);
                item.checkBox[i] = listItemView.findViewById(item.checkBoxID);
                item.answer[i] = listItemView.findViewById(item.answerID);
            }
            listItemView.setTag(item);
        } else {
            item = (ViewHolder) listItemView.getTag();
        }

        radioGroupLeft = listItemView.findViewById(R.id.question_radio_left);
        radioGroupRight = listItemView.findViewById(R.id.question_radio_right);
        item.editTextAnswer.setVisibility(View.GONE);

        //Skips if the current question isn't an EditText
        if (currentQuestion.getQuestionType() != 4) {
            //Loop to initialize and create listeners for all the items used in the required layout
            for (int i = 0; i < mCounterSize; i++) {
                //Skips if the current question isn't CheckBoxes
                if (currentQuestion.getQuestionType() != 3) {
                    //RadioButton clickListeners for checking answers
                    item.radioButton[i].setOnClickListener(new View.OnClickListener() {
                        int questionPosition = currentQuestion.getQuestionNumber() - 1;
                        @Override
                        public void onClick(View v) {
                            String isThisCorrectLeft = "";
                            String isThisCorrectRight = "";
                            //Questions that require only 5 radio buttons
                            if (currentQuestion.getQuestionType() == 1) {
                                item.answeredLeftButton[questionPosition] =
                                        radioGroupLeft.findViewById(radioGroupLeft.getCheckedRadioButtonId());
                                mSubmittedQuestions[questionPosition] = 1;
                                if (item.answeredLeftButton[questionPosition] != null) {
                                    isThisCorrectLeft = item.answeredLeftButton[questionPosition].getText().toString();
                                }
                                if (isThisCorrectLeft.equals(currentQuestion.getCorrect1())) {
                                    mScoredQuestions[questionPosition] = 5;
                                } else {
                                    mScoredQuestions[questionPosition] = 0;
                                }
                                //Questions that require 10 radio buttons
                            } else if (currentQuestion.getQuestionType() == 2) {
                                item.answeredLeftButton[questionPosition] =
                                        radioGroupLeft.findViewById(radioGroupLeft.getCheckedRadioButtonId());
                                mSubmittedQuestions[questionPosition] = 1;
                                if (item.answeredLeftButton[questionPosition] != null) {
                                    isThisCorrectLeft = item.answeredLeftButton[questionPosition].getText().toString();
                                }
                                item.answeredRightButton[questionPosition] =
                                        radioGroupRight.findViewById(radioGroupRight.getCheckedRadioButtonId());
                                if (item.answeredRightButton[questionPosition] != null) {
                                    isThisCorrectRight = item.answeredRightButton[questionPosition].getText().toString();
                                }
                                if (isThisCorrectLeft.equals(currentQuestion.getCorrect1()) &&
                                        (isThisCorrectRight.equals(currentQuestion.getCorrect2()))) {
                                    mScoredQuestions[questionPosition] = 10;
                                } else {
                                    mScoredQuestions[questionPosition] = 0;
                                }
                            }
                        }
                    });

                    //Will check if any radioButtons in the current position were already checked
                    //Will recheck/uncheck the proper radio button when the views are reinflated
                    if (currentQuestion.getAnswered() == -1) {
                        radioGroupLeft.clearCheck();
                    } else {
                        ((RadioButton) radioGroupLeft.getChildAt(currentQuestion.getAnswered())).setChecked(true);
                    }

                    //Sets a value when a radio button is checked
                    //so that it can be checked when the view is reinflated later
                    radioGroupLeft.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId) {
                                case R.id.radio_1:
                                    currentQuestion.setIsItAnswered(0);
                                    break;
                                case R.id.radio_2:
                                    currentQuestion.setIsItAnswered(1);
                                    break;
                                case R.id.radio_3:
                                    currentQuestion.setIsItAnswered(2);
                                    break;
                                case R.id.radio_4:
                                    currentQuestion.setIsItAnswered(3);
                                    break;
                                case R.id.radio_5:
                                    currentQuestion.setIsItAnswered(4);
                                    break;
                            }
                        }
                    });
                }
                //Questions that use checkboxes
                if (currentQuestion.getQuestionType() == 3) {
                    item.checkBox[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            int questionPosition = currentQuestion.getQuestionNumber() - 1;
                            mSubmittedQuestions[questionPosition] = 1;
                            String[] correctAnswers = currentQuestion.getCorrectArray();

                            if (isChecked) {
                                currentQuestion.setCheckBoxAnswer(10);
                            } else {
                                currentQuestion.setCheckBoxAnswer(-1);
                            }
                            if ((!item.checkBox[0].isChecked()) && (!item.checkBox[1].isChecked()) &&
                                    (!item.checkBox[2].isChecked()) && (!item.checkBox[3].isChecked()) &&
                                    (!item.checkBox[4].isChecked()) && (!item.checkBox[5].isChecked()) &&
                                    (!item.checkBox[6].isChecked()) && (!item.checkBox[7].isChecked()) &&
                                    (!item.checkBox[8].isChecked()) && (!item.checkBox[9].isChecked())) {
                                mSubmittedQuestions[questionPosition] = 0;
                            }

                            //Determines how many questions of check box questions are correct
                            int localScore = 0;
                            for (int j = 0; j < item.checkBox.length; j++) {
                                if (item.checkBox[j].isChecked()) {
                                    for (int i = 0; i < correctAnswers.length; i++) {
                                        if (correctAnswers[i].equals(item.checkBox[j].getText().toString())) {
                                            localScore++;
                                        }
                                    }
                                }
                            }

                            //Calculates final score of checkBox questions
                            //localScore needs to match correct number of answers (Get all correct)
                            if (localScore == correctAnswers.length) {
                                mScoredQuestions[questionPosition] = 5 * correctAnswers.length;
                            } else {
                                mScoredQuestions[questionPosition] = 0;
                            }
                        }
                    });
                }

//            for (int j = 0; j < item.checkBox_linearLayout.getChildCount(); j++) {
//                View child = item.checkBox_linearLayout.getChildAt(j);
//                if (child instanceof CheckBox) {
//                    if (currentQuestion.getAnswered() == -1) {
//                        ((CheckBox) child).setChecked(false);
//                    } else if (currentQuestion.getAnswered() == 10) {
//                        ((CheckBox) child).setChecked(true);
//                    }
//                }
//            }
            }
        }

        //Will check any editText answer inputs with the correct answer
        item.editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String theirAnswer = item.editText.getText().toString().toLowerCase();
                    String answerToQuestion = currentQuestion.getTheTextAnswer().toLowerCase();
                    int questionPosition = currentQuestion.getQuestionNumber() - 1;
                    if (v != null) {
                        InputMethodManager imm = (InputMethodManager) getContext()
                                .getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        item.editText.clearFocus();
                        mSubmittedQuestions[questionPosition] = 1;
                        if (theirAnswer.equals(answerToQuestion)) {
                            mScoredQuestions[questionPosition] = 10;
                        } else {
                            mScoredQuestions[questionPosition] = 0;
                        }
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

        //Gets the current question Number and displays it
        String questionNumber = "#" + currentQuestion.getQuestionNumber();
        item.questionNumber.setText(questionNumber);

        //Tracks which questions have been answered
        mUnansweredQuestions = new boolean[mCounterSize];

        //Sets the text for the question
        item.questionTextView.setText(currentQuestion.getQuestions());
        if (mUnansweredQuestions[position]) {
            item.questionTextView.setTextColor(redColorValue);
        }
        //Set answer text to the correct button type
        //TODO See if there is a way to simplify this, such as an array for method name
        if ((currentQuestion.getQuestionType() == 1) || (currentQuestion.getQuestionType() == 2)) {
            item.radioButton[0].setText(currentQuestion.getAnswer1());
            item.radioButton[1].setText(currentQuestion.getAnswer2());
            item.radioButton[2].setText(currentQuestion.getAnswer3());
            item.radioButton[3].setText(currentQuestion.getAnswer4());
            item.radioButton[4].setText(currentQuestion.getAnswer5());
            if (currentQuestion.getQuestionType() == 2) {
                item.radioButton[5].setText(currentQuestion.getAnswer6());
                item.radioButton[6].setText(currentQuestion.getAnswer7());
                item.radioButton[7].setText(currentQuestion.getAnswer8());
                item.radioButton[8].setText(currentQuestion.getAnswer9());
                item.radioButton[9].setText(currentQuestion.getAnswer10());
            }
        } else if (currentQuestion.getQuestionType() == 3) {
            item.checkBox[0].setText(currentQuestion.getAnswer1());
            item.checkBox[1].setText(currentQuestion.getAnswer2());
            item.checkBox[2].setText(currentQuestion.getAnswer3());
            item.checkBox[3].setText(currentQuestion.getAnswer4());
            item.checkBox[4].setText(currentQuestion.getAnswer5());
            item.checkBox[5].setText(currentQuestion.getAnswer6());
            item.checkBox[6].setText(currentQuestion.getAnswer7());
            item.checkBox[7].setText(currentQuestion.getAnswer8());
            item.checkBox[8].setText(currentQuestion.getAnswer9());
            item.checkBox[9].setText(currentQuestion.getAnswer10());
        }

        //Will check if the question has an image or not
        if (currentQuestion.hasImage()) {
            // Get the image resource ID from the current Question object and
            // set the image to the question Image
            item.questionImage.setImageResource(currentQuestion.getImageResourceId());
            item.questionImage.setVisibility(View.VISIBLE);
        } else {
            item.questionImage.setVisibility(View.GONE);
        }

        //Checks what question type the inflated question is to make sure the correct layout is shown
        //Type 1 uses 5 radio buttons and a question
        if (currentQuestion.getQuestionType() == 1) {
            item.editText.setVisibility(View.GONE);
            radioGroupLeft.setVisibility(View.VISIBLE);
            radioGroupRight.setVisibility(View.GONE);
            item.checkBox_linearLayout.setVisibility(View.GONE);

            //Type 2 uses 10 radio buttons in two groups and a question
        } else if (currentQuestion.getQuestionType() == 2) {
            item.editText.setVisibility(View.GONE);
            radioGroupLeft.setVisibility(View.VISIBLE);
            radioGroupRight.setVisibility(View.VISIBLE);
            item.checkBox_linearLayout.setVisibility(View.GONE);

            //Type 3 uses 10 checkboxes and a question
        } else if (currentQuestion.getQuestionType() == 3) {
            item.editText.setVisibility(View.GONE);
            radioGroupLeft.setVisibility(View.GONE);
            radioGroupRight.setVisibility(View.GONE);
            item.checkBox_linearLayout.setVisibility(View.VISIBLE);

            //Type 4 uses edit text to answer the question
        } else if (currentQuestion.getQuestionType() == 4) {
            item.editText.setVisibility(View.VISIBLE);
            radioGroupLeft.setVisibility(View.GONE);
            radioGroupRight.setVisibility(View.GONE);
            item.checkBox_linearLayout.setVisibility(View.GONE);
        }
        for (int i = 1; i<=mCounterSize; i++) Log.v("question" + i, "points" + mScoredQuestions[i]);
        return listItemView;
    }

    //Holds all the views
    static class ViewHolder {
        private ImageView questionImage;
        private ImageView[] answer;
        private TextView editTextAnswer;
        private TextView questionTextView;
        private TextView questionNumber;
        private RadioButton[] radioButton;
        private RadioButton[] answeredLeftButton;
        private RadioButton[] answeredRightButton;
        private CheckBox[] checkBox;
        private EditText editText;
        private LinearLayout checkBox_linearLayout;
        private int radioButtonID;
        private int checkBoxID;
        private int answerID;
    }

    //Returns a string for which questions have yet to be answered
    //Changes the color of text for unanswered questions
    private String finishCheck() {
        String checkQ = "";
        for (int i = 0; i < mCounterSize; i++) {
            if (mSubmittedQuestions[i] != 1) {
                checkQ += "#" + (i + 1) + " ";
                mUnansweredQuestions[i] = true;
            }
        }
        return checkQ;
    }

    //Returns a count of answers that have been answered
    private int submittedCheck() {
        int submitCount = 0;
        for (int i = 0; i < mCounterSize; i++) {
            submitCount += mSubmittedQuestions[i];
        }
        return submitCount;
    }

    //Returns their mScoredQuestions
    private int calculateScore() {
        int playerScore = 0;
        for (int i = 0; i < mScoredQuestions.length; i++) {
            playerScore += mScoredQuestions[i];
        }
        return playerScore;
    }

    //Disables the buttons when the quiz is completed so answers can't be changed
    //TODO Make disabling quiz work correctly
//    private void disableQuiz() {
//        for (int i = 0; i < mCounterSize; i++) {
//            item.radioButton[i].setEnabled(false);
//            item.checkBox[i].setEnabled(false);
//        }
//    }

    //Method to calculate the mScoredQuestions when submit button is pressed
    public void getScore() {
        Resources res = getContext().getResources();
        int mAllSubmitted = submittedCheck();
        int totalScore;

        //Will display which questions still need to be answered
        if (mAllSubmitted < mCounterSize) {
            Toast.makeText(getContext(), (10 - mAllSubmitted) +
                    res.getString(R.string.quiz_finish_questions_need_to_finish) +
                    finishCheck(), Toast.LENGTH_LONG).show();
        } else {
//            disableQuiz();
//            showCorrect();
            totalScore = calculateScore();
            String s = res.getString(R.string.score_text_congratulations) + MainActivity.PLAYER_NAME +
                    res.getString(R.string.score_text_you_scored_a) + totalScore +
                    res.getString(R.string.score_text_percent_symbol);
            Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
        }
    }
}


