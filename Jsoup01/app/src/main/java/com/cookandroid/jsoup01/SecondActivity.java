package com.cookandroid.jsoup01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SecondActivity extends AppCompatActivity {
    WebView web;
    class CookWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        web=(WebView)findViewById(R.id.webView11);
        web.setWebViewClient(new CookWebViewClient());
        WebSettings webSet=web.getSettings(); //웹뷰의 환경설정에 필요한 객체
        webSet.setBuiltInZoomControls(true); //확대 축소버튼
        webSet.setJavaScriptEnabled(true); //자바스크립트 사용 허용
        web.loadUrl("https://news.naver.com"+intent.getStringExtra("url"));
    }
}
