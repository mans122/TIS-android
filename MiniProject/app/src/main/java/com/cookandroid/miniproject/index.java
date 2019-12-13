package com.cookandroid.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class index extends AppCompatActivity {
    Button btnWrite=null;
    ListView listView = null;
    DBManager dbManager = null;
    SQLiteDatabase db=null;
    ListViewAdapter adapter;
    Cursor cursor = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        setTitle("다이어리");

        final DBManager dbManager = DBManager.getInstance(getApplicationContext());
        db= dbManager.getReadableDatabase();

        listView = (ListView)findViewById(R.id.listView);
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);

        cursor = db.rawQuery("select * from Diary;", null);
        while (cursor.moveToNext()) {
            adapter.addItem(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        adapter.notifyDataSetChanged();
        cursor.close();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                db = dbManager.getReadableDatabase();
                cursor = db.rawQuery("select * from Diary where Title='"+((ListViewItem)adapter.getItem(position)).getTitle()+"'",null);
                Intent intent = new Intent(getApplicationContext(),Detail.class);
//                Log.d("Title",cursor.getString(0));
//                Log.d("Date",cursor.getString(1));
//                Log.d("Weather",cursor.getString(2));
//                Log.d("Content",cursor.getString(3));
                intent.putExtra("Title",cursor.getString(0));
                intent.putExtra("Date",cursor.getString(1));
                intent.putExtra("Weather",cursor.getString(2));
                intent.putExtra("Content",cursor.getString(3));
//                intent.putExtra("Title",((ListViewItem)adapter.getItem(position)).getContent());
//                intent.putExtra("Date",((ListViewItem)adapter.getItem(position)).getDate());
//                intent.putExtra("Weather",((ListViewItem)adapter.getItem(position)).getWeather());
//                intent.putExtra("Content",((ListViewItem)adapter.getItem(position)).getContent());
                cursor.close();
                db.close();
                startActivity(intent);

            }
        });
        btnWrite = (Button)findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WriteActivity.class);
                db.close();
                startActivity(intent);
            }
        });
    }
}
