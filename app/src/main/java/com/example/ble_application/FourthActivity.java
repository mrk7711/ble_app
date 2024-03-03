package com.example.ble_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FourthActivity extends AppCompatActivity {
    Button b1;
    Button b2;
    private LocationRequest locationRequest;
    public static final int Request_Check_Setting=1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        b1 = findViewById(R.id.Continue);
        b2 = findViewById(R.id.demo);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ActivityCompat.requestPermissions(FourthActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                ActivityCompat.requestPermissions(FourthActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,}, 1);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FourthActivity.this, MainActivity.class));
            }
        });
    }
}