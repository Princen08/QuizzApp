package com.example.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class menu extends AppCompatActivity {

    Button sport,sci,maths,history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        sport=(Button)findViewById(R.id.sport);
        history=(Button)findViewById(R.id.button4);
        sci=(Button)findViewById(R.id.button5);
        maths=(Button)findViewById(R.id.button6);

        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Loding.class);
                startActivity(i);
                finish();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Loding1.class);
                startActivity(i);
                finish();
            }
        });
        sci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Loding2.class);
                startActivity(i);
                finish();
            }
        });

        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Loding3.class);
                startActivity(i);
                finish();
            }
        });

    }
}