package com.example.ble_application;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Gatt_Activity extends AppCompatActivity {
    TextView status2;
    TextView status3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatt);
        findViewByIdes();
        status2.setText("Device Information:");
        Intent a=getIntent();
        BluetoothDevice device = getIntent().getExtras().getParcelable("BLE");
//        String y=a.getStringExtra("BLE");
//        status3.setText(y);
        status3.setText(device.getAddress());
    }

    private void findViewByIdes() {

        status2 = findViewById(R.id.status2);
        status3 = findViewById(R.id.status3);
    }

    //        private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
//        @Override
//        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
//            super.onConnectionStateChange(gatt, status, newState);
//            if (newState == BluetoothProfile.STATE_CONNECTED) {
//                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//                    if (Build.VERSION.SDK_INT >= 31) {
//                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 100);
//                        return;
//                    }
//                }
//                gatt.discoverServices();
//            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
//                scanningEnd = false;
//            }
//        }
//
//        public void onServiceDiscovered(BluetoothGatt gatt, int status) {
//            super.onServicesDiscovered(gatt, status);
//            characteristicNotifi = gatt.getService(NOTIFI_SERVICE).getCharacteristic(NOTIFI_CHARACTERISTIC);
//            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//                if (Build.VERSION.SDK_INT >= 31) {
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 100);
//                    return;
//                }
//            }
//            gatt.setCharacteristicNotification(characteristicNotifi, true);
//        }
//
//        public void  onCharacteristicChanged(BluetoothGatt gatt,BluetoothGattCharacteristic characteristic){
//            super.onCharacteristicChanged(gatt,characteristic);
//        }
//    };
////        public void onScanFailed(int errorCode) {
////            super.onScanFailed(errorCode);
////        }
}