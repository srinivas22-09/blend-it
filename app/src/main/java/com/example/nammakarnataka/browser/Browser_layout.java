package com.example.nammakarnataka.browser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nammakarnataka.R;

public class Browser_layout extends AppCompatActivity {

    EditText url_input;
    ImageView clear_url;
    WebView webView;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_browser_layout);

        url_input=findViewById(R.id.url_input);
        clear_url=findViewById(R.id.clear_icon);
        webView=findViewById(R.id.web_view);
        progressBar=findViewById(R.id.progress_bar);


        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        findViewById(R.id.back_icon).setOnClickListener((v)->{
            finish();
        });


        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });
        loadMyUrl("google.com");
        url_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i==EditorInfo.IME_ACTION_GO||i==EditorInfo.IME_ACTION_DONE){
                    InputMethodManager imm =(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    loadMyUrl(url_input.getText().toString());
                    imm.hideSoftInputFromWindow(url_input.getWindowToken(),0);
                    return true;
                }
                return false;
            }
        });
        clear_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url_input.setText("");
            }
        });

    }

    void loadMyUrl(String url){
        Boolean match_url= Patterns.WEB_URL.matcher(url).matches();
        if(match_url){
            webView.loadUrl(url);
        }
        else {
            webView.loadUrl("www.google.com/search?q="+url);
        }
    }


    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();

        }else{ super.onBackPressed();}


    }

    class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
   
}