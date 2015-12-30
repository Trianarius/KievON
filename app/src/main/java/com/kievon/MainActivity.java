package com.kievon;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;


import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

  SupportMapFragment mapFregment;
  GoogleMap map;
  final String TAG = "myLogs";
         
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnTest3).setOnClickListener(this);
        findViewById(R.id.btnTest).setOnClickListener(this);
        findViewById(R.id.btnTest2).setOnClickListener(this);
        mapFregment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map=mapFregment.getMap();
        if(map==null){
            finish();
            return;
        }
        init();
    }

    private void init() {
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.d(TAG,"onClickMap:"+latLng.latitude+","+ latLng.longitude);
            }
        });map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Log.d(TAG, "onLongClickMap:" + latLng.latitude + "," + latLng.longitude);
            }
        });
        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                Log.d(TAG,"onCameraChange:"+ cameraPosition.target.latitude+ " " +cameraPosition.target.longitude);
            }
        });

    }

    private void onClickTest2(View view){
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
    private void onClickTest(View view){

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);



    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTest2:
                onClickTest(v);
                break;
            case R.id.btnTest:
                onClickTest2(v);
                break;
            case R.id.btnTest3:
                Intent intent = new Intent(MainActivity.this, Authorization.class);
                startActivity(intent);
                break;

        }

    }
}
































