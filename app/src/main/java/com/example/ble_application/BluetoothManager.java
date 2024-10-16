package com.example.ble_application;

import android.bluetooth.BluetoothGatt;

public class BluetoothManager {
    private static BluetoothManager instance;
    private BluetoothGatt bluetoothGatt;

    private BluetoothManager() {
        // ساختار خصوصی برای جلوگیری از ایجاد مستقیم شی
    }

    public static BluetoothManager getInstance() {
        if (instance == null) {
            instance = new BluetoothManager();
        }
        return instance;
    }

    public BluetoothGatt getBluetoothGatt() {
        return bluetoothGatt;
    }

    public void setBluetoothGatt(BluetoothGatt gatt) {
        this.bluetoothGatt = gatt;
    }
}
