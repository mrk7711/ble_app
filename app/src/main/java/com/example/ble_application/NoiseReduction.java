package com.example.ble_application;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothGatt;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.UUID;

public class NoiseReduction extends AppCompatActivity {
    private Button b1;
    private SeekBar s1;
    private TextView t1;
    private int x=0;
    private BluetoothGatt bluetoothGatt;
    private BluetoothGattCharacteristic ledCharacteristic;
    private BluetoothGattCharacteristic i2cCharacteristic;
    private static final String PREFS_NAME = "SeekBarPrefs";
    private static final String KEY_SEEKBAR_VALUE = "seekBarValue";
    private static final UUID SERVICE_UUID = UUID.fromString("18424398-7cbc-11e9-8f9e-2a86e4085a59");
    private static final UUID I2C_CHARACTERISTIC_UUID = UUID.fromString("5b87b4ef-3bfa-76a8-e642-92933c31434c");
    private static final UUID LED_CHARACTERISTIC_UUID = UUID.fromString("5a87b4ef-3bfa-76a8-e642-92933c31434f");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noise_reduction);
        b1=findViewById(R.id.reset);
        s1=findViewById(R.id.seekBar);
        t1=findViewById(R.id.textView1);
        bluetoothGatt = BluetoothManager.getInstance().getBluetoothGatt();
        BluetoothGattService service = bluetoothGatt.getService(SERVICE_UUID);
        ledCharacteristic = service.getCharacteristic(LED_CHARACTERISTIC_UUID);
        i2cCharacteristic = service.getCharacteristic(I2C_CHARACTERISTIC_UUID);
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int y = preferences.getInt(KEY_SEEKBAR_VALUE, 0);
        s1.setProgress(y);
        if (y==0)
            t1.setText("OFF");
        if (y==1)
            t1.setText("Mild");
        if (y==2)
            t1.setText("Moderate");
        if (y==3)
        if (y==3)
            t1.setText("Considerable");
        if (y==4)
            t1.setText("Strong");
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //currentValueTextView.setText(String.valueOf(progress));
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(KEY_SEEKBAR_VALUE, progress);
                editor.apply();
                x = progress;
                if (x==0)
                    t1.setText("OFF");
                if (x==1)
                    t1.setText("Mild");
                if (x==2)
                    t1.setText("Moderate");
                if (x==3)
                    t1.setText("Considerable");
                if (x==4)
                    t1.setText("Strong");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
                //showToast(String.valueOf(progressChangedValue));
                if(x==0)
                    setregister(11);
                if (x==1)
                    setregister(12);
                if (x==2)
                    setregister(13);
                if (x==3)
                    setregister(14);
                if (x==4)
                    setregister(15);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(1);
            }
        });
    }
    private void setregister(int x) {
        // Send command to turn on the LED to the microcontroller
        byte[] command = new byte[]{(byte)x}; // Command to turn on the LED
        ledCharacteristic.setValue(command);
        bluetoothGatt.writeCharacteristic(ledCharacteristic);
    }
}