package com.example.ble_application;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.UUID;

public class SoundEnhancer extends AppCompat {
    private Button b1,b2,b3,b4,b5,b6,b7,b8;
    private SeekBar s1,s2,s3;
    private static final UUID SERVICE_UUID = UUID.fromString("18424398-7cbc-11e9-8f9e-2a86e4085a59");
    private static final UUID I2C_CHARACTERISTIC_UUID = UUID.fromString("5b87b4ef-3bfa-76a8-e642-92933c31434c");
    private static final UUID LED_CHARACTERISTIC_UUID = UUID.fromString("5a87b4ef-3bfa-76a8-e642-92933c31434f");
    private BluetoothGatt bluetoothGatt;
    private BluetoothGattCharacteristic ledCharacteristic;
    private BluetoothGattCharacteristic i2cCharacteristic;
    int x=0;
    private EqualizerBackgroundView backgroundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_enhancer);
        b1=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        b4=findViewById(R.id.speech);
        b5=findViewById(R.id.clarity);
        b6=findViewById(R.id.sharp);
        b7=findViewById(R.id.echo);
        b8=findViewById(R.id.reset2);
        s1=findViewById(R.id.seekBar2);
        s2=findViewById(R.id.seekBar3);
        s3=findViewById(R.id.seekBar4);
        bluetoothGatt = BluetoothManager.getInstance().getBluetoothGatt();
        BluetoothGattService service = bluetoothGatt.getService(SERVICE_UUID);
        ledCharacteristic = service.getCharacteristic(LED_CHARACTERISTIC_UUID);
        i2cCharacteristic = service.getCharacteristic(I2C_CHARACTERISTIC_UUID);
        backgroundView = findViewById(R.id.equalizerBackground);
        updateBackground(s1.getProgress(), s2.getProgress(), s3.getProgress());
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                x = progress;
                updateBackground(s1.getProgress(), s2.getProgress(), s3.getProgress());
                if (x==-6)
                    b1.setText("-6");
                if (x==-5)
                    b1.setText("-5");
                if (x==-4)
                    b1.setText("-4");
                if (x==-3)
                    b1.setText("-3");
                if (x==-2)
                    b1.setText("-2");
                if (x==-1)
                    b1.setText("-1");
                if (x==0)
                    b1.setText("0");
                if (x==6)
                    b1.setText("+6");
                if (x==5)
                    b1.setText("+5");
                if (x==4)
                    b1.setText("+4");
                if (x==3)
                    b1.setText("+3");
                if (x==2)
                    b1.setText("+2");
                if (x==1)
                    b1.setText("+1");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
                //showToast(String.valueOf(progressChangedValue));
                if (x==-6)
                    setregister(20);
                if (x==-5)
                    setregister(21);
                if (x==-4)
                    setregister(22);
                if (x==-3)
                    setregister(23);
                if (x==-2)
                    setregister(24);
                if (x==-1)
                    setregister(25);
                if (x==0)
                    setregister(26);
                if (x==1)
                    setregister(27);
                if (x==2)
                    setregister(28);
                if (x==3)
                    setregister(29);
                if (x==4)
                    setregister(30);
                if (x==5)
                    setregister(31);
                if (x==6)
                    setregister(32);
            }
        });
        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                x = progress;
                updateBackground(s1.getProgress(), s2.getProgress(), s3.getProgress());
                if (x==-6)
                    b2.setText("-6");
                if (x==-5)
                    b2.setText("-5");
                if (x==-4)
                    b2.setText("-4");
                if (x==-3)
                    b2.setText("-3");
                if (x==-2)
                    b2.setText("-2");
                if (x==-1)
                    b2.setText("-1");
                if (x==0)
                    b2.setText("0");
                if (x==6)
                    b2.setText("+6");
                if (x==5)
                    b2.setText("+5");
                if (x==4)
                    b2.setText("+4");
                if (x==3)
                    b2.setText("+3");
                if (x==2)
                    b2.setText("+2");
                if (x==1)
                    b2.setText("+1");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
                //showToast(String.valueOf(progressChangedValue));
                if (x==-6)
                    setregister(33);
                if (x==-5)
                    setregister(34);
                if (x==-4)
                    setregister(35);
                if (x==-3)
                    setregister(36);
                if (x==-2)
                    setregister(37);
                if (x==-1)
                    setregister(38);
                if (x==0)
                    setregister(39);
                if (x==1)
                    setregister(40);
                if (x==2)
                    setregister(41);
                if (x==3)
                    setregister(42);
                if (x==4)
                    setregister(43);
                if (x==5)
                    setregister(44);
                if (x==6)
                    setregister(45);
            }
        });
        s3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                x = progress;
                updateBackground(s1.getProgress(), s2.getProgress(), s3.getProgress());
                if (x==-6)
                    b3.setText("-6");
                if (x==-5)
                    b3.setText("-5");
                if (x==-4)
                    b3.setText("-4");
                if (x==-3)
                    b3.setText("-3");
                if (x==-2)
                    b3.setText("-2");
                if (x==-1)
                    b3.setText("-1");
                if (x==0)
                    b3.setText("0");
                if (x==6)
                    b3.setText("+6");
                if (x==5)
                    b3.setText("+5");
                if (x==4)
                    b3.setText("+4");
                if (x==3)
                    b3.setText("+3");
                if (x==2)
                    b3.setText("+2");
                if (x==1)
                    b3.setText("+1");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
                //showToast(String.valueOf(progressChangedValue));
                if (x==-6)
                    setregister(46);
                if (x==-5)
                    setregister(47);
                if (x==-4)
                    setregister(48);
                if (x==-3)
                    setregister(49);
                if (x==-2)
                    setregister(50);
                if (x==-1)
                    setregister(51);
                if (x==0)
                    setregister(52);
                if (x==1)
                    setregister(53);
                if (x==2)
                    setregister(54);
                if (x==3)
                    setregister(55);
                if (x==4)
                    setregister(56);
                if (x==5)
                    setregister(57);
                if (x==6)
                    setregister(58);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(0);
                s2.setProgress(3);
                s3.setProgress(0);
                setregister(26);
                setregister(42);
                setregister(52);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(0);
                s2.setProgress(3);
                s3.setProgress(3);
                setregister(26);
                setregister(42);
                setregister(55);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(0);
                s2.setProgress(0);
                s3.setProgress(-2);
                setregister(26);
                setregister(39);
                setregister(50);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(-2);
                s2.setProgress(0);
                s3.setProgress(0);
                setregister(24);
                setregister(39);
                setregister(52);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(0);
                s2.setProgress(0);
                s3.setProgress(0);
                setregister(26);
                setregister(39);
                setregister(52);
            }
        });
    }
    private void setregister(int x) {
        // Send command to turn on the LED to the microcontroller
        byte[] command = new byte[]{(byte)x}; // Command to turn on the LED
        ledCharacteristic.setValue(command);
        bluetoothGatt.writeCharacteristic(ledCharacteristic);
    }
    private void updateBackground(int bass, int mid, int treble) {
        backgroundView.setValues(bass, mid, treble);
    }
}