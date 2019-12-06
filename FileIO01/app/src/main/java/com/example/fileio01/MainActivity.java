package com.example.fileio01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    Button btnWrite=null;
    Button btnRead=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnWrite=(Button)findViewById(R.id.button);
        btnRead=(Button)findViewById(R.id.button2);

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
