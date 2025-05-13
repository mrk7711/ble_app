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
        SharedPreferences preferences1 = getSharedPreferences(PREFS_NAMEE, MODE_PRIVATE);
        int y1 = preferences1.getInt(KEY_SEEKBAR_VALUEE, 0);
        s1.setProgress(y1);
        SharedPreferences preferences2 = getSharedPreferences(PREFS_NAMEF, MODE_PRIVATE);
        int y2 = preferences2.getInt(KEY_SEEKBAR_VALUEF, 0);
        s2.setProgress(y2);
        SharedPreferences preferences3 = getSharedPreferences(PREFS_NAMEG, MODE_PRIVATE);
        int y3 = preferences3.getInt(KEY_SEEKBAR_VALUEG, 0);
        s3.setProgress(y3);
        updateBackground(s1.getProgress(), s2.getProgress(), s3.getProgress());
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor1 = preferences1.edit();
                editor1.putInt(KEY_SEEKBAR_VALUEE, progress);
                editor1.apply();
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
                // کاری انجام نمی‌شود
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
                //showToast(String.valueOf(progressChangedValue));
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
            }
        });
        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor2 = preferences2.edit();
                editor2.putInt(KEY_SEEKBAR_VALUEF, progress);
                editor2.apply();
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
                // کاری انجام نمی‌شود
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
                //showToast(String.valueOf(progressChangedValue));
                int t = s1.getProgress();
                int z = s3.getProgress();
                if (x == -5) {
                    switch (t) {
                        case -5:
                            setregister(61);
                            break;
                        case -4:
                            setregister(62);
                            break;
                        case -3:
                            setregister(63);
                            break;
                        case -2:
                            setregister(64);
                            break;
                        case -1:
                            setregister(65);
                            break;
                        case 0:
                            setregister(66);
                            break;
                        case 1:
                            setregister(67);
                            break;
                        case 2:
                            setregister(68);
                            break;
                        case 3:
                            setregister(69);
                            break;
                        case 4:
                            setregister(70);
                            break;
                        case 5:
                            setregister(71);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(182);
                                break;
                            case -4:
                                setregister(183);
                                break;
                            case -3:
                                setregister(184);
                                break;
                            case -2:
                                setregister(185);
                                break;
                            case -1:
                                setregister(186);
                                break;
                            case 0:
                                setregister(187);
                                break;
                            case 1:
                                setregister(188);
                                break;
                            case 2:
                                setregister(189);
                                break;
                            case 3:
                                setregister(190);
                                break;
                            case 4:
                                setregister(191);
                                break;
                            case 5:
                                setregister(192);
                                break;
                        }
                    }, 300); // تاخیر 500 میلی‌ثانیه

                }
                if (x == -4) {
                    switch (t) {
                        case -5:
                            setregister(72);
                            break;
                        case -4:
                            setregister(73);
                            break;
                        case -3:
                            setregister(74);
                            break;
                        case -2:
                            setregister(75);
                            break;
                        case -1:
                            setregister(76);
                            break;
                        case 0:
                            setregister(77);
                            break;
                        case 1:
                            setregister(78);
                            break;
                        case 2:
                            setregister(79);
                            break;
                        case 3:
                            setregister(80);
                            break;
                        case 4:
                            setregister(81);
                            break;
                        case 5:
                            setregister(82);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(193);
                                break;
                            case -4:
                                setregister(194);
                                break;
                            case -3:
                                setregister(195);
                                break;
                            case -2:
                                setregister(196);
                                break;
                            case -1:
                                setregister(197);
                                break;
                            case 0:
                                setregister(198);
                                break;
                            case 1:
                                setregister(199);
                                break;
                            case 2:
                                setregister(200);
                                break;
                            case 3:
                                setregister(201);
                                break;
                            case 4:
                                setregister(202);
                                break;
                            case 5:
                                setregister(203);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == -3) {
                    switch (t) {
                        case -5:
                            setregister(83);
                            break;
                        case -4:
                            setregister(84);
                            break;
                        case -3:
                            setregister(85);
                            break;
                        case -2:
                            setregister(86);
                            break;
                        case -1:
                            setregister(87);
                            break;
                        case 0:
                            setregister(88);
                            break;
                        case 1:
                            setregister(89);
                            break;
                        case 2:
                            setregister(90);
                            break;
                        case 3:
                            setregister(91);
                            break;
                        case 4:
                            setregister(92);
                            break;
                        case 5:
                            setregister(93);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(204);
                                break;
                            case -4:
                                setregister(205);
                                break;
                            case -3:
                                setregister(206);
                                break;
                            case -2:
                                setregister(207);
                                break;
                            case -1:
                                setregister(208);
                                break;
                            case 0:
                                setregister(209);
                                break;
                            case 1:
                                setregister(210);
                                break;
                            case 2:
                                setregister(211);
                                break;
                            case 3:
                                setregister(212);
                                break;
                            case 4:
                                setregister(213);
                                break;
                            case 5:
                                setregister(214);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == -2) {
                    switch (t) {
                        case -5:
                            setregister(94);
                            break;
                        case -4:
                            setregister(95);
                            break;
                        case -3:
                            setregister(96);
                            break;
                        case -2:
                            setregister(97);
                            break;
                        case -1:
                            setregister(98);
                            break;
                        case 0:
                            setregister(99);
                            break;
                        case 1:
                            setregister(100);
                            break;
                        case 2:
                            setregister(101);
                            break;
                        case 3:
                            setregister(102);
                            break;
                        case 4:
                            setregister(103);
                            break;
                        case 5:
                            setregister(104);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(215);
                                break;
                            case -4:
                                setregister(216);
                                break;
                            case -3:
                                setregister(217);
                                break;
                            case -2:
                                setregister(218);
                                break;
                            case -1:
                                setregister(219);
                                break;
                            case 0:
                                setregister(220);
                                break;
                            case 1:
                                setregister(221);
                                break;
                            case 2:
                                setregister(222);
                                break;
                            case 3:
                                setregister(223);
                                break;
                            case 4:
                                setregister(224);
                                break;
                            case 5:
                                setregister(225);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه

                }
                if (x == -1) {
                    switch (t) {
                        case -5:
                            setregister(105);
                            break;
                        case -4:
                            setregister(106);
                            break;
                        case -3:
                            setregister(107);
                            break;
                        case -2:
                            setregister(108);
                            break;
                        case -1:
                            setregister(109);
                            break;
                        case 0:
                            setregister(110);
                            break;
                        case 1:
                            setregister(111);
                            break;
                        case 2:
                            setregister(112);
                            break;
                        case 3:
                            setregister(113);
                            break;
                        case 4:
                            setregister(114);
                            break;
                        case 5:
                            setregister(115);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(226);
                                break;
                            case -4:
                                setregister(227);
                                break;
                            case -3:
                                setregister(228);
                                break;
                            case -2:
                                setregister(229);
                                break;
                            case -1:
                                setregister(230);
                                break;
                            case 0:
                                setregister(231);
                                break;
                            case 1:
                                setregister(232);
                                break;
                            case 2:
                                setregister(233);
                                break;
                            case 3:
                                setregister(234);
                                break;
                            case 4:
                                setregister(235);
                                break;
                            case 5:
                                setregister(236);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 0) {
                    switch (t) {
                        case -5:
                            setregister(116);
                            break;
                        case -4:
                            setregister(117);
                            break;
                        case -3:
                            setregister(118);
                            break;
                        case -2:
                            setregister(119);
                            break;
                        case -1:
                            setregister(120);
                            break;
                        case 0:
                            setregister(121);
                            break;
                        case 1:
                            setregister(122);
                            break;
                        case 2:
                            setregister(123);
                            break;
                        case 3:
                            setregister(124);
                            break;
                        case 4:
                            setregister(125);
                            break;
                        case 5:
                            setregister(126);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(237);
                                break;
                            case -4:
                                setregister(238);
                                break;
                            case -3:
                                setregister(239);
                                break;
                            case -2:
                                setregister(240);
                                break;
                            case -1:
                                setregister(241);
                                break;
                            case 0:
                                setregister(242);
                                break;
                            case 1:
                                setregister(243);
                                break;
                            case 2:
                                setregister(244);
                                break;
                            case 3:
                                setregister(245);
                                break;
                            case 4:
                                setregister(246);
                                break;
                            case 5:
                                setregister(247);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 1) {
                    switch (t) {
                        case -5:
                            setregister(127);
                            break;
                        case -4:
                            setregister(128);
                            break;
                        case -3:
                            setregister(129);
                            break;
                        case -2:
                            setregister(130);
                            break;
                        case -1:
                            setregister(131);
                            break;
                        case 0:
                            setregister(132);
                            break;
                        case 1:
                            setregister(133);
                            break;
                        case 2:
                            setregister(134);
                            break;
                        case 3:
                            setregister(135);
                            break;
                        case 4:
                            setregister(136);
                            break;
                        case 5:
                            setregister(137);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(248);
                                break;
                            case -4:
                                setregister(249);
                                break;
                            case -3:
                                setregister(250);
                                break;
                            case -2:
                                setregister(251);
                                break;
                            case -1:
                                setregister(252);
                                break;
                            case 0:
                                setregister(253);
                                break;
                            case 1:
                                setregister(254);
                                break;
                            case 2:
                                setregister(255);
                                break;
                            case 3:
                                setregister(20);
                                break;
                            case 4:
                                setregister(32);
                                break;
                            case 5:
                                setregister(33);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 2) {
                    switch (t) {
                        case -5:
                            setregister(138);
                            break;
                        case -4:
                            setregister(139);
                            break;
                        case -3:
                            setregister(140);
                            break;
                        case -2:
                            setregister(141);
                            break;
                        case -1:
                            setregister(142);
                            break;
                        case 0:
                            setregister(143);
                            break;
                        case 1:
                            setregister(144);
                            break;
                        case 2:
                            setregister(145);
                            break;
                        case 3:
                            setregister(146);
                            break;
                        case 4:
                            setregister(147);
                            break;
                        case 5:
                            setregister(148);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(34);
                                break;
                            case -4:
                                setregister(35);
                                break;
                            case -3:
                                setregister(36);
                                break;
                            case -2:
                                setregister(37);
                                break;
                            case -1:
                                setregister(38);
                                break;
                            case 0:
                                setregister(39);
                                break;
                            case 1:
                                setregister(40);
                                break;
                            case 2:
                                setregister(41);
                                break;
                            case 3:
                                setregister(42);
                                break;
                            case 4:
                                setregister(43);
                                break;
                            case 5:
                                setregister(44);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 3) {
                    switch (t) {
                        case -5:
                            setregister(149);
                            break;
                        case -4:
                            setregister(150);
                            break;
                        case -3:
                            setregister(151);
                            break;
                        case -2:
                            setregister(152);
                            break;
                        case -1:
                            setregister(153);
                            break;
                        case 0:
                            setregister(154);
                            break;
                        case 1:
                            setregister(155);
                            break;
                        case 2:
                            setregister(156);
                            break;
                        case 3:
                            setregister(157);
                            break;
                        case 4:
                            setregister(158);
                            break;
                        case 5:
                            setregister(159);
                            break;
                    }
                }
                if (x == 4) {
                    switch (t) {
                        case -5:
                            setregister(160);
                            break;
                        case -4:
                            setregister(161);
                            break;
                        case -3:
                            setregister(162);
                            break;
                        case -2:
                            setregister(163);
                            break;
                        case -1:
                            setregister(164);
                            break;
                        case 0:
                            setregister(165);
                            break;
                        case 1:
                            setregister(166);
                            break;
                        case 2:
                            setregister(167);
                            break;
                        case 3:
                            setregister(168);
                            break;
                        case 4:
                            setregister(169);
                            break;
                        case 5:
                            setregister(170);
                            break;
                    }
                }
                if (x == 5)
                {
                    switch (t)
                    {
                        case -5:
                            setregister(171);
                            break;
                        case -4:
                            setregister(172);
                            break;
                        case -3:
                            setregister(173);
                            break;
                        case -2:
                            setregister(174);
                            break;
                        case -1:
                            setregister(175);
                            break;
                        case 0:
                            setregister(176);
                            break;
                        case 1:
                            setregister(177);
                            break;
                        case 2:
                            setregister(178);
                            break;
                        case 3:
                            setregister(179);
                            break;
                        case 4:
                            setregister(180);
                            break;
                        case 5:
                            setregister(181);
                            break;
                    }
                }
            }
        });
        s3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor3 = preferences3.edit();
                editor3.putInt(KEY_SEEKBAR_VALUEG, progress);
                editor3.apply();
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
                // کاری انجام نمی‌شود
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
                //showToast(String.valueOf(progressChangedValue));

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
                    setregister(154);
                }, 300); // بلافاصله
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(52);
                }, 600); // بلافاصله
                //setregister(26);
                //setregister(154);
                //setregister(52);
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
                    setregister(154);
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
                    setregister(121);
                }, 300); // بلافاصله
                //setregister(121);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(240);
                }, 600); // بلافاصله
                //setregister(240);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(50);
                }, 900); // بلافاصله
                //setregister(50);
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
                    setregister(119);
                }, 300); // بلافاصله
                //setregister(119);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(242);
                }, 600); // بلافاصله
                //setregister(242);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(52);
                }, 900); // بلافاصله
                //setregister(52);
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
                    setregister(121);
                }, 300); // بلافاصله
                //setregister(121);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(242);
                }, 600); // بلافاصله
                //setregister(242);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(52);
                }, 900); // بلافاصله
                //setregister(52);
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