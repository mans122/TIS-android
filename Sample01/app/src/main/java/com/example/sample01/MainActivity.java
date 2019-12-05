package com.example.sample01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button)findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View v){
            Toast.makeText(getApplicationContext(), "안녕하세요" ,Toast.LENGTH_LONG).show();
        }
        });

        Button btnNate = (Button)findViewById(R.id.btnNate);
        btnNate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nate.com"));
                startActivity(intent);
            }
        });

        Button btn911 = (Button)findViewById(R.id.btn911);
        btn911.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel://119"));
                startActivity(intent);
            }
        }));

    }
}
