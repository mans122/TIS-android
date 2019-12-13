package com.example.fileio01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button btnWrite=null;
    Button btnRead=null;
    Button btnSdcard=null;
    EditText editText=null;
    Button btnMkdir = null;
    Button btnDeldir=null;
    Button btnRaw=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnWrite=(Button)findViewById(R.id.button);
        btnRead=(Button)findViewById(R.id.button2);
        btnSdcard=(Button)findViewById(R.id.button3);
        editText=(EditText)findViewById(R.id.editText);
        btnMkdir = (Button)findViewById(R.id.button4);
        btnDeldir = (Button)findViewById(R.id.button5);
        btnRaw = (Button)findViewById(R.id.button6);

        btnRaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    InputStream inputS=getResources().openRawResource(R.raw.rawtest);
                    byte[] txt=new byte[inputS.available()];
                    inputS.read(txt);
                    editText.setText(new String(txt));
                    inputS.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        //sd카드 경로구하기
        final String strSdpath=Environment.getExternalStorageDirectory().getAbsolutePath();
        final File myDir = new File(strSdpath+"/mydir");
        btnMkdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDir.mkdir();
                Toast.makeText(getApplicationContext(),"생성 되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        btnDeldir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDir.delete();
                Toast.makeText(getApplicationContext(),"삭제 되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        //sd카드에 쓰기 권한을 사용자에게요청
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        btnSdcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String strSDpath= Environment.getExternalStorageDirectory().getAbsolutePath();
                    FileInputStream inFs=new FileInputStream(strSDpath+"/sdtest.txt");
//                    FileInputStream inFs=new FileInputStream("/sdcard/sdtest.txt");
                    Log.d("==sdcardpath==",strSDpath);
                    byte[] txt=new byte[inFs.available()];
                    inFs.read(txt);

                    editText.setText(new String(txt));
                    inFs.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileOutputStream outFs=openFileOutput("file.txt", Context.MODE_PRIVATE);
                    String str="안드로이드";
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(),"쓰기완료",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileInputStream inFs=openFileInput("file.txt");
                    byte[] txt=new byte[30];
                    inFs.read(txt);//파일을 읽어서 배열에 저장
                    String str=new String(txt); //byte배열을 String으로 변환
                    Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                    inFs.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
