package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton,button0,button1,button2,button3,playAgain;
    TextView sumTextView, resultTextView,scoreTextView,timerTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    ConstraintLayout gameLayout;
    int ritAns;
    int score = 0;
    int noOfQ = 0;
    Boolean gameOn = true;

    public void start(View view){
        playAgain(timerTextView);
        gameLayout.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);
    }

    public void playAgain(View view){
        gameOn = true;
        score = 0;
        noOfQ = 0;
        resultTextView.setVisibility(View.INVISIBLE);
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+ "/" + Integer.toString(noOfQ));
        newQ();
        playAgain.setVisibility(View.INVISIBLE);

        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000 + "s"));
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                gameOn = false;
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public void newQ(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        ritAns = rand.nextInt(4);

        answers.clear();

        for (int i = 0; i< 4; i++){
            if(i == ritAns)
                answers.add(a+b);
            else {
                int wrongAns = rand.nextInt(42);
                while(wrongAns == ritAns) {
                    wrongAns = rand.nextInt(42);
                }
                answers.add(wrongAns);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view){
        if(gameOn) {
            if (Integer.toString(ritAns).equals(view.getTag().toString())) {
                resultTextView.setText("Correct!!");
                score++;
            } else {
                resultTextView.setText("Wrong :(");
            }
            resultTextView.setVisibility(View.VISIBLE);
            noOfQ++;
            scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQ));
            newQ();
        }else return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        playAgain = findViewById(R.id.playAgainButton);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);
        sumTextView = findViewById(R.id.sumTextView);
        timerTextView = findViewById(R.id.timerTextView);
        gameLayout = findViewById(R.id.gameLayout);

        goButton = findViewById(R.id.gobutton);

        goButton.setVisibility(View.VISIBLE);

    }
}
