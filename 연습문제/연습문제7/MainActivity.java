package com.example.sqlite02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtName,edtNum;
    ListView listName,listNum;
    Button btnInit,btnSelect,btnInsert,btnUpdate,btnDelete;
    MyDBHelper myHelper = null;
    SQLiteDatabase sqlDB;
    ArrayList<String> strName = new ArrayList<String>();
    ArrayList<String> strNum = new ArrayList<String>();

    ArrayAdapter<String> adapterName = null;
    ArrayAdapter<String> adapterNum = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHelper = new MyDBHelper(this);
        edtName = (EditText)findViewById(R.id.edtName);
        edtNum = (EditText)findViewById(R.id.edtNum);
        listName =(ListView)findViewById(R.id.listName);
        listNum = (ListView)findViewById(R.id.listNum);

        btnInit = (Button)findViewById(R.id.btnInit);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnSelect = (Button)findViewById(R.id.btnSelect);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        //list를 ArrayAdapter로 연결
        adapterName = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strName);
        adapterNum = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strNum);

        listName.setAdapter(adapterName);
        listNum.setAdapter(adapterNum);

        listName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //intent를 사용해 SecondActivity를 사용해 값을 넘겨줌
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("gName",strName.get(position));
                intent.putExtra("gNum",strNum.get(position));
                startActivity(intent);
            }
        });

        //초기화 버튼
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB=myHelper.getWritableDatabase(); // 쓰기모드로 DB 오픈
                myHelper.onUpgrade(sqlDB,1,2);
                sqlDB.close();
            }
        });

        // 입력 버튼
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB=myHelper.getWritableDatabase(); // 쓰기모드로 DB 오픈
                sqlDB.execSQL("insert into groupTBL values('"+edtName.getText().toString()+"',"+edtNum.getText().toString()+")");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"입력 완료",Toast.LENGTH_SHORT).show();
            }
        });

        // 조회 버튼
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sqlDB=myHelper.getReadableDatabase(); // 읽기모드로 DB 오픈
//                Cursor cursor; // Java의 ResultSet이 Cursor역할을 한다.
//                cursor = sqlDB.rawQuery("select * from groupTBL",null);
//                //ArrayList clear
//                strName.clear();
//                strNum.clear();
////                strName.add("")
//                // 다음 값이 존재할때까지
//                while(cursor.moveToNext()){
//                    strName.add(cursor.getString(0));
//                    strNum.add(cursor.getString(1));
//                }
//                // adapter 변경을 확인
//                adapterName.notifyDataSetChanged();
//                adapterNum.notifyDataSetChanged();
//
//                cursor.close();
//                sqlDB.close();
                myHelper.select();
            }
        });

        //수정 버튼
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB=myHelper.getWritableDatabase(); // 쓰기모드로 DB 오픈
                sqlDB.execSQL("update groupTBL set gNumber="+edtNum.getText().toString()+" where gName='"+edtName.getText().toString()+"'");
                sqlDB.close();
                myHelper.select();
            }
        });

        //삭제 버튼
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB=myHelper.getWritableDatabase(); // 쓰기모드로 DB 오픈
                sqlDB.execSQL("delete from groupTBL where gName='"+edtName.getText().toString()+"'");
                sqlDB.close();
            }
        });
    }
    public class MyDBHelper extends SQLiteOpenHelper {
        public MyDBHelper(@Nullable Context context) {
            // DB생성
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Table 생성, Table이 이미 존재하면 생성하지 않음.
            db.execSQL("create table groupTBL(gName char(20) primary key, gNumber integer)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Table 초기화 코드
            db.execSQL("drop table if exists groupTBL");
            onCreate(db);
        }

        public void select(){
            sqlDB=myHelper.getReadableDatabase(); // 읽기모드로 DB 오픈
            Cursor cursor; // Java의 ResultSet이 Cursor역할을 한다.
            cursor = sqlDB.rawQuery("select * from groupTBL",null);
            //ArrayList clear
            strName.clear();
            strNum.clear();
//                strName.add("")
            // 다음 값이 존재할때까지
            while(cursor.moveToNext()){
                strName.add(cursor.getString(0));
                strNum.add(cursor.getString(1));
            }
            // adapter 변경을 확인
            adapterName.notifyDataSetChanged();
            adapterNum.notifyDataSetChanged();

            cursor.close();
            sqlDB.close();
        }
    }
}
