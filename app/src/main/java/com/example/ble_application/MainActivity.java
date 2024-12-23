package com.example.ble_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.ScanSettings;
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
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompat {
    private BluetoothAdapter BA = null;
    private Handler handler;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_PERMISSION = 2;
    //    private LeDeviceListAdapter leDeviceListAdapter = new LeDeviceListAdapter();
    private static final UUID NOTIFI_SERVICE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final UUID NOTIFI_CHARACTERISTIC = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final long SCAN_PERIOD = 10000;          // Stops scanning after 10 seconds.
    private ArrayList<BluetoothDevice> mLeDevices;
    private BluetoothLeScanner BS;
    private BluetoothGatt BG = null;
    BluetoothDevice[] btArray;
    BluetoothDevice ble_device;
    private boolean scanningEnd;
    private static final String BLE_DEVICE_ADDRESS = "48:23:35:F4:00:0B";
    BluetoothGattCharacteristic characteristicNotifi;
    private static final String TAG = "Main";
    //    private LeDeviceListAdapter mLeDeviceListAdapter;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private ListView lv;
    private TextView status;
    private TextView ble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        findViewByIdes();
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        BA = bluetoothManager.getAdapter();
        checkBTState();
        implementListeners();
        mLeDevices = new ArrayList<BluetoothDevice>();
    }

    private void implementListeners() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VolumePage.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SlideActivity.class));
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanLeDevice();
                status.setText("Scanning ........");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, REQUEST_PERMISSION);
                        return;
                    }
                }
                else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION);
                        return;
                    }
                }
                BS.stopScan(lescanDevice);
                status.setText("Scan Finished ..");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ble_device=BA.getRemoteDevice(BLE_DEVICE_ADDRESS);
                if (ble_device != null)
                {
                    Intent senderIntent = new Intent(MainActivity.this, Gatt_Activity.class);
                    senderIntent.putExtra("BLE", ble_device);
                    MainActivity.this.startActivity(senderIntent);
                    clearUI();
                }
                else {
                    showToast("Not_OK");
                }
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                status.setText("Connecting");
                BluetoothDevice selectedDevice = btArray[i];
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, REQUEST_PERMISSION);
                        return;
                    }
                }
                else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION);
                        return;
                    }
                }
                ble.setText("Device Name : " + selectedDevice.getName() + " \n");
                Intent senderIntent = new Intent(MainActivity.this, Gatt_Activity.class);
                senderIntent.putExtra("BLE", selectedDevice);
                MainActivity.this.startActivity(senderIntent);
                clearUI();
//                MainActivity.this.startActivity(new Intent(MainActivity.this, Gatt_Activity.class));
            }
        });
    }

    private void clearUI() {
        lv.setAdapter(null);
        ble.setText(null);
        status.setText(null);
    }

    private void scanLeDevice() {
        if (!scanningEnd) {
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

            BS = BA.getBluetoothLeScanner();
            BS.startScan(lescanDevice);
        } else {
            BS.stopScan(lescanDevice);
        }
    }

    private ScanCallback lescanDevice = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            BluetoothDevice device = result.getDevice();
            mLeDevices.add(device);
            String[] strings = new String[mLeDevices.size()];
            btArray = new BluetoothDevice[mLeDevices.size()];
            int index = 0;
//            if (mLeDevices.size() > 0) {
            for (BluetoothDevice a : mLeDevices) {
                btArray[index] = a;
                strings[index] = a.getAddress();
                index++;

                }
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,strings);
                lv.setAdapter(arrayAdapter);
//            }
//            if (!scanningEnd) {

//                ble.append("Device Address: " + device.getName() + " rssi: " + result.getRssi() + "\n");

//                if (result.getDevice().getAddress().equals("48:23:35:F4:00:17")) {
//                    scanningEnd = true;
//                    device.connectGatt(this, false, mGattCallback);
//                }
//            }
        }
    };

    private void checkBTState() {

       if (BA == null) {
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
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        lv = findViewById(R.id.lv);
        status = findViewById(R.id.status);
        ble = findViewById(R.id.ble);
    }

    private void errorExit(String title, String message) {
//        Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
            showToast(title + "-" + message);
//            finish();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                scanLeDevice();
            } else {
                Toast.makeText(this, "Permission required", Toast.LENGTH_SHORT).show();
            }
        }
    }
}




