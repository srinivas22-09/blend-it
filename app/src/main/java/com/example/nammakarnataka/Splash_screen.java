package com.example.nammakarnataka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class Splash_screen extends AppCompatActivity {
    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                Boolean isFirst=onBoardingScreen.getBoolean("firstTime",true);
                if(isFirst){
                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();


                }
                else {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();

                }


            }
        },4000);
    }
}