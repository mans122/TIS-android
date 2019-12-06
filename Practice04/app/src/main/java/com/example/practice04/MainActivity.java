package com.example.practice04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtUrl;
    Button btnGo;
    WebView web,web2;
    class CookWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUrl=(EditText)findViewById(R.id.edtUrl);
        web=(WebView)findViewById(R.id.webView1);
        web2=(WebView)findViewById(R.id.webView2);
        btnGo=(Button)findViewById(R.id.btnGo);

        web.setWebViewClient(new CookWebViewClient()); // 링크 클릭시 내장브라우저 사용 안함.
        web2.setWebViewClient(new CookWebViewClient()); // 링크 클릭시 내장브라우저 사용 안함.

        WebSettings webSet=web.getSettings(); //웹뷰의 환경설정에 필요한 객체
        webSet.setBuiltInZoomControls(true); //확대 축소버튼
        webSet.setJavaScriptEnabled(true); //자바스크립트 사용 허용

        WebSettings webSet2=web2.getSettings(); //웹뷰의 환경설정에 필요한 객체
        webSet2.setBuiltInZoomControls(true); //확대 축소버튼
        webSet2.setJavaScriptEnabled(true); //자바스크립트 사용 허용

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.loadUrl("http://google.com/search?hl=ko&souce=hp&ei=EOi3XfbbNorth-AaPtqmwDw&q="+edtUrl.getText().toString()); //이동 버튼 클릭 시 url값을 web에 전달
                web2.loadUrl("https://search.naver.com/search.naver?sm=top_hty&fbm=0&ie=utf8&query="+edtUrl.getText().toString()); //이동 버튼 클릭 시 url값을 web에 전달
            }
        });

    }
}
