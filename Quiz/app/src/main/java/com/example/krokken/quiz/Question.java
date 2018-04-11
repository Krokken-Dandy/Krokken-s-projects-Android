package com.example.krokken.quiz;

import android.os.MessageQueue;

public class Question {

    private String mQuestions, mQuestionNumber;

    private String mAnswer1, mAnswer2, mAnswer3, mAnswer4, mAnswer5, mAnswer6, mAnswer7, mAnswer8, mAnswer9, mAnswer10,
            mCorrect1, mCorrect2, mCorrect3, mCorrect4, mCorrect5;

    private int mQuestionImage = NO_IMAGE_PROVIDED, answer = -1, mQuestionType, mTheAnswer;

    private static final int NO_IMAGE_PROVIDED = -1;

    //Constructor for 10 possible answers, an image, and 5 correct answers
    public Question(int questionType, String questionNumber, int questionImage, String question, String answer1, String answer2, String answer3, String answer4,
                    String answer5, String answer6, String answer7, String answer8, String answer9, String answer10, String correct1, String correct2, String correct3, String correct4, String correct5) {
        mQuestionType = questionType;
        mQuestionNumber = questionNumber;
        mQuestions = question;
        mAnswer1 = answer1;
        mAnswer2 = answer2;
        mAnswer3 = answer3;
        mAnswer4 = answer4;
        mAnswer5 = answer5;
        mAnswer6 = answer6;
        mAnswer7 = answer7;
        mAnswer8 = answer8;
        mAnswer9 = answer9;
        mAnswer10 = answer10;
        mQuestionImage = questionImage;
        mCorrect1 = correct1;
        mCorrect2 = correct2;
        mCorrect3 = correct3;
        mCorrect4 = correct4;
        mCorrect5 = correct5;
    }

    //Constructor for 10 possible answers, no image, and 5 correct answers
    public Question(int questionType, String questionNumber, String question, String answer1, String answer2, String answer3, String answer4,
                    String answer5, String answer6, String answer7, String answer8, String answer9, String answer10, String correct1, String correct2, String correct3, String correct4, String correct5) {
        mQuestionType = questionType;
        mQuestionNumber = questionNumber;
        mQuestions = question;
        mAnswer1 = answer1;
        mAnswer2 = answer2;
        mAnswer3 = answer3;
        mAnswer4 = answer4;
        mAnswer5 = answer5;
        mAnswer6 = answer6;
        mAnswer7 = answer7;
        mAnswer8 = answer8;
        mAnswer9 = answer9;
        mAnswer10 = answer10;
        mCorrect1 = correct1;
        mCorrect2 = correct2;
        mCorrect3 = correct3;
        mCorrect4 = correct4;
        mCorrect5 = correct5;
    }

    //Constructor for 10 possible answers, no image, and 2 correct answers
    public Question(int questionType, String questionNumber, String question, String answer1, String answer2, String answer3, String answer4, String answer5,
                    String answer6, String answer7, String answer8, String answer9, String answer10, String correct1, String correct2) {
        mQuestionType = questionType;
        mQuestionNumber = questionNumber;
        mQuestions = question;
        mAnswer1 = answer1;
        mAnswer2 = answer2;
        mAnswer3 = answer3;
        mAnswer4 = answer4;
        mAnswer5 = answer5;
        mAnswer6 = answer6;
        mAnswer7 = answer7;
        mAnswer8 = answer8;
        mAnswer9 = answer9;
        mAnswer10 = answer10;
        mCorrect1 = correct1;
        mCorrect2 = correct2;
    }

    //Constructor for 5 possible answers, an image, and a single correct answer
    public Question(int questionType, String questionNumber, int questionImage, String question, String answer1, String answer2, String answer3, String answer4,
                    String answer5, String correct1) {
        mQuestionType = questionType;
        mQuestionNumber = questionNumber;
        mQuestions = question;
        mAnswer1 = answer1;
        mAnswer2 = answer2;
        mAnswer3 = answer3;
        mAnswer4 = answer4;
        mAnswer5 = answer5;
        mQuestionImage = questionImage;
        mCorrect1 = correct1;
    }

    //Constructor for 5 possible answers, no image, and a single correct answer
    public Question(int questionType, String questionNumber, String question, String answer1, String answer2, String answer3, String answer4, String answer5,
                    String correct1) {
        mQuestionType = questionType;
        mQuestionNumber = questionNumber;
        mQuestions = question;
        mAnswer1 = answer1;
        mAnswer2 = answer2;
        mAnswer3 = answer3;
        mAnswer4 = answer4;
        mAnswer5 = answer5;
        mCorrect1 = correct1;
    }

    //Test constructor
    public Question(int questionType, String questionNumber, String question, String answer1, String answer2, String answer3, String answer4, String answer5, int questionImage, String correct1) {
        mQuestionType = questionType;
        mQuestionNumber = questionNumber;
        mQuestionImage = questionImage;
        mQuestions = question;
        mAnswer1 = answer1;
        mAnswer2 = answer2;
        mAnswer3 = answer3;
        mAnswer4 = answer4;
        mAnswer5 = answer5;
        mCorrect1 = correct1;
    }

    //EditText constructor
    public Question(int questionType, String questionNumber, int questionImage, String question, int theAnswer) {
        mQuestionType = questionType;
        mQuestionNumber = questionNumber;
        mQuestionImage = questionImage;
        mQuestions = question;
        mTheAnswer = theAnswer;
    }

    public int getQuestionType() {
        return mQuestionType;
    }

    public String getQuestionNumber() {
        return mQuestionNumber;
    }

    public int getAnswered(){
        return answer;
        }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getQuestions() {
        return mQuestions;
    }

    public String getAnswer1() {
        return mAnswer1;
    }

    public String getAnswer2() {
        return mAnswer2;
    }

    public String getAnswer3() {
        return mAnswer3;
    }

    public String getAnswer4() {
        return mAnswer4;
    }

    public String getAnswer5() {
        return mAnswer5;
    }

    public String getAnswer6() {
        return mAnswer6;
    }

    public String getAnswer7() {
        return mAnswer7;
    }

    public String getAnswer8() {
        return mAnswer8;
    }

    public String getAnswer9() {
        return mAnswer9;
    }

    public String getAnswer10() {
        return mAnswer10;
    }

    public int theAnswer() {
        return mTheAnswer;
    }

    public String getCorrect1(){
        return mCorrect1;
    }

    public String getCorrect2(){
        return mCorrect2;
    }

    public String getCorrect3(){
        return mCorrect3;
    }

    public String getCorrect4(){
        return mCorrect4;
    }

    public String getCorrect5(){
        return mCorrect5;
    }

    public int getImageResourceId() {
        return mQuestionImage;
    }

    public boolean hasImage() {
        return mQuestionImage != NO_IMAGE_PROVIDED;
    }


}

