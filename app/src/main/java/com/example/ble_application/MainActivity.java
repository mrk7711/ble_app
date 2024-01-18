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

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter BA = null;
    private Handler handler;
    //    private LeDeviceListAdapter leDeviceListAdapter = new LeDeviceListAdapter();
    private static final UUID NOTIFI_SERVICE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final UUID NOTIFI_CHARACTERISTIC = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final long SCAN_PERIOD = 10000;          // Stops scanning after 10 seconds.
    private static final int REQUEST_ENABLE_BT = 1;
    private ArrayList<BluetoothDevice> mLeDevices;
    private BluetoothLeScanner BS;
    private BluetoothGatt BG = null;
    BluetoothDevice[] btArray;
    private boolean scanningEnd;
    BluetoothGattCharacteristic characteristicNotifi;
    private static final String TAG = "Main";
    //    private LeDeviceListAdapter mLeDeviceListAdapter;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    ListView lv;
    TextView status;
    TextView ble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        findViewByIdes();
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
//        BA = BluetoothAdapter.getDefaultAdapter();
        BA = bluetoothManager.getAdapter();
        checkBTState();
        implementListeners();
        mLeDevices = new ArrayList<BluetoothDevice>();
    }

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

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                        return;
                    }
                }
                BS.stopScan(lescanDevice);
                status.setText("Scan Finished ..");
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                status.setText("Connecting");
                BluetoothDevice selectedDevice = btArray[i];
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                        return;
                    }
                }
                ble.setText("Device Name : " + selectedDevice.getName() +" \n");
                Intent senderIntent = new Intent(MainActivity.this,Gatt_Activity.class);
                senderIntent.putExtra("BLE",selectedDevice);
                MainActivity.this.startActivity(senderIntent);
//                MainActivity.this.startActivity(new Intent(MainActivity.this, Gatt_Activity.class));
            }
        });
    }

    private void scanLeDevice() {
        if (!scanningEnd) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= 31) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
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
}




