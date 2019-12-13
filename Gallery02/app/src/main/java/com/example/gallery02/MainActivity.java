package com.example.gallery02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button btnPrev,btnNext;
    MyPicktureView myPicktureView;
    int curNum;
    File[] imageFiles;
    String imageFname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //사용자에게 쓰기권한요청
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        setContentView(R.layout.activity_main);


        btnPrev = (Button)findViewById(R.id.btnPrev);
        btnNext = (Button)findViewById(R.id.btnNext);
        myPicktureView = (MyPicktureView)findViewById(R.id.myPictureView);
        // 저장파일목록 구하기
        imageFiles=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures").listFiles();
        imageFname=imageFiles[0].toString(); //첫번째 이미지
        myPicktureView.imagePath=imageFname;


        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curNum<=0){
                    Toast.makeText(getApplicationContext(),"첫번째 이미지입니다.",Toast.LENGTH_SHORT).show();
                }
                else{
                    curNum--;
                    imageFname=imageFiles[curNum].toString();
                    myPicktureView.imagePath=imageFname;
                    myPicktureView.invalidate();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curNum>=imageFiles.length-1){
                    Toast.makeText(getApplicationContext(),"마지막 이미지입니다.",Toast.LENGTH_SHORT).show();
                }
                else{
                    curNum++;
                    imageFname=imageFiles[curNum].toString();
                    myPicktureView.imagePath=imageFname;
                    myPicktureView.invalidate();
                }
            }
        });

    }
}
