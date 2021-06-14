package com.example.android.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridLayout gridLayout;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView question;
    TextView timer;
    TextView grade;
    TextView status;
    Button playAgain;
    ArrayList<Integer> answers;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    public void makeQuestion(){
        answers = new ArrayList<>();
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        question.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                    answers.add(a + b);
            } else {
                int wrong = rand.nextInt(41);
                while (wrong == a + b) {
                    wrong = rand.nextInt(41);
                    }
                    answers.add(wrong);
                }
            }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.gridLayout);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        question = findViewById(R.id.question);
        playAgain = findViewById(R.id.playAgain);
        timer = findViewById(R.id.timer);
        grade = findViewById(R.id.grade);
        status = findViewById(R.id.status);
        makeQuestion();


    }
    public void go(View view){
        gridLayout.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        grade.setVisibility(View.VISIBLE);
        view.setVisibility(View.GONE);
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+"S");
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                status.setText("Done");
            }
        }.start();
    }
    public void answerIn(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag())){
            status.setText("Correct :)");
            score++;
        }
        else
        {
            status.setText("Wrong :(");
        }
        numberOfQuestions++;
        grade.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        status.setVisibility(View.VISIBLE);
        makeQuestion();
    }
    public void playAgain(View view){
        view.setVisibility(View.GONE);
        status.setVisibility(View.GONE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        grade.setText("0/0");
        timer.setText("30s");
        score = 0;
        numberOfQuestions = 0;
        makeQuestion();
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+"S");
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                status.setText("Done");
            }
        }.start();
    }
}