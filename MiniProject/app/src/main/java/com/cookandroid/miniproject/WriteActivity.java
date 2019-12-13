package com.cookandroid.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

public class WriteActivity extends AppCompatActivity {
    Button btnInsert = null;
    CalendarView calendarView = null;
    EditText edtTitle,edtWeather,edtContent;
    TextView dateView = null;
    SQLiteDatabase db=null;
    //    DBManager dbManager = null;
    int year,month,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write);
        DBManager dbManager = DBManager.getInstance(getApplicationContext());
        db= dbManager.getWritableDatabase();

//        dbManager = new DBManager(this);
        edtTitle = (EditText)findViewById(R.id.edtTitle);
        edtWeather = (EditText)findViewById(R.id.edtWeather);
        edtContent = (EditText)findViewById(R.id.edtContent);
        dateView = (TextView)findViewById(R.id.dateView);
        btnInsert =(Button)findViewById(R.id.btnInsert);
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        calendarView.setVisibility(View.GONE);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int y, int m, int d) {
                year = y;
                month = m+1;
                day = d;
                dateView.setText(year+"."+month+"."+day);
                calendarView.setVisibility(View.GONE);
            }
        });
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.VISIBLE);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString();
                String date = dateView.getText().toString();
                String weather = edtWeather.getText().toString();
                String content = edtContent.getText().toString();
//                dbManager.insert(edtTitle.getText().toString(),dateView.getText().toString(),edtWeather.getText().toString(),edtContent.getText().toString());
                db.execSQL("insert into Diary values('"+title+"','"+date+"','"+weather+"','"+content+"')");
                Intent intent = new Intent(getApplicationContext(),index.class);
                startActivity(intent);
            }
        });

    }
}
