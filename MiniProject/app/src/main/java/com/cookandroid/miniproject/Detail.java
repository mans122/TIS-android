package com.cookandroid.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends AppCompatActivity {
    EditText textTitle,textWeather,textContent;
    TextView textDate;
    Button btnList,btnUpdate;
    CalendarView cv = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        Intent intent = getIntent();
        textTitle = (EditText)findViewById(R.id.edtTitle);
        textDate = (TextView) findViewById(R.id.dateView);
        textWeather = (EditText)findViewById(R.id.edtWeather);
        textContent = (EditText)findViewById(R.id.edtContent);
        btnUpdate =(Button)findViewById(R.id.btnUpdate);
        btnList=(Button)findViewById(R.id.btnList);
        cv=(CalendarView)findViewById(R.id.calendarView);
        cv.setVisibility(View.GONE);
        textTitle.setText(intent.getStringExtra("Title"));
        textDate.setText(intent.getStringExtra("Date"));
        textWeather.setText(intent.getStringExtra("Weather"));
        textContent.setText(intent.getStringExtra("Content"));

        textTitle.setFocusable(false);
        textTitle.setClickable(false);
        textDate.setFocusable(false);
        textDate.setClickable(false);
        textWeather.setFocusable(false);
        textWeather.setClickable(false);
        textContent.setFocusable(false);
        textContent.setClickable(false);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),index.class);
                startActivity(intent);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnUpdate.getText().equals("수정")){
                    Toast.makeText(getApplicationContext(),"수정",Toast.LENGTH_SHORT).show();
                    textTitle.setFocusable(true);
                    textTitle.setClickable(true);
                    textDate.setFocusable(true);
                    textDate.setClickable(true);
                    textWeather.setFocusable(true);
                    textWeather.setClickable(true);
                    textContent.setFocusable(true);
                    textContent.setClickable(true);
                    btnUpdate.setText("등록");
                }else{
                    Toast.makeText(getApplicationContext(),"등록",Toast.LENGTH_SHORT).show();
                    btnUpdate.setText("수정");
                }

//                    DBManager dbManager = DBManager.getInstance(getApplicationContext());
//                    SQLiteDatabase db= dbManager.getWritableDatabase();
//                    db.execSQL("update Diary set Date='"+textDate.getText().toString()+"', Content='"+textContent.getText().toString()+"' where Title='"+textTitle.getText().toString()+"'");
//                    db.close();


            }
        });
    }
}
