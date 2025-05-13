package com.example.ble_application;

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

public class SoundEnhancer_Mode2 extends AppCompat {
    private Button b1,b2,b3,b4,b5,b6,b7,b8;
    private SeekBar s1,s2,s3;
    private static final UUID SERVICE_UUID = UUID.fromString("18424398-7cbc-11e9-8f9e-2a86e4085a59");
    private static final UUID I2C_CHARACTERISTIC_UUID = UUID.fromString("5b87b4ef-3bfa-76a8-e642-92933c31434c");
    private static final UUID LED_CHARACTERISTIC_UUID = UUID.fromString("5a87b4ef-3bfa-76a8-e642-92933c31434f");
    private BluetoothGatt bluetoothGatt;
    private BluetoothGattCharacteristic ledCharacteristic;
    private BluetoothGattCharacteristic i2cCharacteristic;
    private static final String PREFS_NAMEH = "SeekBarPrefs8";
    private static final String KEY_SEEKBAR_VALUEH = "seekBarValue8";
    private static final String PREFS_NAMEI = "SeekBarPrefs9";
    private static final String KEY_SEEKBAR_VALUEI = "seekBarValue9";
    private static final String PREFS_NAMEJ = "SeekBarPrefs10";
    private static final String KEY_SEEKBAR_VALUEJ = "seekBarValue10";
    int x=0;
    private EqualizerBackgroundView backgroundView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_enhancer_mode2);
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
        SharedPreferences preferences4 = getSharedPreferences(PREFS_NAMEH, MODE_PRIVATE);
        int y4 = preferences4.getInt(KEY_SEEKBAR_VALUEH, 0);
        s1.setProgress(y4);
        SharedPreferences preferences5 = getSharedPreferences(PREFS_NAMEI, MODE_PRIVATE);
        int y5 = preferences5.getInt(KEY_SEEKBAR_VALUEI, 0);
        s2.setProgress(y5);
        SharedPreferences preferences6 = getSharedPreferences(PREFS_NAMEJ, MODE_PRIVATE);
        int y6 = preferences6.getInt(KEY_SEEKBAR_VALUEJ, 0);
        s3.setProgress(y6);
        updateBackground(s1.getProgress(), s2.getProgress(), s3.getProgress());
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor4 = preferences4.edit();
                editor4.putInt(KEY_SEEKBAR_VALUEH, progress);
                editor4.apply();
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
                    setregister(321);
                if (x==-4)
                    setregister(322);
                if (x==-3)
                    setregister(323);
                if (x==-2)
                    setregister(324);
                if (x==-1)
                    setregister(325);
                if (x==0)
                    setregister(326);
                if (x==1)
                    setregister(327);
                if (x==2)
                    setregister(328);
                if (x==3)
                    setregister(329);
                if (x==4)
                    setregister(330);
                if (x==5)
                    setregister(331);
            }
        });
        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor5 = preferences5.edit();
                editor5.putInt(KEY_SEEKBAR_VALUEI, progress);
                editor5.apply();
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
                            setregister(361);
                            break;
                        case -4:
                            setregister(362);
                            break;
                        case -3:
                            setregister(363);
                            break;
                        case -2:
                            setregister(364);
                            break;
                        case -1:
                            setregister(365);
                            break;
                        case 0:
                            setregister(366);
                            break;
                        case 1:
                            setregister(367);
                            break;
                        case 2:
                            setregister(368);
                            break;
                        case 3:
                            setregister(369);
                            break;
                        case 4:
                            setregister(370);
                            break;
                        case 5:
                            setregister(371);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(482);
                                break;
                            case -4:
                                setregister(483);
                                break;
                            case -3:
                                setregister(484);
                                break;
                            case -2:
                                setregister(485);
                                break;
                            case -1:
                                setregister(486);
                                break;
                            case 0:
                                setregister(487);
                                break;
                            case 1:
                                setregister(488);
                                break;
                            case 2:
                                setregister(489);
                                break;
                            case 3:
                                setregister(490);
                                break;
                            case 4:
                                setregister(491);
                                break;
                            case 5:
                                setregister(492);
                                break;
                        }
                    }, 300); // تاخیر 500 میلی‌ثانیه

                }
                if (x == -4) {
                    switch (t) {
                        case -5:
                            setregister(372);
                            break;
                        case -4:
                            setregister(373);
                            break;
                        case -3:
                            setregister(374);
                            break;
                        case -2:
                            setregister(375);
                            break;
                        case -1:
                            setregister(376);
                            break;
                        case 0:
                            setregister(377);
                            break;
                        case 1:
                            setregister(378);
                            break;
                        case 2:
                            setregister(379);
                            break;
                        case 3:
                            setregister(380);
                            break;
                        case 4:
                            setregister(381);
                            break;
                        case 5:
                            setregister(382);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(493);
                                break;
                            case -4:
                                setregister(494);
                                break;
                            case -3:
                                setregister(495);
                                break;
                            case -2:
                                setregister(496);
                                break;
                            case -1:
                                setregister(497);
                                break;
                            case 0:
                                setregister(498);
                                break;
                            case 1:
                                setregister(499);
                                break;
                            case 2:
                                setregister(500);
                                break;
                            case 3:
                                setregister(501);
                                break;
                            case 4:
                                setregister(502);
                                break;
                            case 5:
                                setregister(503);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == -3) {
                    switch (t) {
                        case -5:
                            setregister(383);
                            break;
                        case -4:
                            setregister(384);
                            break;
                        case -3:
                            setregister(385);
                            break;
                        case -2:
                            setregister(386);
                            break;
                        case -1:
                            setregister(387);
                            break;
                        case 0:
                            setregister(388);
                            break;
                        case 1:
                            setregister(389);
                            break;
                        case 2:
                            setregister(390);
                            break;
                        case 3:
                            setregister(391);
                            break;
                        case 4:
                            setregister(392);
                            break;
                        case 5:
                            setregister(393);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(504);
                                break;
                            case -4:
                                setregister(505);
                                break;
                            case -3:
                                setregister(506);
                                break;
                            case -2:
                                setregister(507);
                                break;
                            case -1:
                                setregister(508);
                                break;
                            case 0:
                                setregister(509);
                                break;
                            case 1:
                                setregister(510);
                                break;
                            case 2:
                                setregister(511);
                                break;
                            case 3:
                                setregister(512);
                                break;
                            case 4:
                                setregister(513);
                                break;
                            case 5:
                                setregister(514);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == -2) {
                    switch (t) {
                        case -5:
                            setregister(394);
                            break;
                        case -4:
                            setregister(395);
                            break;
                        case -3:
                            setregister(396);
                            break;
                        case -2:
                            setregister(397);
                            break;
                        case -1:
                            setregister(398);
                            break;
                        case 0:
                            setregister(399);
                            break;
                        case 1:
                            setregister(400);
                            break;
                        case 2:
                            setregister(401);
                            break;
                        case 3:
                            setregister(402);
                            break;
                        case 4:
                            setregister(403);
                            break;
                        case 5:
                            setregister(404);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(515);
                                break;
                            case -4:
                                setregister(516);
                                break;
                            case -3:
                                setregister(517);
                                break;
                            case -2:
                                setregister(518);
                                break;
                            case -1:
                                setregister(519);
                                break;
                            case 0:
                                setregister(520);
                                break;
                            case 1:
                                setregister(521);
                                break;
                            case 2:
                                setregister(522);
                                break;
                            case 3:
                                setregister(523);
                                break;
                            case 4:
                                setregister(524);
                                break;
                            case 5:
                                setregister(525);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه

                }
                if (x == -1) {
                    switch (t) {
                        case -5:
                            setregister(405);
                            break;
                        case -4:
                            setregister(406);
                            break;
                        case -3:
                            setregister(407);
                            break;
                        case -2:
                            setregister(408);
                            break;
                        case -1:
                            setregister(409);
                            break;
                        case 0:
                            setregister(410);
                            break;
                        case 1:
                            setregister(411);
                            break;
                        case 2:
                            setregister(412);
                            break;
                        case 3:
                            setregister(413);
                            break;
                        case 4:
                            setregister(414);
                            break;
                        case 5:
                            setregister(415);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(526);
                                break;
                            case -4:
                                setregister(527);
                                break;
                            case -3:
                                setregister(528);
                                break;
                            case -2:
                                setregister(529);
                                break;
                            case -1:
                                setregister(530);
                                break;
                            case 0:
                                setregister(531);
                                break;
                            case 1:
                                setregister(532);
                                break;
                            case 2:
                                setregister(533);
                                break;
                            case 3:
                                setregister(534);
                                break;
                            case 4:
                                setregister(535);
                                break;
                            case 5:
                                setregister(536);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 0) {
                    switch (t) {
                        case -5:
                            setregister(416);
                            break;
                        case -4:
                            setregister(417);
                            break;
                        case -3:
                            setregister(418);
                            break;
                        case -2:
                            setregister(419);
                            break;
                        case -1:
                            setregister(420);
                            break;
                        case 0:
                            setregister(421);
                            break;
                        case 1:
                            setregister(422);
                            break;
                        case 2:
                            setregister(423);
                            break;
                        case 3:
                            setregister(424);
                            break;
                        case 4:
                            setregister(425);
                            break;
                        case 5:
                            setregister(426);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(537);
                                break;
                            case -4:
                                setregister(538);
                                break;
                            case -3:
                                setregister(539);
                                break;
                            case -2:
                                setregister(540);
                                break;
                            case -1:
                                setregister(541);
                                break;
                            case 0:
                                setregister(542);
                                break;
                            case 1:
                                setregister(543);
                                break;
                            case 2:
                                setregister(544);
                                break;
                            case 3:
                                setregister(545);
                                break;
                            case 4:
                                setregister(546);
                                break;
                            case 5:
                                setregister(547);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 1) {
                    switch (t) {
                        case -5:
                            setregister(427);
                            break;
                        case -4:
                            setregister(428);
                            break;
                        case -3:
                            setregister(429);
                            break;
                        case -2:
                            setregister(430);
                            break;
                        case -1:
                            setregister(431);
                            break;
                        case 0:
                            setregister(432);
                            break;
                        case 1:
                            setregister(433);
                            break;
                        case 2:
                            setregister(434);
                            break;
                        case 3:
                            setregister(435);
                            break;
                        case 4:
                            setregister(436);
                            break;
                        case 5:
                            setregister(437);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(548);
                                break;
                            case -4:
                                setregister(549);
                                break;
                            case -3:
                                setregister(550);
                                break;
                            case -2:
                                setregister(551);
                                break;
                            case -1:
                                setregister(552);
                                break;
                            case 0:
                                setregister(553);
                                break;
                            case 1:
                                setregister(554);
                                break;
                            case 2:
                                setregister(555);
                                break;
                            case 3:
                                setregister(320);
                                break;
                            case 4:
                                setregister(332);
                                break;
                            case 5:
                                setregister(333);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 2) {
                    switch (t) {
                        case -5:
                            setregister(438);
                            break;
                        case -4:
                            setregister(439);
                            break;
                        case -3:
                            setregister(440);
                            break;
                        case -2:
                            setregister(441);
                            break;
                        case -1:
                            setregister(442);
                            break;
                        case 0:
                            setregister(443);
                            break;
                        case 1:
                            setregister(444);
                            break;
                        case 2:
                            setregister(445);
                            break;
                        case 3:
                            setregister(446);
                            break;
                        case 4:
                            setregister(447);
                            break;
                        case 5:
                            setregister(448);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(334);
                                break;
                            case -4:
                                setregister(335);
                                break;
                            case -3:
                                setregister(336);
                                break;
                            case -2:
                                setregister(337);
                                break;
                            case -1:
                                setregister(338);
                                break;
                            case 0:
                                setregister(339);
                                break;
                            case 1:
                                setregister(340);
                                break;
                            case 2:
                                setregister(341);
                                break;
                            case 3:
                                setregister(342);
                                break;
                            case 4:
                                setregister(343);
                                break;
                            case 5:
                                setregister(344);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 3) {
                    switch (t) {
                        case -5:
                            setregister(449);
                            break;
                        case -4:
                            setregister(450);
                            break;
                        case -3:
                            setregister(451);
                            break;
                        case -2:
                            setregister(452);
                            break;
                        case -1:
                            setregister(453);
                            break;
                        case 0:
                            setregister(454);
                            break;
                        case 1:
                            setregister(455);
                            break;
                        case 2:
                            setregister(456);
                            break;
                        case 3:
                            setregister(457);
                            break;
                        case 4:
                            setregister(458);
                            break;
                        case 5:
                            setregister(459);
                            break;
                    }
                }
                if (x == 4) {
                    switch (t) {
                        case -5:
                            setregister(460);
                            break;
                        case -4:
                            setregister(461);
                            break;
                        case -3:
                            setregister(462);
                            break;
                        case -2:
                            setregister(463);
                            break;
                        case -1:
                            setregister(464);
                            break;
                        case 0:
                            setregister(465);
                            break;
                        case 1:
                            setregister(466);
                            break;
                        case 2:
                            setregister(467);
                            break;
                        case 3:
                            setregister(468);
                            break;
                        case 4:
                            setregister(469);
                            break;
                        case 5:
                            setregister(470);
                            break;
                    }
                }
                if (x == 5)
                {
                    switch (t)
                    {
                        case -5:
                            setregister(471);
                            break;
                        case -4:
                            setregister(472);
                            break;
                        case -3:
                            setregister(473);
                            break;
                        case -2:
                            setregister(474);
                            break;
                        case -1:
                            setregister(475);
                            break;
                        case 0:
                            setregister(476);
                            break;
                        case 1:
                            setregister(477);
                            break;
                        case 2:
                            setregister(478);
                            break;
                        case 3:
                            setregister(479);
                            break;
                        case 4:
                            setregister(480);
                            break;
                        case 5:
                            setregister(481);
                            break;
                    }
                }
            }
        });
        s3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor6 = preferences6.edit();
                editor6.putInt(KEY_SEEKBAR_VALUEJ, progress);
                editor6.apply();
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
                    setregister(347);
                if (x==-4)
                    setregister(348);
                if (x==-3)
                    setregister(349);
                if (x==-2)
                    setregister(350);
                if (x==-1)
                    setregister(351);
                if (x==0)
                    setregister(352);
                if (x==1)
                    setregister(353);
                if (x==2)
                    setregister(354);
                if (x==3)
                    setregister(355);
                if (x==4)
                    setregister(356);
                if (x==5)
                    setregister(357);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(0);
                s2.setProgress(3);
                s3.setProgress(0);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(326);
                }, 0); // بلافاصله
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(454);
                }, 300); // بلافاصله
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(352);
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
                    setregister(326);
                }, 0); // بلافاصله
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(454);
                }, 300); // بعد از 300 میلی‌ثانیه
                //setregister(154);
                //setregister(55);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(355);
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
                    setregister(326);
                }, 0); // بلافاصله
                //setregister(26);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(421);
                }, 300); // بلافاصله
                //setregister(121);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(540);
                }, 600); // بلافاصله
                //setregister(240);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(350);
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
                    setregister(324);
                }, 0); // بلافاصله
                //setregister(24);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(419);
                }, 300); // بلافاصله
                //setregister(119);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(542);
                }, 600); // بلافاصله
                //setregister(242);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(352);
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
                    setregister(326);
                }, 0); // بلافاصله
                //setregister(26);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(421);
                }, 300); // بلافاصله
                //setregister(121);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(542);
                }, 600); // بلافاصله
                //setregister(242);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(352);
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