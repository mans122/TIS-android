package com.example.webview01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
//    EditText edtUrl;
//    Button btnGo,btnBack;
    WebView web;
    //링크 클릭시 내장브라우저가 호출되는 것을 막아준다
    // 메뉴 > Code > Override Methods > 원하는 메소드 클릭
    class CookWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        edtUrl=(EditText)findViewById(R.id.edtUrl);
        web=(WebView)findViewById(R.id.webView1);
//        btnGo=(Button)findViewById(R.id.btnGo);
//        btnBack=(Button)findViewById(R.id.btnBack);
        web.setWebViewClient(new CookWebViewClient()); // 링크 클릭시 내장브라우저 사용 안함.

        WebSettings webSet=web.getSettings(); //웹뷰의 환경설정에 필요한 객체
        webSet.setBuiltInZoomControls(true); //확대 축소버튼
        webSet.setJavaScriptEnabled(true); //자바스크립트 사용 허용
        web.loadUrl("http://mans122.dothome.co.kr/movie");
//        btnGo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                web.loadUrl(edtUrl.getText().toString()); //이동 버튼 클릭 시 url값을 web에 전달
//            }
//        });
//
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                web.goBack(); // 이전페이지로 이동하는 기능
//           }
//        });

    }
}
