package com.cookandroid.provider01;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button button1;
    EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        editText1 = (EditText) findViewById(R.id.editText1);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG},MODE_PRIVATE);
        button1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                editText1.setText(getCallHistory());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public String getCallHistory() {
        String[] callSet = new String[]{CallLog.Calls.DATE,
                CallLog.Calls.TYPE,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION};


            Cursor c = getContentResolver().query(CallLog.Calls.CONTENT_URI, callSet, null, null, null);
            if (c == null) {
                return "통화기록 거의 없음";
            }
            StringBuffer callBuf = new StringBuffer();
            callBuf.append("\n날짜 구분  전화번호    통화시간\n\n");
            c.moveToNext();
            do {
                long callDate = c.getLong(0);
                SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
                String date_str = datePattern.format(new Date(callDate));
                callBuf.append(date_str + "");
                if (c.getInt(1) == CallLog.Calls.INCOMING_TYPE) {
                    callBuf.append("착신 : ");
                } else {
                    callBuf.append("발신 : ");
                }
                callBuf.append(c.getString(2) + ":");
                callBuf.append(c.getString(3) + "초\n");
            } while (c.moveToNext());
            c.close();
            return callBuf.toString();
        }


}

