package com.example.ble_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

public class Gatt_Activity extends AppCompatActivity {
    TextView status2;
    TextView status3;
    TextView status4;
    Button b_1;
    private boolean scanningEnd;
    private static final UUID NOTIFI_SERVICE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final UUID NOTIFI_CHARACTERISTIC = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    BluetoothGattCharacteristic characteristicNotifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatt);
        findViewByIdes();

        Intent a = getIntent();
        BluetoothDevice device2 = getIntent().getExtras().getParcelable("BLE");
        status2.setText("Device Information:");
        status3.setText(device2.getAddress());
        if (ContextCompat.checkSelfPermission(Gatt_Activity.this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= 31) {
                ActivityCompat.requestPermissions(Gatt_Activity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 100);
                return;
            }
        }
        status4.setText(device2.getName());
        implementListeners2();
    }

    private void findViewByIdes() {

        status2 = findViewById(R.id.status2);
        status3 = findViewById(R.id.status3);
        status4 = findViewById(R.id.status4);
        b_1 = findViewById(R.id.connect);
    }

    private void implementListeners2() {
        b_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                device2.connectGatt(this, false, mGattCallback);
                }

        });
    }


    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                if (ActivityCompat.checkSelfPermission(Gatt_Activity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        ActivityCompat.requestPermissions(Gatt_Activity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 100);
                        return;
                    }
                }
                gatt.discoverServices();
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                scanningEnd = false;
            }
        }

        public void onServiceDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            characteristicNotifi = gatt.getService(NOTIFI_SERVICE).getCharacteristic(NOTIFI_CHARACTERISTIC);
            if (ActivityCompat.checkSelfPermission(Gatt_Activity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= 31) {
                    ActivityCompat.requestPermissions(Gatt_Activity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 100);
                    return;
                }
            }
            gatt.setCharacteristicNotification(characteristicNotifi, true);
        }

        public void  onCharacteristicChanged(BluetoothGatt gatt,BluetoothGattCharacteristic characteristic){
            super.onCharacteristicChanged(gatt,characteristic);
        }
    };
//        public void onScanFailed(int errorCode) {
//            super.onScanFailed(errorCode);
//        }
}