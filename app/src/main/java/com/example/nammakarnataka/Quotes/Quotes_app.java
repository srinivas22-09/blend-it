package com.example.nammakarnataka.Quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nammakarnataka.MainActivity;
import com.example.nammakarnataka.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quotes_app extends AppCompatActivity {
    private List<Integer> listtquotes=new ArrayList<>();
    private int quoteNumber=0;
    private String mainTest="";
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_app);
        listtquotes.add(R.string.quote1);
        listtquotes.add(R.string.quote2);
        listtquotes.add(R.string.quote3);
        listtquotes.add(R.string.quote4);
        listtquotes.add(R.string.quote5);
        listtquotes.add(R.string.quote6);
        listtquotes.add(R.string.quote7);
        listtquotes.add(R.string.quote8);
        listtquotes.add(R.string.quote9);
        listtquotes.add(R.string.quote10);
        listtquotes.add(R.string.quote11);
        listtquotes.add(R.string.quote12);
        listtquotes.add(R.string.quote13);
        listtquotes.add(R.string.quote14);
        listtquotes.add(R.string.quote15);
        listtquotes.add(R.string.quote16);

        quoteOnApploaded();
        clickNewQuote();
        back=findViewById(R.id.back_button_quotes);
        back.setOnClickListener((v)->{

            finish();

        });


    }
    private void clickNewQuote(){
        Button fab_newQuote=findViewById(R.id.next);
        fab_newQuote.setOnClickListener(view -> {

            fab_newQuote.setEnabled(false);
            if(quoteNumber==listtquotes.size()){
                quoteOnApploaded();
            }
            else {
                typeText(getString(listtquotes.get(quoteNumber)));
                ++quoteNumber;
            }
        });

    }
    private void quoteOnApploaded(){
        Button fab_newQuote=findViewById(R.id.next);
        fab_newQuote.setEnabled(false);
        quoteNumber=0;
        Collections.shuffle(listtquotes);
        typeText(getString(listtquotes.get(quoteNumber)));
        ++quoteNumber;
    }
    private void typeText(String text){
        mainTest="";
        long textDelay=50L;
        new Thread(()->{
            StringBuilder sb = new StringBuilder();
            String updatedText="";
            for (int i = 0; i < text.length(); i++) {
                mainTest=sb.append(updatedText+text.charAt(i)).toString();
                try {
                    Thread.sleep(textDelay);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }

            } }).start();
        Handler handler=new Handler();
        Log.d("Main","Handler started");
        Runnable runnable = new Runnable(){
            @SuppressLint("setTextI18n")

            @Override
            public void run() {
                TextView tv_text = findViewById(R.id.text_quote);
                tv_text.setText(mainTest+"|");
                handler.postDelayed(this,10);
                if (text.equals(mainTest)){
                    handler.removeCallbacks(this);
                    tv_text.setText(mainTest);
                    Button fb_newQuotes=findViewById(R.id.next);
                    fb_newQuotes.setEnabled(true);
                }
            }
        };handler.postDelayed(runnable,0);


    }

}