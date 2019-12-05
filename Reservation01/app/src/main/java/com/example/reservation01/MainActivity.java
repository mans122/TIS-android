package com.example.reservation01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer = null;
    Button btnStart=null;
    Button btnEnd=null;
    CalendarView calendarView = null;
    TimePicker timePicker=null;
    RadioButton radioButton1 = null;
    RadioButton radioButton2 = null;
    int year,month,day,hour,minute;
    TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        chronometer=(Chronometer)findViewById(R.id.chronometer1);
        btnStart=(Button)findViewById(R.id.btnStart);
        btnEnd=(Button)findViewById(R.id.btnEnd);
        calendarView = (CalendarView)findViewById(R.id.calendarView1);
        timePicker = (TimePicker)findViewById(R.id.timePicker1);
        calendarView.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);
        radioButton1 = (RadioButton)findViewById(R.id.radioButton);
        radioButton2 = (RadioButton)findViewById(R.id.radioButton2);

//        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//            @Override
//            public void onTimeChanged(TimePicker view, int h, int m) {
//                hour = h;
//                minute=m;
//            }
//        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int y, int m, int d) {
                year = y;
                month = m+1;
                day = d;
            }
        });

        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.INVISIBLE);
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.INVISIBLE);
                timePicker.setVisibility(View.VISIBLE);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int h = timePicker.getCurrentHour();
                int mi = timePicker.getCurrentMinute();
                chronometer.stop();
                textView.setText(year+"년 "+month+"월 "+day+"일"+h+"시"+mi+"분");
            }
        });


    }
}
