package com.example.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class Loding1 extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loding1);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();}
        lottieAnimationView=findViewById(R.id.loding1);

        lottieAnimationView.animate().setDuration(2500).setStartDelay(2500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),Quiz1.class);
                startActivity(intent);
            }
        }, 4500);

    }
}