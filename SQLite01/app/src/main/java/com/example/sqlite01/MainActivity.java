package com.example.sqlite01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtName,edtNum,edtNameResult,edtNumResult;
    Button btnInit,btnSelect,btnInsert;
    MyDBHelper myHelper = null;
    SQLiteDatabase sqlDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHelper = new MyDBHelper(this);
        edtName = (EditText)findViewById(R.id.edtName);
        edtNum = (EditText)findViewById(R.id.edtNum);
        edtNameResult = (EditText)findViewById(R.id.edtNameResult);
        edtNumResult = (EditText)findViewById(R.id.edtNumResult);
        btnInit = (Button)findViewById(R.id.btnInit);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnSelect = (Button)findViewById(R.id.btnSelect);

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
                sqlDB=myHelper.getReadableDatabase(); // 읽기모드로 DB 오픈
                Cursor cursor;
                cursor = sqlDB.rawQuery("select * from groupTBL",null);
                String strName ="그룹 이름 "+"\r\n"+" ============ "+"\r\n";
                String strNum = "그룹 인원 "+"\r\n"+" ============ "+"\r\n";
                while(cursor.moveToNext()){
                    strName+=cursor.getString(0)+"\r\n";
                    strNum+=cursor.getString(1)+"\r\n";
                }
                edtNameResult.setText(strName);
                edtNumResult.setText(strNum);
                cursor.close();
                sqlDB.close();
            }
        });

    }

    public class MyDBHelper extends SQLiteOpenHelper{
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
    }
}
