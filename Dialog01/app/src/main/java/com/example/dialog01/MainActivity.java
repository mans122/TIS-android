package com.example.dialog01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn=null;
    Button btn2=null;
    Button btn3=null;
    Button btn4=null;
    View dialogView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn2=(Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            int selected;
            @Override
            public void onClick(View v) {
                selected=0;
                final String[] versionArray={"롤리팝","마시멜로","오레오","파이"};
                final AlertDialog.Builder dig=new AlertDialog.Builder(MainActivity.this);
                dig.setTitle("선택");
                dig.setSingleChoiceItems(versionArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selected= which;
                        Toast.makeText(getApplicationContext(),versionArray[which],Toast.LENGTH_SHORT).show();
                    }
                });
                dig.setPositiveButton("닫기",null);
                dig.setNegativeButton("선택", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (selected){
                            case 0:
                                Toast.makeText(getApplicationContext(),"0선택",Toast.LENGTH_SHORT).show();
                                return;
                            case 1:
                                Toast.makeText(getApplicationContext(),"1선택",Toast.LENGTH_SHORT).show();
                                return;
                        }
                        //Toast.makeText(getApplicationContext(),versionArray[selected],Toast.LENGTH_SHORT).show();
                    }
                });
                dig.show();
            }
        });


        btn3=(Button)findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] versionArray={"롤리팝","마시멜로","오레오","파이"};
                final boolean[] checkArray={true,false,false,false};
                final AlertDialog.Builder dig=new AlertDialog.Builder(MainActivity.this);
                dig.setTitle("선택");
                dig.setMultiChoiceItems(versionArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Toast.makeText(getApplicationContext(),versionArray[which],Toast.LENGTH_SHORT).show();
                    }
                });

                dig.setPositiveButton("닫기",null);
                dig.setNegativeButton("선택", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dig.show();
            }
        });

        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dig=new AlertDialog.Builder(MainActivity.this);
                dig.setTitle("삭제");
                dig.setMessage("삭제하시겠습니까?");
                dig.setPositiveButton("아니오",null);
                dig.setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //삭제처리
                        Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                dig.show();
            }
        });

        btn4=(Button)findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //custom dialog view 생성
                dialogView = (View)View.inflate(MainActivity.this,R.layout.dialog1,null);
                AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);
                dig.setTitle("로그인");
                dig.setView(dialogView); // dialog에 custom view 세팅
                dig.setPositiveButton("닫기",null);
                dig.setNegativeButton("로그인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 로그인처리
//                        Toast.makeText(getApplicationContext(), "로그인 처리", Toast.LENGTH_SHORT).show();
                        EditText edtID=(EditText)dialogView.findViewById(R.id.dlgedt1);
                        EditText edtPW=(EditText)dialogView.findViewById(R.id.dlgedt2);
                        Log.d("===id==",edtID.getText().toString());
                        Log.d("===pw==",edtPW.getText().toString());
                    }
                });
                dig.show();
            }
        });

    }
}
