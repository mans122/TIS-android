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
        Toast.makeText(getApplicationContext(),intent.getStringExtra("Title"),Toast.LENGTH_SHORT).show();
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
                DBManager dbManager = DBManager.getInstance(getApplicationContext());
                SQLiteDatabase db= dbManager.getWritableDatabase();
                db.execSQL("update Diary set Date='"+textDate.getText().toString()+"', Content='"+textContent.getText().toString()+"' where Title='"+textTitle.getText().toString()+"'");
                db.close();
            }
        });
    }
}
