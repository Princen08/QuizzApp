package com.example.quizz;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class Incorrect extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incorrect);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();}
        lottieAnimationView=findViewById(R.id.incor);

        lottieAnimationView.animate().setDuration(2500).setStartDelay(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2500);
    }
}