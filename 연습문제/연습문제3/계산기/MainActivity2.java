package com.example.practice03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practice03.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity extends AppCompatActivity {
    EditText edit1 = null;
    EditText edit2 = null;
    Button btnadd, btnsub, btnmul, btndiv;
    TextView result = null;
    Button btnclear,btncancle,btnresult;
    int total = 0;
    String totalstr;
    int sss = 0; //1 :+ ,2:-,3:*,4:/
    int num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edit1 = (EditText)findViewById(R.id.edit1);
        edit2 = (EditText)findViewById(R.id.edit2);

        btnadd=(Button)findViewById(R.id.BtnAdd);
        btnsub=(Button)findViewById(R.id.BtnSub);
        btnmul=(Button)findViewById(R.id.BtnMul);
        btndiv=(Button)findViewById(R.id.BtnDiv);
        btnclear=(Button)findViewById(R.id.btnClear);
        btncancle=(Button)findViewById(R.id.btnCancle);
        btnresult=(Button)findViewById(R.id.btnResult);
        final Integer[] numsid = {R.id.BtnNum00,R.id.BtnNum01,R.id.BtnNum02,R.id.BtnNum03,R.id.BtnNum04,R.id.BtnNum05,R.id.BtnNum06,R.id.BtnNum07,R.id.BtnNum08,R.id.BtnNum09};
        final Button[] nums = new Button[10];

        final ArrayList mDataList = new ArrayList<String>();
        for(int i=0;i<nums.length;i++){
            final int index = i;
            nums[i] = (Button)findViewById(numsid[i]);
            nums[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit2.append(nums[index].getText().toString());
                }
            });
        }



        // 계산 --------------------------------------------
        //더하기
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit2.getText().toString().indexOf("0")==0){
                    String imsi = edit2.getText().toString().replaceFirst("0","");
                    edit2.setText(imsi);
                }
                try {
                    num = Integer.parseInt(edit2.getText().toString());
                    if(edit1.getText().toString().length()!=0){
                        totalstr = edit1.getText().toString();
                        String k = totalstr.substring(totalstr.length()-1,totalstr.length());
                        if (k.equals("+")) {total += num;}
                        if (k.equals("-")) {total -= num;}
                        if (k.equals("*")) {total *= num;}
                        if (k.equals("/")) {total /= num;}
                    }else{
                        total +=num;
                    }

                        edit1.append(num+" +");
                        edit2.setText("");
                }
                catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });
        //뺄셈
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit2.getText().toString().indexOf("0")==0){
                    String imsi = edit2.getText().toString().replaceFirst("0","");
                    edit2.setText(imsi);
                }
                try {
                    if(edit1.getText().toString().length()!=0){
                        totalstr = edit1.getText().toString();
                        String k = totalstr.substring(totalstr.length()-1,totalstr.length());
                        num = Integer.parseInt(edit2.getText().toString());
                        if (k.equals("+")) {total += num;}
                        if (k.equals("-")) {total -= num;}
                        if (k.equals("*")) {total *= num;}
                        if (k.equals("/")) {total /= num;}
                    }else{
                        num = Integer.parseInt(edit2.getText().toString());
                        total +=num;
                    }
                    edit1.append(num+" -");
                    edit2.setText("");
                }
                catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });
        //곱셈
        btnmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit2.getText().toString().indexOf("0")==0){
                    String imsi = edit2.getText().toString().replaceFirst("0","");
                    edit2.setText(imsi);
                }
                try {
                    num = Integer.parseInt(edit2.getText().toString());
                    if(edit1.getText().toString().length()!=0){
                        totalstr = edit1.getText().toString();
                        String k = totalstr.substring(totalstr.length()-1,totalstr.length());
                        if (k.equals("+")) {total += num;}
                        if (k.equals("-")) {total -= num;}
                        if (k.equals("*")) {total *= num;}
                        if (k.equals("/")) {total /= num;}
                    }else{
                        total +=num;
                    }
                    edit1.append(num+" *");
                    edit2.setText("");
                }
                catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });
        //나눗셈
        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit2.getText().toString().indexOf("0")==0){
                    String imsi = edit2.getText().toString().replaceFirst("0","");
                    edit2.setText(imsi);
                }
                try {
                    if(edit1.getText().toString().length()!=0){
                        totalstr = edit1.getText().toString();
                        String k = totalstr.substring(totalstr.length()-1,totalstr.length());
                        num = Integer.parseInt(edit2.getText().toString());
                        if (k.equals("+")) {total += num;}
                        if (k.equals("-")) {total -= num;}
                        if (k.equals("*")) {total *= num;}
                        if (k.equals("/")) {total /= num;}
                    }else{
                        num = Integer.parseInt(edit2.getText().toString());
                        total +=num;
                    }

                    edit1.append(num+" /");
                    edit2.setText("");
                }
                catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });

        // CE버튼 클릭 ---------------------------------------------------------------
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit1.setText("");
                edit2.setText("");
                total = 0;
                totalstr = "";
            }
        });
        // C버튼 클릭
        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = edit2.getText().toString();
                if(str.length()==0){
                    return;
                }else{
                    str = str.substring(0,str.length()-1);
                    edit2.setText(str);
                }
            }
        });

        // 결과 버튼 클릭 ----------------------------------------------------------
        btnresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalstr = edit1.getText().toString();
                String k = totalstr.substring(totalstr.length()-1,totalstr.length());
                num = Integer.parseInt(edit2.getText().toString());
                try {
                    if(edit1.getText().toString()==null){
                        Toast.makeText(getApplicationContext(),"입력하세요",Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        if (k.equals("+")) {total += num;}
                        if (k.equals("-")) {total -= num;}
                        if (k.equals("*")) {total *= num;}
                        if (k.equals("/")) {total /= num;}
                        edit1.setText("");
                        edit2.setText("" + total);
                        total = 0;
                    }
                }
                catch (Exception e ){
                    e.printStackTrace();
                }
//                edit1.append(edit2.getText().toString());
//                String expression=edit1.getText().toString();
//                ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
//                try{
//                    Object result=engine.eval(expression);
//                    edit1.setText(expression+"="+result);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
            }
        });
    }
}
