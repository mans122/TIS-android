package com.cookandroid.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

public class intro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Intent intent = new Intent(getApplicationContext(),index.class);
                startActivity(intent);
                finish();
            }
        };
        //핸들러 지연시간 3000 - 3초
        handler.sendEmptyMessageDelayed(0,2200);
    }
}
