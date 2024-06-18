package com.example.nammakarnataka.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.nammakarnataka.MainActivity;
import com.example.nammakarnataka.R;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class NewsApp extends AppCompatActivity implements View.OnClickListener{
    RecyclerView recyclerView;
    List<Article> articleList = new ArrayList<>();
    NewsRecyclerAdapter adapter;
    LinearProgressIndicator progressIndicator;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_app);
        progressIndicator = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);
        back=findViewById(R.id.back_button_news);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);

        setRecyclerView();
        getNews("GENERAL");
        back.setOnClickListener((v->{
            finish();
        }));


    }
    void setRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new NewsRecyclerAdapter(articleList);
        recyclerView.setAdapter(adapter);
    }
    void changeInProgress(Boolean show){
        if(show){
            progressIndicator.setVisibility(View.VISIBLE);
        }
        else {
            progressIndicator.setVisibility(View.INVISIBLE);
        }

    }

    void getNews(String catagory){
        changeInProgress(true);
        NewsApiClient newsApiClient = new NewsApiClient("d6f1b6a0edc640ed8f24c74c4ca5c404");
        newsApiClient.getTopHeadlines(new TopHeadlinesRequest.Builder()
                        .category(catagory)
                        .language("en").build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                       runOnUiThread(()->{
                           changeInProgress(false);
                           articleList = response.getArticles();
                           adapter.UpdateData(articleList);
                           adapter.notifyDataSetChanged();
                        });
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.i("got failure",throwable.getMessage());

                    }
                }

        );
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String catagory = btn.getText().toString();
        getNews(catagory);



    }
}