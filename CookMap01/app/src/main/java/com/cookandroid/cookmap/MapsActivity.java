package com.cookandroid.cookmap;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GroundOverlayOptions marker;
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


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //위치 추가
//        LatLng tis = new LatLng(37.534509, 126.898530);
        LatLng gn = new LatLng(37.501576, 127.026319);
        LatLng agj = new LatLng(37.524533, 127.028844);
        LatLng cd = new LatLng(37.522868, 127.037000);


//        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).position(tis).title("TIS"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).position(gn).title("강남CGV"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).position(agj).title("압구정CGV"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).position(cd).title("청담동CGV"));
//        marker = new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.marker)).position(tis,50f,50f);
//        mMap.addGroundOverlay(marker);
        //강남으로 지도 포커스 이동
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(gn));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gn,13));
        mMap.setMaxZoomPreference(20);
        mMap.setMinZoomPreference(6);


    }
}
