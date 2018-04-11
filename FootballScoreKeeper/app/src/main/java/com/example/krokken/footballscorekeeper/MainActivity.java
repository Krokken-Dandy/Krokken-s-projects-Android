package com.example.krokken.footballscorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int teamAScore = 0;
    int teamBScore = 0;
    int touchdown = 6;
    int fieldGoal = 3;
    int twoPoint = 2;
    int extraPoint = 1;
    int whichTwoPoint = 0;
    String scoreText = " scored a";
    String touchdownText = " \nTOUCHDOWN!";
    String fieldGoalText = " \nFIELD GOAL!";
    String conversionText = " \ntwo-point conversion!";
    String touchbackText = " \ntouch-back!";
    String extraPointText = "n \nextra point!";
    String pointText = "";
    String twoPointText = "";
    String teamAText = "Team A";
    String teamBText = "Team B";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(teamAScore);
        displayForTeamB(teamBScore);

        // Declaring the Button and Type Casting it.
        Button touchbackA = (Button) findViewById(R.id.touchbackA);
        Button touchbackB = (Button) findViewById(R.id.touchbackB);

        // Setting OnClickListener for touchback A & B
        touchbackA.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {

                whichTwoPoint = 1;
                twoPointA(view);
            }
        });

        touchbackB.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {

                whichTwoPoint = 1;
                twoPointB(view);
            }
        });
    }

    //Team A scores a touchdown
    //displays text and updates score
    public void touchdownAPoint(View view) {
        teamAScore = teamAScore + touchdown;
        displayForTeamA(teamAScore);
        pointText = teamAText + scoreText + touchdownText;
        scoreText(pointText);
    }

    //Team B scores a touchdown
    //displays text and updates score
    public void touchdownBPoint(View v) {
        teamBScore = teamBScore + touchdown;
        displayForTeamB(teamBScore);
        pointText = teamBText + scoreText + touchdownText;
        scoreText(pointText);
    }

    //Team A scores a field goal
    //displays text and updates score
    public void fieldGoalAPoint(View view) {
        teamAScore = teamAScore + fieldGoal;
        displayForTeamA(teamAScore);
        pointText = teamAText + scoreText + fieldGoalText;
        scoreText(pointText);
    }

    //Team B scores a field goal
    //displays text and updates score
    public void fieldGoalBPoint(View v) {
        teamBScore = teamBScore + fieldGoal;
        displayForTeamB(teamBScore);
        pointText = teamBText + scoreText + fieldGoalText;
        scoreText(pointText);
    }

    //Team A scores either a touchback or two point conversion
    //displays text and updates score
    public void twoPointA(View view) {
        teamAScore = teamAScore + twoPoint;
         if (whichTwoPoint > 0) {
            twoPointText = touchbackText;
        }
        else {
            twoPointText = conversionText;
        }
        pointText = teamAText + scoreText + twoPointText;
        displayForTeamA(teamAScore);
        scoreText(pointText);
        whichTwoPoint = 0;
    }

    //Team B scores either a touchback or two point conversion
    //displays text and updates score
    public void twoPointB(View v) {
        teamBScore = teamBScore + twoPoint;
        if (whichTwoPoint > 0) {
            twoPointText = touchbackText;
        }
        else {
            twoPointText = conversionText;
        }
        pointText = teamBText + scoreText + twoPointText;
        displayForTeamB(teamBScore);
        scoreText(pointText);
        whichTwoPoint = 0;
    }

    //Team A scores an extra point
    //displays text and updates score
    public void extraPointA(View view) {
        teamAScore = teamAScore + extraPoint;
        displayForTeamA(teamAScore);
        pointText = teamAText + scoreText + extraPointText;
        scoreText(pointText);
    }

    //Team B scores an extra point
    //displays text and updates score
    public void extraPointB(View v) {
        teamBScore = teamBScore + extraPoint;
        displayForTeamB(teamBScore);
        pointText = teamBText + scoreText + extraPointText;
        scoreText(pointText);
    }

    //Will reset both scores to 0
    public void resetScores(View v) {
        teamAScore = 0;
        teamBScore = 0;
        displayForTeamA(teamAScore);
        displayForTeamB(teamBScore);
        pointText = "Time for a new game!";
        scoreText(pointText);
    }

    //Method for displaying Team A's score
    public void displayForTeamA(int score) {
        TextView scoreView = findViewById(R.id.teamAScore);
        scoreView.setText(String.valueOf(score));
    }

    //Method for displaying Team A's score
    public void displayForTeamB(int score) {
        TextView scoreView = findViewById(R.id.teamBScore);
        scoreView.setText(String.valueOf(score));
    }

    //Displays the score text at the bottom
    public void scoreText(String score) {
        TextView scoreView = findViewById(R.id.scoreText);
        scoreView.setText(String.valueOf(score));
    }
}
