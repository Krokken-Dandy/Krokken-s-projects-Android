package com.example.krokken.reportcard;

import android.widget.EditText;

import java.util.ArrayList;

public class ReportCard {
    String mMathGradeLetter, mScienceGradeLetter, mHistoryGradeLetter, mLanguageGradeLetter, mGymGradeLetter, mComputerGradeLetter, mStudentName;
    double mMathGrade, mHistoryGrade, mLanguageGrade, mScienceGrade, mComputerGrade, mGymGrade;

    ArrayList reportCardList = new ArrayList();
    MainActivity mainActivity = new MainActivity();

    public ReportCard(double mathGrade, double scienceGrade, double historyGrade, double languageGrade, double computerGrade, double gymGrade, String studentName) {
        mMathGrade = mathGrade;
        mScienceGrade = scienceGrade;
        mHistoryGrade = historyGrade;
        mLanguageGrade = languageGrade;
        mComputerGrade = computerGrade;
        mGymGrade = gymGrade;
        mStudentName = studentName;

    }

    public void addStudent(String studentName) {
        reportCardList.add(studentName);
    }

    private String calculateLetterGrade(double grade) {
        String letterGrade = "";
        if (grade < 60) {
            letterGrade = "F";
        } else if (grade < 66) {
            letterGrade = "D";
        } else if (grade < 69) {
            letterGrade = "D+";
        } else if (grade < 72) {
            letterGrade = "C-";
        } else if (grade < 76) {
            letterGrade = "C";
        } else if (grade < 79) {
            letterGrade = "C+";
        } else if (grade < 83) {
            letterGrade = "B-";
        } else if (grade < 86) {
            letterGrade = "B";
        } else if (grade < 89) {
            letterGrade = "B+";
        } else if (grade < 93) {
            letterGrade = "A-";
        } else if (grade < 100) {
            letterGrade = "A";
        } else if (grade == 100) {
            letterGrade = "A+";
        }
        return letterGrade;
    }

    public void getAllGradeLetters() {
        getComputerGradeLetter();
        getGymGradeLetter();
        getMathGradeLetter();
        getScienceGradeLetter();
        getHistoryGradeLetter();
        getLanguageGradeLetter();
        }

    public String getMathGradeLetter() {
        mMathGradeLetter = calculateLetterGrade(mMathGrade);
        return mMathGradeLetter;
    }

    public String getScienceGradeLetter() {
        mScienceGradeLetter = calculateLetterGrade(mScienceGrade);
        return mScienceGradeLetter;
    }

    public String getHistoryGradeLetter() {
        mHistoryGradeLetter = calculateLetterGrade(mHistoryGrade);
        return mHistoryGradeLetter;
    }

    public String getLanguageGradeLetter() {
        mLanguageGradeLetter = calculateLetterGrade(mLanguageGrade);
        return mLanguageGradeLetter;
    }

    public String getGymGradeLetter() {
        mGymGradeLetter = calculateLetterGrade(mGymGrade);
        return mGymGradeLetter;
    }

    public String getComputerGradeLetter() {
        mComputerGradeLetter = calculateLetterGrade(mComputerGrade);
        return mComputerGradeLetter;
    }

//    public void setScienceGrade(double grade) {
//        chemistryGrade = grade;
//    }

    @Override
    public String toString() {
        String fullReportCard = mStudentName;
        getAllGradeLetters();
        fullReportCard += "\n" + "Math Grade: " + mMathGrade + " | " + mMathGradeLetter;
        fullReportCard += "\n" + "Science Grade: " + mScienceGrade + " | " + mScienceGradeLetter;
        fullReportCard += "\n" + "History Grade: " + mHistoryGrade + " | " + mHistoryGradeLetter;
        fullReportCard += "\n" + "Language Grade: " + mLanguageGrade + " | " + mLanguageGradeLetter;
        fullReportCard += "\n" + "Computer Grade: " + mComputerGrade + " | " + mComputerGradeLetter;
        fullReportCard += "\n" + "Gym Grade: " + mGymGrade + " | " + mGymGradeLetter;

        return fullReportCard;
    }


}

