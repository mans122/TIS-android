package com.cookandroid.json01_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    TextView tv =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.data);
        String file = "customers.json";
        String result = "";
        try{
            InputStream is = getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            result = new String(buffer, "utf-8");

            JSONObject json = new JSONObject(result);
            JSONArray jArr = json.getJSONArray("customers");
            for(int i=0; i<jArr.length();i++) {
                json = jArr.getJSONObject(i);
//                String name = json.getString("name");
                String name2 = jArr.getJSONObject(i).getString("name");
                String address = json.getString("address");
                Log.d("===name===", name2);
                Log.d("===address===", address);
                tv.append(name2+","+address+"\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
