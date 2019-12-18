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
    Button btnList=null;
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

        cursor = db.rawQuery("select * from Diary order by Num desc;", null);
        while (cursor.moveToNext()) {
            adapter.addItem(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        }
        adapter.notifyDataSetChanged();
        cursor.close();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                db = dbManager.getReadableDatabase();
                Log.d("title",((ListViewItem)adapter.getItem(position)).getTitle());
                cursor = db.rawQuery("select * from Diary where Title='"+((ListViewItem)adapter.getItem(position)).getTitle()+"'",null);
                Intent intent = new Intent(getApplicationContext(),Detail.class);
                cursor.moveToNext();
                intent.putExtra("Num",cursor.getInt(0));
                intent.putExtra("Title",cursor.getString(1));
                intent.putExtra("Date",cursor.getString(2));
                intent.putExtra("Weather",cursor.getString(3));
                intent.putExtra("Content",cursor.getString(4));

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
                finish();
            }
        });

        btnList=(Button)findViewById(R.id.btnList);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),IDListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
