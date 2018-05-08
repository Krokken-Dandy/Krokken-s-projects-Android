package com.example.krokken.quiz;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
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
    private int mCounterArraySize;

    //The size of array based on question with most amount of Answers
    private int mHowManyAnswers;

    //Array used to collect the mScoredQuestions of the user as they finish questions
    private int[] mScoredQuestions;

    //Array used to track which questions have been mSubmittedQuestions
    private int[] mSubmittedQuestions;

    //Array used to track the boolean of questions; answered/unanswered
    private boolean[] mUnansweredQuestions;

    public QuestionAdapter(Activity context, ArrayList<Question> question,
                           int backgroundColor, int foregroundColor, int arraySize, int howManyAnswers) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        super(context, 0, question);
        mBackgroundColorResource = backgroundColor;
        mForegroundColorResource = foregroundColor;
        mCounterArraySize = arraySize;
        mHowManyAnswers = howManyAnswers;
        mScoredQuestions = new int[mCounterArraySize];
        mSubmittedQuestions = new int[mCounterArraySize];
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        //Used to dynamically find the views in my @question_list_item
        redColorValue = R.color.red_color_value;
        View listItemView = convertView;
        final ViewHolder item;
        final Question currentQuestion = getItem(position);
        final String[] currentQuestionAnswers = currentQuestion.getAnswersArray();
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
            item.radioGroupLeft = listItemView.findViewById(R.id.question_radio_left);
            item.radioGroupRight = listItemView.findViewById(R.id.question_radio_right);

            //Initializing arrays to the largest question answer length
            item.radioButton = new RadioButton[mHowManyAnswers];
            item.answeredLeftButton = new RadioButton[mHowManyAnswers];
            item.answeredRightButton = new RadioButton[mHowManyAnswers];
            item.checkBox = new CheckBox[mHowManyAnswers];

            //Loop to find, create, and initialize the Radio button and Check boxes that will be used
            for (int i = 0; i < mHowManyAnswers; i++) {
                String sRadioButtonID = "radio_" + (i + 1);
                String sCheckBoxesID = "checkbox_" + (i + 1);

                item.radioButtonID = listItemView.getResources()
                        .getIdentifier(sRadioButtonID, "id", MainActivity.PACKAGE_NAME);
                item.checkBoxID = listItemView.getResources()
                        .getIdentifier(sCheckBoxesID, "id", MainActivity.PACKAGE_NAME);

                item.radioButton[i] = listItemView.findViewById(item.radioButtonID);
                item.checkBox[i] = listItemView.findViewById(item.checkBoxID);
            }
            listItemView.setTag(item);
        } else {
            item = (ViewHolder) listItemView.getTag();
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

        View textContainer = listItemView.findViewById(R.id.text_container);
        View foregroundContainer = listItemView.findViewById(R.id.foreground_container);
        int backgroundColor = ContextCompat.getColor(getContext(), mBackgroundColorResource);
        int foregroundColor = ContextCompat.getColor(getContext(), mForegroundColorResource);
        textContainer.setBackgroundColor(backgroundColor);
        foregroundContainer.setBackgroundColor(foregroundColor);

        //Gets the current question Number and displays it
        final String questionNumber = "#" + currentQuestion.getQuestionNumber();
        item.questionNumber.setText(questionNumber);

        //Clears old text from buttons
        if (currentQuestion.getQuestionType() != 4) {
            for (int i = 0; i < mHowManyAnswers; i++) {
                if ((currentQuestion.getQuestionType() == 1) ||
                        (currentQuestion.getQuestionType() == 2)) {
                    item.radioButton[i].setText(" ");
                } else if (currentQuestion.getQuestionType() == 3) {
                    item.checkBox[i].setText(" ");
                }
            }
        }

        //Set answer text to the correct button type
        if (currentQuestion.getQuestionType() != 4) {
            for (int i = 0; i < currentQuestionAnswers.length; i++) {
                if ((currentQuestion.getQuestionType() == 1) ||
                        (currentQuestion.getQuestionType() == 2)) {
                    item.radioButton[i].setText(currentQuestionAnswers[i]);
                } else if (currentQuestion.getQuestionType() == 3) {
                    item.checkBox[i].setText(currentQuestionAnswers[i]);
                }
            }
        }

        //Sets views to GONE and will make visible based on Question Type
        //Checks what question type the inflated question is to make sure the correct layout is shown
        //Type 1 uses 5 radio buttons and a question
        if (currentQuestion.getQuestionType() == 1) {
            item.radioGroupLeft.setVisibility(View.VISIBLE);
            for (int i = 0; i < mHowManyAnswers; i++) {
                String questionText = item.radioButton[i].getText().toString().trim();
                if (TextUtils.isEmpty(questionText)) {
                    item.radioButton[i].setVisibility(View.GONE);
                }
            }
            item.editText.setVisibility(View.GONE);
            item.editTextAnswer.setVisibility(View.GONE);
            item.radioGroupRight.setVisibility(View.GONE);
            item.checkBox_linearLayout.setVisibility(View.GONE);

            //Type 2 uses 10 radio buttons in two groups and a question
        } else if (currentQuestion.getQuestionType() == 2) {
            item.radioGroupLeft.setVisibility(View.VISIBLE);
            item.radioGroupRight.setVisibility(View.VISIBLE);
            for (int i = 0; i < mHowManyAnswers; i++) {
                String questionText = item.radioButton[i].getText().toString().trim();
                if (TextUtils.isEmpty(questionText)) {
                    item.radioButton[i].setVisibility(View.GONE);
                } else {
                    item.radioButton[i].setVisibility(View.VISIBLE);
                }
            }
            item.editText.setVisibility(View.GONE);
            item.editTextAnswer.setVisibility(View.GONE);
            item.checkBox_linearLayout.setVisibility(View.GONE);

            //Type 3 uses 10 checkboxes and a question
        } else if (currentQuestion.getQuestionType() == 3) {
            item.checkBox_linearLayout.setVisibility(View.VISIBLE);
            for (int i = 0; i < mHowManyAnswers; i++) {
                String questionText = item.checkBox[i].getText().toString().trim();
                if (questionText.equals("")) {
                    item.checkBox[i].setVisibility(View.GONE);
                } else {
                    item.checkBox[i].setVisibility(View.VISIBLE);
                }
            }
            item.editText.setVisibility(View.GONE);
            item.editTextAnswer.setVisibility(View.GONE);
            item.radioGroupLeft.setVisibility(View.GONE);
            item.radioGroupRight.setVisibility(View.GONE);

            //Type 4 uses edit text to answer the question
        } else if (currentQuestion.getQuestionType() == 4) {
            item.editText.setVisibility(View.VISIBLE);
            item.editTextAnswer.setVisibility(View.GONE);
            item.radioGroupLeft.setVisibility(View.GONE);
            item.radioGroupRight.setVisibility(View.GONE);
            item.checkBox_linearLayout.setVisibility(View.GONE);
        }

        //Makes sure there are answers for the question being displayed
        if (currentQuestionAnswers.length != 0) {
            //Loop to initialize and create listeners for all the items used in the required layout
            for (int i = 0; i < currentQuestionAnswers.length; i++) {
                //Will execute if question uses Radio Buttons
                if ((currentQuestion.getQuestionType() == 1) ||
                        (currentQuestion.getQuestionType() == 2)) {

                    //RadioButton clickListeners for checking answers
                    item.radioButton[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String isThisCorrectLeft = "";
                            String isThisCorrectRight = "";
                            final String currentCorrectAnswerLeft = currentQuestion.getCorrect1();
                            final String currentCorrectAnswerRight = currentQuestion.getCorrect2();
                            final int questionPosition = currentQuestion.getQuestionNumber() - 1;

                            //Questions that require only 5 radio buttons
                            if (currentQuestion.getQuestionType() == 1) {
                                item.answeredLeftButton[questionPosition]
                                        = item.radioGroupLeft
                                        .findViewById(item.radioGroupLeft
                                                .getCheckedRadioButtonId());
                                mSubmittedQuestions[questionPosition] = 1;
                                if (item.answeredLeftButton[questionPosition] != null) {
                                    isThisCorrectLeft
                                            = item.answeredLeftButton[questionPosition]
                                            .getText().toString();
                                }
                                if (isThisCorrectLeft.equals(currentCorrectAnswerLeft)) {
                                    mScoredQuestions[questionPosition] = 5;
                                } else {
                                    mScoredQuestions[questionPosition] = 0;
                                }
                                //Questions that require 10 radio buttons
                            } else if (currentQuestion.getQuestionType() == 2) {
                                item.answeredLeftButton[questionPosition]
                                        = item.radioGroupLeft
                                        .findViewById(item.radioGroupLeft
                                                .getCheckedRadioButtonId());
                                mSubmittedQuestions[questionPosition] = 1;
                                if (item.answeredLeftButton[questionPosition] != null) {
                                    isThisCorrectLeft
                                            = item.answeredLeftButton[questionPosition]
                                            .getText().toString();
                                }
                                item.answeredRightButton[questionPosition] =
                                        item.radioGroupRight
                                                .findViewById(item.radioGroupRight
                                                        .getCheckedRadioButtonId());
                                if (item.answeredRightButton[questionPosition] != null) {
                                    isThisCorrectRight
                                            = item.answeredRightButton[questionPosition]
                                            .getText().toString();
                                }
                                if (isThisCorrectLeft.equals(currentCorrectAnswerLeft) &&
                                        (isThisCorrectRight.equals(currentCorrectAnswerRight))) {
                                    mScoredQuestions[questionPosition] = 10;
                                } else {
                                    mScoredQuestions[questionPosition] = 0;
                                }
                            }

                            //Sets a value when a radio button is checked
                            //so that it can be checked when the view is reinflated later
                            for (int i = 0; i < currentQuestionAnswers.length; i++) {
                                if (item.radioButton[i].isChecked()) {
                                    currentQuestion.setChildPosition(i);
                                }
                            }
                            currentQuestion.setIsItChecked(true);
                            currentQuestion.setListViewPosition(questionPosition);
                        }
                    });

                }

                //Executes for questions that use checkboxes
                if (currentQuestion.getQuestionType() == 3) {
                    item.checkBox[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            int questionPosition = currentQuestion.getQuestionNumber() - 1;
                            mSubmittedQuestions[questionPosition] = 1;
                            String[] correctAnswers = currentQuestion.getCorrectArray();

                            //Determines how many questions of check box questions are correct
                            //@localScore tracks how many correct of the possible correct
                            //@incorrectlyChecked tracks if any checked do not match an answer
                            int localScore = 0;
                            int incorrectlyChecked = 0;
                            for (int j = 0; j < currentQuestionAnswers.length; j++) {
                                if (item.checkBox[j].isChecked()) {
                                    //@didNotMatch tracks a checked box text with possible answers
                                    int didNotMatch = 0;
                                    for (int i = 0; i < correctAnswers.length; i++) {
                                        if (correctAnswers[i].equals(item.checkBox[j].getText().toString())) {
                                            localScore++;
                                        } else {
                                            didNotMatch++;
                                        }
                                        //Will note that an incorrect checkbox is checked
                                        //@incorrectlyChecked tracks how many are incorrectly checked
                                        if (didNotMatch == correctAnswers.length) {
                                            incorrectlyChecked++;
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
                            //Will make score for this question 0 if they have any answers
                            //marked incorrectly
                            if (incorrectlyChecked > 0) {
                                mScoredQuestions[questionPosition] = 0;
                            }

                            //Sets a value when a checkbox is checked
                            //so that it can be checked when the view is reinflated later
                            for (int i = 0; i < currentQuestionAnswers.length; i++) {
                                if (item.checkBox[i].isChecked()) {
                                    currentQuestion.putsCheckBoxBoolean(i, true);
                                } else {
                                    currentQuestion.putsCheckBoxBoolean(i, false);
                                }
                            }
                            currentQuestion.setIsItChecked(true);
                        }
                    });
                }
            }
        }

        //Sets checkboxes to true or false based on if they were preivously checked
        if ((currentQuestion.getQuestionType() == 1) || (currentQuestion.getQuestionType() == 2)) {
            //Will check if any radioButtons in the current position were already checked
            //Will recheck/uncheck the proper radio button when the views are reinflated
            if (!currentQuestion.getIsItChecked()) {
                item.radioGroupLeft.clearCheck();
            } else {
                ((RadioButton) item.radioGroupLeft.getChildAt(currentQuestion.getChildPosition())).setChecked(true);
            }
        }

        if (currentQuestion.getQuestionType() == 3) {
            for (int j = 0; j < currentQuestionAnswers.length; j++) {
                boolean checked = currentQuestion.getCheckBoxBoolean(j);
                item.checkBox[j].setChecked(checked);
                Log.v("checked", "" + checked);
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

        //Sets the text for the question
        if (currentQuestion.getQuestions() != null) {
            item.questionTextView.setText(currentQuestion.getQuestions());
        }

        //Tracks which questions have been answered
        mUnansweredQuestions = new boolean[mCounterArraySize];
        if (mUnansweredQuestions[position]) {
            item.questionTextView.setTextColor(redColorValue);
        }
        return listItemView;
    }

    //Holds all the views
    static class ViewHolder {
        private ImageView questionImage;
        private TextView editTextAnswer;
        private TextView questionTextView;
        private TextView questionNumber;
        //References the group of radio buttons on the left of the question, if needed
        private RadioGroup radioGroupLeft;

        //References the group of radio buttons on the right of the question, if needed
        private RadioGroup radioGroupRight;
        private RadioButton[] radioButton;
        private RadioButton[] answeredLeftButton;
        private RadioButton[] answeredRightButton;
        private CheckBox[] checkBox;
        private EditText editText;
        private LinearLayout checkBox_linearLayout;
        private int radioButtonID;
        private int checkBoxID;
    }

    //Returns a string for which questions have yet to be answered
    //Changes the color of text for unanswered questions
    private String finishCheck() {
        String checkQ = "";
        for (int i = 0; i < mCounterArraySize; i++) {
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
        for (int i = 0; i < mCounterArraySize; i++) {
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
//        for (int i = 0; i < mCounterArraySize; i++) {
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
        if (mAllSubmitted < mCounterArraySize) {
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


