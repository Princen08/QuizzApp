package com.example.quizz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Quiz1 extends AppCompatActivity {

    TextView questionnumber,question;
    Button option1,option2,option3,option4;
    ArrayList<QuizModal>quizlist;
    Random random;
    LottieAnimationView lottieAnimationView;
    int curr_score=0;
    int question_attempted=1,currpos;
    private Object LinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
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
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(Quiz1.this,R.anim.blinck);
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
                Animation animation= AnimationUtils.loadAnimation(Quiz1.this,R.anim.blinck);
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
                Animation animation= AnimationUtils.loadAnimation(Quiz1.this,R.anim.blinck);
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
                Animation animation= AnimationUtils.loadAnimation(Quiz1.this,R.anim.blinck);
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
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);

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

        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(Quiz1.this);
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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
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
        quizlist.add(new QuizModal("Who among the following had drafted the “Declaration of Independence” pledge in 1930?","Motilal Nehru","Jawahar Lal Nehru","Mahatma Gandhi","C R Das","Mahatma Gandhi"));
        quizlist.add(new QuizModal("The national anthem of India ‘Jana Gana Mana’ was first sung at __?","Calcutta, 1911","Calcutta, 1912","Delhi, 1911","Mumbai, 1912","Calcutta, 1911"));
        quizlist.add(new QuizModal("In which country Indian Independence Committee was formed during British Era?","France","UK","Germany","USA","Germany"));
        quizlist.add(new QuizModal("Which of the following was the court poet of Harsha?","Kalidasa","Banabhatta","Harishena","Bhasa","Banabhatta"));
        quizlist.add(new QuizModal("Where was the electricity supply first introduced in India "," Mumbai","Dehradun","Darjeeling","Chennai","Darjeeling"));
        quizlist.add(new QuizModal("Which of the following assumed the title of ‘Siladitya’?","Harsha Vardhan","Rajya Vardhana","Prabhakar Vardhana","None of the above","Harsha Vardhan"));
        quizlist.add(new QuizModal("Which one is the longest epic of the world?","Ramayana","Ramcharitmanas","Mahabharata","Hanuman Chalisa","Mahabharata"));
        quizlist.add(new QuizModal("Which of the following kings is considered as the founder of the Pandya empire?","Kadungon","Varguna I","Srimar Srivallabha","Maravarman Rajasimha I","Kadungon"));
        quizlist.add(new QuizModal("Which of the following gives earliest evidence of settled life?","Mehrgarh","Mohejo Dero","Harappa","Kalibangan","Mehrgarh"));
        quizlist.add(new QuizModal("Who among the following was nominated as first Satyagrahi by Mahatma Gandhi for the Individual Satyagarha of 1940?","Jawarharlal Nehru","Vinoba Bhave","Lal Bahadur Shastri","S. Satyamurti","Vinoba Bhave"));
    }
}