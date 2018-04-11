package com.example.krokken.reportcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    double mathGrade = 50, scienceGrade = 62, historyGrade = 79, languageGrade = 81, computerGrade = 92, gymGrade = 100;
    String studentName;
    EditText nameInput;
    TextView fullReportCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameInput = findViewById(R.id.name_input);
        fullReportCard = findViewById(R.id.report_card);

    }

    public void getName(){
        studentName = nameInput.getText().toString();
    }

    public void getGrades(){

    }

    public void getReportCard(View view){
        getName();
        //getGrades();
        ReportCard reportCard = new ReportCard(mathGrade, scienceGrade, historyGrade, languageGrade, computerGrade, gymGrade, studentName);
        fullReportCard.setText(reportCard.toString());
    }

}
