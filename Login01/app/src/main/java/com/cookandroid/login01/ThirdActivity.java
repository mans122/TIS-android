package com.cookandroid.login01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Enumeration;
import java.util.Properties;

public class ThirdActivity extends AppCompatActivity {
    TextView textNum,textName,textEmail,textTitle,textContent,textReadCount,textWriteDate;
    String num;
    DownLoadURL myTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        textNum=(TextView)findViewById(R.id.textNum);
        textName=(TextView)findViewById(R.id.textName);
        textEmail=(TextView)findViewById(R.id.textEmail);
        textTitle=(TextView)findViewById(R.id.textTitle);
        textContent=(TextView)findViewById(R.id.textContent);
        textReadCount=(TextView)findViewById(R.id.textReadCount);
        textWriteDate=(TextView)findViewById(R.id.textWriteDate);

        Intent intent = getIntent();
         num= intent.getStringExtra("num");
        String url="http://10.0.2.2:8181/AndroidConn01/BoardView.jsp";
        myTask=new DownLoadURL();
        myTask.execute(url);
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

    //DownLoaURL Thread 작성
    class DownLoadURL extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return (String)downloadUrl((String)urls[0]);
            } catch (IOException e) {
                return "다운로드 실패";
            }
        }

        private String downloadUrl(String myurl) throws IOException {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                /* post방식으로 처리 시작 ==========================*/
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                DataOutputStream out = null;
                out = new DataOutputStream(conn.getOutputStream());
                Properties prop = new Properties();
                // 서버에 전달하는 parameter 값 설정
                prop.setProperty("num", num);
                String encodedString = encodeString(prop);
                out.writeBytes(encodedString);

                out.flush();
                /* post방식으로 처리 끝 ===============================*/
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
                json = json.getJSONObject("view");
                textNum.setText(json.getString("num"));
                textName.setText(json.getString("name"));
                textTitle.setText(json.getString("title"));
                textEmail.setText(json.getString("email"));
                textReadCount.setText(json.getString("readcount"));
                textWriteDate.setText(json.getString("writedate"));
                textContent.setText(json.getString("content"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
