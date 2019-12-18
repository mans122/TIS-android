package com.cookandroid.miniproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    TextView textTitle,textDate,textNum,textWeather,textContent;
    Button btnList,btnUpdate,btnDelete;
    CalendarView cv = null;
    String weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        Intent intent = getIntent();
        textNum=(TextView)findViewById(R.id.textNum);
        textTitle = (TextView)findViewById(R.id.textTitle);
        textDate = (TextView) findViewById(R.id.textDate);
        textWeather = (TextView)findViewById(R.id.textWeather);
        textContent = (TextView) findViewById(R.id.textContent);
        btnUpdate =(Button)findViewById(R.id.btnUpdate);
        btnDelete =(Button)findViewById(R.id.btnDelete);
        btnList=(Button)findViewById(R.id.btnList);
        cv=(CalendarView)findViewById(R.id.calendarView);
        cv.setVisibility(View.GONE);

       textNum.setText(String.valueOf(intent.getIntExtra("Num",0)));
        textTitle.setText(intent.getStringExtra("Title"));
        textDate.setText(intent.getStringExtra("Date"));
        weather = intent.getStringExtra("Weather");
        textContent.setText(intent.getStringExtra("Content"));

        if(weather.equals("")){
            ;
        }else {
            switch (weather) {
                case "sunny":
                    textWeather.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.sunny, 0);
                    textWeather.setCompoundDrawablePadding(10);
                    weather = "sunny";
                    break;
                case "cloud":
                    textWeather.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.cloud, 0);
                    textWeather.setCompoundDrawablePadding(10);
                    weather = "cloud";
                    break;
                case "storm":
                    textWeather.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.strom,0);
                    textWeather.setCompoundDrawablePadding(10);
                    weather = "storm";
                    break;
                case "rain":
                    textWeather.setCompoundDrawablesWithIntrinsicBounds( 0, 0,R.drawable.rain, 0);
                    textWeather.setCompoundDrawablePadding(10);
                    weather = "rain";
                    break;
                case "snow":
                    textWeather.setCompoundDrawablesWithIntrinsicBounds( 0, 0,R.drawable.snow, 0);
                    textWeather.setCompoundDrawablePadding(10);
                    weather = "snow";
                    break;
                default:
                    break;
            }
        }

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
                Intent intent = new Intent(getApplicationContext(),UpdateActivity.class);
                intent.putExtra("Num",textNum.getText().toString());
                intent.putExtra("Title",textTitle.getText().toString());
                intent.putExtra("Date",textDate.getText().toString());
                intent.putExtra("Weather",weather);
                intent.putExtra("Content",textContent.getText().toString());
                startActivity(intent);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dig=new AlertDialog.Builder(Detail.this);
                dig.setTitle("삭제");
                dig.setMessage("삭제하시겠습니까?");
                dig.setPositiveButton("아니오",null);
                dig.setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBManager dbManager = DBManager.getInstance(getApplicationContext());
                        SQLiteDatabase db= dbManager.getWritableDatabase();
                        db.execSQL("delete from Diary where Num='"+Integer.parseInt(textNum.getText().toString())+"'");
                        db.close();
                        Intent intent = new Intent(getApplicationContext(),index.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                dig.show();
            }
        });
    }
}
