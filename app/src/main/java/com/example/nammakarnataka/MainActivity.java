package com.example.nammakarnataka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.nammakarnataka.ChatComrade.Chat_comrade_app;
import com.example.nammakarnataka.Location.Location_MainActivity;
import com.example.nammakarnataka.NewQuiz.NewQuiz;
//import com.example.nammakarnataka.QuizApp.Quiz_app_layout;
import com.example.nammakarnataka.Quotes.Quotes_app;
import com.example.nammakarnataka.Tictactoe.Tictok;
import com.example.nammakarnataka.browser.Browser_layout;
import com.example.nammakarnataka.news.NewsApp;
import com.example.nammakarnataka.todo.MainActivity_todo;
import com.example.nammakarnataka.video_zoom.Zoom_video;

public class MainActivity extends AppCompatActivity {
    TextView text;
    String texxt[]= {"Ticktacktoe_","Location_","News_","Browse_","Conference_","Quotes_","Notes_","Quiz_"};
    int i=0,j=0;
    String color_code[]={"#d4cbe5","#7edccf","#f7c59f","#FFBB86FC","#f2b2bd","#cfe5ff","#a6daae","#f8d8d8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        //loop display text
        text=findViewById(R.id.display_text);
        loodText(texxt[j]);





        //onclick listners for 8 buttons
        findViewById(R.id.browser_layout).setOnClickListener((v)->{
            startActivity(new Intent(getApplicationContext(), Browser_layout.class));
        });

        findViewById(R.id.news_app).setOnClickListener((v)->{
            startActivity(new Intent(getApplicationContext(),NewsApp.class));
        });
        findViewById(R.id.Chat_app_layout).setOnClickListener((v)->{
            startActivity(new Intent(getApplicationContext(), Location_MainActivity.class));
        });

        findViewById(R.id.Tictoktoe_app).setOnClickListener((v)->{
            startActivity(new Intent(getApplicationContext(), Tictok.class));
        });

        findViewById(R.id.video_call_app).setOnClickListener((v)->{
            startActivity(new Intent(getApplicationContext(), Zoom_video.class));
        });

        findViewById(R.id.Quotes_app_layout).setOnClickListener((v)->{
            startActivity(new Intent(getApplicationContext(), Quotes_app.class));
        });
        findViewById(R.id.notes_app).setOnClickListener((v)->{
            startActivity(new Intent(getApplicationContext(), MainActivity_todo.class));
        });
        findViewById(R.id.quiz_app).setOnClickListener((v)->{
            startActivity(new Intent(getApplicationContext(), NewQuiz.class));
        });

        findViewById(R.id.card1).setOnClickListener((v)->{
            startActivity(new Intent(getApplicationContext(), Tictok.class));

        });

        findViewById(R.id.card2).setOnClickListener((v -> {
            startActivity(new Intent(getApplicationContext(), Location_MainActivity.class));
        }));
        findViewById(R.id.card3).setOnClickListener((v -> {
            startActivity(new Intent(getApplicationContext(), NewsApp.class));
        }));
        findViewById(R.id.card4).setOnClickListener((v -> {
            startActivity(new Intent(getApplicationContext(), Browser_layout.class));
        }));
        findViewById(R.id.card5).setOnClickListener((v -> {
            startActivity(new Intent(getApplicationContext(), Zoom_video.class));
        }));
        findViewById(R.id.card6).setOnClickListener((v -> {
            startActivity(new Intent(getApplicationContext(), Quotes_app.class));
        }));
        findViewById(R.id.card7).setOnClickListener((v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity_todo.class));
        }));
        findViewById(R.id.card8).setOnClickListener((v -> {
            startActivity(new Intent(getApplicationContext(), NewQuiz.class));
        }));


    }
    void loodText(String passed_text)
    {
        if (i<passed_text.length()){
            String fetched_string="<font color='"+color_code[j]+"'>"+passed_text.substring(0,i)+"</font>";
            text.setText(Html.fromHtml(fetched_string));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    i++;
                    loodText(passed_text);
                }
            },300);
        }
        else {
            j++;
            if(j==text.length()){
                i=0;
                j=0;
                loodText(texxt[j]);

            }
            else {
                i=0;

                loodText(texxt[j]);
            }
        }

    }
}