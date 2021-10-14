package com.example.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class quiz3 extends AppCompatActivity {

    TextView questionnumber,question;
    Button option1,option2,option3,option4;
    ArrayList<QuizModal>quizlist;
    Random random;
    int curr_score=0;
    int question_attempted=1,currpos;
    private Object LinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        questionnumber=(TextView)findViewById(R.id.NumberofQue);
        question=(TextView)findViewById(R.id.Question);
        option1=(Button)findViewById(R.id.option1);
        option2=(Button)findViewById(R.id.option2);
        option3=(Button)findViewById(R.id.option3);
        option4=(Button)findViewById(R.id.option4);
        quizlist=new ArrayList<>();

        getQuestion(quizlist);
        currpos=0;
        Collections.shuffle(quizlist,new Random());
        setDataToView(currpos);
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(quiz3.this,R.anim.blinck);
                option1.startAnimation(animation);
                if(quizlist.get(currpos).getAnswer().trim().toLowerCase().equals(option1.getText().toString().trim().toLowerCase())){
                    Intent intent=new Intent(getApplicationContext(),Correct.class);
                    startActivity(intent);
                    curr_score++;
                }
                else{
                    Intent intent=new Intent(getApplicationContext(),Incorrect.class);
                    startActivity(intent);
                }

                question_attempted++;
                currpos++;
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setDataToView(currpos);
                    }
                }, 2000);
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(quiz3.this,R.anim.blinck);
                option2.startAnimation(animation);
                if(quizlist.get(currpos).getAnswer().trim().toLowerCase().equals(option2.getText().toString().trim().toLowerCase())){
                    curr_score++;
                    Intent intent=new Intent(getApplicationContext(),Correct.class);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(getApplicationContext(),Incorrect.class);
                    startActivity(intent);
                }

                question_attempted++;
                currpos++;
                option1.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setDataToView(currpos);
                    }
                }, 2000);
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(quiz3.this,R.anim.blinck);
                option3.startAnimation(animation);
                if(quizlist.get(currpos).getAnswer().trim().toLowerCase().equals(option3.getText().toString().trim().toLowerCase())){
                    curr_score++;
                    Intent intent=new Intent(getApplicationContext(),Correct.class);
                    startActivity(intent);

                }
                else{
                    Intent intent=new Intent(getApplicationContext(),Incorrect.class);
                    startActivity(intent);
                }
                question_attempted++;
                currpos++;
                option2.setEnabled(false);
                option1.setEnabled(false);
                option4.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setDataToView(currpos);
                    }
                }, 2000);
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option2.setEnabled(false);
                option3.setEnabled(false);
                option1.setEnabled(false);
                Animation animation= AnimationUtils.loadAnimation(quiz3.this,R.anim.blinck);
                option4.startAnimation(animation);
                if(quizlist.get(currpos).getAnswer().trim().toLowerCase().equals(option4.getText().toString().trim().toLowerCase())){
                    curr_score++;
                    Intent intent=new Intent(getApplicationContext(),Correct.class);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(getApplicationContext(),Incorrect.class);
                    startActivity(intent);
                }

                question_attempted++;
                currpos++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setDataToView(currpos);
                    }
                }, 2000);
            }
        });
    }

    private void showresult(){
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(quiz3.this);
        View bottomSheetView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_score,(LinearLayout)findViewById(R.id.answer));
        TextView score_dis=bottomSheetView.findViewById(R.id.score);
        Button restart=bottomSheetView.findViewById(R.id.restart);
        score_dis.setText("Score :"+ curr_score +"/"+quizlist.size());
        Button menu=bottomSheetView.findViewById(R.id.goback);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_attempted=1;
                Collections.shuffle(quizlist,new Random());
                currpos=0;
                setDataToView(currpos);
                curr_score=0;
                question_attempted=1;
                bottomSheetDialog.dismiss();
            }
        });
        final boolean[] ok = {false};
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),menu.class);
                ok[0] =true;
                startActivity(intent);
                finish();
            }
        });
        if(ok[0] ==true){
            finish();
        }


        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }
    private void setDataToView(int currpos){
        if(question_attempted==quizlist.size()+1){
            showresult();
        }
        else{
            question.setText(quizlist.get(currpos).getQuestion());
            option1.setText(quizlist.get(currpos).getOption1());
            option2.setText(quizlist.get(currpos).getOption2());
            option3.setText(quizlist.get(currpos).getOption3());
            option4.setText(quizlist.get(currpos).getOption4());
            option1.setEnabled(true);
            option2.setEnabled(true);
            option3.setEnabled(true);
            option4.setEnabled(true);
            option1.clearAnimation();
            option2.clearAnimation();
            option3.clearAnimation();
            option4.clearAnimation();
        }
        questionnumber.setText("Question Attempted :"+Math.min(quizlist.size(),question_attempted)+"/"+quizlist.size());
    }

    private void getQuestion(ArrayList<QuizModal> quizlist) {
        quizlist.add(new QuizModal("What is the average of first 150 natural numbers?","70","70.5","75","75.5","75.5"));
        quizlist.add(new QuizModal("What is the rate of discount if a car which price was $4,000 was sold for $3,200 ?","14%","16%","18%","20%","20%"));
        quizlist.add(new QuizModal("What comes next in the sequence: 1, 3, 11, 43, ____ ?","161","171","181","191","171"));
        quizlist.add(new QuizModal("In a class of 40 students 20% are girls. How many boys are there in the class?","26","32","24","28","32"));
        quizlist.add(new QuizModal("If the cost of 12 eggs is $40. What will be the cost of 3 eggs?","10","8","12","14","10"));
        quizlist.add(new QuizModal("If the sum of two numbers is 16, and their product is 63. Then the larger number is","9","8","12","7","9"));
        quizlist.add(new QuizModal("What is the sum of the first 12 numbers of the series: 2 , 6, 10, 14, …. ?","288","280","286","290","288"));
        quizlist.add(new QuizModal("In a class of 60 students the ratio of boys to girls is 3:2. How many girls are there in the class?","23","22","24","20","24"));
        quizlist.add(new QuizModal("If 75 percent of a number is y. And, 80 percent of y is 60, then what is the number??","100","120","80","90","100"));
        quizlist.add(new QuizModal("What is the average of the numbers: 2, 5, 0, 10, 10, 12, 8 and 9?","7","8","9","7.5","7"));

    }
}