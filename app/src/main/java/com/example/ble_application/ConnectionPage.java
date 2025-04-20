package com.example.ble_application;
import android.Manifest;
import android.animation.ObjectAnimator;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Handler;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class ConnectionPage extends AppCompat {
    private static final int REQUEST_PERMISSION = 2;
    private Handler handler;
    private BluetoothAdapter BA = null;
    private BluetoothLeScanner BS;
    private static final String BLE_DEVICE_ADDRESS = "F4:35:23:48:00:00";
    private static final String BLE_DEVICE_ADDRESS2 = "48:23:35:F4:00:0B";
    BluetoothDevice ble_device;
    private final long SCAN_PERIOD = 10000;
    private TextView t1,t2,t3;
    private ProgressBar p1;
    private Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_page);
        handler = new Handler();
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        BA = bluetoothManager.getAdapter();
        ProgressBar progressBar = findViewById(R.id.progressBar);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(progressBar, "rotation", 0f, 360f);
        rotate.setDuration(7000);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(ObjectAnimator.INFINITE);
        rotate.start(); //
        t1 = findViewById(R.id.device_name_text);
        t2 = findViewById(R.id.device_name_text2);
        t3 = findViewById(R.id.device_name_text3);
        b2 = findViewById(R.id.demo2);
        b1 = findViewById(R.id.search);
        p1 = findViewById(R.id.progressBar);
        BS = BA.getBluetoothLeScanner();
        startBleScan();

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ble_device != null)
                {
                    Intent intent = new Intent(ConnectionPage.this, Gatt_Activity.class);
                    intent.putExtra("BLE", ble_device);
                    startActivity(intent);
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBleScan();
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConnectionPage.this, DemoSlideActivity.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        startBleScan();
    }
    private void startBleScan() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(ConnectionPage.this, android.Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ConnectionPage.this, new String[]{android.Manifest.permission.BLUETOOTH_SCAN}, REQUEST_PERMISSION);
                return;
            }
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ConnectionPage.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ConnectionPage.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION);
                return;
            }
        }
        BS.startScan(bleScanCallback);
        t1.setText("");
        t2.setText("");
        t3.setText("");
        p1.setVisibility(ProgressBar.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopBleScan();
                p1.setVisibility(ProgressBar.INVISIBLE);
                t1.setText(R.string.notfound);
                t2.setText(R.string.make1);
                t3.setText(R.string.make2);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
            }
        }, SCAN_PERIOD);
    }

    private void stopBleScan() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, REQUEST_PERMISSION);
                return;
            }
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION);
                return;
            }
        }
        BS.stopScan(bleScanCallback);
    }

    private ScanCallback bleScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);

            BluetoothDevice device = result.getDevice();
            String deviceAddress = device.getAddress();

            if (deviceAddress.equals(BLE_DEVICE_ADDRESS)) {
                ble_device=device;
                t1.setText("Arnica Hearing Aid");
                //t1.setText( device.getAddress());
                stopBleScan();
                handler.removeCallbacksAndMessages(null);
            }
            if (deviceAddress.equals(BLE_DEVICE_ADDRESS2)) {
                ble_device=device;
                t1.setText("Arnica Hearing Aid2");
                //t1.setText( device.getAddress());
                stopBleScan();
                handler.removeCallbacksAndMessages(null);
            }
            //else {t1.setText(deviceAddress);}
        }
    };

}