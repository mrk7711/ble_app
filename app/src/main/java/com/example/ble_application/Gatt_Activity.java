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
import android.widget.ExpandableListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.UUID;

public class Gatt_Activity extends AppCompatActivity {
    TextView status2;
    TextView status3;
    TextView status4;
    TextView status5;
    Button   but_1;
    private boolean scanningEnd;
    private final static String TAG = Gatt_Activity.class.getSimpleName();
    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
    private ExpandableListView mGattServicesList;
    private TextView mConnectionState2;
    private TextView mDataField;
    private BluetoothGatt BG = null;
    private int mConnectionState = STATE_DISCONNECTED;
    private static final int STATE_DISCONNECTED = 0;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_CONNECTED = 2;
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
//    private BluetoothLeService mBluetoothLeService;
private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();
    private static final UUID NOTIFI_SERVICE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final UUID NOTIFI_CHARACTERISTIC = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private boolean mConnected = false;
    private BluetoothGattCharacteristic mNotifyCharacteristic;
    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";
    BluetoothDevice device2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatt);
        findViewByIdes();
        Intent a = getIntent();
        device2 = getIntent().getExtras().getParcelable("BLE");
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
        status5 = findViewById(R.id.status5);
        but_1 = findViewById(R.id.connect);
        mGattServicesList =findViewById(R.id.expand);
    }

    private void implementListeners2() {
        but_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(Gatt_Activity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                BG = device2.connectGatt(Gatt_Activity.this, false, mGattCallback);
                }
        });
    }

    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            String intentAction;
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                intentAction = ACTION_GATT_CONNECTED;
                mConnectionState = STATE_CONNECTED;
                broadcastUpdate(intentAction);
                status5.setText("Connected to GATT server.");
                if (ActivityCompat.checkSelfPermission(Gatt_Activity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        ActivityCompat.requestPermissions(Gatt_Activity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 100);
                        return;
                    }
                }
                gatt.discoverServices();
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                intentAction = ACTION_GATT_DISCONNECTED;
                mConnectionState = STATE_DISCONNECTED;
                status5.setText("Disconnected from GATT server.");
                broadcastUpdate(intentAction);
                scanningEnd = false;
            }
        }
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);
            }
            else {
                status5.setText("onServicesDiscovered received: ");
            }
//            mNotifyCharacteristic = gatt.getService(NOTIFI_SERVICE).getCharacteristic(NOTIFI_CHARACTERISTIC);
//            if (ActivityCompat.checkSelfPermission(Gatt_Activity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//                if (Build.VERSION.SDK_INT >= 31) {
//                    ActivityCompat.requestPermissions(Gatt_Activity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 100);
//                    return;
//                }
//            }
//            gatt.setCharacteristicNotification(mNotifyCharacteristic, true);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
            }
        }

        @Override
        public void  onCharacteristicChanged(BluetoothGatt gatt,BluetoothGattCharacteristic characteristic){
            super.onCharacteristicChanged(gatt,characteristic);
            broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
        }
    };
    private void broadcastUpdate(final String action) {
        final Intent intent = new Intent(action);
        sendBroadcast(intent);
    }
    private void broadcastUpdate(final String action,
                                 final BluetoothGattCharacteristic characteristic) {
        final Intent intent = new Intent(action);
        // This is special handling for the DA14531 profile.  Data parsing is
        // carried out as per profile specifications:
        // http://developer.bluetooth.org/gatt/characteristics/Pages/CharacteristicViewer.aspx?u=org.bluetooth.characteristic.heart_rate_measurement.xml
        if (NOTIFI_SERVICE.equals(characteristic.getUuid())) {
            int flag = characteristic.getProperties();
            int format = -1;
            if ((flag & 0x01) != 0) {
                format = BluetoothGattCharacteristic.FORMAT_UINT16;
                status5.setText("Heart rate format UINT16.");
            } else {
                format = BluetoothGattCharacteristic.FORMAT_UINT8;
                status5.setText("Heart rate format UINT8.");
            }
            final int heartRate = characteristic.getIntValue(format, 1);
            intent.putExtra(EXTRA_DATA, String.valueOf(heartRate));
        } else {
            // For all other profiles, writes the data formatted in HEX.
            final byte[] data = characteristic.getValue();
            if (data != null && data.length > 0) {
                final StringBuilder stringBuilder = new StringBuilder(data.length);
                for(byte byteChar : data)
                    stringBuilder.append(String.format("%02X ", byteChar));
                intent.putExtra(EXTRA_DATA, new String(data) + "\n" + stringBuilder.toString());
            }
        }
        sendBroadcast(intent);
    }


}