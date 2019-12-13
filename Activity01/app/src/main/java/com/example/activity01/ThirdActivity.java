package com.example.activity01;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends  AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        final int result;
        Intent intent =getIntent();
        intent.getIntExtra("age",0);
        intent.getStringExtra("name");
        Log.d("===name===",intent.getStringExtra("name"));
        Log.d("===age===",Integer.toString(intent.getIntExtra("age",0)));
        result = intent.getIntExtra("age",0)+10;
//        Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
//        intent2.putExtra("message",Integer.toString(result));
//        Log.d("message",Integer.toString(result));
//        setResult(RESULT_OK,intent2);
//        finish();

        Button btnMain=null;
        btnMain=(Button)findViewById(R.id.btnMain);
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
                intent2.putExtra("message",Integer.toString(result));
                Log.d("message",Integer.toString(result));
                setResult(RESULT_OK,intent2);
                finish(); //현재 액티비티 종료
            }
        });

    }
}
