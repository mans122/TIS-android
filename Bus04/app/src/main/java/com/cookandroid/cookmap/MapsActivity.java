package com.cookandroid.cookmap;

import androidx.fragment.app.FragmentActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String serviceKey = "usmRX9yJpkddJY6Z1WS3gH8TJntcMRRzH%2B37k%2FYMELUYsCr8e%2BNbobhGWvYtQTkfZhS4x5KteS4AmrI73w9r6g%3D%3D";
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).position(new LatLng(37.525323, 126.924185)).title("당산역"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).position(new LatLng(37.535000, 126.899761)).title("당산역"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.529144, 126.917075), 14));
        mMap.setMaxZoomPreference(16);
        mMap.setMinZoomPreference(10);
        // Add a marker in Sydney and move the camera
        String url="http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?serviceKey="+serviceKey+"&strSrch=8600";
        DownloadWebpageTask1 task1 = new DownloadWebpageTask1();
        task1.execute(url);

    }


    private class DownloadWebpageTask1 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String busRouteId="";
            try {
                Document doc = Jsoup.connect(urls[0]).get();
                Elements headerCd_elements = doc.select("busRouteId");
                for (Element item : headerCd_elements) {
                    busRouteId =item.text().trim();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return busRouteId;
        }

        protected void onPostExecute(String busRouteId) {
            String url="http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?serviceKey=usmRX9yJpkddJY6Z1WS3gH8TJntcMRRzH%2B37k%2FYMELUYsCr8e%2BNbobhGWvYtQTkfZhS4x5KteS4AmrI73w9r6g%3D%3D&busRouteId="+busRouteId;
            Log.d("==url==",url);
            new DownloadWebpageTask2().execute(url);
        }
    }

    private class DownloadWebpageTask2 extends AsyncTask<String,Void,String> {

        Document doc;
        Elements gpsX_elements;
        Elements gpsY_elements;
        Elements plainNo_elements;

        private void displayBus(String gpsX, String gpsY, String plainNo) {

            double latitude;
            double longitude;
            LatLng LOC;

            latitude = Double.parseDouble(gpsY);
            longitude = Double.parseDouble(gpsX);
            LOC = new LatLng(latitude, longitude);
            Marker mk1 = mMap.addMarker(new MarkerOptions()
                    .position(LOC)
                    .title(plainNo)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(LOC));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(11));

        }

        //주요 내용 실행
        @Override
        protected String doInBackground(String... urls) {
            String busRouteId="";
            try {
                doc = Jsoup.connect(urls[0]).get();
                gpsX_elements = doc.select("gpsX");
                gpsY_elements = doc.select("gpsY");
                plainNo_elements = doc.select("plainNo");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        protected void onPostExecute(String result) {

            for (int i=0;i<gpsX_elements.size();i++) {
                String gpsX = gpsX_elements.get(i).text().trim(); // gpsX
                String gpsY = gpsY_elements.get(i).text().trim(); //gpsY
                String plainNo = plainNo_elements.get(i).text().trim(); //plainNo

                displayBus(gpsX, gpsY, plainNo);
            }
        }
    }
}
