package com.cookandroid.cookmap;

import androidx.fragment.app.FragmentActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GroundOverlayOptions marker;
    private GoogleMap mMap;
    private String serviceUrl="http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
    private String serviceKey;
    private String strSrch;
    private String strUrl;
    private String busRouteId;
    private DownloadWebpageTask1 task1;
    private DownloadWebpageTask2 task2;
    private String serviceUrl2 = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid";
    private String strUrl2;
    private String gpsX;
    private String gpsY;
    private String plainNo;
    Button btnRe = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnRe = (Button)findViewById(R.id.btnRe);
        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task1 = new DownloadWebpageTask1();
                mMap.clear();
                task1.execute(strUrl);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        serviceKey = "usmRX9yJpkddJY6Z1WS3gH8TJntcMRRzH%2B37k%2FYMELUYsCr8e%2BNbobhGWvYtQTkfZhS4x5KteS4AmrI73w9r6g%3D%3D"; //노선 ID 추출을 위한 공공DB API키
        strSrch = "8600"; //노선버스의 노선번호
        strUrl = serviceUrl + "?ServiceKey=" + serviceKey + "&strSrch=" + strSrch;
        task1 = new DownloadWebpageTask1();
        task1.execute(strUrl);


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.529144, 126.917075), 14));
        mMap.setMaxZoomPreference(16);
        mMap.setMinZoomPreference(10);

    }

    private class DownloadWebpageTask1 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return (String) downloadUrl((String) urls[0]); // downloadUrl 클래스
            } catch (IOException e) {
                return "다운로드 실패";
            }
        }

        @Override
        protected void onPostExecute(String result) {

            String headerCd = "";
            String busRouteNm = "";

            boolean bSet_headerCd = false;
            boolean bSet_busRouteId = false;
            boolean bSet_busRouteNm = false;
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();


                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if (eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if (tag_name.equals("headerCd"))
                            bSet_headerCd = true;
                        if (tag_name.equals("busRouteId"))
                            bSet_busRouteId = true;
                        if (tag_name.equals("busRouteNm"))
                            bSet_busRouteNm = true;
                    } else if (eventType == XmlPullParser.TEXT) {
                        if (bSet_headerCd) {
                            headerCd = xpp.getText();
                            bSet_headerCd = false;
                        }

                        if (headerCd.equals("0")) {
                            if (bSet_busRouteId) {
                                busRouteId = xpp.getText();
                                bSet_busRouteId = false;
                            }
                            if (bSet_busRouteNm) {
                                busRouteNm = xpp.getText();
                                bSet_busRouteNm = false;
                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
            }
            serviceUrl2 = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid";
            strUrl2 = serviceUrl2 + "?ServiceKey=" + serviceKey + "&busRouteId=" + busRouteId;

            task2 = new DownloadWebpageTask2();
            task2.execute(strUrl2);

        }

        //url에 해당하는 웹문서 다운로드 -----------------------------------------------------------
        public String downloadUrl(String myurl) throws IOException {
            HttpURLConnection conn = null; // http로 URL 접속을 위한 객체 생성
            try {
                URL url = new URL(myurl); //URL 객체 생성
                conn = (HttpURLConnection) url.openConnection(); // URL객체를 이용한 HTTP 연결
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream()); //버퍼에 문서 다운로드
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8")); //버퍼내용을 UTF-8문서 형식으로 변환
                String line = null;
                String page = "";
                while ((line = bufreader.readLine()) != null) {
                    page += line; //버퍼내용을 행단위로 읽어 문자 변수에 저장
                }
                return page; // 추출한 웹문서의 문서 내용을 반환
            } finally {
                conn.disconnect();  //http연결 해제
            }
        }
    }


    private class DownloadWebpageTask2 extends DownloadWebpageTask1 {

        protected void onPostExecute(String result) {
            String headerCd = "";
            boolean bSet_headerCd = false;
            boolean bSet_gpsX = false;
            boolean bSet_gpsY = false;
            boolean bSet_plainNo = false;

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();


                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if (eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if (tag_name.equals("headerCd"))
                            bSet_headerCd = true;
                        if (tag_name.equals("gpsX"))
                            bSet_gpsX = true;
                        if (tag_name.equals("gpsY"))
                            bSet_gpsY = true;
                        if (tag_name.equals("plainNo"))
                            bSet_plainNo = true;
                    } else if (eventType == XmlPullParser.TEXT) {
                        if (bSet_headerCd) {
                            headerCd = xpp.getText();
                            bSet_headerCd = false;
                        }
                        if (headerCd.equals("0")) {
                            if (bSet_gpsX) {
                                gpsX = xpp.getText();
                                bSet_gpsX = false;
                            }
                            if (bSet_gpsY) {
                                gpsY = xpp.getText();
                                bSet_gpsY = false;
                            }
                            if (bSet_plainNo) {
                                plainNo = xpp.getText();
                                bSet_plainNo = false;
                                displayBus(gpsX, gpsY, plainNo);
                            }
                        }

                    } else if (eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
            }
            Toast.makeText(getApplicationContext(),"로드 완료",Toast.LENGTH_SHORT).show();
        }

        private void displayBus(String gpsX, String gpsY, String plainNo) {
            double lat;
            double lng;
            LatLng LOC;
            lat = Double.parseDouble(gpsY);
            lng = Double.parseDouble(gpsX);
            LOC = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).position(new LatLng(37.525323, 126.924185)).title("여의도환승센터")).setZIndex(-1);
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).position(new LatLng(37.535000, 126.899761)).title("당산역")).setZIndex(-1);
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)).position(LOC).title(plainNo)).setZIndex(1);

        }
    }
}


