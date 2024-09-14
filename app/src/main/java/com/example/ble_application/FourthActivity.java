package com.example.ble_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.location.LocationManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FourthActivity extends AppCompatActivity {
    Button b1;
    Button b2;
    private LocationRequest locationRequest;
    private LocationManager locationManager;
    private static final int REQUEST_LOCATION_SETTINGS = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        b1 = findViewById(R.id.Continue);
        b2 = findViewById(R.id.demo);
        // دریافت LocationManager برای دسترسی به تنظیمات لوکیشن
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // بررسی وضعیت روشن یا خاموش بودن GPS
                if (!isLocationEnabled()) {
                    // اگر GPS خاموش است، کاربر را به تنظیمات لوکیشن هدایت کنید
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(intent, REQUEST_LOCATION_SETTINGS);
                } else {
                    // اگر GPS روشن است، کاربر را به صفحه بعدی هدایت کنید
                    FourthActivity.this.startActivity(new Intent(FourthActivity.this,MainActivity.class));
                    FourthActivity.this.finish();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FourthActivity.this, MainActivity.class));
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOCATION_SETTINGS) {
            if (isLocationEnabled())
            {
                FourthActivity.this.startActivity(new Intent(FourthActivity.this,MainActivity.class));
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

}
