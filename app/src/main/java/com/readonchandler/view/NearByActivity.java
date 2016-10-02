package com.readonchandler.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.readonchandler.R;

public class NearByActivity extends FragmentActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private boolean isGPSEnabled;
    private GoogleMap mMap;
    private LocationManager mLocManager;

    private ProgressBar mLocationProgress;

    private int LOCATION_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by);
        mLocationProgress = (ProgressBar) findViewById(R.id.location_progress);
        mLocManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mLocManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
            mLocationProgress.setVisibility(View.GONE);
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
            mLocationProgress.setVisibility(View.GONE);
        }
        mMap.addMarker(new MarkerOptions().position(new LatLng(33.420534, -111.933983)).title("Event 1"+"\n"+"date: 5 oct"+"\n"+"Time 08:30 AM"+"\n"+"Location Tempe"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(33.436020, -112.043288)).title("Event 2"+"\n"+"date: 10 oct"+"\n"+"Time 10:30 AM"+"\n"+"Location Tucson"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(33.415184, -111.831472)).title("Event 3"+"\n"+"date: 15 oct"+"\n"+"Time 12:00 PM"+"\n"+"Location Flagstaff"));
        CameraUpdate camerupdate = CameraUpdateFactory.newLatLngZoom(new LatLng(33.425510, -111.940005), 10);
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

}
