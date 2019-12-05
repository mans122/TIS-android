package com.example.tablelayout01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edit1 = null;
    EditText edit2 = null;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnadd, btnsub, btnmul, btndiv;
    TextView result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        edit1 = (EditText)findViewById(R.id.edit1);
        edit2 = (EditText)findViewById(R.id.edit2);

        btnadd=(Button)findViewById(R.id.BtnAdd);
        btnsub=(Button)findViewById(R.id.BtnSub);
        btnmul=(Button)findViewById(R.id.BtnMul);
        btndiv=(Button)findViewById(R.id.BtnDiv);
        final Integer[] numsid = {R.id.BtnNum00,R.id.BtnNum01,R.id.BtnNum02,R.id.BtnNum03,R.id.BtnNum04,R.id.BtnNum05,R.id.BtnNum06,R.id.BtnNum07,R.id.BtnNum08,R.id.BtnNum09};
        final Button[] nums = new Button[10];
        result=(TextView)findViewById(R.id.TextResult);
        final ArrayList mDataList = new ArrayList<String>();
        for(int i=0;i<nums.length;i++){
            final int index = i;
            nums[i] = (Button)findViewById(numsid[i]);
            nums[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edit1.isFocused()) {
//                        String num1 = edit1.getText().toString()+nums[index].getText().toString();
//                        edit1.setText(num1);
                        edit1.append(nums[index].getText().toString());
                    } else if (edit2.isFocused()) {
//                        String num2 = edit2.getText().toString()+nums[index].getText().toString();
//                        edit2.setText(num2);
                        edit2.append(nums[index].getText().toString());
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"입력할 숫자칸을 선택하세요",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        //더하기
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit1.getText().toString().equals("") || edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit1.getText().toString().indexOf("0")==0 || edit2.getText().toString().indexOf("0")==0){
                    String imsi = edit1.getText().toString().replaceFirst("0","");
                    Toast.makeText(getApplicationContext(),"0부터 입력됨",Toast.LENGTH_SHORT).show();
                    edit1.setText(imsi);
                }
                try{
                    int num1 = Integer.parseInt(edit1.getText().toString());
                    int num2 = Integer.parseInt(edit2.getText().toString());
                    result.setText("계산결과 : "+(num1+num2));
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit1.getText().toString().equals("") || edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                    return;                }
                try{
                    int num1 = Integer.parseInt(edit1.getText().toString());
                    int num2 = Integer.parseInt(edit2.getText().toString());
                    result.setText("계산결과 : "+(num1-num2));
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit1.getText().toString().equals("") || edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                    return;                }
                try{
                    int num1 = Integer.parseInt(edit1.getText().toString());
                    int num2 = Integer.parseInt(edit2.getText().toString());
                    result.setText("계산결과 : "+(num1*num2));
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit1.getText().toString().equals("") || edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                    return;                }
                try{
                    if(edit1.getText().toString().indexOf("0")==0){
                        Toast.makeText(getApplicationContext(),"0부터 입력됨",Toast.LENGTH_SHORT).show();
                    }
                    int num1 = Integer.parseInt(edit1.getText().toString());
                    int num2 = Integer.parseInt(edit2.getText().toString());

                    result.setText("계산결과 : "+(num1/num2)+" + "+(num1%num2));
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
