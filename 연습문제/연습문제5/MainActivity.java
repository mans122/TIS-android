package com.example.practice05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout = null;
    View dialogView=null;
    int selected;
    String color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = (View)View.inflate(MainActivity.this,R.layout.dialog1,null);
                AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);
                dig.setTitle("회원가입");
                dig.setView(dialogView); // dialog에 custom view 세팅
                dig.setPositiveButton("닫기",null);
                dig.setNegativeButton("회원가입", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 로그인처리
//                        Toast.makeText(getApplicationContext(), "로그인 처리", Toast.LENGTH_SHORT).show();
                        EditText id=(EditText)dialogView.findViewById(R.id.editId);
                        EditText pw=(EditText)dialogView.findViewById(R.id.editPW);
                        EditText name=(EditText)dialogView.findViewById(R.id.editName);
                        EditText email=(EditText)dialogView.findViewById(R.id.editEmail);

                        Log.d("===id==",id.getText().toString());
                        Log.d("===pw==",pw.getText().toString());
                        Log.d("===name==",name.getText().toString());
                        Log.d("===email==",email.getText().toString());
                        Toast.makeText(getApplicationContext(),"회원가입 완료",Toast.LENGTH_SHORT).show();
                    }
                });
                dig.show();
            }
        });

        baseLayout = (LinearLayout)findViewById(R.id.baseLayout);
        try{
            FileInputStream inFs=openFileInput("config.txt");
            byte[] txt=new byte[inFs.available()];
            inFs.read(txt);//파일을 읽어서 배열에 저장
            String str=new String(txt); //byte배열을 String으로 변환
            Log.d("==========str==========",str);
            switch (str){
                case "RED" :
                    baseLayout.setBackgroundColor(Color.RED);
                    return;
                case "BLUE":
                    baseLayout.setBackgroundColor(Color.BLUE);
                    return;
                case "GREEN":
                    baseLayout.setBackgroundColor(Color.GREEN);
                    return;
            }
            inFs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.btnSetting) {
            dialogView=(View)View.inflate(MainActivity.this,R.layout.dialog,null);
            final AlertDialog.Builder dig=new AlertDialog.Builder(MainActivity.this);
            dig.setTitle("앱 배경 설정");
            dig.setView(dialogView);
            dig.setNegativeButton("저장", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    RadioButton rbBlue = (RadioButton)dialogView.findViewById(R.id.itemBlue);
                    RadioButton rbRed = (RadioButton)dialogView.findViewById(R.id.itemRed);
                    RadioButton rbGreen = (RadioButton)dialogView.findViewById(R.id.itemGreen);
                    if(rbRed.isChecked()){color="RED";}
                    if(rbBlue.isChecked()){color="BLUE";}
                    if(rbGreen.isChecked()){color="GREEN";}
                    //file 쓰기
                    try{
                        FileOutputStream outFs=openFileOutput("config.txt", Context.MODE_PRIVATE);
                        outFs.write(color.getBytes());
                        outFs.close();
                        Toast.makeText(getApplicationContext(),"저장완료",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    }
                });
            dig.setPositiveButton("닫기",null);
            dig.show();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu, menu);
        return true;
    }


}
