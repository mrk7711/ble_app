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

public class SoundEnhancer_Mode3 extends AppCompat {
    private Button b1,b2,b3,b4,b5,b6,b7,b8;
    private SeekBar s1,s2,s3;
    private static final UUID SERVICE_UUID = UUID.fromString("18424398-7cbc-11e9-8f9e-2a86e4085a59");
    private static final UUID I2C_CHARACTERISTIC_UUID = UUID.fromString("5b87b4ef-3bfa-76a8-e642-92933c31434c");
    private static final UUID LED_CHARACTERISTIC_UUID = UUID.fromString("5a87b4ef-3bfa-76a8-e642-92933c31434f");
    private BluetoothGatt bluetoothGatt;
    private BluetoothGattCharacteristic ledCharacteristic;
    private BluetoothGattCharacteristic i2cCharacteristic;
    private static final String PREFS_NAMEK = "SeekBarPrefs11";
    private static final String KEY_SEEKBAR_VALUEK = "seekBarValue11";
    private static final String PREFS_NAMEL = "SeekBarPrefs12";
    private static final String KEY_SEEKBAR_VALUEL = "seekBarValue12";
    private static final String PREFS_NAMEM = "SeekBarPrefs13";
    private static final String KEY_SEEKBAR_VALUEM = "seekBarValue13";
    int x=0;
    private EqualizerBackgroundView backgroundView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_enhancer_mode3);
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
        SharedPreferences preferences7 = getSharedPreferences(PREFS_NAMEK, MODE_PRIVATE);
        int y7 = preferences7.getInt(KEY_SEEKBAR_VALUEK, 0);
        s1.setProgress(y7);
        SharedPreferences preferences8 = getSharedPreferences(PREFS_NAMEL, MODE_PRIVATE);
        int y8 = preferences8.getInt(KEY_SEEKBAR_VALUEL, 0);
        s2.setProgress(y8);
        SharedPreferences preferences9 = getSharedPreferences(PREFS_NAMEM, MODE_PRIVATE);
        int y9 = preferences9.getInt(KEY_SEEKBAR_VALUEM, 0);
        s3.setProgress(y9);
        updateBackground(s1.getProgress(), s2.getProgress(), s3.getProgress());
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor7 = preferences7.edit();
                editor7.putInt(KEY_SEEKBAR_VALUEK, progress);
                editor7.apply();
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
                    setregister(621);
                if (x==-4)
                    setregister(622);
                if (x==-3)
                    setregister(623);
                if (x==-2)
                    setregister(624);
                if (x==-1)
                    setregister(625);
                if (x==0)
                    setregister(626);
                if (x==1)
                    setregister(627);
                if (x==2)
                    setregister(628);
                if (x==3)
                    setregister(629);
                if (x==4)
                    setregister(630);
                if (x==5)
                    setregister(631);
            }
        });
        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor8 = preferences8.edit();
                editor8.putInt(KEY_SEEKBAR_VALUEL, progress);
                editor8.apply();
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
                            setregister(661);
                            break;
                        case -4:
                            setregister(662);
                            break;
                        case -3:
                            setregister(663);
                            break;
                        case -2:
                            setregister(664);
                            break;
                        case -1:
                            setregister(665);
                            break;
                        case 0:
                            setregister(666);
                            break;
                        case 1:
                            setregister(667);
                            break;
                        case 2:
                            setregister(668);
                            break;
                        case 3:
                            setregister(669);
                            break;
                        case 4:
                            setregister(670);
                            break;
                        case 5:
                            setregister(671);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(782);
                                break;
                            case -4:
                                setregister(783);
                                break;
                            case -3:
                                setregister(784);
                                break;
                            case -2:
                                setregister(785);
                                break;
                            case -1:
                                setregister(786);
                                break;
                            case 0:
                                setregister(787);
                                break;
                            case 1:
                                setregister(788);
                                break;
                            case 2:
                                setregister(789);
                                break;
                            case 3:
                                setregister(790);
                                break;
                            case 4:
                                setregister(791);
                                break;
                            case 5:
                                setregister(792);
                                break;
                        }
                    }, 300); // تاخیر 500 میلی‌ثانیه

                }
                if (x == -4) {
                    switch (t) {
                        case -5:
                            setregister(672);
                            break;
                        case -4:
                            setregister(673);
                            break;
                        case -3:
                            setregister(674);
                            break;
                        case -2:
                            setregister(675);
                            break;
                        case -1:
                            setregister(676);
                            break;
                        case 0:
                            setregister(677);
                            break;
                        case 1:
                            setregister(678);
                            break;
                        case 2:
                            setregister(679);
                            break;
                        case 3:
                            setregister(680);
                            break;
                        case 4:
                            setregister(681);
                            break;
                        case 5:
                            setregister(382);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(793);
                                break;
                            case -4:
                                setregister(794);
                                break;
                            case -3:
                                setregister(795);
                                break;
                            case -2:
                                setregister(796);
                                break;
                            case -1:
                                setregister(797);
                                break;
                            case 0:
                                setregister(798);
                                break;
                            case 1:
                                setregister(799);
                                break;
                            case 2:
                                setregister(800);
                                break;
                            case 3:
                                setregister(801);
                                break;
                            case 4:
                                setregister(802);
                                break;
                            case 5:
                                setregister(803);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == -3) {
                    switch (t) {
                        case -5:
                            setregister(683);
                            break;
                        case -4:
                            setregister(684);
                            break;
                        case -3:
                            setregister(685);
                            break;
                        case -2:
                            setregister(686);
                            break;
                        case -1:
                            setregister(687);
                            break;
                        case 0:
                            setregister(688);
                            break;
                        case 1:
                            setregister(689);
                            break;
                        case 2:
                            setregister(690);
                            break;
                        case 3:
                            setregister(691);
                            break;
                        case 4:
                            setregister(692);
                            break;
                        case 5:
                            setregister(693);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(804);
                                break;
                            case -4:
                                setregister(805);
                                break;
                            case -3:
                                setregister(806);
                                break;
                            case -2:
                                setregister(807);
                                break;
                            case -1:
                                setregister(808);
                                break;
                            case 0:
                                setregister(809);
                                break;
                            case 1:
                                setregister(810);
                                break;
                            case 2:
                                setregister(811);
                                break;
                            case 3:
                                setregister(812);
                                break;
                            case 4:
                                setregister(813);
                                break;
                            case 5:
                                setregister(814);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == -2) {
                    switch (t) {
                        case -5:
                            setregister(694);
                            break;
                        case -4:
                            setregister(695);
                            break;
                        case -3:
                            setregister(696);
                            break;
                        case -2:
                            setregister(697);
                            break;
                        case -1:
                            setregister(698);
                            break;
                        case 0:
                            setregister(699);
                            break;
                        case 1:
                            setregister(700);
                            break;
                        case 2:
                            setregister(701);
                            break;
                        case 3:
                            setregister(702);
                            break;
                        case 4:
                            setregister(703);
                            break;
                        case 5:
                            setregister(704);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(815);
                                break;
                            case -4:
                                setregister(816);
                                break;
                            case -3:
                                setregister(817);
                                break;
                            case -2:
                                setregister(818);
                                break;
                            case -1:
                                setregister(819);
                                break;
                            case 0:
                                setregister(820);
                                break;
                            case 1:
                                setregister(821);
                                break;
                            case 2:
                                setregister(822);
                                break;
                            case 3:
                                setregister(823);
                                break;
                            case 4:
                                setregister(824);
                                break;
                            case 5:
                                setregister(825);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه

                }
                if (x == -1) {
                    switch (t) {
                        case -5:
                            setregister(705);
                            break;
                        case -4:
                            setregister(706);
                            break;
                        case -3:
                            setregister(707);
                            break;
                        case -2:
                            setregister(708);
                            break;
                        case -1:
                            setregister(709);
                            break;
                        case 0:
                            setregister(710);
                            break;
                        case 1:
                            setregister(711);
                            break;
                        case 2:
                            setregister(712);
                            break;
                        case 3:
                            setregister(713);
                            break;
                        case 4:
                            setregister(714);
                            break;
                        case 5:
                            setregister(715);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(826);
                                break;
                            case -4:
                                setregister(827);
                                break;
                            case -3:
                                setregister(828);
                                break;
                            case -2:
                                setregister(829);
                                break;
                            case -1:
                                setregister(830);
                                break;
                            case 0:
                                setregister(831);
                                break;
                            case 1:
                                setregister(832);
                                break;
                            case 2:
                                setregister(833);
                                break;
                            case 3:
                                setregister(834);
                                break;
                            case 4:
                                setregister(835);
                                break;
                            case 5:
                                setregister(836);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 0) {
                    switch (t) {
                        case -5:
                            setregister(716);
                            break;
                        case -4:
                            setregister(717);
                            break;
                        case -3:
                            setregister(718);
                            break;
                        case -2:
                            setregister(719);
                            break;
                        case -1:
                            setregister(720);
                            break;
                        case 0:
                            setregister(721);
                            break;
                        case 1:
                            setregister(722);
                            break;
                        case 2:
                            setregister(723);
                            break;
                        case 3:
                            setregister(724);
                            break;
                        case 4:
                            setregister(725);
                            break;
                        case 5:
                            setregister(726);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(837);
                                break;
                            case -4:
                                setregister(838);
                                break;
                            case -3:
                                setregister(839);
                                break;
                            case -2:
                                setregister(840);
                                break;
                            case -1:
                                setregister(841);
                                break;
                            case 0:
                                setregister(842);
                                break;
                            case 1:
                                setregister(843);
                                break;
                            case 2:
                                setregister(844);
                                break;
                            case 3:
                                setregister(845);
                                break;
                            case 4:
                                setregister(846);
                                break;
                            case 5:
                                setregister(847);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 1) {
                    switch (t) {
                        case -5:
                            setregister(727);
                            break;
                        case -4:
                            setregister(728);
                            break;
                        case -3:
                            setregister(729);
                            break;
                        case -2:
                            setregister(730);
                            break;
                        case -1:
                            setregister(731);
                            break;
                        case 0:
                            setregister(732);
                            break;
                        case 1:
                            setregister(733);
                            break;
                        case 2:
                            setregister(734);
                            break;
                        case 3:
                            setregister(735);
                            break;
                        case 4:
                            setregister(736);
                            break;
                        case 5:
                            setregister(737);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(848);
                                break;
                            case -4:
                                setregister(849);
                                break;
                            case -3:
                                setregister(850);
                                break;
                            case -2:
                                setregister(851);
                                break;
                            case -1:
                                setregister(852);
                                break;
                            case 0:
                                setregister(853);
                                break;
                            case 1:
                                setregister(854);
                                break;
                            case 2:
                                setregister(855);
                                break;
                            case 3:
                                setregister(620);
                                break;
                            case 4:
                                setregister(632);
                                break;
                            case 5:
                                setregister(633);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 2) {
                    switch (t) {
                        case -5:
                            setregister(738);
                            break;
                        case -4:
                            setregister(739);
                            break;
                        case -3:
                            setregister(740);
                            break;
                        case -2:
                            setregister(741);
                            break;
                        case -1:
                            setregister(742);
                            break;
                        case 0:
                            setregister(743);
                            break;
                        case 1:
                            setregister(744);
                            break;
                        case 2:
                            setregister(745);
                            break;
                        case 3:
                            setregister(746);
                            break;
                        case 4:
                            setregister(747);
                            break;
                        case 5:
                            setregister(748);
                            break;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        switch (z) {
                            case -5:
                                setregister(634);
                                break;
                            case -4:
                                setregister(635);
                                break;
                            case -3:
                                setregister(636);
                                break;
                            case -2:
                                setregister(637);
                                break;
                            case -1:
                                setregister(638);
                                break;
                            case 0:
                                setregister(639);
                                break;
                            case 1:
                                setregister(640);
                                break;
                            case 2:
                                setregister(641);
                                break;
                            case 3:
                                setregister(642);
                                break;
                            case 4:
                                setregister(643);
                                break;
                            case 5:
                                setregister(644);
                                break;
                        }
                    }, 300); // تاخیر 300 میلی‌ثانیه
                }
                if (x == 3) {
                    switch (t) {
                        case -5:
                            setregister(749);
                            break;
                        case -4:
                            setregister(750);
                            break;
                        case -3:
                            setregister(751);
                            break;
                        case -2:
                            setregister(752);
                            break;
                        case -1:
                            setregister(753);
                            break;
                        case 0:
                            setregister(754);
                            break;
                        case 1:
                            setregister(755);
                            break;
                        case 2:
                            setregister(756);
                            break;
                        case 3:
                            setregister(757);
                            break;
                        case 4:
                            setregister(758);
                            break;
                        case 5:
                            setregister(759);
                            break;
                    }
                }
                if (x == 4) {
                    switch (t) {
                        case -5:
                            setregister(760);
                            break;
                        case -4:
                            setregister(761);
                            break;
                        case -3:
                            setregister(762);
                            break;
                        case -2:
                            setregister(763);
                            break;
                        case -1:
                            setregister(764);
                            break;
                        case 0:
                            setregister(765);
                            break;
                        case 1:
                            setregister(766);
                            break;
                        case 2:
                            setregister(767);
                            break;
                        case 3:
                            setregister(768);
                            break;
                        case 4:
                            setregister(769);
                            break;
                        case 5:
                            setregister(770);
                            break;
                    }
                }
                if (x == 5)
                {
                    switch (t)
                    {
                        case -5:
                            setregister(771);
                            break;
                        case -4:
                            setregister(772);
                            break;
                        case -3:
                            setregister(773);
                            break;
                        case -2:
                            setregister(774);
                            break;
                        case -1:
                            setregister(775);
                            break;
                        case 0:
                            setregister(776);
                            break;
                        case 1:
                            setregister(777);
                            break;
                        case 2:
                            setregister(778);
                            break;
                        case 3:
                            setregister(779);
                            break;
                        case 4:
                            setregister(780);
                            break;
                        case 5:
                            setregister(781);
                            break;
                    }
                }
            }
        });
        s3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor9 = preferences9.edit();
                editor9.putInt(KEY_SEEKBAR_VALUEM, progress);
                editor9.apply();
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
                    setregister(647);
                if (x==-4)
                    setregister(648);
                if (x==-3)
                    setregister(649);
                if (x==-2)
                    setregister(650);
                if (x==-1)
                    setregister(651);
                if (x==0)
                    setregister(652);
                if (x==1)
                    setregister(653);
                if (x==2)
                    setregister(654);
                if (x==3)
                    setregister(655);
                if (x==4)
                    setregister(656);
                if (x==5)
                    setregister(657);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(0);
                s2.setProgress(3);
                s3.setProgress(0);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(626);
                }, 0); // بلافاصله
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(754);
                }, 300); // بلافاصله
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(652);
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
                    setregister(626);
                }, 0); // بلافاصله
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(754);
                }, 300); // بعد از 300 میلی‌ثانیه
                //setregister(154);
                //setregister(55);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(655);
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
                    setregister(626);
                }, 0); // بلافاصله
                //setregister(26);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(721);
                }, 300); // بلافاصله
                //setregister(121);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(840);
                }, 600); // بلافاصله
                //setregister(240);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(650);
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
                    setregister(624);
                }, 0); // بلافاصله
                //setregister(24);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(719);
                }, 300); // بلافاصله
                //setregister(119);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(842);
                }, 600); // بلافاصله
                //setregister(242);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(652);
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
                    setregister(626);
                }, 0); // بلافاصله
                //setregister(26);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(721);
                }, 300); // بلافاصله
                //setregister(121);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(842);
                }, 600); // بلافاصله
                //setregister(242);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setregister(652);
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