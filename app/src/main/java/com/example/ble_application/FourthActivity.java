package com.example.ble_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;
import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FourthActivity extends AppCompat {
    private Button b1;
    private Button b2;
    private LocationRequest locationRequest;
    private LocationManager locationManager;
    private static final int REQUEST_LOCATION_SETTINGS = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        b1 = findViewById(R.id.Continue);
        b2 = findViewById(R.id.demo);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R)
                    requestLocationPermissions();
                else
                    enableLocation();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(FourthActivity.this, MainActivity.class));
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOCATION_SETTINGS) {
            if (isLocationEnabled())
            {
                FourthActivity.this.startActivity(new Intent(FourthActivity.this,Instruction.class));
            }
            else
            {
                showToast("GPS is off");
            }
        }
    }
    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void enableLocation() {
        if (!isLocationEnabled()) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent,REQUEST_LOCATION_SETTINGS);
        } else {
            FourthActivity.this.startActivity(new Intent(FourthActivity.this,Instruction.class));
        }
    }
    private void requestLocationPermissions() {
        // Request location permissions if needed
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            enableLocation();
        }
    }
}
