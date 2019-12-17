package com.cookandroid.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    TextView textDate,textNum,textWeather;
    EditText edtTitle,edtContent;
    Button btnUpdate;
    CalendarView cv = null;
    String weather;
    int year,month,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        textNum = (TextView)findViewById(R.id.textNum);
        textDate = (TextView)findViewById(R.id.textDate);
        textDate.setClickable(true);
        textWeather = (TextView)findViewById(R.id.textWeather);
        textWeather.setClickable(true);
        edtTitle =(EditText)findViewById(R.id.edtTitle);
        edtContent =(EditText)findViewById(R.id.edtContent);


        cv=(CalendarView)findViewById(R.id.calendarView);
        cv.setVisibility(View.GONE);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int y, int m, int d) {
                year = y;
                month = m+1;
                day = d;
                textDate.setText(year+"."+month+"."+day);
                cv.setVisibility(View.GONE);
            }
        });

        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv.setVisibility(View.VISIBLE);
            }
        });

        Intent intent = getIntent();
        textNum.setText(intent.getStringExtra("Num"));
        Toast.makeText(getApplicationContext(),intent.getStringExtra("Num")+","+Integer.parseInt(textNum.getText().toString()),Toast.LENGTH_SHORT).show();
        textDate.setText(intent.getStringExtra("Date"));
        weather=intent.getStringExtra("Weather");
        textWeather.setText("");
        switch (weather){
            case "sunny":
                textWeather.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sunny, 0, 0, 0);
                textWeather.setCompoundDrawablePadding(10);
                weather="sunny";
                break;
            case "cloud":
                textWeather.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cloud, 0, 0, 0);
                textWeather.setCompoundDrawablePadding(10);
                weather="cloud";
                break;
            case "storm":
                textWeather.setCompoundDrawablesWithIntrinsicBounds(R.drawable.strom, 0, 0, 0);
                textWeather.setCompoundDrawablePadding(10);
                weather="storm";
                break;
            case "rain":
                textWeather.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rain, 0, 0, 0);
                textWeather.setCompoundDrawablePadding(10);
                weather="rain";
                break;
            case "snow":
                textWeather.setCompoundDrawablesWithIntrinsicBounds(R.drawable.snow, 0, 0, 0);
                textWeather.setCompoundDrawablePadding(10);
                weather="snow";
                break;
            default:
                break;
        }
        edtTitle.setText(intent.getStringExtra("Title"));
        edtContent.setText(intent.getStringExtra("Content"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DBManager dbManager = DBManager.getInstance(getApplicationContext());
                    SQLiteDatabase db= dbManager.getWritableDatabase();
                    db.execSQL("update Diary set Title='"+edtTitle.getText().toString()+"', Date='"+textDate.getText().toString()+"', Content='"+edtContent.getText().toString()+"',Weather ='"+weather+"' where Num='"+Integer.parseInt(textNum.getText().toString())+"'");
                    db.close();
                    Intent intent = new Intent(getApplicationContext(),Detail.class);
                    intent.putExtra("Num",textNum.getText().toString());
                    intent.putExtra("Title",edtTitle.getText().toString());
                    intent.putExtra("Date",textDate.getText().toString());
                    intent.putExtra("Weather",weather);
                    intent.putExtra("Content",edtContent.getText().toString());
                    startActivity(intent);
                    finish();


            }
        });

        textWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup=new PopupMenu(getApplicationContext(),v);
                getMenuInflater().inflate(R.menu.menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        textWeather.setText("");
                        switch (item.getItemId()){
                            case R.id.sunny:
                                textWeather.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sunny, 0, 0, 0);
                                textWeather.setCompoundDrawablePadding(10);
                                weather="sunny";
                                break;
                            case R.id.cloud:
                                textWeather.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cloud, 0, 0, 0);
                                textWeather.setCompoundDrawablePadding(10);
                                weather="cloud";
                                break;
                            case R.id.storm:
                                textWeather.setCompoundDrawablesWithIntrinsicBounds(R.drawable.strom, 0, 0, 0);
                                textWeather.setCompoundDrawablePadding(10);
                                weather="storm";
                                break;
                            case R.id.rain:
                                textWeather.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rain, 0, 0, 0);
                                textWeather.setCompoundDrawablePadding(10);
                                weather="rain";
                                break;
                            case R.id.snow:
                                textWeather.setCompoundDrawablesWithIntrinsicBounds(R.drawable.snow, 0, 0, 0);
                                textWeather.setCompoundDrawablePadding(10);
                                weather="snow";
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });


    }
}
