package com.cookandroid.bus01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tv = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.data);

        String serviceUrl = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList"; // 노선ID 추출을 위한 공공DB PAI 의 URL
        String serviceKey = "usmRX9yJpkddJY6Z1WS3gH8TJntcMRRzH%2B37k%2FYMELUYsCr8e%2BNbobhGWvYtQTkfZhS4x5KteS4AmrI73w9r6g%3D%3D"; //노선 ID 추출을 위한 공공DB API키
        String strSrch = "8600"; //노선버스의 노선번호
        String strUrl = serviceUrl+"?ServiceKey="+serviceKey+"&strSrch="+strSrch; //공공 DB API호출을 위한 URL

        new DownloadWebpageTask().execute(strUrl); // URL에 해당하는 문서 실행
    }
    private class DownloadWebpageTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... urls) {
            try{
                return (String)downloadUrl((String)urls[0]); // downloadUrl 클래스
            }catch (IOException e){
                return "다운로드 실패";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();

                String headerCd = "";
                String busRouteId = "";
                String busRouteNm = "";

                boolean bSet_headerCd = false;
                boolean bSet_busRouteId = false;
                boolean bSet_busRouteNm = false;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if(eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if(eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if (tag_name.equals("headerCd"))
                            bSet_headerCd = true;
                        if (tag_name.equals("busRouteId"))
                            bSet_busRouteId = true;
                        if (tag_name.equals("busRouteNm"))
                            bSet_busRouteNm = true;
                    } else if(eventType == XmlPullParser.TEXT) {
                        if (bSet_headerCd) {
                            headerCd = xpp.getText();
                            tv.append("headerCd: " + headerCd + "\n");
                            bSet_headerCd = false;
                        }

                        if (headerCd.equals("0")) {
                            if (bSet_busRouteId) {
                                busRouteId = xpp.getText();
                                tv.append("busRouteId: " + busRouteId + "\n");
                                bSet_busRouteId = false;
                            }
                            if (bSet_busRouteNm) {
                                busRouteNm = xpp.getText();
                                tv.append("busRouteNm; " + busRouteNm + "\n");
                                bSet_busRouteNm = false;
                            }
                        }
                    } else if(eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
                tv.setText(e.getMessage());
            }
        }

        //url에 해당하는 웹문서 다운로드 -----------------------------------------------------------
        private String downloadUrl(String myurl) throws  IOException{
            HttpURLConnection conn = null; // http로 URL 접속을 위한 객체 생성
            try{
                URL url = new URL(myurl); //URL 객체 생성
                conn =(HttpURLConnection)url.openConnection(); // URL객체를 이용한 HTTP 연결
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream()); //버퍼에 문서 다운로드
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf,"utf-8")); //버퍼내용을 UTF-8문서 형식으로 변환
                String line = null;
                String page = "";
                while((line=bufreader.readLine()) != null){
                    page += line; //버퍼내용을 행단위로 읽어 문자 변수에 저장
                }
                return page; // 추출한 웹문서의 문서 내용을 반환
            }finally {
                conn.disconnect();  //http연결 해제
            }
        }
        // -----------------------------------------------------------------------------------------
    }
}
