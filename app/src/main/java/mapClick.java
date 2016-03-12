package com.example.desktop.project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class mapClick extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    public GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("new Map", "");
        setContentView(R.layout.event_map_click);

        Fragment_category_map.mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        Fragment_category_map.mapFragment.getMapAsync(this);
        Log.e("new Map", "");
    }

    @Override
    protected void onResumeFragments() {
        Log.e("resume", "");
        super.onResumeFragments();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (Settings.EventHoldList != null) {
            parseMarker(googleMap);
        }
        initCamera(googleMap);
        mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(final LatLng latLng) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mapClick.this);
        builder.setTitle("Do You Want to Select This Location?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(mapClick.this, Event_Edit.class);
                intent.putExtra("Lat", latLng.latitude);
                intent.putExtra("Lng", latLng.longitude);
                setResult(1, intent);
                mapClick.this.finish();
                Log.e(Double.toString(latLng.latitude), Double.toString(latLng.longitude));
            }
        });
        builder.setNegativeButton("No", null);
        builder.create().show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("asda", "backPressed");
        mapClick.this.finish();
    }

    public void parseMarker(GoogleMap googleMap) {
        ArrayList<Event> arr = new ArrayList<Event>();
        arr.addAll(Settings.EventHoldList);
        for (Event evt : arr) {
            googleMap.addMarker(new MarkerOptions().position(new LatLng(evt.getLat(), evt.getLng())).snippet(evt.getEventDesc()).title(evt.getEventName()));
        }
    }

    public void initCamera(GoogleMap mMap) {
        if (getIntent().hasExtra("savedLat")) {
            LatLng savedPos = new LatLng(getIntent().getDoubleExtra("savedLat", 0.00), getIntent().getDoubleExtra("savedLng", 0.00));
            CameraPosition currCam = new CameraPosition.Builder().target(savedPos).zoom(15).build();
            CameraUpdate pos = CameraUpdateFactory.newCameraPosition(currCam);
            mMap.animateCamera(pos);
        } else {
            MapUtil.initCamera(mMap);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        Log.e("Detach", "");
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDestroy() {
        Log.e("destroy", "");
        super.onDestroy();
    }
}