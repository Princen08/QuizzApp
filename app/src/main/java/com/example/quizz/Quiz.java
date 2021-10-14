package com.example.quizz;

import android.annotation.SuppressLint;
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

public class Quiz extends AppCompatActivity {

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
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                Animation animation= AnimationUtils.loadAnimation(Quiz.this,R.anim.blinck);
                option1.startAnimation(animation);
                if(quizlist.get(currpos).getAnswer().trim().toLowerCase().equals(option1.getText().toString().trim().toLowerCase())){
                    Intent intent=new Intent(getApplicationContext(),Correct.class);
                    startActivity(intent);
                    //finish();
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
                Animation animation= AnimationUtils.loadAnimation(Quiz.this,R.anim.blinck);
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
                Animation animation= AnimationUtils.loadAnimation(Quiz.this,R.anim.blinck);
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
                Animation animation= AnimationUtils.loadAnimation(Quiz.this,R.anim.blinck);
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
                option2.setEnabled(false);
                option3.setEnabled(false);
                option1.setEnabled(false);
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
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(Quiz.this);
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
    @SuppressLint("ResourceAsColor")
    private void setDataToView(int currpos){
        if(question_attempted==quizlist.size()+1){
            showresult();
        }
        else{
            option1.setBackgroundResource(R.drawable.black_button);
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
        quizlist.add(new QuizModal("After how many Year’s FIFA World Cup is held?","2 Years","3 Years","4 Years","Every Year","4 Years"));
        quizlist.add(new QuizModal("Who won the first ICC World Cup?","India","West Indies","England","Australia","West Indies"));
        quizlist.add(new QuizModal("Who is known as the Flying Sikh?","Michael Johnson","Usain Bolt","Milkha Sing","Carl Lewis","Milkha Sing"));
        quizlist.add(new QuizModal("Who has the Highest Number of Gold Medals in Olympic History?","Larisa Latynina","Mark Spitz","Michael Phelps","Saina Nehwal","Michael Phelps"));
        quizlist.add(new QuizModal("Who is the first Indian Badminton Player to win an Olympic Medal?","Srikanth Kidambi","P.V. Sindhu","P.V. Nehwal","Saina Nehwal","Saina Nehwal"));
        quizlist.add(new QuizModal("When was the first FIFA World Cup held?","1930","1925","1934","1818","1930"));
        quizlist.add(new QuizModal("What is the National Game of the USA?","Tennis","Soccer","Baseball","Basket Ball","Baseball"));
        quizlist.add(new QuizModal("Who is given the Nickname “God of Cricket”?","Ricky Ponting","MS Dhoni","Sachin Tendulkar","Anil Kumble","Sachin Tendulkar"));
        quizlist.add(new QuizModal("What is the Full Name of the Famous Football Player Ronaldo?","Cristiano Santos Ronaldo","Cristiano Ronaldo Santos Aveiro","Cristiano Ronaldo dos Santos Aveiro","Cristiano dos Ronaldo","Cristiano Ronaldo dos Santos Aveiro"));
        quizlist.add(new QuizModal("When was Virat Kohli Selected as the Captain of the Indian Cricket Team (ODI)?","2019","2013","2017","2014","2013"));
    }
}