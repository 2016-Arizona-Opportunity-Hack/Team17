package com.readonchandler.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.readonchandler.R;

import java.util.ArrayList;
import java.util.List;

import database.DBContentProvider;
import database.DBHelper;
import model.Event;

public class NearByActivity extends FragmentActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnInfoWindowClickListener {

    private boolean isGPSEnabled;
    private GoogleMap mMap;
    private LocationManager mLocManager;

    private RelativeLayout mProgressLayout;

    private int LOCATION_REQUEST_CODE = 101;

    private List<Event> events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by);
        mProgressLayout = (RelativeLayout) findViewById(R.id.progress_layout);
        mLocManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mLocManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
        DBHelper dbHelper = DBHelper.getInstance(this);
        SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();
        events = DBContentProvider.getAllEvents(sqliteDB);
        if(mLocManager != null)
        {
            isGPSEnabled = mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }
        if(isGPSEnabled){
            updateGoogleMaps();
        } else {
            startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), LOCATION_REQUEST_CODE);
        }
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
        updateMaps();
        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
            mProgressLayout.setVisibility(View.GONE);
        }

     }

    private void updateGoogleMaps(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void updateMaps(){
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            mMap.setMyLocationEnabled(true);
            mProgressLayout.setVisibility(View.GONE);
        }
        double zoomLatitude = 33;
        double zoomLongitude = 110;
        if(events != null && events.size() > 0){
            for(int i = 0; i < events.size(); i++){
                if(i == 0){
                    zoomLatitude = events.get(i).getLatitude();
                    zoomLongitude = events.get(i).getLongitude();
                }
                mMap.addMarker(new MarkerOptions().position(new LatLng(events.get(i).getLatitude(), events.get(i).getLongitude())).title(events.get(i).getName()).snippet(events.get(i).getDate()+" "+events.get(i).getTime()));
            }
        }
        CameraUpdate camerupdate = CameraUpdateFactory.newLatLngZoom(new LatLng(zoomLatitude, zoomLongitude), 10);
        mMap.moveCamera(camerupdate);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == LOCATION_REQUEST_CODE){
            if(mLocManager != null)
            {
                isGPSEnabled = mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            }
            if(!isGPSEnabled){
                Toast.makeText(this, "Please enable GPS to retrieve your current location", Toast.LENGTH_SHORT).show();
            } else {
                updateGoogleMaps();
            }

        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.d("tag", "something");

    }

    private class LocationInfo {
        public double latitude;
        public double longitude;
    }

}
