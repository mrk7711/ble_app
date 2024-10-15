package com.example.ble_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.widget.ProgressBar;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Gatt_Activity extends AppCompatActivity {
    private TextView Information;       //Device Information
    private TextView Address;           //Device Address
    private TextView Name;              //Device Name
    private TextView ConnectionState;      //Connection State
    private TextView mDataField;
    private TextView title;
    private ProgressBar p1;
    private Button ConnectionButton;
    private Button DisconnectionButton;
    private Button ShowButton;
    private Button Notif;
    private Button Pair;
    private  Button Cont;
    BluetoothDevice device2;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final UUID I2C_UUID_Service = UUID.fromString("18424398-7cbc-11e9-8f9e-2a86e4085a59");
    private static final UUID I2C_UUID_Characteristic = UUID.fromString("5b87b4ef-3bfa-76a8-e642-92933c31434c");
    private static final UUID I2C_UUID_Descriptor = null;
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
    public final static UUID UUID_HEART_RATE_MEASUREMENT =
            UUID.fromString(SampleGattAttributes.HEART_RATE_MEASUREMENT);
    static final int STATE_DISCOVERED = 1;
    static final int STATE_CONNECTING = 2;
    static final int STATE_CONNECTED = 3;
    static final int STATE_CONNECTION_FAILED = 4;
    static final int STATE_MESSAGE_RECEIVED = 5;
    static final int STATE_DISCOVERY = 6;
    private int connectionState;
    List<BluetoothGattService> services2;
    List<BluetoothGattDescriptor> desc;
    BluetoothGattService service3=null;
    BluetoothGattCharacteristic char1=null;
    BluetoothGattDescriptor desc1=null;
    int a;
    String b;
    String c;
    byte[] d;
    int e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatt);
        findViewByIdes();
        Intent a = getIntent();
        device2 = getIntent().getExtras().getParcelable("BLE");
        Information.setText("Device Name:");
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
    @Override
    protected void onResume() {
        super.onResume();
    }
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what) {
                case STATE_DISCOVERED:
                    p1.setVisibility(View.INVISIBLE);
                    Pair.setVisibility(View.INVISIBLE);
                    title.setText("Pairing Completed!");
                    Cont.setVisibility(View.VISIBLE);
                    //ConnectionState.setText("Service Discovered");
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
        //mGattServicesList = findViewById(R.id.expand);
        mDataField = findViewById(R.id.status6);
        Notif=findViewById(R.id.Notif);
        Pair=findViewById(R.id.pair);
        p1=findViewById(R.id.progressBar);
        title=findViewById(R.id.label1);
        Cont=findViewById(R.id.cont);
    }

    private void implementListeners2() {
        ShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(service3!=null)
                {
                    //showToast("detected");

                    if(char1!= null)
                    {
                        //showToast("Again detected");
                        //showToast(String.valueOf(a));
                        if(e != 0 )
                        {
                            Notif.setVisibility(View.VISIBLE);
                        }
                        //showToast(b);
                    }
                }
                if(service3==null)
                {
                    showToast("Not detected");
                }
                //displayGattServices(services3);
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
                ConnectionState.setText("Disconnected");
                services2 = null;
                Notif.setVisibility(View.INVISIBLE);
                clearUI();
            }
        });
        Notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Gatt_Activity.this, SlideActivity.class));

            }
        });

        Pair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p1.setVisibility(View.VISIBLE);
                if (ActivityCompat.checkSelfPermission(Gatt_Activity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        ActivityCompat.requestPermissions(Gatt_Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                        return;
                    }
                }
                BG = device2.connectGatt(Gatt_Activity.this, false, mGattCallback);
                Pair.setText("Pairing..");
            }
        });
        Cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Gatt_Activity.this, SlideActivity.class));

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
                        services2 = services;
                        service3=gatt.getService(I2C_UUID_Service);
                        char1=service3.getCharacteristic(I2C_UUID_Characteristic);

                        desc=char1.getDescriptors();
                        a= desc.size();
                        e=char1.PROPERTY_NOTIFY;
                        desc1=desc.get(1);
                        b=desc1.getUuid().toString();
                        if(char1!=null)
                        {
                            //gatt.readCharacteristic(char1);
                            //desc1=char1.getDescriptor(I2C_UUID_Descriptor);
                            //if(desc1!=null)
                            //{
                                //gatt.readDescriptor(desc1);
                                //a=desc1.getValue();

                            //}
                        }
                        //displayGattServices(services);
                    }
                }

                @Override
                public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                    if (status == BluetoothGatt.GATT_SUCCESS) {
                        broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
                    } 
                }

                @Override
                public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                    if (status == BluetoothGatt.GATT_SUCCESS) {
                    }
                }

                @Override
                public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                    broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
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

    private void clearUI() {
        mGattServicesList.setAdapter((SimpleExpandableListAdapter) null);
        mDataField.setText(null);
    }

    private void broadcastUpdate(final String action) {
        final Intent intent = new Intent(action);
        sendBroadcast(intent);
    }

    private void broadcastUpdate(final String action,
                                 final BluetoothGattCharacteristic characteristic) {
        final Intent intent = new Intent(action);
        if (UUID_HEART_RATE_MEASUREMENT.equals(characteristic.getUuid())) {
            int flag = characteristic.getProperties();
            int format = -1;
            if ((flag & 0x01) != 0) {
                format = BluetoothGattCharacteristic.FORMAT_UINT16;
                Log.d(TAG, "Heart rate format UINT16.");
            } else {
                format = BluetoothGattCharacteristic.FORMAT_UINT8;
                Log.d(TAG, "Heart rate format UINT8.");
            }
            final int heartRate = characteristic.getIntValue(format, 1);
            Log.d(TAG, String.format("Received heart rate: %d", heartRate));
            intent.putExtra(EXTRA_DATA, String.valueOf(heartRate));
        } else {
            // For all other profiles, writes the data formatted in HEX.
            final byte[] data = characteristic.getValue();
            if (data != null && data.length > 0) {
                final StringBuilder stringBuilder = new StringBuilder(data.length);
                for (byte byteChar : data)
                    stringBuilder.append(String.format("%02X ", byteChar));
                intent.putExtra(EXTRA_DATA, new String(data) + "\n" + stringBuilder.toString());
            }
        }
        sendBroadcast(intent);
    }
    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null) return;
        String uuid = null;
        String charuuid = null;
        String unknownServiceString = getResources().getString(R.string.unknown_service);
        String unknownCharaString = getResources().getString(R.string.unknown_characteristic);
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<HashMap<String, String>>();
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData = new ArrayList<ArrayList<HashMap<String, String>>>();
        mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();
        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {
            HashMap<String, String> currentServiceData = new HashMap<String, String>();
            uuid = gattService.getUuid().toString();
            currentServiceData.put(LIST_NAME, SampleGattAttributes.lookup(uuid, unknownServiceString));
            currentServiceData.put(LIST_NAME, lookup(uuid, unknownServiceString));
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);
            ArrayList<HashMap<String, String>> gattCharacteristicGroupData = new ArrayList<HashMap<String, String>>();
            List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas = new ArrayList<BluetoothGattCharacteristic>();
            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                charuuid = gattCharacteristic.getUuid().toString();
                currentCharaData.put(LIST_NAME, SampleGattAttributes.lookup(charuuid, unknownCharaString));
                currentCharaData.put(LIST_NAME, lookup(charuuid, unknownCharaString));
                currentCharaData.put(LIST_UUID, charuuid);
                gattCharacteristicGroupData.add(currentCharaData);
            }
            mGattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
        }
        SimpleExpandableListAdapter gattServiceAdapter = new SimpleExpandableListAdapter(this, gattServiceData, android.R.layout.simple_expandable_list_item_2, new String[]{LIST_NAME, LIST_UUID}, new int[]{android.R.id.text1, android.R.id.text2}, gattCharacteristicData, android.R.layout.simple_expandable_list_item_2, new String[]{LIST_NAME, LIST_UUID}, new int[]{android.R.id.text1, android.R.id.text2}
        );
        mGattServicesList.setAdapter(gattServiceAdapter);
    }
}





