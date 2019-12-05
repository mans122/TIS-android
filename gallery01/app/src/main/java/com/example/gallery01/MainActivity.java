package com.example.gallery01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox1 = null;
    TextView tv1 = null;
    RadioGroup rg1 = null;
    Button btn1 = null;
    ImageView img1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.text1);
        checkBox1 = (CheckBox)findViewById(R.id.checkbox1);
        rg1 = (RadioGroup)findViewById(R.id.rgroup1);
        btn1 = (Button)findViewById(R.id.btn1);
        img1 = (ImageView)findViewById(R.id.img1);

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox1.isChecked()==true){
                    tv1.setVisibility(View.VISIBLE);
                    rg1.setVisibility(View.VISIBLE);
                    btn1.setVisibility(View.VISIBLE);
                    img1.setVisibility(View.VISIBLE);
                }else{
                    tv1.setVisibility(View.INVISIBLE);
                    rg1.setVisibility(View.INVISIBLE);
                    btn1.setVisibility(View.INVISIBLE);
                    img1.setVisibility(View.INVISIBLE);
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switch (rg1.getCheckedRadioButtonId()){
                    case R.id.rbtn1:
                        img1.setImageResource(R.drawable.dog);
                        break;
                    case R.id.rbtn2:
                        img1.setImageResource(R.drawable.cat);
                        break;
                    case R.id.rbtn3:
                        img1.setImageResource(R.drawable.rabbit);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"동물을 선택해주세요", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
