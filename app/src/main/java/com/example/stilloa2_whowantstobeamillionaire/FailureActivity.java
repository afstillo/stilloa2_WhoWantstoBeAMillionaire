package com.example.stilloa2_whowantstobeamillionaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stilloa2_whowantstobeamillionaire.Questions.Question1Activity;

public class FailureActivity extends AppCompatActivity {
    TextView scoreView;
    int score;

    /*
    score = current score
    scoreView = XML element for displaying the score.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure);

        //Get the score from the intent
        Intent intent = getIntent();
        score = intent.getIntExtra("SCORE", 0);

        //Associating variables with XML
        scoreView = findViewById(R.id.score);

        String text = "Earnings: $" + score;

        //Setting up the question.
        scoreView.setText(text);
    }

    //Redirect the user back to the start of the game if they wish.
    public void onClick(View V) {
        Intent intent = new Intent(this, Question1Activity.class);
        startActivity(intent);
        finish();
    }
}

