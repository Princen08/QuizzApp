package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button btnlogin,btnsignup;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=(EditText)findViewById(R.id.username1);
        password=(EditText)findViewById(R.id.password1);
        btnlogin=(Button)findViewById(R.id.btnsignin1);
        btnsignup=(Button)findViewById(R.id.btnsignup1);
        DB=new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  String user=username.getText().toString();
                  String pass=password.getText().toString();

                  if(user.equals("") || pass.equals("")){
                      if(user.equals("")){
                          Toast.makeText(Login.this,"Please Enter Username",Toast.LENGTH_SHORT).show();
                      }
                      else{
                          Toast.makeText(Login.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                      }
                  }
                  else{
                      Boolean checkuserpass=DB.checkusernamepassword(user,pass);
                      if(checkuserpass==true){
                          Toast.makeText(Login.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                          Intent intent=new Intent(getApplicationContext(),menu.class);
                          startActivity(intent);
                          finish();
                      }
                      else{
                          Toast.makeText(Login.this,"Login Failed,Please Registration",Toast.LENGTH_SHORT).show();
                      }
                  }
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}