package com.cookandroid.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class WriteActivity extends AppCompatActivity {
    Button btnInsert = null;
    CalendarView calendarView = null;
    EditText edtTitle,edtContent;
    TextView dateView,textWeather = null;
    SQLiteDatabase db=null;
    //    DBManager dbManager = null;
    int year,month,day;
    String weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write);
        DBManager dbManager = DBManager.getInstance(getApplicationContext());
        db= dbManager.getWritableDatabase();

//        dbManager = new DBManager(this);
        edtTitle = (EditText)findViewById(R.id.edtTitle);
        textWeather = (TextView)findViewById(R.id.textWeather);
        textWeather.setClickable(true);
        textWeather.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sunny, 0, 0, 0);
        textWeather.setCompoundDrawablePadding(10);
        edtContent = (EditText)findViewById(R.id.edtContent);
        dateView = (TextView)findViewById(R.id.viewDate);
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
                String content = edtContent.getText().toString();
//                dbManager.insert(edtTitle.getText().toString(),dateView.getText().toString(),edtWeather.getText().toString(),edtContent.getText().toString());
                db.execSQL("insert into Diary(title,date,weather,content) values('"+title+"','"+date+"','"+weather+"','"+content+"')");
                Intent intent = new Intent(getApplicationContext(),index.class);
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
