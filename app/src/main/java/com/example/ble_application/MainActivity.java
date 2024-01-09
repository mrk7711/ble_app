package com.example.ble_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter BA = null;
    private boolean scanning;
    private Handler handler = new Handler();
    private static final UUID NOTIFI_SERVICE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final UUID NOTIFI_CHARACTERISTIC = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final long SCAN_PERIOD = 10000;          // Stops scanning after 10 seconds.
    private BluetoothLeScanner BL;
    private boolean scanningEnd;
    BluetoothGattCharacteristic characteristicNotifi;
    String [] permissions=new String[]{Manifest.permission.ACCESS_COARSE_LOCATION};
    int requestCodePermission;
    private static final String TAG ="Main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBTState();
        scanLeDevice();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();}

    private void scanLeDevice() {
        BA = BluetoothAdapter.getDefaultAdapter();
        BL=BA.getBluetoothLeScanner();
        if(!scanningEnd)
        {
            BL.startScan(lescanDevice);
        }
        else {
            BL.stopScan(lescanDevice);
        }
    }

    private ScanCallback lescanDevice= new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            if(!scanningEnd)
            {
                Log.i(TAG, "onScanResult: "+result.getDevice().getAddress()+ ":"+ result.getDevice().getName());
                if(result.getDevice().getAddress().equals("48:23:35:F4:00:17"))
                {
                    scanningEnd=true;
                    BL.stopScan(lescanDevice);
                    result.getDevice().connectGatt(MainActivity.this,false,mGattCallback);
                }
            }
        }
    };

    private BluetoothGattCallback mGattCallback=new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            if(newState== BluetoothProfile.STATE_CONNECTED)
            {
                gatt.discoverServices();
            }
            else if (newState==BluetoothProfile.STATE_DISCONNECTED){
                scanningEnd=false;
            }
        }

        public void onServiceDiscovered(BluetoothGatt gatt,int status){
            super.onServicesDiscovered(gatt,status);
            characteristicNotifi=gatt.getService(NOTIFI_SERVICE).getCharacteristic(NOTIFI_CHARACTERISTIC);
            gatt.setCharacteristicNotification(characteristicNotifi,true);
        }

        public void  onCharacteristicChanged(BluetoothGatt gatt,BluetoothGattCharacteristic characteristic){
            super.onCharacteristicChanged(gatt,characteristic);
        }
    };
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
        }



    private void checkBTState() {
        if (BA == null) {
            errorExit("Error", "Bluetooth not support");
        }
        else {
            if (!BA.isEnabled()) {
                showToast("...Please Turn Bluetooth ON...");
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 100);
                        return;
                    }
                }
                BA.enable();
            } else {
                showToast("Bluetooth is ON!");
            }
        }
    }
    private void errorExit(String title, String message) {
        Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
        finish();
    }
}

