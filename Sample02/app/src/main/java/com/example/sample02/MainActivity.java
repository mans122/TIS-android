package com.example.sample02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.YELLOW;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    final Button btn = (Button) findViewById(R.id.btn);
    btn.setBackgroundColor(GREEN);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btn.setBackgroundColor(YELLOW);
        }
    });


    }
}
