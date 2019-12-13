package com.example.activity01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnSecond = null;
    Button btnThird = null;
    TextView textView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        textView =(TextView)findViewById(R.id.textView2);
        textView.setText(intent.getStringExtra("message"));
        btnSecond=(Button)findViewById(R.id.btnSecond);
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("age",20);
                intent.putExtra("name","홍길덩");
                startActivity(intent);
            }
        });

        btnThird=(Button)findViewById(R.id.btnThird);
        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ThirdActivity.class);
                intent.putExtra("age",20);
                intent.putExtra("name","홍길덩");
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            textView =(TextView)findViewById(R.id.textView2);
            Log.d("===Result===",data.getStringExtra("message"));
            textView.setText(data.getStringExtra("message"));
        }
    }
}
