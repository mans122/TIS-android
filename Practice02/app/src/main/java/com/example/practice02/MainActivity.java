package com.example.practice02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView portfolio = (TextView)findViewById(R.id.portfolio);
        TextView hp = (TextView)findViewById(R.id.phoneNum);
        hp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel://010-8888-8888"));
                startActivity(intent);
            }
        });
        TextView mail = (TextView)findViewById(R.id.mail);
        mail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("plain/text");
                // email setting 배열로 해놔서 복수 발송 가능
                String[] address = {"mans122@naver.com"};
                email.putExtra(Intent.EXTRA_EMAIL, address);
                email.putExtra(Intent.EXTRA_SUBJECT,"안녕하세요");
                email.putExtra(Intent.EXTRA_TEXT,"자기소개서를 보고 연락드렸습니다.\n");
                startActivity(email);
            }
        });
        portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mans122.dothome.co.kr"));
                startActivity(intent);
            }
        });
    }
}
