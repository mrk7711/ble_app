package com.example.ble_application;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
    private static final String PREFS_NAMEE = "SeekBarPrefs5";
    private static final String KEY_SEEKBAR_VALUEE = "seekBarValue5";
    private static final String PREFS_NAMEF = "SeekBarPrefs6";
    private static final String KEY_SEEKBAR_VALUEF = "seekBarValue6";
    private static final String PREFS_NAMEG = "SeekBarPrefs7";
    private static final String KEY_SEEKBAR_VALUEG = "seekBarValue7";
    int S1_lastProgress = 0;
    int S1_CurrentProgress = 0;
    int S1_DifferProgress = 0;
    int S2_lastProgress = 0;
    int S2_CurrentProgress = 0;
    int S2_DifferProgress = 0;
    int S3_lastProgress = 0;
    int S3_CurrentProgress = 0;
    int S3_DifferProgress = 0;
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
//        SharedPreferences preferences1 = getSharedPreferences(PREFS_NAMEE, MODE_PRIVATE);
//        int y1 = preferences1.getInt(KEY_SEEKBAR_VALUEE, 0);
//        s1.setProgress(y1);
//        b1.setText(y1);
//        SharedPreferences preferences2 = getSharedPreferences(PREFS_NAMEF, MODE_PRIVATE);
//        int y2 = preferences2.getInt(KEY_SEEKBAR_VALUEF, 0);
//        s2.setProgress(y2);
//        b2.setText(y2);
//        SharedPreferences preferences3 = getSharedPreferences(PREFS_NAMEG, MODE_PRIVATE);
//        int y3 = preferences3.getInt(KEY_SEEKBAR_VALUEG, 0);
//        s3.setProgress(y3);
//        b3.setText(y3);
        updateBackground(s1.getProgress(), s2.getProgress(), s3.getProgress());
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //SharedPreferences.Editor editor1 = preferences1.edit();
                //editor1.putInt(KEY_SEEKBAR_VALUEE, progress);
                //editor1.apply();
                x = progress;
                updateBackground(s1.getProgress(), s2.getProgress(), s3.getProgress());
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
                S1_lastProgress= s1.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                S1_CurrentProgress=s1.getProgress();
                S1_DifferProgress= S1_CurrentProgress -S1_lastProgress;
                setregister(S1_DifferProgress+30);
            }
        });
        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //SharedPreferences.Editor editor2 = preferences2.edit();
                //editor2.putInt(KEY_SEEKBAR_VALUEF, progress);
                //editor2.apply();
                x = progress;
                updateBackground(s1.getProgress(), s2.getProgress(), s3.getProgress());
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
                S2_lastProgress= s2.getProgress();
                // کاری انجام نمی‌شود
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                S2_CurrentProgress=s2.getProgress();
                S2_DifferProgress= S2_CurrentProgress -S2_lastProgress;
                setregister(S2_DifferProgress+51);
            }
        });
        s3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //SharedPreferences.Editor editor3 = preferences3.edit();
               // editor3.putInt(KEY_SEEKBAR_VALUEG, progress);
                //editor3.apply();
                x = progress;
                updateBackground(s1.getProgress(), s2.getProgress(), s3.getProgress());
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
                S3_lastProgress= s3.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                S3_CurrentProgress=s3.getProgress();
                S3_DifferProgress= S3_CurrentProgress -S3_lastProgress;
                setregister(S3_DifferProgress+72);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(0);
                s2.setProgress(3);
                s3.setProgress(0);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(26);
                }, 0); // بلافاصله
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(69);
                }, 300); // بلافاصله
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(52);
                }, 600); // بلافاصله
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(0);
                s2.setProgress(3);
                s3.setProgress(3);
                //setregister(26);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(26);
                }, 0); // بلافاصله
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(69);
                }, 300); // بعد از 300 میلی‌ثانیه
                //setregister(154);
                //setregister(55);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(55);
                }, 600); // بعد از 600 میلی‌ثانیه
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(0);
                s2.setProgress(0);
                s3.setProgress(-2);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(26);
                }, 0); // بلافاصله
                //setregister(26);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(66);
                }, 300); // بلافاصله
                //setregister(121);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(50);
                }, 600); // بلافاصله
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(-2);
                s2.setProgress(0);
                s3.setProgress(0);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(24);
                }, 0); // بلافاصله
                //setregister(24);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(66);
                }, 300); // بلافاصله
                //setregister(119);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(52);
                }, 600); // بلافاصله
                //setregister(242);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(0);
                s2.setProgress(0);
                s3.setProgress(0);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(26);
                }, 0); // بلافاصله
                //setregister(26);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(66);
                }, 300); // بلافاصله
                //setregister(121);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(52);
                }, 600); // بلافاصله
            }
        });
    }
    private void setregister(int x) {
        // Send command to turn on the LED to the microcontroller
        byte highByte = (byte) ((x >> 8) & 0xFF); // بایت بالا
        byte lowByte  = (byte) (x & 0xFF);        // بایت پایین
        byte[] command = new byte[]{highByte, lowByte}; // Command to turn on the LED
        ledCharacteristic.setValue(command);
        bluetoothGatt.writeCharacteristic(ledCharacteristic);
    }
    private void updateBackground(int bass, int mid, int treble) {
        backgroundView.setValues(bass, mid, treble);
    }
}