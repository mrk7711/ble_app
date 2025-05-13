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

public class SoundEnhancer_Mode4 extends AppCompat {
    private Button b1,b2,b3,b4,b5,b6,b7,b8;
    private SeekBar s1,s2,s3;
    private static final UUID SERVICE_UUID = UUID.fromString("18424398-7cbc-11e9-8f9e-2a86e4085a59");
    private static final UUID I2C_CHARACTERISTIC_UUID = UUID.fromString("5b87b4ef-3bfa-76a8-e642-92933c31434c");
    private static final UUID LED_CHARACTERISTIC_UUID = UUID.fromString("5a87b4ef-3bfa-76a8-e642-92933c31434f");
    private BluetoothGatt bluetoothGatt;
    private BluetoothGattCharacteristic ledCharacteristic;
    private BluetoothGattCharacteristic i2cCharacteristic;
    private static final String PREFS_NAMEN = "SeekBarPrefs14";
    private static final String KEY_SEEKBAR_VALUEN = "seekBarValue14";
    private static final String PREFS_NAMEO = "SeekBarPrefs15";
    private static final String KEY_SEEKBAR_VALUEO = "seekBarValue15";
    private static final String PREFS_NAMEP = "SeekBarPrefs16";
    private static final String KEY_SEEKBAR_VALUEP = "seekBarValue16";
    int x=0;
    private EqualizerBackgroundView backgroundView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_enhancer_mode4);
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
        SharedPreferences preferences10 = getSharedPreferences(PREFS_NAMEN, MODE_PRIVATE);
        int y10 = preferences10.getInt(KEY_SEEKBAR_VALUEN, 0);
        s1.setProgress(y10);
        SharedPreferences preferences11 = getSharedPreferences(PREFS_NAMEO, MODE_PRIVATE);
        int y11 = preferences11.getInt(KEY_SEEKBAR_VALUEO, 0);
        s2.setProgress(y11);
        SharedPreferences preferences12 = getSharedPreferences(PREFS_NAMEP, MODE_PRIVATE);
        int y12 = preferences12.getInt(KEY_SEEKBAR_VALUEP, 0);
        s3.setProgress(y12);
        updateBackground(s1.getProgress(), s2.getProgress(), s3.getProgress());
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor10 = preferences10.edit();
                editor10.putInt(KEY_SEEKBAR_VALUEN, progress);
                editor10.apply();
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
                    setregister(921);
                if (x==-4)
                    setregister(922);
                if (x==-3)
                    setregister(923);
                if (x==-2)
                    setregister(924);
                if (x==-1)
                    setregister(925);
                if (x==0)
                    setregister(926);
                if (x==1)
                    setregister(927);
                if (x==2)
                    setregister(928);
                if (x==3)
                    setregister(929);
                if (x==4)
                    setregister(930);
                if (x==5)
                    setregister(931);
            }
        });
        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor11 = preferences11.edit();
                editor11.putInt(KEY_SEEKBAR_VALUEO, progress);
                editor11.apply();
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
                            setregister(961);
                            break;
                        case -4:
                            setregister(962);
                            break;
                        case -3:
                            setregister(963);
                            break;
                        case -2:
                            setregister(964);
                            break;
                        case -1:
                            setregister(965);
                            break;
                        case 0:
                            setregister(966);
                            break;
                        case 1:
                            setregister(967);
                            break;
                        case 2:
                            setregister(968);
                            break;
                        case 3:
                            setregister(969);
                            break;
                        case 4:
                            setregister(970);
                            break;
                        case 5:
                            setregister(971);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(1082);
                                break;
                            case -4:
                                setregister(1083);
                                break;
                            case -3:
                                setregister(1084);
                                break;
                            case -2:
                                setregister(1085);
                                break;
                            case -1:
                                setregister(1086);
                                break;
                            case 0:
                                setregister(1087);
                                break;
                            case 1:
                                setregister(1088);
                                break;
                            case 2:
                                setregister(1089);
                                break;
                            case 3:
                                setregister(1090);
                                break;
                            case 4:
                                setregister(1091);
                                break;
                            case 5:
                                setregister(1092);
                                break;
                        }
                    }, 300); // تاخیر 500 میلی‌ثانیه

                }
                if (x == -4) {
                    switch (t) {
                        case -5:
                            setregister(972);
                            break;
                        case -4:
                            setregister(973);
                            break;
                        case -3:
                            setregister(974);
                            break;
                        case -2:
                            setregister(975);
                            break;
                        case -1:
                            setregister(976);
                            break;
                        case 0:
                            setregister(977);
                            break;
                        case 1:
                            setregister(978);
                            break;
                        case 2:
                            setregister(979);
                            break;
                        case 3:
                            setregister(980);
                            break;
                        case 4:
                            setregister(981);
                            break;
                        case 5:
                            setregister(982);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(1093);
                                break;
                            case -4:
                                setregister(1094);
                                break;
                            case -3:
                                setregister(1095);
                                break;
                            case -2:
                                setregister(1096);
                                break;
                            case -1:
                                setregister(1097);
                                break;
                            case 0:
                                setregister(1098);
                                break;
                            case 1:
                                setregister(1099);
                                break;
                            case 2:
                                setregister(1100);
                                break;
                            case 3:
                                setregister(1101);
                                break;
                            case 4:
                                setregister(1102);
                                break;
                            case 5:
                                setregister(1103);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == -3) {
                    switch (t) {
                        case -5:
                            setregister(983);
                            break;
                        case -4:
                            setregister(984);
                            break;
                        case -3:
                            setregister(985);
                            break;
                        case -2:
                            setregister(986);
                            break;
                        case -1:
                            setregister(987);
                            break;
                        case 0:
                            setregister(988);
                            break;
                        case 1:
                            setregister(989);
                            break;
                        case 2:
                            setregister(990);
                            break;
                        case 3:
                            setregister(991);
                            break;
                        case 4:
                            setregister(992);
                            break;
                        case 5:
                            setregister(993);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(1104);
                                break;
                            case -4:
                                setregister(1105);
                                break;
                            case -3:
                                setregister(1106);
                                break;
                            case -2:
                                setregister(1107);
                                break;
                            case -1:
                                setregister(1108);
                                break;
                            case 0:
                                setregister(1109);
                                break;
                            case 1:
                                setregister(1110);
                                break;
                            case 2:
                                setregister(1111);
                                break;
                            case 3:
                                setregister(1112);
                                break;
                            case 4:
                                setregister(1113);
                                break;
                            case 5:
                                setregister(1114);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == -2) {
                    switch (t) {
                        case -5:
                            setregister(994);
                            break;
                        case -4:
                            setregister(995);
                            break;
                        case -3:
                            setregister(996);
                            break;
                        case -2:
                            setregister(997);
                            break;
                        case -1:
                            setregister(998);
                            break;
                        case 0:
                            setregister(999);
                            break;
                        case 1:
                            setregister(1000);
                            break;
                        case 2:
                            setregister(1001);
                            break;
                        case 3:
                            setregister(1002);
                            break;
                        case 4:
                            setregister(1003);
                            break;
                        case 5:
                            setregister(1004);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(1115);
                                break;
                            case -4:
                                setregister(1116);
                                break;
                            case -3:
                                setregister(1117);
                                break;
                            case -2:
                                setregister(1118);
                                break;
                            case -1:
                                setregister(1119);
                                break;
                            case 0:
                                setregister(1120);
                                break;
                            case 1:
                                setregister(1121);
                                break;
                            case 2:
                                setregister(1122);
                                break;
                            case 3:
                                setregister(1123);
                                break;
                            case 4:
                                setregister(1124);
                                break;
                            case 5:
                                setregister(1125);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه

                }
                if (x == -1) {
                    switch (t) {
                        case -5:
                            setregister(1005);
                            break;
                        case -4:
                            setregister(1006);
                            break;
                        case -3:
                            setregister(1007);
                            break;
                        case -2:
                            setregister(1008);
                            break;
                        case -1:
                            setregister(1009);
                            break;
                        case 0:
                            setregister(1010);
                            break;
                        case 1:
                            setregister(1011);
                            break;
                        case 2:
                            setregister(1012);
                            break;
                        case 3:
                            setregister(1013);
                            break;
                        case 4:
                            setregister(1014);
                            break;
                        case 5:
                            setregister(1015);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(1126);
                                break;
                            case -4:
                                setregister(1127);
                                break;
                            case -3:
                                setregister(1128);
                                break;
                            case -2:
                                setregister(1129);
                                break;
                            case -1:
                                setregister(1130);
                                break;
                            case 0:
                                setregister(1131);
                                break;
                            case 1:
                                setregister(1132);
                                break;
                            case 2:
                                setregister(1133);
                                break;
                            case 3:
                                setregister(1134);
                                break;
                            case 4:
                                setregister(1135);
                                break;
                            case 5:
                                setregister(1136);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 0) {
                    switch (t) {
                        case -5:
                            setregister(1016);
                            break;
                        case -4:
                            setregister(1017);
                            break;
                        case -3:
                            setregister(1018);
                            break;
                        case -2:
                            setregister(1019);
                            break;
                        case -1:
                            setregister(1020);
                            break;
                        case 0:
                            setregister(1021);
                            break;
                        case 1:
                            setregister(1022);
                            break;
                        case 2:
                            setregister(1023);
                            break;
                        case 3:
                            setregister(1024);
                            break;
                        case 4:
                            setregister(1025);
                            break;
                        case 5:
                            setregister(1026);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(1137);
                                break;
                            case -4:
                                setregister(1138);
                                break;
                            case -3:
                                setregister(1139);
                                break;
                            case -2:
                                setregister(1140);
                                break;
                            case -1:
                                setregister(1141);
                                break;
                            case 0:
                                setregister(1142);
                                break;
                            case 1:
                                setregister(1143);
                                break;
                            case 2:
                                setregister(1144);
                                break;
                            case 3:
                                setregister(1145);
                                break;
                            case 4:
                                setregister(1146);
                                break;
                            case 5:
                                setregister(1147);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 1) {
                    switch (t) {
                        case -5:
                            setregister(1027);
                            break;
                        case -4:
                            setregister(1028);
                            break;
                        case -3:
                            setregister(1029);
                            break;
                        case -2:
                            setregister(1030);
                            break;
                        case -1:
                            setregister(1031);
                            break;
                        case 0:
                            setregister(1032);
                            break;
                        case 1:
                            setregister(1033);
                            break;
                        case 2:
                            setregister(1034);
                            break;
                        case 3:
                            setregister(1035);
                            break;
                        case 4:
                            setregister(1036);
                            break;
                        case 5:
                            setregister(1037);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(1148);
                                break;
                            case -4:
                                setregister(1149);
                                break;
                            case -3:
                                setregister(1150);
                                break;
                            case -2:
                                setregister(1151);
                                break;
                            case -1:
                                setregister(1152);
                                break;
                            case 0:
                                setregister(1153);
                                break;
                            case 1:
                                setregister(1154);
                                break;
                            case 2:
                                setregister(1155);
                                break;
                            case 3:
                                setregister(920);
                                break;
                            case 4:
                                setregister(932);
                                break;
                            case 5:
                                setregister(933);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 2) {
                    switch (t) {
                        case -5:
                            setregister(1038);
                            break;
                        case -4:
                            setregister(1039);
                            break;
                        case -3:
                            setregister(1040);
                            break;
                        case -2:
                            setregister(1041);
                            break;
                        case -1:
                            setregister(1042);
                            break;
                        case 0:
                            setregister(1043);
                            break;
                        case 1:
                            setregister(1044);
                            break;
                        case 2:
                            setregister(1045);
                            break;
                        case 3:
                            setregister(1046);
                            break;
                        case 4:
                            setregister(1047);
                            break;
                        case 5:
                            setregister(1048);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(934);
                                break;
                            case -4:
                                setregister(935);
                                break;
                            case -3:
                                setregister(936);
                                break;
                            case -2:
                                setregister(937);
                                break;
                            case -1:
                                setregister(938);
                                break;
                            case 0:
                                setregister(939);
                                break;
                            case 1:
                                setregister(940);
                                break;
                            case 2:
                                setregister(941);
                                break;
                            case 3:
                                setregister(942);
                                break;
                            case 4:
                                setregister(943);
                                break;
                            case 5:
                                setregister(944);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 3) {
                    switch (t) {
                        case -5:
                            setregister(1049);
                            break;
                        case -4:
                            setregister(1050);
                            break;
                        case -3:
                            setregister(1051);
                            break;
                        case -2:
                            setregister(1052);
                            break;
                        case -1:
                            setregister(1053);
                            break;
                        case 0:
                            setregister(1054);
                            break;
                        case 1:
                            setregister(1055);
                            break;
                        case 2:
                            setregister(1056);
                            break;
                        case 3:
                            setregister(1057);
                            break;
                        case 4:
                            setregister(1058);
                            break;
                        case 5:
                            setregister(1059);
                            break;
                    }
                }
                if (x == 4) {
                    switch (t) {
                        case -5:
                            setregister(1060);
                            break;
                        case -4:
                            setregister(1061);
                            break;
                        case -3:
                            setregister(1062);
                            break;
                        case -2:
                            setregister(1063);
                            break;
                        case -1:
                            setregister(1064);
                            break;
                        case 0:
                            setregister(1065);
                            break;
                        case 1:
                            setregister(1066);
                            break;
                        case 2:
                            setregister(1067);
                            break;
                        case 3:
                            setregister(1068);
                            break;
                        case 4:
                            setregister(1069);
                            break;
                        case 5:
                            setregister(1070);
                            break;
                    }
                }
                if (x == 5)
                {
                    switch (t)
                    {
                        case -5:
                            setregister(1071);
                            break;
                        case -4:
                            setregister(1072);
                            break;
                        case -3:
                            setregister(1073);
                            break;
                        case -2:
                            setregister(1074);
                            break;
                        case -1:
                            setregister(1075);
                            break;
                        case 0:
                            setregister(1076);
                            break;
                        case 1:
                            setregister(1077);
                            break;
                        case 2:
                            setregister(1078);
                            break;
                        case 3:
                            setregister(1079);
                            break;
                        case 4:
                            setregister(1080);
                            break;
                        case 5:
                            setregister(1081);
                            break;
                    }
                }
            }
        });
        s3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor12 = preferences12.edit();
                editor12.putInt(KEY_SEEKBAR_VALUEP, progress);
                editor12.apply();
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
                    setregister(947);
                if (x==-4)
                    setregister(948);
                if (x==-3)
                    setregister(949);
                if (x==-2)
                    setregister(950);
                if (x==-1)
                    setregister(951);
                if (x==0)
                    setregister(952);
                if (x==1)
                    setregister(953);
                if (x==2)
                    setregister(954);
                if (x==3)
                    setregister(955);
                if (x==4)
                    setregister(956);
                if (x==5)
                    setregister(957);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(0);
                s2.setProgress(3);
                s3.setProgress(0);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(926);
                }, 0); // بلافاصله
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(1054);
                }, 300); // بلافاصله
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(952);
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
                    setregister(926);
                }, 0); // بلافاصله
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(1054);
                }, 300); // بعد از 300 میلی‌ثانیه
                //setregister(154);
                //setregister(55);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(955);
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
                    setregister(926);
                }, 0); // بلافاصله
                //setregister(26);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(1021);
                }, 300); // بلافاصله
                //setregister(121);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(1140);
                }, 600); // بلافاصله
                //setregister(240);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(950);
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
                    setregister(924);
                }, 0); // بلافاصله
                //setregister(24);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(1019);
                }, 300); // بلافاصله
                //setregister(119);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(1142);
                }, 600); // بلافاصله
                //setregister(242);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(952);
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
                    setregister(926);
                }, 0); // بلافاصله
                //setregister(26);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(1021);
                }, 300); // بلافاصله
                //setregister(121);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(1142);
                }, 600); // بلافاصله
                //setregister(242);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(952);
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