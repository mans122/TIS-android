package com.cookandroid.login01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {
    EditText editText1,editText2;
    Button button1;
    TextView tv1;
    DownLoadURL myTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=(EditText)findViewById(R.id.edtText1);
        editText2=(EditText)findViewById(R.id.edtText2);
        button1 = (Button)findViewById(R.id.button1);
        tv1=(TextView)findViewById(R.id.textView);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //JSP연동.json파싱해서 ArrayList에 추가
                //post방식일 때 url
                String url="http://10.0.2.2:8181/AndroidConn01/Login.jsp";
                //AsyncTask실행
                myTask=new DownLoadURL();
                myTask.execute(url);
            }
        });
    }

    //Parameter를 인코딩해서 문자열로 리턴
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
    class DownLoadURL extends AsyncTask<String,Void,String>{
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
                prop.setProperty("id", editText1.getText().toString());
                prop.setProperty("pw", editText2.getText().toString());
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
                JSONArray jArr = json.getJSONArray("result");

                for (int i=0; i<jArr.length(); i++) {

                    json = jArr.getJSONObject(i);

                    final String msg    = json.getString("msg");
                    Log.d("디버깅 msg : ",msg);
                    if(msg.equals("ok")){
                        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                        startActivity(intent);
                        //finish();
                    }else{
                        tv1.setText("로그인 실패");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
