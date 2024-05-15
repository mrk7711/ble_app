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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
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
    Button DisconnectionButton;
    Button ShowButton;
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
    List<BluetoothGattService> services2;

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
                    ConnectionState.setText("Service Discovered");
                    break;
                case STATE_CONNECTING:
                    ConnectionState.setText("Connecting");
                    break;
                case STATE_CONNECTED:
                    ConnectionState.setText("Connected");
                    break;
                case STATE_CONNECTION_FAILED:
                    ConnectionState.setText("Disconnected");
                    break;
                case STATE_DISCOVERY:
                    ConnectionState.setText("Service Dicovery");
                    break;
            }
            return true;
        }
    });

    private void findViewByIdes() {

        Information = findViewById(R.id.status2);
        Address = findViewById(R.id.status4);
        Name = findViewById(R.id.status3);
        ConnectionState = findViewById(R.id.status5);
        ConnectionButton = findViewById(R.id.connect);
        DisconnectionButton = findViewById(R.id.disconnect);
        ShowButton = findViewById(R.id.show);
        mGattServicesList = findViewById(R.id.expand);
        mDataField = findViewById(R.id.status6);
    }

    private void implementListeners2() {
        ShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGattServices(services2);
            }
        });
        ConnectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ConnectionState.setText("yes till now");
//                BG = device3.connectGatt(Gatt_Activity.this, false, mGattCallback);
                if (ActivityCompat.checkSelfPermission(Gatt_Activity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        ActivityCompat.requestPermissions(Gatt_Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                        return;
                    }
                }
                BG = device2.connectGatt(Gatt_Activity.this, false, mGattCallback);
                //mDataField.setText("yes till now");
            }
        });

        DisconnectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(Gatt_Activity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        ActivityCompat.requestPermissions(Gatt_Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                        return;
                    }
                }
                BG.disconnect();
                BG.close();
                mDataField.setText("Disconnected");
            }
        });
    }

    private final BluetoothGattCallback mGattCallback =
            new BluetoothGattCallback() {
                @Override
                public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                    String intentAction;
                    if (newState == BluetoothProfile.STATE_CONNECTED) {
                        handler.obtainMessage(STATE_CONNECTED, gatt).sendToTarget();
                        if (ActivityCompat.checkSelfPermission(Gatt_Activity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                            if (Build.VERSION.SDK_INT >= 31) {
                                ActivityCompat.requestPermissions(Gatt_Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                                return;
                            }
                        }
                        gatt.discoverServices();
                    } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                        handler.obtainMessage(STATE_CONNECTION_FAILED, gatt).sendToTarget();
                    }
                }
                @Override
                public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                    if (status == BluetoothGatt.GATT_SUCCESS) {
                        handler.obtainMessage(STATE_DISCOVERED, gatt).sendToTarget();
                        List<BluetoothGattService> services = gatt.getServices();
                        services2=services;
                        //displayGattServices(services);
                    }
                }
                @Override
                public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                    Log.d(TAG, "onCharacteristicRead characteristic: " + characteristic.getUuid());
                    if (status == BluetoothGatt.GATT_SUCCESS) {
                    }
                }
                @Override
                public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                    final byte[] data = characteristic.getValue();
                }
            };
    private static HashMap<String, String> attributes = new HashMap();
    public static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null)
        {
            ConnectionState.setText("Look1");
            return;
        }
        ConnectionState.setText("Look2");
        String uuid = null;
        int i=0;
        String unknownServiceString = getResources().getString(R.string.unknown_service);
        String unknownCharaString = getResources().getString(R.string.unknown_characteristic);
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<HashMap<String, String>>();
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData = new ArrayList<ArrayList<HashMap<String, String>>>();
        mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();
        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices){
            HashMap<String, String> currentServiceData = new HashMap<String, String>();
            uuid = gattService.getUuid().toString();
            currentServiceData.put(LIST_NAME, SampleGattAttributes.lookup(uuid, unknownServiceString));
            //currentServiceData.put(LIST_NAME,lookup(uuid, unknownServiceString));
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);
            ArrayList<HashMap<String, String>> gattCharacteristicGroupData = new ArrayList<HashMap<String, String>>();
            List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas = new ArrayList<BluetoothGattCharacteristic>();
            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();
                currentCharaData.put(LIST_NAME, SampleGattAttributes.lookup(uuid,unknownCharaString));
                //currentCharaData.put(LIST_NAME,lookup(uuid, unknownCharaString));
                currentCharaData.put(LIST_UUID, uuid);
                gattCharacteristicGroupData.add(currentCharaData);
            }
            mGattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
            i++;
        }
        mDataField.setText(String.valueOf(i));
        SimpleExpandableListAdapter gattServiceAdapter = new SimpleExpandableListAdapter(this, gattServiceData, android.R.layout.simple_expandable_list_item_2, new String[] {LIST_NAME, LIST_UUID}, new int[] { android.R.id.text1, android.R.id.text2 }, gattCharacteristicData, android.R.layout.simple_expandable_list_item_2, new String[] {LIST_NAME, LIST_UUID}, new int[] { android.R.id.text1, android.R.id.text2 }
        );
        mGattServicesList.setAdapter(gattServiceAdapter);
        }


}





