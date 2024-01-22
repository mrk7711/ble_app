package com.example.ble_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Gatt_Activity extends AppCompatActivity {
    TextView Information;       //Device Information
    TextView Address;           //Device Address
    TextView Name;              //Device Name
    TextView ConnectionState;      //Connection State
    TextView mDataField;
    Button ConnectionButton;
    BluetoothDevice device2;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private final static String TAG = "BluetoothLeService";
    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
    private Handler handler2;
    private BluetoothAdapter BA;
    private BluetoothGatt BG;
    private String mDeviceName;
    private String mDeviceAddress;
    private ExpandableListView mGattServicesList;
    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();
    private boolean mConnected;
    private BluetoothGattCharacteristic mNotifyCharacteristic;
    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";
    BluetoothGattCallback getmGattCallback;
    public final static String ACTION_GATT_CONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED =
            "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE =
            "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
    public final static String EXTRA_DATA =
            "com.example.bluetooth.le.EXTRA_DATA";
    static final int STATE_DISCOVERED = 1;
    static final int STATE_CONNECTING = 2;
    static final int STATE_CONNECTED = 3;
    static final int STATE_CONNECTION_FAILED = 4;
    static final int STATE_MESSAGE_RECEIVED = 5;
    static final int STATE_DISCOVERY = 6;
    private int connectionState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatt);
        findViewByIdes();
        Intent a = getIntent();
        device2 = getIntent().getExtras().getParcelable("BLE");
        Information.setText("Device Information:");
        Address.setText(device2.getAddress());
        if (ContextCompat.checkSelfPermission(Gatt_Activity.this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= 31) {
                ActivityCompat.requestPermissions(Gatt_Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                return;
            }
        }
        Name.setText(device2.getName());
        mDeviceName = device2.getName();
        mDeviceAddress = device2.getAddress();
        implementListeners2();
        if (device2 == null) {
            showToast("There is not any Device!");
        }
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what) {
                case STATE_DISCOVERED:
                    ConnectionState.setText("Service Discovery");
                    break;
                case STATE_CONNECTING:
                    ConnectionState.setText("Connecting");
                    break;
                case STATE_CONNECTED:
                    ConnectionState.setText("Connected");
                    break;
                case STATE_CONNECTION_FAILED:
                    ConnectionState.setText("Connection Failed");
                    break;
                case STATE_DISCOVERY:
                    ConnectionState.setText("Service Dicovered");
                    break;
            }
            return true;
        }
    });

    private void findViewByIdes() {

        Information = findViewById(R.id.status2);
        Address = findViewById(R.id.status3);
        Name = findViewById(R.id.status4);
        ConnectionState = findViewById(R.id.status5);
        ConnectionButton = findViewById(R.id.connect);
        mGattServicesList = findViewById(R.id.expand);
        mDataField = findViewById(R.id.status6);
    }

    private void implementListeners2() {
        ConnectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectionState.setText("yes till now");
//                BG = device3.connectGatt(Gatt_Activity.this, false, mGattCallback);
                if (ActivityCompat.checkSelfPermission(Gatt_Activity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        ActivityCompat.requestPermissions(Gatt_Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                        return;
                    }
                }
                device2.connectGatt(Gatt_Activity.this, false, mGattCallback);
                mDataField.setText("yes till now");
            }
        });
    }

    private final BluetoothGattCallback mGattCallback =
            new BluetoothGattCallback() {
                @Override
                public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                    String intentAction;
                    if (newState == BluetoothProfile.STATE_CONNECTED) {
                        handler.obtainMessage(STATE_DISCOVERED, gatt).sendToTarget();
//                        connectionState = STATE_CONNECTED;
                        if (ActivityCompat.checkSelfPermission(Gatt_Activity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                            if (Build.VERSION.SDK_INT >= 31) {
                                ActivityCompat.requestPermissions(Gatt_Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                                return;
                            }
                        }
                        BG.discoverServices();
                        mDataField.setText("Service Discovery Finished!");
                    } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                        handler.obtainMessage(STATE_CONNECTION_FAILED, gatt).sendToTarget();
//                        connectionState = STATE_CONNECTION_FAILED;
                    }
                }

                @Override
                public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                    if(status == BluetoothGatt.GATT_SUCCESS) {
//                        List<BluetoothGattService> services = gatt.getServices();
                        handler.obtainMessage(STATE_DISCOVERY, gatt).sendToTarget();
                    }
                    showToast("Hi");
                }

                @Override
                public void onCharacteristicRead(BluetoothGatt gatt,
                                                 BluetoothGattCharacteristic characteristic,
                                                 int status) {
                    Log.d(TAG, "onCharacteristicRead characteristic: " + characteristic.getUuid());
                    if (status == BluetoothGatt.GATT_SUCCESS) {
                    }
                }
            };

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}



