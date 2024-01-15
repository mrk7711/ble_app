package com.example.ble_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
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
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter BA = null;
    private boolean scanning;
    private Handler mHandler;
    private static final UUID NOTIFI_SERVICE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final UUID NOTIFI_CHARACTERISTIC = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final long SCAN_PERIOD = 10000;          // Stops scanning after 10 seconds.
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothLeScanner BS;
    private BluetoothGatt BG = null;

    private boolean scanningEnd;
    BluetoothGattCharacteristic characteristicNotifi;
    String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION};
    int requestCodePermission;
    private static final String TAG = "Main";
//    private LeDeviceListAdapter mLeDeviceListAdapter;
    Button b1;
    Button b2;
    Button b3;
    ListView lv;
    TextView status;
    TextView ble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        findViewByIdes();
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
//        BA = BluetoothAdapter.getDefaultAdapter();
        BA =bluetoothManager.getAdapter();
        checkBTState();
        implementListeners();
    }

    private void scanLeDevice() {
        if (!scanningEnd) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= 31) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                    return;
                }
            }
            BS = BA.getBluetoothLeScanner();
            BS.startScan(lescanDevice);
        } else {
            BS.stopScan(lescanDevice);
        }
    }

    private ScanCallback lescanDevice = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
//            super.onScanResult(callbackType, result);
                ble.setText("aa");
                BluetoothDevice device = result.getDevice();
                String uuidsFromScan = String.valueOf(result.getScanRecord().getServiceUuids());
//            if (!scanningEnd) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                        return;
                    }
                }
                ble.append("Device Name: " + device.getName() + " rssi: " + result.getRssi() + "\n");
//                final int scrollAmount = ble.getLayout().getLineTop(ble.getLineCount()) - ble.getHeight();
                showToast("onScanResult: "+ result.getDevice().getAddress()+ ":" + result.getDevice().getName());
                if (result.getDevice().getAddress().equals("48:23:35:F4:00:17")) {
                    scanningEnd = true;
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                        if (Build.VERSION.SDK_INT >= 31) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                            return;
                        }
                    }
                    BS.stopScan(lescanDevice);
//                    device.connectGatt(this, false, mGattCallback);
                }
//            }
        }
    };

//    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
//        @Override
//        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
//            super.onConnectionStateChange(gatt, status, newState);
//            if (newState == BluetoothProfile.STATE_CONNECTED) {
//                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//                    if (Build.VERSION.SDK_INT >= 31) {
//                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
//                        return;
//                    }
//                }
//                showToast("BLE_Connected");
//                gatt.discoverServices();
//            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
//                showToast("BLE_Disconnected");
//                scanningEnd = false;
//            }
//        }
//
//        public void onServiceDiscovered(BluetoothGatt gatt, int status) {
//            super.onServicesDiscovered(gatt, status);
//            characteristicNotifi = gatt.getService(NOTIFI_SERVICE).getCharacteristic(NOTIFI_CHARACTERISTIC);
//            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//                if (Build.VERSION.SDK_INT >= 31) {
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
//                    return;
//                }
//            }
//            gatt.setCharacteristicNotification(characteristicNotifi, true);
//        }
//        public void  onCharacteristicChanged(BluetoothGatt gatt,BluetoothGattCharacteristic characteristic){
//            super.onCharacteristicChanged(gatt,characteristic);
//        }
//    };
//        public void onScanFailed(int errorCode) {
//            showToast("Scan Failed!");
//            scanningEnd = false;
//        }
    private void implementListeners() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!BA.isEnabled()) {
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        if (Build.VERSION.SDK_INT >= 31) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 100);
                            return;
                        }
                    }
                    BA.enable();
                } else {
//                    showToast("Bluetooth is ON!");
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BA.isEnabled()) {
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        if (Build.VERSION.SDK_INT >= 31) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 100);
                            return;
                        }
                    }
                    BA.disable();
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanLeDevice();
                status.setText("Scanning ........");
            }
        });
    }

    private void checkBTState() {
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
//        if (BA == null) {
            errorExit("Error", "Bluetooth Low Energy Not Support");
        } else {
            if(BA.isEnabled()){showToast("Bluetooth is Already ON....");}
            else{showToast("Please Turn Bluetooth ON...");}
        }
    }

    private void findViewByIdes() {
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        lv = findViewById(R.id.lv);
        status = findViewById(R.id.status);
        ble = findViewById(R.id.ble);
    }

    private void errorExit(String title, String message) {
//        Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
        showToast(title + "-" + message);
//       finish();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}




