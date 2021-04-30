package com.example.stilloa2_whowantstobeamillionaire.Questions;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stilloa2_whowantstobeamillionaire.FailureActivity;
import com.example.stilloa2_whowantstobeamillionaire.R;

public class Question6Activity extends AppCompatActivity {
    TextView questionView, scoreView;
    RadioButton rb1, rb2, rb3, rb4;
    RadioGroup rbGroup;
    Button confirm;
    int score;
    int score_fall_value;

    /*
    question = question received.
    score = current score
    score_fall_value = What the user will earn should they get the question wrong.
    All others are associated with XML elements.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();

        score = intent.getIntExtra("SCORE", 0);
        score_fall_value = 1000;

        //Associating variables with XML
        questionView = findViewById(R.id.question);
        scoreView = findViewById(R.id.score);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio1);
        rb2 = findViewById(R.id.radio2);
        rb3 = findViewById(R.id.radio3);
        rb4 = findViewById(R.id.radio4);
        confirm = findViewById(R.id.confirm);

        String text = "Earnings: $" + score;

        //Setting up the question.
        scoreView.setText(text);
        questionView.setText(R.string.q6);
        rb1.setText(R.string.q6c1);
        rb2.setText(R.string.q6c2);
        rb3.setText(R.string.q6c3);
        rb4.setText(R.string.q6c4);
    }

    public void onClick(View V) {
        //if no radiobutton is checked, return a toast.
        if (!rb1.isChecked() && !rb2.isChecked() && !rb3.isChecked() &&
                !rb4.isChecked()){
            Toast toast = Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT);
            toast.show();
        } //check if the user has answered correctly.
        else if (rb2.isChecked()){
            score = 2000;

            Toast toast = Toast.makeText(this, "Correct! You've earned $" + score + "!", Toast.LENGTH_SHORT);
            toast.show();

            Intent intent = new Intent(this, Question7Activity.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
            finish();
        } //If they have not, drop their winnings to the fall value and go to the failure activity.
        else{
            Toast toast = Toast.makeText(this,
                    "Incorrect! Your winnings have dropped to $" + score_fall_value + ".", Toast.LENGTH_SHORT);
            toast.show();
            score = score_fall_value;

            Intent intent = new Intent(this, FailureActivity.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
            finish();
        }
    }

    //Clicking the TextView at the bottom Allows the user to quit with their current winnings.
    public void onClick_give_up(View v){
        //AlertDialog creation.
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogStyle);
        builder.setMessage("Are you sure you want to give up?")
                .setCancelable(false)
                .setPositiveButton("I'm sure.", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Create an intent
                        Intent intent = new Intent(getBaseContext(), FailureActivity.class);
                        intent.putExtra("SCORE", score);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Wait...",
                        (dialog, id) -> dialog.cancel());

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
