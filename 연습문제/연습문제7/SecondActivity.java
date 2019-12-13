package com.example.sqlite02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    TextView textName,textNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        Toast.makeText(getApplicationContext(),intent.getStringExtra("gName"),Toast.LENGTH_SHORT).show();

        //gName으로 검색해서 textView에 출력

        textName=(TextView)findViewById(R.id.textName);
        textNum=(TextView)findViewById(R.id.textNum);

        textName.setText(intent.getStringExtra("gName"));
        textNum.setText(intent.getStringExtra("gNum"));

    }
}
