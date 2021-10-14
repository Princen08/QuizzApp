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

public class quiz2 extends AppCompatActivity {

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
                Animation animation= AnimationUtils.loadAnimation(quiz2.this,R.anim.blinck);
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
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
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
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(quiz2.this,R.anim.blinck);
                option2.startAnimation(animation);
                if(quizlist.get(currpos).getAnswer().trim().toLowerCase().equals(option2.getText().toString().trim().toLowerCase())){
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
                Animation animation= AnimationUtils.loadAnimation(quiz2.this,R.anim.blinck);
                option3.startAnimation(animation);
                if(quizlist.get(currpos).getAnswer().trim().toLowerCase().equals(option3.getText().toString().trim().toLowerCase())){
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
                option1.setEnabled(false);
                option2.setEnabled(false);
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
                Animation animation= AnimationUtils.loadAnimation(quiz2.this,R.anim.blinck);
                option4.startAnimation(animation);
                if(quizlist.get(currpos).getAnswer().trim().toLowerCase().equals(option4.getText().toString().trim().toLowerCase())){
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
                option1.setEnabled(false);
                option3.setEnabled(false);
                option2.setEnabled(false);
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
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(quiz2.this);
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
        quizlist.add(new QuizModal("Who invented telescope?","Galileo Galilei","Hans Lipperhey","Copernicus","None of the above","Galileo Galilei"));
        quizlist.add(new QuizModal("Which consists of two plates separated by a dielectric and can store a charge?","Inductor","Capacitor","Transistor","Relay","Capacitor"));
        quizlist.add(new QuizModal("What are three types of lasers?"," Gas, metal vapor, rock","Pointer, diode, CD","Diode, inverted, pointer","Gas, solid state, diode","Gas, solid state, diode"));
        quizlist.add(new QuizModal("Who invented Dynamite?","Sir Alexander Graham Bell","Benjamin Franklin","Thomas Alva Edison","Alfred B. Nobel","Alfred B. Nobel"));
        quizlist.add(new QuizModal("Which of the following gas is reduced in the reduction process?","Oxygen","Helium","Carbon","Hydrogen","Hydrogen"));
        quizlist.add(new QuizModal("Which of the following compound is mainly used in hand sanitizer?","Aldehyde","Acetic acid","Alcohol","Ketone8","Alcohol"));
        quizlist.add(new QuizModal("What is the unit of wavelength?","Hertz","Diopter","Faraday","Meter","Meter"));
        quizlist.add(new QuizModal("What is the other name of Newton's first law of motion?","Action-reaction","Change in momentum","Law of inertia","Constant momentum","Law of inertia"));
        quizlist.add(new QuizModal("What is the PH range of acids?","0 - 7","1 - 7","7 - 14","7 - 15","0 - 7"));
        quizlist.add(new QuizModal("Who discovered the x-rays?","Maxwell","Wilhelm roentgen","Faraday","Hertz","Wilhelm roentgen"));
    }
}