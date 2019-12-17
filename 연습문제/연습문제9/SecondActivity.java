package com.cookandroid.login01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

public class SecondActivity extends AppCompatActivity {
    ListView listView1;
    ArrayList<String> arrayListNum = new ArrayList<>();
    ArrayList<String> arrayListTitle = new ArrayList<>();
    ArrayAdapter<String> adapter;
    DownLoadURL myTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listView1=(ListView)findViewById(R.id.listView1);
        String url="http://10.0.2.2:8181/AndroidConn01/BoardList.jsp";
        myTask=new DownLoadURL();
        myTask.execute(url);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ThirdActivity.class);
                intent.putExtra("num",arrayListNum.get(position));
                startActivity(intent);
                finish();
            }
        });
    }
    public static String encodeString(Properties params) {
        StringBuffer sb = new StringBuffer(256);
        Enumeration names = params.propertyNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            String value = params.getProperty(name);
            try{
                sb.append(URLEncoder.encode(name) + "=" + URLEncoder.encode(value,"utf-8") );
            }catch(Exception e){
                e.printStackTrace();
            }
            if (names.hasMoreElements()) sb.append("&");
        }
        return sb.toString();
    }

    class DownLoadURL extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return (String)downloadUrl((String)urls[0]);
            } catch (IOException e) {
                return "다운로드 실패";
            }
        }

        //Download AsyncTask
        private String downloadUrl(String myurl) throws IOException {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
                String line = null;
                String page = "";
                while((line = bufreader.readLine()) != null) {
                    page += line;
                }
                return page;
            } finally {
                conn.disconnect();
            }
        }
        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject json = new JSONObject(result);
                JSONArray jArr = json.getJSONArray("list");
                for (int i=0; i<jArr.length(); i++) {
                    json = jArr.getJSONObject(i);
                    arrayListNum.add(json.getString("num"));
                    arrayListTitle.add(json.getString("num")+" - "+json.getString("title"));
                }
                adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayListTitle);
                listView1.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
