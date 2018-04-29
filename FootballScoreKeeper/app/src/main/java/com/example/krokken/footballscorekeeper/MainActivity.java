package com.example.krokken.footballscorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TEAM_A_SCORE = "team a score";
    private static final String TEAM_B_SCORE = "team b score";
    Animation fadeIn, fadeOut;
    int teamAScore, teamBScore, touchdownPoints, fieldGoalPoints, twoPointPoints,
            extraPointPoints;
    Button buttonTouchdownA, buttonTouchdownB, buttonFieldGoalA, buttonFieldGoalB,
            buttonTwoPointConversionA, buttonTwoPointConversionB, buttonSafetyA,
            buttonSafetyB, buttonExtraPointA, buttonExtraPointB, buttonResetScores;
    TextView scoreTextForTeam;
    ImageView scoredImage;
    String scoredText, touchdownText, fieldGoalText, conversionText, safetyText,
            extraPointText, teamAText, teamBText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initializeVariables();
        onClickListeners();
        fadeListeners();

        //Will restore a saved state if we have one
        if (savedInstanceState != null) {
            teamAScore = savedInstanceState.getInt(TEAM_A_SCORE);
            teamBScore = savedInstanceState.getInt(TEAM_B_SCORE);
        }
        displayScoreForTeam(teamAScore, "a");
        displayScoreForTeam(teamBScore, "b");
    }

    //Saves the team's scores so it can be restored
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TEAM_A_SCORE, teamAScore);
        outState.putInt(TEAM_B_SCORE, teamBScore);
    }

    //Finds all views that will be used
    private void findViews() {
        buttonTouchdownA = findViewById(R.id.touchdown_a);
        buttonTouchdownB = findViewById(R.id.touchdown_b);
        buttonFieldGoalA = findViewById(R.id.field_goal_a);
        buttonFieldGoalB = findViewById(R.id.field_goal_b);
        buttonTwoPointConversionA = findViewById(R.id.two_point_conversion_a);
        buttonTwoPointConversionB = findViewById(R.id.two_point_conversion_b);
        buttonSafetyA = findViewById(R.id.safety_a);
        buttonSafetyB = findViewById(R.id.safety_b);
        buttonExtraPointA = findViewById(R.id.extra_point_a);
        buttonExtraPointB = findViewById(R.id.extra_point_b);
        buttonResetScores = findViewById(R.id.reset_scores);
        scoredImage = findViewById(R.id.scored_image);
    }

    //Initializes needed variables
    private void initializeVariables() {
        fadeIn = AnimationUtils.loadAnimation(
                MainActivity.this, R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(
                MainActivity.this, R.anim.fade_out);

        scoredText = getString(R.string.score_text);
        touchdownText = getString(R.string.touchdown_text);
        fieldGoalText = getString(R.string.field_goal_text);
        conversionText = getString(R.string.conversion_text);
        safetyText = getString(R.string.safety_text);
        extraPointText = getString(R.string.extra_point_text);
        teamAText = getString(R.string.team_a);
        teamBText = getString(R.string.team_b);

        teamAScore = 0;
        teamBScore = 0;
        touchdownPoints = 6;
        fieldGoalPoints = 3;
        twoPointPoints = 2;
        extraPointPoints = 1;
    }

    //Sets OnClickListener for each button
    private void onClickListeners() {
        buttonTouchdownA.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                teamAScore = scored(teamAScore, touchdownPoints, teamAText, touchdownText);
                displayScoreForTeam(teamAScore, "a");
                displayScoredImageForTeam(R.drawable.touchdown_a);
            }
        });

        buttonTouchdownB.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                teamBScore = scored(teamBScore, touchdownPoints, teamBText, touchdownText);
                displayScoreForTeam(teamBScore, "b");
                displayScoredImageForTeam(R.drawable.touchdown_b);
            }
        });

        buttonFieldGoalA.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                teamAScore = scored(teamAScore, fieldGoalPoints, teamAText, fieldGoalText);
                displayScoreForTeam(teamAScore, "a");
                displayScoredImageForTeam(R.drawable.field_goal_a);
            }
        });

        buttonFieldGoalB.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                teamBScore = scored(teamBScore, fieldGoalPoints, teamBText, fieldGoalText);
                displayScoreForTeam(teamBScore, "b");
                displayScoredImageForTeam(R.drawable.field_goal_b);
            }
        });

        buttonTwoPointConversionA.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                teamAScore = scored(teamAScore, twoPointPoints, teamAText, conversionText);
                displayScoreForTeam(teamAScore, "a");
                displayScoredImageForTeam(R.drawable.two_point_conversion_a);
            }
        });

        buttonTwoPointConversionB.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                teamBScore = scored(teamBScore, twoPointPoints, teamBText, conversionText);
                displayScoreForTeam(teamBScore, "b");
                displayScoredImageForTeam(R.drawable.two_point_conversion_b);
            }
        });

        buttonSafetyA.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                teamAScore = scored(teamAScore, twoPointPoints, teamAText, safetyText);
                displayScoreForTeam(teamAScore, "a");
                displayScoredImageForTeam(R.drawable.safety_a);
            }
        });

        buttonSafetyB.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                teamBScore = scored(teamBScore, twoPointPoints, teamBText, safetyText);
                displayScoreForTeam(teamBScore, "b");
                displayScoredImageForTeam(R.drawable.safety_b);
            }
        });

        buttonExtraPointA.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                teamAScore = scored(teamAScore, extraPointPoints, teamAText, extraPointText);
                displayScoreForTeam(teamAScore, "a");
                displayScoredImageForTeam(R.drawable.extra_point_a);
            }
        });

        buttonExtraPointB.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                teamBScore = scored(teamBScore, extraPointPoints, teamBText, extraPointText);
                displayScoreForTeam(teamBScore, "b");
                displayScoredImageForTeam(R.drawable.extra_point_b);
            }
        });

        buttonResetScores.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                resetScores();
            }
        });
    }

    //Creates listeners for the fade animations
    public void fadeListeners() {
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scoredImage.startAnimation(fadeOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scoredImage.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    //Method that updates a team's score based on which button was clicked
    //@param scoreInput Is the current score of the team using the Method
    //@param pointType Is the points to be added based on what was scored
    //@param teamText Name of the team
    //@param pointText Is the type of the goal scored, e.g. Touchdown
    private int scored(int scoreInput, int pointType, String teamText, String pointText) {
        int score = scoreInput;
        String scoreOutputText = teamText + scoredText + pointText;
        score += pointType;
        scoreText(scoreOutputText);
        return score;
    }

    //Method for displaying a Team's score
    //Uses a string to create the reference to a team's score so their score can be displayed
    //@param score Passes the current score of the team
    //@param team Passes the name of the team so the score is displayed for correct team
    public void displayScoreForTeam(int score, String team) {
        String sTeamTextView = "team_" + team + "_score_text";
        int scoreView = getResources().getIdentifier(sTeamTextView,
                "id", getApplicationContext().getPackageName());
        scoreTextForTeam = findViewById(scoreView);
        scoreTextForTeam.setText(String.valueOf(score));
    }

    //Method for the images on each score
    //@param image Is the image related to the goal scored
    private void displayScoredImageForTeam(int image) {
        scoredImage.setImageResource(image);
        scoredImage.startAnimation(fadeIn);
    }

    //Displays the score text at the bottom
    //@param  scoreText Passes the String to display what and which team accomplished
    //  a type of scoring
    public void scoreText(String scoreText) {
        TextView scoreView = findViewById(R.id.score_description_text);
        scoreView.setText(String.valueOf(scoreText));
    }

    //Will reset both scores to 0 for a new game
    public void resetScores() {
        teamAScore = 0;
        teamBScore = 0;
        displayScoreForTeam(teamAScore, "a");
        displayScoreForTeam(teamBScore, "b");
        scoreText(getString(R.string.new_game_text));
    }
}
