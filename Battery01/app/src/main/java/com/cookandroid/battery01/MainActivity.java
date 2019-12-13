package com.cookandroid.battery01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1;
    EditText editText1;
    BroadcastReceiver br;
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter iFilter=new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED); // 배터리 변경 메시지 수신
        registerReceiver(br, iFilter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1=(ImageView)findViewById(R.id.imageView1);
        editText1=(EditText)findViewById(R.id.editText1);

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action =intent.getAction();
                if(action.equals(intent.ACTION_BATTERY_CHANGED)){
                    int remain=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);

                    if(remain==100){
                        imageView1.setImageResource(R.drawable.battery_100);
                    }else if(remain>=80){
                        imageView1.setImageResource(R.drawable.battery_80);
                    }else if(remain>=60){
                        imageView1.setImageResource(R.drawable.battery_60);
                    }else if(remain>=20){
                        imageView1.setImageResource(R.drawable.battery_20);
                    }

                    int plug=intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
                    switch (plug){
                        case 0:
                            editText1.append("전원 연결 : 안됨");
                            break;
                        case BatteryManager.BATTERY_PLUGGED_AC:
                            editText1.setText("전원 연결 : 어댑터 연결됨");
                            break;
                        case BatteryManager.BATTERY_PLUGGED_USB:
                            editText1.setText("전원 연결 : USB 연결됨");
                            break;
                    }
                }
            }
        };

    }
}
